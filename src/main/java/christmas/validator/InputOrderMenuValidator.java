package christmas.validator;

import christmas.domain.Menu;

import java.util.Map;

public class InputOrderMenuValidator {
    private static final String NON_INPUT_MENU_ERROR_MESSAGE = "[ERROR] 입력하지 않으셨습니다. 주문하실 메뉴와 개수를 입력해주세요";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ONLY_ORDER_DRINK_ERROR_MESSAGE = "[ERROR] 음료만 주문할 수 없습니다. 다시 주문해주세요";
    private static final String OVER_MAX_ORDER_COUNT_ERROR_MESSAGE = "[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.";
    private static final int MAX_ORDER_COUNT = 20;
    private static final String regex = "^([가-힣]+-[0-9]+,)*[가-힣]+-[0-9]+$";

    public static void validateInputOrderMenuAndCount(Map<Menu, Integer> order) {
        validateNonInputOrderMenu(order);   // 입력 안함
        validateNonExistMenuName(order);    // 메뉴에 없는 메뉴
        validateNonZeroMenuCount(order);    // 메뉴 개수 0이하
        validateOrderOnlyDrink(order);      // 음료만 주문
        validateOrderCountMax(order);       // 메뉴 총 개수 20개 넘음
    }

    public static void validateNonInputOrderMenu(Map<Menu, Integer> order) {
        if (order.isEmpty()) {
            throw new IllegalArgumentException(NON_INPUT_MENU_ERROR_MESSAGE);
        }
    }

    public static void validateNonExistMenuName(Map<Menu, Integer> order) {
        for (Map.Entry<Menu, Integer> entry : order.entrySet()) {
            Menu menu = entry.getKey();

            if (!Menu.isMenuName(menu)) {
                throw new IllegalArgumentException(WRONG_ORDER_ERROR_MESSAGE);
            }
        }
    }

    public static void validateNonZeroMenuCount(Map<Menu, Integer> order) {
        for (Map.Entry<Menu, Integer> entry : order.entrySet()) {
            int menuCount = entry.getValue();

            if (menuCount <= 0) {
                throw new IllegalArgumentException(WRONG_ORDER_ERROR_MESSAGE);
            }
        }
    }

    public static void validateOrderOnlyDrink(Map<Menu, Integer> order) {
        if (Menu.isOnlyDrink(order)) {
            throw new IllegalArgumentException(ONLY_ORDER_DRINK_ERROR_MESSAGE);
        }
    }

    public static void validateOrderCountMax(Map<Menu, Integer> order) {
        if (order.values().stream().mapToInt(Integer::intValue).sum() > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(OVER_MAX_ORDER_COUNT_ERROR_MESSAGE);
        }
    }

    public static void validateWrongOrderFormat(String inputMenu) {
        if (!inputMenu.matches(regex)) {
            throw new IllegalArgumentException(WRONG_ORDER_ERROR_MESSAGE);
        }
    }

    public static void validateDuplicateMenu(Menu menu, Map<Menu, Integer> order) {
        if (order.containsKey(menu)) {
            throw new IllegalArgumentException(WRONG_ORDER_ERROR_MESSAGE);
        }
    }
}