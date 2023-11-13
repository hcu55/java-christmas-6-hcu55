package christmas.domain;

import java.util.Map;

public class ChristmasEvent {

    private int calculateTotalOrderPrice(Map<Menu, Integer> order) {
        return order.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPriceForCount(entry.getValue()))
                .sum();
    }
}