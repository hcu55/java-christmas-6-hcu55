package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int inputExpectedVisitDate() {
        String inputVisitDate = Console.readLine();
        return Integer.parseInt(inputVisitDate);
    }
}
