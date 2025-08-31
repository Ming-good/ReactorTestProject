package com.react.ming.part2.chapter14.ex;

public class CryptoCurrencyPriceEmitter {
    private CryptoCurrencyPriceListener listener;

    public void setListener(CryptoCurrencyPriceListener listener) {
        this.listener = listener;
    }

    public void flowInto() {
        listener.onPrice(SampleData.btcPrices);
    }

    public void complete() {
        listener.onComplete();
    }
}
