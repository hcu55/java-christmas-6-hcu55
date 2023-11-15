package christmas.controller;

import christmas.domain.ChristmasEvent;
import christmas.domain.EventBadge;
import christmas.domain.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasPromotionController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasEvent christmasEvent;
    private final EventBadge eventBadge;

    public ChristmasPromotionController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.christmasEvent = new ChristmasEvent();
        this.eventBadge = new EventBadge();
    }

    public void play() {
        printStartMessage();
        int visitDate = inputVisitDate();
        Map<Menu, Integer> order = inputOrderMenu();
        christmasEventDiscountAndDisplayEventBenefit(visitDate, order);
    }

    private void printStartMessage() {
        outputView.printChristmasPromotionStartMessage();
    }

    private int inputVisitDate() {
        int expectedVisitDate;
        outputView.printExpectedVisitDateInputMessage();
        while (true) {
            try {
                expectedVisitDate = inputView.inputExpectedVisitDate();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return expectedVisitDate;
    }

    private Map<Menu, Integer> inputOrderMenu() {
        Map<Menu, Integer> orderMenu;
        outputView.printOrderMenuInputMessage();
        while (true) {
            try {
                orderMenu = inputView.inputOrderMenu();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return orderMenu;
    }

    private void christmasEventDiscountAndDisplayEventBenefit(int visitDate, Map<Menu, Integer> order) {
        printPreviewAndOrderMenu(visitDate, order);

        int totalOrderPrice = calculateOrderPrice(order);
        boolean isChampagneGet = checkChampagneGiveaway(totalOrderPrice);
        printChampagneGiveaway(isChampagneGet);

        int totalDiscount = calculateDiscount(totalOrderPrice, visitDate, order);
        printDiscountDetails(isChampagneGet);

        int totalBenefit = calculateBenefit(totalDiscount, isChampagneGet);
        printBenefitAndPaymentAfterDiscount(order);
        printEventBadgeGranting(totalBenefit);
    }

    private void printPreviewAndOrderMenu(int visitDate, Map<Menu, Integer> order) {
        outputView.printPreviewEventBenefit(visitDate);
        outputView.printOrderMenuAndCount(order);
    }

    private int calculateOrderPrice(Map<Menu, Integer> order) {
        int totalOrderPrice = christmasEvent.calculateTotalOrderPrice(order);
        outputView.printTotalOrderPrice(totalOrderPrice);
        return totalOrderPrice;
    }

    private boolean checkChampagneGiveaway(int totalOrderPrice) {
        return christmasEvent.checkGiveawayChampagne(totalOrderPrice);
    }

    private void printChampagneGiveaway(boolean isChampagneGet) {
        outputView.printChampagneGiveaway(isChampagneGet);
    }

    private int calculateDiscount(int totalOrderPrice, int visitDate, Map<Menu, Integer> order) {
        return christmasEvent.totalDiscountIfCanEvent(totalOrderPrice, visitDate, order);
    }

    private void printDiscountDetails(boolean isChampagneGet) {
        outputView.printDiscountDetails(christmasEvent, isChampagneGet);
    }

    private int calculateBenefit(int totalDiscount, boolean isChampagneGet) {
        return eventBadge.totalBenefitAmount(totalDiscount, isChampagneGet);
    }

    private void printBenefitAndPaymentAfterDiscount(Map<Menu, Integer> order) {
        outputView.printTotalBenefit(christmasEvent, order);
        outputView.printPaymentAfterDiscount(christmasEvent, order);
    }

    private void printEventBadgeGranting(int totalBenefit) {
        outputView.printEventBadge(eventBadge, totalBenefit);
    }
}
