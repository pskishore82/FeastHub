package psk.example.feasthub;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feasthub.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FoodRVAdapter extends RecyclerView.Adapter<FoodRVAdapter.ViewHolder> {

    private Context context;
    private List<FoodRVModal> foodList;
    private List<CartItem> cartItems;

    public FoodRVAdapter(Context context, ArrayList<FoodRVModal> foodList) {
        this.context = context;
        this.foodList = foodList;
        cartItems = new ArrayList<>();
    }

    public void setFilteredList(List<FoodRVModal> filteredList){
        this.foodList = filteredList;
        notifyDataSetChanged();
    }

    public FoodRVAdapter(Context context, ArrayList<FoodRVModal> foodList,List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_rv_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodRVModal food = foodList.get(position);

        holder.foodName.setText(food.getName());
        holder.foodPrice.setText(food.getPrice());


        // Load the image using Picasso or your preferred image loading library
        Picasso.get().load(food.getImageUrl()).into(holder.foodImage);

        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item click
                // Here, you can add the selected item to the cartItems list
                FoodRVModal selectedItem = foodList.get(position);
                CartItem cartItem = new CartItem(selectedItem.getName(), selectedItem.getPrice(), selectedItem.getImageUrl());
                //cartItems.add(new CartItem(selectedItem.getTime(), selectedItem.getCondition()));
                CartData.addToCart(cartItem);

                notifyDataSetChanged();

                // Show a toast or perform any other action to indicate that the item was added to the cart
                Toast.makeText(context, selectedItem.getName() + " added to cart", Toast.LENGTH_SHORT).show();


            }
        });

         */

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView foodImage;
        private TextView foodName;
        private TextView foodDescription;
        private TextView foodPrice;
        private ImageButton btn;

        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.food_image);
            foodName = itemView.findViewById(R.id.food_name);
            foodPrice = itemView.findViewById(R.id.food_price);
            btn = itemView.findViewById(R.id.Cartbtn);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();

                    FoodRVModal selectedItem = foodList.get(position);
                    CartItem cartItem = new CartItem(selectedItem.getName(), selectedItem.getPrice(), selectedItem.getImageUrl());
                    //cartItems.add(new CartItem(selectedItem.getTime(), selectedItem.getCondition()));
                    CartData.addToCart(cartItem);

                    notifyDataSetChanged();

                    // Show a toast or perform any other action to indicate that the item was added to the cart
                    Toast.makeText(context, selectedItem.getName() + " added to cart", Toast.LENGTH_SHORT).show();

                }
            });
        }


    }



    public void filter(String query){
        ArrayList<FoodRVModal> filteredList = new ArrayList<>();
        ArrayList<FoodRVModal> orginalList = new ArrayList<>();
        orginalList.addAll(foodList);
        if (TextUtils.isEmpty(query)){
            filteredList.addAll(foodList);
        }
        else {
            query = query.toLowerCase(Locale.getDefault());
            for (FoodRVModal food : foodList){
                if (food.getName().toLowerCase(Locale.getDefault()).contains(query)){
                    filteredList.add(food);
                }
            }
        }
        foodList.clear();
        foodList.addAll(filteredList);
        notifyDataSetChanged();
    }

}
