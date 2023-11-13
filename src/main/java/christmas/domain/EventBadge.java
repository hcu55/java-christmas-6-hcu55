package christmas.domain;

public class EventBadge {
    private static final int CHAMPAGNE_PRICE = 25_000;

    public int totalBenefitAmount(int totalDiscount, boolean isChampagneGet) {
        int totalBenefit = totalDiscount;
        if (isChampagneGet) {
            totalBenefit += CHAMPAGNE_PRICE;
        }
        return totalBenefit;
    }
}
