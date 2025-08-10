package com.react.ming.part1.chapter4.ex;

import com.react.ming.part1.chapter4.CryptoCurrency;
import com.react.ming.part1.chapter4.CryptoCurrency.CurrencyUnit;
import com.react.ming.part1.chapter4.SampleData;
import java.util.List;

public class CapturingMain1 {

    public static void main(String[] args) {
        List<CryptoCurrency> cs = SampleData.cryptoCurrencies;

        String korBTC = "비트코인";
        cs.stream()
          .filter(cc -> cc.getUnit() == CurrencyUnit.BTC)
          .map(cc -> cc.getName() + "[" + korBTC + "]")
          .forEach(System.out::println);
    }
}
