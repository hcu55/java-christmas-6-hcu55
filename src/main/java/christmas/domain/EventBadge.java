package christmas.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class EventBadge {
    private static final int CHAMPAGNE_PRICE = 25_000;
    private static final Map<Integer, String> badgeMap;

    static {
        badgeMap = new LinkedHashMap<>();
        badgeMap.put(20_000, "산타");
        badgeMap.put(10_000, "트리");
        badgeMap.put(5_000, "별");
    }

    public int totalBenefitAmount(int totalDiscount, boolean isChampagneGet) {
        int totalBenefit = totalDiscount;
        if (isChampagneGet) {
            totalBenefit += CHAMPAGNE_PRICE;
        }
        return totalBenefit;
    }

    public String GrantingEventBadge(int totalBenefit) {
        for (Map.Entry<Integer, String> entry : badgeMap.entrySet()) {
            if (totalBenefit >= entry.getKey()) {
                return entry.getValue();
            }
        }
        return "없음";
    }
}
