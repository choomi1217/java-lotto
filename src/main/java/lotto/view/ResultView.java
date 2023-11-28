package lotto.view;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoWallet;
import lotto.domain.StatisticsReport;

public class ResultView {

    private final static String EMPTY_MESSAGE = "";
    private final static String LOTTO_PURCHASE_RESULT_MESSAGE = "%d개를 구매했습니다. \n";

    private final static String LOTTO_PURCHASE_AUTO_MANUALLY_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";

    private final static String LOTTO_WARNING_OF_LOSS_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

    private static final Map<LottoRank, String> outputFormats = new HashMap<>();

    static {
        String defaultFormat = "%d개 일치(%d원) - %d 개";
        outputFormats.put(LottoRank.SECOND, "%d개 일치, 보너스 볼 일치(%d원) - %d 개");

        Arrays.stream(LottoRank.values()).forEach(
            lottoRank -> {
                if (lottoRank != LottoRank.SECOND) {
                    outputFormats.put(lottoRank, defaultFormat);
                }
            }
        );
    }

    private final PrintStream sout;

    public ResultView(PrintStream sout) {
        this.sout = sout;
    }

    public void calculateResult(int result) {
        sout.printf(LOTTO_PURCHASE_RESULT_MESSAGE, result);
    }

    public void out(LottoWallet lottoWallet) {
        sout.printf(LOTTO_PURCHASE_AUTO_MANUALLY_COUNT_MESSAGE, lottoWallet.autoLottoCount(), lottoWallet.manuallyLottoCount());
        for (int i = 0; i < lottoWallet.totalTicketCount(); i++) {
            sout.println(lottoNumbers(lottoWallet.oneTicket(i)));
        }
        sout.print("\n");
    }

    private String lottoNumbers(Lotto lotto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        lotto.lottoNumbers().forEach(lottoNumber -> {
            stringBuilder.append(lottoNumber.toString()).append(", ");
        });
        int lastIndexOf = stringBuilder.lastIndexOf(", ");
        stringBuilder.replace(lastIndexOf, lastIndexOf + 1, "]");
        return stringBuilder.toString();
    }

    public void resultOut(StatisticsReport report) {
        sout.println("당첨 통계");
        sout.println("---------");
        IntStream.range(1, LottoRank.values().length).forEach(i -> {
            LottoRank lottoRank = LottoRank.values()[i];
            String output = String.format(outputFormats.get(lottoRank), lottoRank.rank(),
                lottoRank.prize(), report.count(lottoRank));
            sout.println(output);
        });
    }

    public void out(BigDecimal rate) {
        DecimalFormat decimalFormat = new DecimalFormat();
        if(rate.compareTo(BigDecimal.ONE) <= 0){
            sout.printf("총 수익률은 %s 입니다. %s\n", decimalFormat.format(rate), LOTTO_WARNING_OF_LOSS_MESSAGE);
        }
        sout.printf("총 수익률은 %s 입니다. %s\n", decimalFormat.format(rate), EMPTY_MESSAGE);
    }
}
