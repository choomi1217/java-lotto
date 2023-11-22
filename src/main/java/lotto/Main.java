package lotto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Main {

    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in), System.out);
        ResultView resultView = new ResultView(System.out);

        int money = inputView.lottoInitCount();

        MoneyWallet moneyWallet = new MoneyWallet(money);
        LottoWallet lottoWallet = LottoShop.from(money).purchase();
        moneyWallet = moneyWallet.withdraw(lottoWallet.totalPurchaseAmount());
        resultView.calculateResult(lottoWallet.totalTicketCount());
        resultView.out(lottoWallet);

        List<LottoNumber> lastWeakLottoNumbers = createLastWeakLottoNumbers(inputView.lastWeakLottoInit());
        WinningLotto winningLotto = WinningLotto.from(new Lotto(lastWeakLottoNumbers));

        StatisticsReport report = StatisticsReport.of(lottoWallet).report(winningLotto);
        resultView.out(report);
        resultView.out(report.rate());
    }

    private static List<LottoNumber> createLastWeakLottoNumbers(List<Integer> lastWeakLottoInit) {
        return lastWeakLottoInit.stream().map(LottoNumber::new)
            .collect(Collectors.toList());
    }

}
