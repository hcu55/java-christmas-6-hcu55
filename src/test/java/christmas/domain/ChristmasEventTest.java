package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChristmasEventTest {
    private ChristmasEvent christmasEvent;
    private Map<Menu, Integer> order;

    @BeforeEach
    void setUp() {
        christmasEvent = new ChristmasEvent();
        order = new HashMap<>();
    }

    @Test
    @DisplayName("총주문 금액 구하기 테스트")
    void 총주문_금액_구하기_테스트() {
        order.put(Menu.BBQRIB, 2);
        order.put(Menu.ZEROCOLA, 1);
        assertEquals(111000, christmasEvent.calculateTotalOrderPrice(order));
    }

    @Test
    @DisplayName("총주문 10000원 이상 할인 적용 테스트")
    void 총주문_10000원_이상_할인_적용_테스트() {
        order.put(Menu.YANGSONGSOUP, 5);
        order.put(Menu.TBONESTEAK, 1);
        int totalOrderPrice = christmasEvent.calculateTotalOrderPrice(order);
        assertEquals(2200, christmasEvent.totalDiscountIfCanEvent(totalOrderPrice, 3, order));
    }

    @Test
    @DisplayName("총주문 10000원 이하 할인 적용 테스트")
    void 총주문_10000원_이하_할인_적용_테스트() {
        order.put(Menu.ICECREAM, 1);
        order.put(Menu.ZEROCOLA, 1);
        int totalOrderPrice = christmasEvent.calculateTotalOrderPrice(order);
        assertEquals(0, christmasEvent.totalDiscountIfCanEvent(totalOrderPrice, 3, order));
    }

    @Test
    @DisplayName("혜택 내역 출력 테스트")
    void 혜택_내역_출력_테스트() {
        order.put(Menu.YANGSONGSOUP, 5);
        order.put(Menu.TBONESTEAK, 1);
        int totalOrderPrice = christmasEvent.calculateTotalOrderPrice(order);
        christmasEvent.totalDiscountIfCanEvent(totalOrderPrice, 3, order);
        christmasEvent.processDiscountDetails((discountName, discountAmount) -> {
            System.out.println(discountName + ": " + discountAmount);
        });
    }

    @Test
    @DisplayName("할인 금액 합계 계산 테스트")
    void 할인_금액_합계_계산_테스트() {
        order.put(Menu.TAPAS, 5);
        order.put(Menu.CHOCOCAKE, 1);
        int totalOrderPrice = christmasEvent.calculateTotalOrderPrice(order);
        christmasEvent.totalDiscountIfCanEvent(totalOrderPrice, 25, order);
        assertEquals(6423, christmasEvent.calculateTotalDiscount());
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액 테스트")
    void 할인_후_예상_결제_금액_테스트() {
        order.put(Menu.TAPAS, 5);
        order.put(Menu.CHOCOCAKE, 1);
        int totalOrderPrice = christmasEvent.calculateTotalOrderPrice(order);
        int discount = christmasEvent.totalDiscountIfCanEvent(totalOrderPrice, 25, order);
        assertEquals(36077, christmasEvent.deductDiscountFromTotalPrice(totalOrderPrice, discount));
    }

    @Test
    @DisplayName("샴페인 증정 유무 테스트")
    void 샴페인_증정_유무_테스트() {
        order.put(Menu.TBONESTEAK, 3);
        int totalOrderPrice = christmasEvent.calculateTotalOrderPrice(order);
        assertTrue(christmasEvent.checkGiveawayChampagne(totalOrderPrice));

        order.put(Menu.TBONESTEAK, 1);
        totalOrderPrice = christmasEvent.calculateTotalOrderPrice(order);
        assertFalse(christmasEvent.checkGiveawayChampagne(totalOrderPrice));
    }
}