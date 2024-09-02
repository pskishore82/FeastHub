package psk.example.feasthub;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feasthub.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<OrderModel> orderList;

    private static final String PREFS_NAME = "MyPrefsFile";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_order, container, false);

        recyclerView = view.findViewById(R.id.OrderRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        orderList = new ArrayList<>();
        orderAdapter = new OrderAdapter(getContext(), orderList);
        recyclerView.setAdapter(orderAdapter);

        readData();

        return view;
    }

    private void readData() {
        List<OrderModel> cachedData = readDataFromLocalCache();
        if (cachedData != null && !cachedData.isEmpty()) {
            orderList.addAll(cachedData);
            orderAdapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this.getContext(),"empty file",Toast.LENGTH_SHORT).show();
        }
    }

    private List<OrderModel> readDataFromLocalCache() {
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String productListJson = prefs.getString("productList", null);
        if (productListJson != null) {
            try {
                Type type = new TypeToken<List<OrderModel>>() {}.getType();
                List<OrderModel> retrievedData = new Gson().fromJson(productListJson, type);
                Log.d("OrderActivity", "Retrieved data: " + retrievedData.toString());
                return retrievedData;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(requireContext(), "Error reading data from SharedPreferences", Toast.LENGTH_SHORT).show();
                Log.d("Error reading data",e.toString());
            }
        }
        return new ArrayList<>();
    }

}
