package com.react.ming.part1.chapter4.ex;

import com.react.ming.part1.chapter4.CryptoCurrency;
import com.react.ming.part1.chapter4.CryptoCurrency.CurrencyUnit;
import com.react.ming.part1.chapter4.PaymentCalculator;
import com.react.ming.part1.chapter4.SampleData;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class RamdaRefMain1 {

    public static void main(String[] args) {
        List<CryptoCurrency> cs = SampleData.cryptoCurrencies;

        PaymentCalculator cal = new PaymentCalculator();
        cs.stream()
          .filter(c -> c.getUnit() == CurrencyUnit.BTC)
          .map(c -> new ImmutablePair(c.getPrice(), 2))
          .map(cal::getTotalPayment)
          .forEach(System.out::println);
    }
}
