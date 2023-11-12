package christmas.validator;

public class InputVisitDateValidator {
    private static final String NON_INPUT_ERROR_MESSAGE = "[ERROR] 입력하지 않으셨습니다. 예상 방문 날짜를 입력해주세요";
    private static final String WRONG_RANGE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String NON_INTEGER_ERROR_MESSAGE = "[ERROR] 예상 방문 날짜는 숫자만 입력 가능합니다.";
    private static final String BLANK = "";
    private static final int MIN_VISIT_DATE = 1;
    private static final int MAX_VISIT_DATE = 31;

    public void validateInputExpectedVisitDate(String visitDate) {
        validateNonInputVisitDate(visitDate);
        validateWrongRangeVisitDate(visitDate);
        validateNonIntegerVisitDate(visitDate);
    }

    public void validateNonInputVisitDate(String visitDate) {
        if (visitDate.equals(BLANK)) {
            throw new IllegalArgumentException(NON_INPUT_ERROR_MESSAGE);
        }
    }

    public void validateWrongRangeVisitDate(String visitDate) {
        int date = Integer.parseInt(visitDate);

        if (date < MIN_VISIT_DATE || date > MAX_VISIT_DATE) {
            throw new IllegalArgumentException(WRONG_RANGE_ERROR_MESSAGE);
        }
    }

    public void validateNonIntegerVisitDate(String visitDate) {
        try {
            Integer.parseInt(visitDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NON_INTEGER_ERROR_MESSAGE);
        }
    }
}
