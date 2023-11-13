package christmas.domain;

import java.util.Map;

public class ChristmasEvent {
    private static final int CHRISTMAS_BASE_DISCOUNT = 1000;
    private static final int INCREASE_DISCOUNT = 100;
    private static final int CHRISTMAS_DAY = 25;
    private static final int WEEKDAY_WEEKEND_DISCOUNT = 2023;

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

    private int weekdayDiscount(int visitDate, Map<Menu, Integer> order) {
        if (isWeekday(visitDate)) {
            return order.entrySet().stream()
                    .filter(entry -> entry.getKey().isDessert())
                    .mapToInt(entry -> WEEKDAY_WEEKEND_DISCOUNT * entry.getValue())
                    .sum();
        }
        return 0;
    }

    private int weekendDiscount(int visitDate, Map<Menu, Integer> order) {
        if (isWeekend(visitDate)) {
            return order.entrySet().stream()
                    .filter(entry -> entry.getKey().isMain())
                    .mapToInt(entry -> WEEKDAY_WEEKEND_DISCOUNT * entry.getValue())
                    .sum();
        }
        return 0;
    }

    private boolean isInChristmasDdayPeriod(int visitDate) {
        return visitDate <= CHRISTMAS_DAY;
    }

    private boolean isWeekday(int visitDate) {
        return visitDate % 7 >= 3 || visitDate % 7 == 0;
    }

    private boolean isWeekend(int visitDate) {
        return visitDate % 7 == 1 || visitDate % 7 == 2;
    }
}