package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EventBadgeTest {
    private EventBadge eventBadge;

    @BeforeEach
    void setUp() {
        eventBadge = new EventBadge();
    }

    @Test
    @DisplayName("총혜택 금액 구하기 테스트")
    void 총혜택_금액_구하기_테스트() {
        assertEquals(30000, eventBadge.totalBenefitAmount(5000, true));
        assertEquals(10000, eventBadge.totalBenefitAmount(10000, false));
    }

    @Test
    @DisplayName("이벤트 배지 부여 테스트")
    void 이벤트_배지_부여_테스트() {
        assertEquals("산타", eventBadge.GrantingEventBadge(20000));
        assertEquals("트리", eventBadge.GrantingEventBadge(15000));
        assertEquals("별", eventBadge.GrantingEventBadge(5000));
        assertEquals("없음", eventBadge.GrantingEventBadge(3000));
        assertThat(eventBadge.GrantingEventBadge(2000)).isEqualTo("없음");
        assertThat(eventBadge.GrantingEventBadge(8000)).isEqualTo("별");
        assertThat(eventBadge.GrantingEventBadge(16000)).isEqualTo("트리");
        assertThat(eventBadge.GrantingEventBadge(23000)).isEqualTo("산타");
    }
}