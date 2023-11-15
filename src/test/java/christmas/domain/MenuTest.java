package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    @DisplayName("메뉴 이름 변환 테스트")
    void 메뉴_이름_변환_테스트() {
        assertEquals(Menu.TAPAS, Menu.getMenuByName("타파스"));
        assertNull(Menu.getMenuByName("토마토파스타"));
    }

    @Test
    @DisplayName("메뉴 이름 맞는지 테스트")
    void 메뉴_이름_맞음() {
        assertTrue(Menu.isMenuName(Menu.getMenuByName("티본스테이크")));
        assertTrue(Menu.isMenuName(Menu.TBONESTEAK));
    }

    @Test
    @DisplayName("음료만 주문 테스트")
    void 음료만_주문_테스트() {
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.ZEROCOLA, 2);
        order.put(Menu.CHAMPAGNE, 1);

        assertTrue(Menu.isOnlyDrink(order));

        order.put(Menu.SEAFOODPASTA, 1);

        assertFalse(Menu.isOnlyDrink(order));
    }

    @Test
    @DisplayName("메뉴 개수에 따른 가격 테스트")
    void 메뉴_개수에_따른_가격_테스트() {
        assertEquals(108000, Menu.BBQRIB.getPriceForCount(2));
    }

    @Test
    @DisplayName("디저트 맞는지 확인 테스트")
    void 디저트_맞는지_확인_테스트() {
        assertTrue(Menu.CHOCOCAKE.isDessert());
        assertFalse(Menu.YANGSONGSOUP.isDessert());
    }

    @Test
    @DisplayName("메인 메뉴 맞는지 확인 테스트")
    void 메인_메뉴_맞는지_확인_테스트() {
        assertTrue(Menu.TBONESTEAK.isMain());
        assertFalse(Menu.CHOCOCAKE.isMain());
    }

    @Test
    @DisplayName("메뉴 이름 필드 값 반환 테스트")
    void 메뉴_이름_필드_값_반환_테스트() {
        assertEquals("시저샐러드", Menu.CAESARSALAD.toString());
    }
}