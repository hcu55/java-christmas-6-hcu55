package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class InputView {

    public int inputExpectedVisitDate() {
        String inputVisitDate = Console.readLine();
        return Integer.parseInt(inputVisitDate);
    }

    public Map<Menu, Integer> inputOrderMenu() {
        String inputMenu = Console.readLine();

        return Arrays.stream(inputMenu.split(","))
                .map(menu -> menu.split("-"))
                .collect(Collectors.toMap(
                        arr -> Menu.valueOf(arr[0]),
                        arr -> Integer.parseInt(arr[1])
                ));
    }
}
