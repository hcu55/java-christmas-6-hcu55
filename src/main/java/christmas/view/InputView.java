package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
import christmas.validator.InputOrderMenuValidator;
import christmas.validator.InputVisitDateValidator;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class InputView {

    InputVisitDateValidator inputVisitDateValidator = new InputVisitDateValidator();

    public int inputExpectedVisitDate() {
        String inputVisitDate = Console.readLine();
        inputVisitDateValidator.validateInputExpectedVisitDate(inputVisitDate);

        return Integer.parseInt(inputVisitDate);
    }

    public Map<Menu, Integer> inputOrderMenu() {
        String inputMenu = Console.readLine();
        InputOrderMenuValidator.validateWrongOrderFormat(inputMenu);    // 메뉴 주문 형식 검증

        String[] orderMenus = inputMenu.split(",");
        Map<Menu, Integer> order = new HashMap<>();

        for (String orderMenu : orderMenus) {
            Map.Entry<Menu, Integer> parsedOrder = parseMenuNameAndCount(orderMenu);
            addToOrder(order, parsedOrder);
        }
        InputOrderMenuValidator.validateInputOrderMenuAndCount(order);

        return order;
    }

    private Map.Entry<Menu, Integer> parseMenuNameAndCount(String orderMenu) {
        String[] arr = orderMenu.split("-");
        Menu menu = Menu.getMenuByName(arr[0]);
        int count = Integer.parseInt(arr[1]);

        return new AbstractMap.SimpleEntry<>(menu, count);
    }

    private void addToOrder(Map<Menu, Integer> order, Map.Entry<Menu, Integer> parsedOrder) {
        InputOrderMenuValidator.validateDuplicateMenu(parsedOrder.getKey(), order);
        order.put(parsedOrder.getKey(), parsedOrder.getValue());
    }
}
