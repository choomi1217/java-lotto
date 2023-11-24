package lotto.domain;

public class WinningLotto {

    private final Lotto lastWeekLotto;

    private final LottoNumber bonusBall;

    public WinningLotto(Lotto lastWeekLotto, LottoNumber bonusBall) {
        this.lastWeekLotto = lastWeekLotto;
        this.bonusBall = bonusBall;
    }

    public static WinningLotto of(Lotto lotto, LottoNumber bonusBall) {
        return new WinningLotto(lotto, bonusBall);
    }

    public int compare(Lotto lotto) {
        return lastWeekLotto.matchCount(lotto);
    }

    public int compareBonus(Lotto lotto) {
        return lotto.lottoNumbers().stream().mapToInt(lottoNumber -> {
            if(lottoNumber.equals(bonusBall)){
                return 1;
            }
            return 0;
        }).sum();
    }
}
