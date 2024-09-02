package psk.example.feasthub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feasthub.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddCartActivity extends AppCompatActivity implements PaymentResultListener {

    private RecyclerView cartRV;
    public TextView subtotal,discount,total;
    private EditText couponCode;
    private Button applayBtn;
    private ImageView backicon;
    private List<CartItem> cartItem;
    private OrderModel orderModelList;

    List<OrderModel> orderHistoryList;
    private Handler handler = new Handler();

    private String amt;
    private static final String PREFS_NAME = "MyPrefsFile";

    private Runnable updateTotalAmount = new Runnable() {
        @Override
        public void run() {
            calculateTotalAmount();
            handler.postDelayed(this,500);
        }
    };

    String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    int GOOGLE_PAY_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);

        subtotal = findViewById(R.id.Subtl);

        discount = findViewById(R.id.discount);
        total = findViewById(R.id.Total);
        couponCode = findViewById(R.id.couponCode);
        applayBtn = findViewById(R.id.paymentbtn);
        backicon = findViewById(R.id.back);

        orderHistoryList = new ArrayList<>();

        int orderNumber = generateRandomOrderNumber();

        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cartRV = findViewById(R.id.OrderRV);

        handler.post(updateTotalAmount);

        CartAdapter cartAdapter = new CartAdapter(CartData.getCartItems());

        cartRV.setAdapter(cartAdapter);

        Checkout.preload(getApplicationContext());

        Checkout checkout = new Checkout();

        checkout.setKeyID("rzp_test_p9qVwLOKe1l6zG");



        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle the item removal here by calling the removeItem method
                cartAdapter.removeItem(position);
                // Optionally, update your cartItems list if needed
                // cartItems.remove(position);
                // Notify the adapter of the change
                // cartAdapter.notifyDataSetChanged();
                // Show a toast or perform any other action
                calculateTotalAmount();
                Toast.makeText(AddCartActivity.this, "Item removed", Toast.LENGTH_SHORT).show();
            }
        });

        applayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*

                Uri uri =
                        new Uri.Builder()
                                .scheme("upi")
                                .authority("pay")
                                .appendQueryParameter("pa", "pskishore793@okaxis")
                                .appendQueryParameter("pn", "Kishore P.S")
                                //.appendQueryParameter("mc", "")
                                //.appendQueryParameter("tr", "12345")
                                //.appendQueryParameter("tn", "your-transaction-note")
                                .appendQueryParameter("am", amt)
                                .appendQueryParameter("cu", "INR")
                                .appendQueryParameter("aid", "uGICAgMCAn826cQ")
                                // .appendQueryParameter("url", "your-transaction-url")
                                .build();
               // String uri1 = "upi://pay?pa=pskishore793@okaxis&pn=Kishore P.S&am=1.00&cu=INR&aid=uGICAgMCAn826cQ";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);

                Intent chooser = Intent.createChooser(intent,"Pay with");
                startActivityForResult(chooser, GOOGLE_PAY_REQUEST_CODE);

                 */



                JSONObject object = new JSONObject();
                try {

                    // to put name
                    object.put("name", "Feasthub");

                    // put description
                    object.put("description", "Test payment");

                    // to set theme color
                    object.put("theme.color", "#FF9B00");

                    // put the currency
                    object.put("currency", "INR");

                    // put amount
                    object.put("amount", amt);

                    // put mobile number
                    object.put("prefill.contact", "9962783368");

                    // put email
                    object.put("prefill.email", "chaitanyamunje@gmail.com");

                    // open razorpay to checkout activity
                    checkout.open(AddCartActivity.this, object);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            });

    }

    @Override
    public void onPaymentSuccess(String s) {

        try {

            // this method is called on payment success.
            Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();

            // Get ordered items and total amount
            List<CartItem> orderedItems = CartData.getCartItems();
            float totalAmount = Float.parseFloat(total.getText().toString().replace("₹", "").trim());

            List<OrderModel> cachedData = readDataFromLocalCache();
            if (cachedData != null && !cachedData.isEmpty()) {
                // Display cached data
                orderHistoryList.addAll(cachedData);
                // productAdapter.notifyDataSetChanged();
            }

            int orderid = generateRandomOrderNumber();
            LocalDate currentDate = LocalDate.now();
            orderModelList = new OrderModel(currentDate.toString(), amt, orderid);
            orderHistoryList.add(orderModelList);
            saveDataToLocalCache(orderHistoryList);

            // Create intent to navigate to OrderActivity and pass order data
            Intent intent = new Intent(AddCartActivity.this, OrderActivity.class);
            intent.putExtra("orderedItems", (Serializable) orderedItems);
            intent.putExtra("totalAmount", totalAmount);
            startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        // on payment failed.
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }


    private int generateRandomOrderNumber() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("PaymentResult", "resultCode: " + resultCode);

        if (requestCode == GOOGLE_PAY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data.getData() != null) {
                // Payment was successful
                // You can perform further actions here, such as updating your database or displaying a success message.
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference productsRef = database.getReference("products");

                int orderid = generateRandomOrderNumber();

                productsRef.push().setValue(CartData.getCartItems());

                Toast.makeText(AddCartActivity.this, "Payment completed, Your orderId is :  " +orderid, Toast.LENGTH_SHORT).show();

            }
            else {
                // Payment was not successful
                // Handle the failure or display an error message.

                List<OrderModel> cachedData = readDataFromLocalCache();
                if (cachedData != null && !cachedData.isEmpty()) {
                    // Display cached data
                    orderHistoryList.addAll(cachedData);
                   // productAdapter.notifyDataSetChanged();
                }

                int orderid = generateRandomOrderNumber();
                LocalDate currentDate = LocalDate.now();
                orderModelList = new OrderModel(currentDate.toString(),amt,orderid);
                orderHistoryList.add(orderModelList);
                saveDataToLocalCache(orderHistoryList);
                //savePurchasedProductsToLocalJson(orderModelList);

                Toast.makeText(AddCartActivity.this, "Payment failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void savePurchasedProductsToLocalJson(OrderModel purchasedItems) {
        // Convert list of CartItems to JSON string using Gson
        // Save JSON string to local file
        // Create a new file named "purchased_products.json" in internal storage
        List<OrderModel> orderList = new ArrayList<>();
        orderList.add(purchasedItems);

        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        // Convert productList to a string and save it
        // (You may want to use a more efficient serialization method for larger datasets)

        String file = new Gson().toJson(purchasedItems);
        editor.putString("productList", new Gson().toJson(purchasedItems));
        editor.apply();
        Toast.makeText(this,file,Toast.LENGTH_SHORT).show();
        Log.d("SaveToJson", "Purchased products saved to local JSON file");
    }

    private void saveDataToLocalCache(List<OrderModel> productList){

        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        // Convert productList to a string and save it
        // (You may want to use a more efficient serialization method for larger datasets)
        editor.putString("productList", new Gson().toJson(productList));
        editor.apply();

    }

    private List<OrderModel> readDataFromLocalCache() {
        orderHistoryList.clear();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String productListJson = prefs.getString("productList", null);
        if (productListJson != null) {
            try {
                Type type = new TypeToken<List<OrderModel>>() {}.getType();
                List<OrderModel> retrievedData = new Gson().fromJson(productListJson, type);
                Log.d("OrderActivity", "Retrieved data: " + retrievedData.toString());
                return retrievedData;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error reading data from SharedPreferences", Toast.LENGTH_SHORT).show();
                Log.d("Error reading data",e.toString());
            }
        }
        return new ArrayList<>();
    }

    public void calculateTotalAmount() {
        float totalAmount = 0.0f;

        for (CartItem cartItem : CartData.getCartItems()) {
            float itemPrice = Float.parseFloat(cartItem.getPrice().replace("₹", "").trim());
            totalAmount += (itemPrice * cartItem.getQuantaty());
        }

        // Display the total amount in the appropriate TextView
        total.setText(String.format("₹%.2f", totalAmount));
        subtotal.setText(String.format("₹%.2f", totalAmount));
        amt = Float.toString(totalAmount);
    }

}