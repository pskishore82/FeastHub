package psk.example.feasthub;

public class DataBridge {
    private static DataBridge instance;
    private FoodRVModal selectedFoodItem;

    private DataBridge() {
        // Private constructor to prevent multiple instances
    }

    public static DataBridge getInstance() {
        if (instance == null) {
            instance = new DataBridge();
        }
        return instance;
    }

    public FoodRVModal getSelectedFoodItem() {
        return selectedFoodItem;
    }

    public void setSelectedFoodItem(FoodRVModal selectedFoodItem) {
        this.selectedFoodItem = selectedFoodItem;
    }
}
