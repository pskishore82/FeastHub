package psk.example.feasthub;

import java.util.ArrayList;
import java.util.List;

public class OrderData {
    private static List<OrderModel> orderModelList = new ArrayList<>();
    public static List<OrderModel> getItems() {
        return orderModelList;
    }

    public static void addToOrder(OrderModel item) {
        orderModelList.add(item);
    }
}
