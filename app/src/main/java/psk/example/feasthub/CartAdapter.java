package psk.example.feasthub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feasthub.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<CartItem> cartItems;
    private Context context;

    private AddCartActivity addCartActivity;

    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public CartAdapter(List<CartItem> cartItems, AddCartActivity addCartActivity) {
        this.cartItems = cartItems;
        this.addCartActivity = addCartActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cart_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        holder.itemNameTextView.setText(cartItem.getName());
        holder.itemPriceTextView.setText(cartItem.getPrice());
        Picasso.get().load(cartItem.getImg()).into(holder.itemImage);
        holder.itemquantity.setText(String.valueOf(cartItem.getQuantaty()));

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;
        TextView itemPriceTextView, itemquantity;
        ImageView itemImage,incre,decre;
        ImageView itemremove;

        TextView totalItemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.RVItemName);
            itemPriceTextView = itemView.findViewById(R.id.RVItemPrice);
            itemImage = itemView.findViewById(R.id.RVFoodimage);
            decre = itemView.findViewById(R.id.minus);
            incre = itemView.findViewById(R.id.increase);
            itemquantity = itemView.findViewById(R.id.num);

            totalItemPrice = itemView.findViewById(R.id.Total);

            itemremove = itemView.findViewById(R.id.cartbtn);

            itemremove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                        itemClickListener.onItemClick(position);
                    }
                }
            });

            incre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        CartItem item = cartItems.get(position);
                        int newQuantity = item.getQuantaty() + 1;
                        item.setQuantaty(newQuantity);
                        notifyItemChanged(position);
                    }
                }
            });

            decre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        CartItem item = cartItems.get(position);
                        int newQuantity = item.getQuantaty() - 1;
                        if (newQuantity >= 1) {
                            item.setQuantaty(newQuantity);
                            notifyItemChanged(position);
                        }
                    }
                }
            });

        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void removeItem(int position) {
        cartItems.remove(position);
        notifyItemRemoved(position);
    }


}
