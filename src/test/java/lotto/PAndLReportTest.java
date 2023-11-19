package lotto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PAndLReportTest {

    @DisplayName("가지고 있는 로또 개수과 통계를 이용해 손익레포트를 만든다.")
    @Test
    void rate(){
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoWallet lottoWallet = LottoWallet.from(List.of(lotto1, lotto2));
        StatisticsReport statisticsReport = new StatisticsReport(lottoWallet, Map.of(Prize.THIRD, 1));
        PAndLReport pAndLReport = PAndLReport.of(lottoWallet, statisticsReport);
        BigDecimal rate = pAndLReport.rate();
        Assertions.assertThat(rate).isEqualTo(BigDecimal.valueOf(0.36));
    }
}
