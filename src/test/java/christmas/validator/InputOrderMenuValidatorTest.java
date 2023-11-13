package christmas.validator;

import christmas.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InputOrderMenuValidatorTest {

    @Test
    @DisplayName("주문 메뉴와 개수 입력 안함 테스트")
    void 주문_메뉴_개수_입력_안함() {
        Map<Menu, Integer> emptyOrder = new HashMap<>();

        assertSimpleTest(() ->
                assertThatThrownBy(() -> InputOrderMenuValidator.validateNonInputOrderMenu(emptyOrder))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("메뉴 개수가 0이하인 경우 테스트")
    void 메뉴_개수_0이하_입력() {
        Map<Menu, Integer> orderWithZeroCount = new HashMap<>();
        orderWithZeroCount.put(Menu.YANGSONGSOUP, 2);
        orderWithZeroCount.put(Menu.SEAFOODPASTA, 0);

        assertThatThrownBy(() -> InputOrderMenuValidator.validateNonZeroMenuCount(orderWithZeroCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음료만 주문한 경우 테스트")
    void 음료만_주문() {
        Map<Menu, Integer> drinkOnlyOrder = new HashMap<>();
        drinkOnlyOrder.put(Menu.ZEROCOLA, 3);

        assertThatThrownBy(() -> InputOrderMenuValidator.validateOrderOnlyDrink(drinkOnlyOrder))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴 총 개수가 20개를 넘는 경우 테스트")
    void 메뉴_총_개수_20개_넘음() {
        Map<Menu, Integer> overMaxCountOrder = new HashMap<>();
        overMaxCountOrder.put(Menu.CHOCOCAKE, 3);
        overMaxCountOrder.put(Menu.TBONESTEAK, 4);
        overMaxCountOrder.put(Menu.CAESARSALAD, 8);
        overMaxCountOrder.put(Menu.ZEROCOLA, 6);

        assertThatThrownBy(() -> InputOrderMenuValidator.validateOrderCountMax(overMaxCountOrder))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문 형식이 잘못된 경우 테스트")
    void 주문_형식_잘못됨() {
        String wrongFormatOrder = "아이스크림/3";

        assertThatThrownBy(() -> InputOrderMenuValidator.validateWrongOrderFormat(wrongFormatOrder))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문 형식이 잘못된 경우 테스트2")
    void 주문_형식_잘못됨2() {
        String wrongFormatOrder = "티본스테이크_2";

        assertThatThrownBy(() -> InputOrderMenuValidator.validateWrongOrderFormat(wrongFormatOrder))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복 메뉴가 있는 경우 테스트")
    void 중복_메뉴_있음() {
        Map<Menu, Integer> orderWithDuplicateMenu = new HashMap<>();
        orderWithDuplicateMenu.put(Menu.BBQRIB, 2);
        orderWithDuplicateMenu.put(Menu.CHOCOCAKE, 1);

        assertThatThrownBy(() -> InputOrderMenuValidator.validateDuplicateMenu(Menu.CHOCOCAKE, orderWithDuplicateMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문이 없는 경우 종합 테스트")
    void 주문_없음() {
        Map<Menu, Integer> order = new HashMap<>();

        assertThatThrownBy(() -> InputOrderMenuValidator.validateInputOrderMenuAndCount(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴 개수가 0 이하인 경우 종합 테스트")
    void 메뉴_개수_0_이하() {
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.CHRISTMASPASTA, 3);
        order.put(Menu.ZEROCOLA, 0);

        assertThatThrownBy(() -> InputOrderMenuValidator.validateInputOrderMenuAndCount(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문이 모두 음료인 경우 종합 테스트")
    void 주문_모두_음료() {
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.ZEROCOLA, 1);
        order.put(Menu.CHAMPAGNE, 1);

        assertThatThrownBy(() -> InputOrderMenuValidator.validateInputOrderMenuAndCount(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문 개수가 20개를 초과한 경우 종합 테스트")
    void 주문_개수_20개_초과() {
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.CAESARSALAD, 6);
        order.put(Menu.CHRISTMASPASTA, 2);
        order.put(Menu.ZEROCOLA, 10);
        order.put(Menu.REDWINE, 3);

        assertThatThrownBy(() -> InputOrderMenuValidator.validateInputOrderMenuAndCount(order))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
