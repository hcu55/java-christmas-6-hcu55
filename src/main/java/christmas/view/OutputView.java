package christmas.view;

import christmas.domain.ChristmasEvent;
import christmas.domain.EventBadge;
import christmas.domain.Menu;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String PRINT_CHRISTMAS_PROMOTION_START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PRINT_EXPECTED_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String PRINT_ORDER_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String PRINT_PREVIEW_EVENT_BENEFIT_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String PRINT_ORDER_MENU_AND_COUNT_MESSAGE = "\n<주문 메뉴>";
    private static final String PRINT_TOTAL_ORDER_PRICE_MESSAGE = "\n<할인 전 총주문 금액>\n%s원\n";
    private static final String PRINT_CHAMPAGNE_GIVEAWAY_MESSAGE = "\n<증정 메뉴>\n%s\n";
    private static final String PRINT_DISCOUNT_DETAILS_MESSAGE = "\n<혜택 내역>\n%s\n";
    private static final String PRINT_TOTAL_BENEFIT_MESSAGE = "\n<총혜택 금액>\n%s원\n";
    private static final String PRINT_AFTER_DISCOUNT_PAYMENT_MESSAGE = "\n<할인 후 예상 결제 금액>\n%s원\n";
    private static final String PRINT_EVENT_BADGE_MESSAGE = "\n<12월 이벤트 배지>\n%s";
    private DecimalFormat formatter = new DecimalFormat("#,###");

    public void printChristmasPromotionStartMessage() {
        System.out.println(PRINT_CHRISTMAS_PROMOTION_START_MESSAGE);
    }

    public void printExpectedVisitDateInputMessage() {
        System.out.println(PRINT_EXPECTED_VISIT_DATE_MESSAGE);
    }

    public void printOrderMenuInputMessage() {
        System.out.println(PRINT_ORDER_MENU_MESSAGE);
    }

    public void printPreviewEventBenefit(int visitDate) {
        System.out.printf(PRINT_PREVIEW_EVENT_BENEFIT_MESSAGE, visitDate);
    }

    public void printOrderMenuAndCount(Map<Menu, Integer> order) {
        System.out.println(PRINT_ORDER_MENU_AND_COUNT_MESSAGE);
        for (Map.Entry<Menu, Integer> entry : order.entrySet()) {
            System.out.printf("%s %d개\n", entry.getKey(), entry.getValue());
        }
    }

    public void printTotalOrderPrice(int totalOrderPrice) {
        System.out.printf(PRINT_TOTAL_ORDER_PRICE_MESSAGE, formatter.format(totalOrderPrice));
    }

    public void printChampagneGiveaway(boolean isChampagneGiveaway) {
        if (isChampagneGiveaway) {
            System.out.printf(PRINT_CHAMPAGNE_GIVEAWAY_MESSAGE, "샴페인 1개");
            return;
        }
        System.out.printf(PRINT_CHAMPAGNE_GIVEAWAY_MESSAGE, "없음");
    }

    public void printDiscountDetails(ChristmasEvent event, boolean isChampagneGiveaway) {
        StringBuilder sb = new StringBuilder();
        event.processDiscountDetails((key, value) -> {
            sb.append(key).append(": -").append(formatter.format(value)).append("원\n");
        });
        if (isChampagneGiveaway) {
            sb.append("증정 이벤트: -25,000원");
        }
        if (sb.length() == 0) {
            System.out.printf(PRINT_DISCOUNT_DETAILS_MESSAGE, "없음");
            return;
        }
        System.out.printf(PRINT_DISCOUNT_DETAILS_MESSAGE, sb.toString());
    }

    public void printTotalBenefit(ChristmasEvent event, Map<Menu, Integer> order) {
        EventBadge eventBadge = new EventBadge();

        int totalDiscount = event.calculateTotalDiscount();
        boolean isChampagneGet = event.checkGiveawayChampagne(event.calculateTotalOrderPrice(order));
        int totalBenefit = eventBadge.totalBenefitAmount(totalDiscount, isChampagneGet);

        String formattedTotalBenefit = formatter.format(totalBenefit);
        if (totalBenefit != 0) {
            formattedTotalBenefit = "-" + formattedTotalBenefit;
        }
        System.out.printf(PRINT_TOTAL_BENEFIT_MESSAGE, formattedTotalBenefit);
    }

    public void printPaymentAfterDiscount(ChristmasEvent event, Map<Menu, Integer> order) {
        int totalOrderPrice = event.calculateTotalOrderPrice(order);
        int totalDiscount = event.calculateTotalDiscount();
        int afterDiscountPayment = event.deductDiscountFromTotalPrice(totalOrderPrice, totalDiscount);

        String formattedAfterDiscountPayment = formatter.format(afterDiscountPayment);
        System.out.printf(PRINT_AFTER_DISCOUNT_PAYMENT_MESSAGE, formattedAfterDiscountPayment);
    }

    public void printEventBadge(EventBadge eventBadge, int totalBenefit) {
        String badge = eventBadge.GrantingEventBadge(totalBenefit);

        System.out.printf(PRINT_EVENT_BADGE_MESSAGE, badge);
    }
}
