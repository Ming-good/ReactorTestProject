package com.react.ming.part2.chapter14.ex;

import java.util.List;

public interface CryptoCurrencyPriceListener {
    void onPrice(List<Integer> priceList);
    void onComplete();
}
