package com.react.ming.part1.chapter4.ex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SupplyFuncMain1 {

    public static void main(String[] args) {
        System.out.println(createMnemonic());
    }

    private static String createMnemonic() {
        return Stream.generate(() -> getMnemonic())
                     .limit(12)
                     .collect(Collectors.joining(" "));
    }

    private static String getMnemonic() {
        List<String> mnemonic = Arrays.asList("alpha", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel");
        Collections.shuffle(mnemonic);
        return mnemonic.get(0);
    }
}
