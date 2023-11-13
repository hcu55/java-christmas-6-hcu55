package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class InputView {

    public int inputExpectedVisitDate() {
        String inputVisitDate = Console.readLine();
        return Integer.parseInt(inputVisitDate);
    }

    public Map<Menu, Integer> inputOrderMenu() {
        String inputMenu = Console.readLine();

        String[] orderMenus = inputMenu.split(",");
        Map<Menu, Integer> order = new HashMap<>();

        for (String orderMenu : orderMenus) {
            Map.Entry<Menu, Integer> parsedOrder = parseOrder(orderMenu);
            addToOrder(order, parsedOrder);
        }
        return order;
    }

    private static Map.Entry<Menu, Integer> parseOrder(String orderMenu) {
        String[] arr = orderMenu.split("-");
        Menu menu = Menu.valueOf(arr[0]);
        int count = Integer.parseInt(arr[1]);

        return new AbstractMap.SimpleEntry<>(menu, count);
    }

    private static void addToOrder(Map<Menu, Integer> order, Map.Entry<Menu, Integer> parsedOrder) {
        order.put(parsedOrder.getKey(), parsedOrder.getValue());
    }
}
