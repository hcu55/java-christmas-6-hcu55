package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ChristmasEvent {
    private static final int CHRISTMAS_BASE_DISCOUNT = 1000;
    private static final int INCREASE_DISCOUNT = 100;
    private static final int CHRISTMAS_DAY = 25;
    private static final int WEEKDAY_WEEKEND_DISCOUNT = 2023;
    private static final int STAR_DAY_DISCOUNT = 1000;
    private static final int MINIMUM_ORDER_FOR_CHAMPAGNE = 120_000;
    private static final int MINIMUM_ORDER_FOR_EVENT_DISCOUNT = 10_000;
    private static final String CHRISTMAS_DAY_DISCOUNT = "크리스마스 디데이 할인";
    private static final String WEEKDAY_DISCOUNT = "평일 할인";
    private static final String WEEKEND_DISCOUNT = "주말 할인";
    private static final String STARDAY_DISCOUNT = "특별 할인";
    private static final List<Integer> STAR_DAYS = List.of(3, 10, 17, 24, 25, 31);

    private Map<String, Integer> discountDetails = new HashMap<>();

    public int calculateTotalOrderPrice(Map<Menu, Integer> order) {
        return order.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPriceForCount(entry.getValue()))
                .sum();
    }

    public int totalDiscountIfCanEvent(int totalOrderPrice, int visitDate, Map<Menu, Integer> order) {
        if (totalOrderPrice >= MINIMUM_ORDER_FOR_EVENT_DISCOUNT) {
            return christmasDdayDiscount(visitDate) +
                    weekdayDiscount(visitDate, order) +
                    weekendDiscount(visitDate, order) +
                    starDayDiscount(visitDate);
        }
        return 0;
    }

    private int christmasDdayDiscount(int visitDate) {
        if (isInChristmasDdayPeriod(visitDate)) {
            int discountAmount = CHRISTMAS_BASE_DISCOUNT + INCREASE_DISCOUNT * (visitDate - 1);
            discountDetails.put(CHRISTMAS_DAY_DISCOUNT, discountAmount);
            return discountAmount;
        }
        return 0;
    }

    private int weekdayDiscount(int visitDate, Map<Menu, Integer> order) {
        if (isWeekday(visitDate)) {
            int discountAmount = order.entrySet().stream()
                    .filter(entry -> entry.getKey().isDessert())
                    .mapToInt(entry -> WEEKDAY_WEEKEND_DISCOUNT * entry.getValue())
                    .sum();
            if (discountAmount > 0) {
                discountDetails.put(WEEKDAY_DISCOUNT, discountAmount);
            }
            return discountAmount;
        }
        return 0;
    }

    private int weekendDiscount(int visitDate, Map<Menu, Integer> order) {
        if (isWeekend(visitDate)) {
            int discountAmount = order.entrySet().stream()
                    .filter(entry -> entry.getKey().isMain())
                    .mapToInt(entry -> WEEKDAY_WEEKEND_DISCOUNT * entry.getValue())
                    .sum();
            if (discountAmount > 0) {
                discountDetails.put(WEEKEND_DISCOUNT, discountAmount);
            }
            return discountAmount;
        }
        return 0;
    }

    private int starDayDiscount(int visitDate) {
        if (isStarDay(visitDate)) {
            discountDetails.put(STARDAY_DISCOUNT, STAR_DAY_DISCOUNT);
            return STAR_DAY_DISCOUNT;
        }
        return 0;
    }

    public void processDiscountDetails(BiConsumer<String, Integer> action) {
        for (Map.Entry<String, Integer> entry : discountDetails.entrySet()) {
            action.accept(entry.getKey(), entry.getValue());
        }
    }

    public int calculateTotalDiscount() {
        return discountDetails.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int deductDiscountFromTotalPrice(int totalOrderPrice, int discount) {
        return totalOrderPrice - discount;
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

    private boolean isStarDay(int visitDate) {
        return STAR_DAYS.contains(visitDate);
    }

    public boolean checkGiveawayChampagne(int totalOrderPrice) {
        return totalOrderPrice >= MINIMUM_ORDER_FOR_CHAMPAGNE;
    }
}