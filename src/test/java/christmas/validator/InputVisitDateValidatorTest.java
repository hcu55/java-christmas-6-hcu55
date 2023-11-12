package christmas.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InputVisitDateValidatorTest {

    InputVisitDateValidator inputVisitDateValidator = new InputVisitDateValidator();

    @Test
    @DisplayName("예상 방문 날짜 입력 안함 테스트")
    void 예상_방문_날짜_입력_안함() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> inputVisitDateValidator.validateNonInputVisitDate(""))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예상 방문 날짜 1일 ~ 31일 사이 아닌 경우 테스트")
    void 예상_방문_날짜_범위_아님() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> inputVisitDateValidator.validateWrongRangeVisitDate("0"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예상 방문 날짜 1일 ~ 31일 사이 아닌 경우 테스트2")
    void 예상_방문_날짜_범위_아님2() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> inputVisitDateValidator.validateWrongRangeVisitDate("32"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예상 방문 날짜 숫자 아닌 경우 테스트")
    void 예상_방문_날짜_숫자_아님() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> inputVisitDateValidator.validateNonIntegerVisitDate("3일"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "0", "32", "1일", "31일", "이일"})
    @DisplayName("예상 방문 날짜 예외 처리 종합 테스트")
    void 예상_방문_날짜_예외_처리_종합(String visitDate) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> inputVisitDateValidator.validateInputExpectedVisitDate(visitDate))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
}
