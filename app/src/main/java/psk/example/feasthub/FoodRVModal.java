package psk.example.feasthub;

public class FoodRVModal {
    private String name;
    private String price;
    private String imageUrl;

    public FoodRVModal(String name, String price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public FoodRVModal(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }


    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
