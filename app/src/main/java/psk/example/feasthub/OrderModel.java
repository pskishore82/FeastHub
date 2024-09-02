package psk.example.feasthub;


public class OrderModel {
    private String date;
    private String price;
    private int orderId;  // Add orderId to associate items with orders

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderModel(String date, String price, int orderId) {
        this.date = date;
        this.price = price;
        this.orderId = orderId;
    }
}
