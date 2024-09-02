package psk.example.feasthub;



public class CartItem{
    private String img;
    private String name;
    private String price;
    private int quantaty =1;
    private int OrderID;
    private String total;

    public CartItem(String name, String price, String img) {
        this.name = name;
        this.price = price;
        this.img = img;

    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public int getQuantaty() {
        return quantaty;
    }

    public void setQuantaty(int quantaty) {
        this.quantaty = quantaty;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

}
