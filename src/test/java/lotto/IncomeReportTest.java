package lotto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IncomeReportTest {

    @DisplayName("2,000원 로또를 구매하고 5,000원이 당첨 되면 손익률은 2.5입니다.")
    @Test
    void rate() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoWallet lottoWallet = LottoWallet.of(List.of(lotto1, lotto2), 2);
        StatisticsReport statisticsReport = new StatisticsReport(lottoWallet,
            Map.of(Prize.FOURTH, 1));
        IncomeReport incomeReport = IncomeReport.of(statisticsReport);
        BigDecimal actual = incomeReport.rate();
        Assertions.assertThat(actual).isEqualTo(BigDecimal.valueOf(2.5));
    }
}
