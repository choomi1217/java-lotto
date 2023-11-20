package lotto.view;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import lotto.LottoWallet;
import lotto.Prize;
import lotto.StatisticsReport;

public class ResultView {

    private final static String LOTTO_PURCHASE_RESULT_MESSAGE = "%d개를 구매했습니다. \n";

    private final PrintStream sout;

    public ResultView(PrintStream sout) {
        this.sout = sout;
    }
    
    public void calculateResult(int result) {
        sout.printf(LOTTO_PURCHASE_RESULT_MESSAGE, result);
    }

    public void out(LottoWallet lottoWallet) {
        for (int i = 0; i < lottoWallet.totalTicketCount(); i++){
            sout.println(lottoWallet.oneTicket(i).lottoNumbers());
        }
    }

    public void out(StatisticsReport statisticsReport) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(Prize.values()).forEach(prize -> {
            if(prizeRankOver3(prize)){
                stringBuilder.append(prize.rank());
                stringBuilder.append("개 일치");
                stringBuilder.append(" (").append(prize.price()).append(")-");
                stringBuilder.append(statisticsReport.countByPrize(prize)).append("개 \n");
            }
        });
        sout.print(stringBuilder.toString());
    }

    private static boolean prizeRankOver3(Prize prize) {
        return prize.rank() >= 3;
    }

    public void out(BigDecimal rate) {
        DecimalFormat decimalFormat = new DecimalFormat();
        sout.printf("총 수익률은 %s 입니다. \n", decimalFormat.format(rate));
    }
}
