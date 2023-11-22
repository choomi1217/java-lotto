package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberFactory {

    private final List<LottoNumber> randomNumbers;

    private LottoNumberFactory() {
        randomNumbers = new ArrayList<>();
        for (int i = LottoNumber.MIN_NUMBER; i <= LottoNumber.MAX_NUMBER; i++) {
            randomNumbers.add(new LottoNumber(i));
        }
    }

    public static LottoNumberFactory getInstance() {
        return RandomHolder.INSTANCE;
    }

    public List<LottoNumber> number() {
        Collections.shuffle(randomNumbers);
        return randomNumbers.subList(0, Lotto.MAX_TICKET_NUMBERS);
    }

    private static class RandomHolder {

        private static final LottoNumberFactory INSTANCE = new LottoNumberFactory();
    }
}
