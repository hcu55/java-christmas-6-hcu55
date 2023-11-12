package christmas.view;

public class OutputView {
    private static final String PRINT_CHRISTMAS_PROMOTION_START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PRINT_EXPECTED_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public void printChristmasPromotionStartMessage() {
        System.out.println(PRINT_CHRISTMAS_PROMOTION_START_MESSAGE);
    }

    public void printExpectedVisitDateInputMessage() {
        System.out.println(PRINT_EXPECTED_VISIT_DATE_MESSAGE);
    }
}
