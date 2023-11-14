package christmas.view;

public class OutputView {
    private static final String PRINT_CHRISTMAS_PROMOTION_START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PRINT_EXPECTED_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String PRINT_ORDER_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String PRINT_PREVIEW_EVENT_BENEFIT_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

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
}
