package christmas.domain;

import java.util.Map;

public class ChristmasEvent {
    private static final int CHRISTMAS_BASE_DISCOUNT = 1000;
    private static final int INCREASE_DISCOUNT = 100;
    private static final int CHRISTMAS_DAY = 25;

    private int calculateTotalOrderPrice(Map<Menu, Integer> order) {
        return order.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPriceForCount(entry.getValue()))
                .sum();
    }

    private int christmasDdayDiscount(int visitDate) {
        if (isInChristmasDdayPeriod(visitDate)) {
            return CHRISTMAS_BASE_DISCOUNT + INCREASE_DISCOUNT * (visitDate - 1);
        }
        return 0;
    }

    private boolean isInChristmasDdayPeriod(int visitDate) {
        return visitDate <= CHRISTMAS_DAY;
    }
}