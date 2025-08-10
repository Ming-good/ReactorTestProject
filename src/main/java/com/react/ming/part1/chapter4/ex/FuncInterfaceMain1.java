package com.react.ming.part1.chapter4.ex;

import com.react.ming.part1.chapter4.CryptoCurrency;
import com.react.ming.part1.chapter4.SampleData;
import java.util.Collections;
import java.util.List;

public class FuncInterfaceMain1 {

    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = SampleData.cryptoCurrencies;
        Collections.sort(cryptoCurrencies, (c1, c2) -> {
            return c1.getUnit().name().compareTo(c2.getUnit().name());
        });

        cryptoCurrencies.stream()
                .forEach(c -> System.out.println("암호 화폐명: " + c.getName() + ", 가격: " + c.getUnit()));
    }
}
