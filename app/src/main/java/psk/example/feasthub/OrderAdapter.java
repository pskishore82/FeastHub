package psk.example.feasthub;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feasthub.R;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context context;
    private List<OrderModel> orderModels;

    public OrderAdapter(Context context, List<OrderModel> orderModels) {
        this.context = context;
        this.orderModels = orderModels;
    }

    public OrderAdapter(List<OrderModel> orderModels) {
        this.orderModels = orderModels;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.myorder_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);
        String profileImageUriString = sharedPreferences.getString("user_photo_uri", null);

        OrderModel orderModel = orderModels.get(position);
        holder.orderid.setText("Order id "+String.valueOf(orderModel.getOrderId()));
        holder.total.setText("Total "+orderModel.getPrice());
        Picasso.get().load(Uri.parse(profileImageUriString)).into(holder.imageView);
        LocalDate localDate = LocalDate.now();
        String formattedDate = localDate.toString();

        holder.date.setText("Date "+formattedDate);

    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView orderid,total,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.RVImage);
            orderid = itemView.findViewById(R.id.RVOrderId);
            total = itemView.findViewById(R.id.RVTotalPrice);
            date = itemView.findViewById(R.id.Date);

        }
    }
}
