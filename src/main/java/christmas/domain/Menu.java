package christmas.domain;

import java.util.Arrays;
import java.util.Map;

public enum Menu {
    // 애피타이저
    YANGSONGSOUP("양송이수프", 6000, "APPETIZER"),
    TAPAS("타파스", 5500, "APPETIZER"),
    CAESARSALAD("시저샐러드", 8000, "APPETIZER"),

    // 메인
    TBONESTEAK("티본스테이크", 55000, "MAIN"),
    BBQRIB("바비큐립", 54000, "MAIN"),
    SEAFOODPASTA("해산물파스타", 35000, "MAIN"),
    CHRISTMASPASTA("크리스마스파스타", 25000, "MAIN"),

    // 디저트
    CHOCOCAKE("초코케이크", 15000, "DESSERT"),
    ICECREAM("아이스크림", 5000, "DESSERT"),

    // 음료
    ZEROCOLA("제로콜라", 3000, "DRINK"),
    REDWINE("레드와인", 60000, "DRINK"),
    CHAMPAGNE("샴페인", 25000, "DRINK");

    private final String menuName;
    private final int menuPrice;
    private final String menuType;

    Menu(String menuName, int menuPrice, String menuType) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuType = menuType;
    }

    public static Menu getMenuByName(String inputMenuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.menuName.equals(inputMenuName))
                .findFirst()
                .orElse(null);
    }

    public static boolean isMenuName(Menu inputMenu) {
        return Arrays.stream(Menu.values())
                .anyMatch(menu -> menu.menuName.equals(inputMenu.menuName));
    }

    public static boolean isOnlyDrink(Map<Menu, Integer> order) {
        return order.keySet().stream()
                .allMatch(menu -> menu.menuType.equals("DRINK"));
    }

    public int getPriceForCount(int count) {
        return this.menuPrice * count;
    }

    public boolean isDessert() {
        return this.menuType.equals("DESSERT");
    }

    public boolean isMain() {
        return this.menuType.equals("MAIN");
    }

    @Override
    public String toString() {
        return menuName;
    }
}
