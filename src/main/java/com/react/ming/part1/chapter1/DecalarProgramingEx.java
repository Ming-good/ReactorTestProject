package com.react.ming.part1.chapter1;

import java.util.List;

public class DecalarProgramingEx {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 3, 21, 10, 8, 11);
        int sum = numbers.stream()
                         .filter(num -> num > 6 && (num % 2 != 0))
                         .mapToInt(num -> num)
                         .sum();
        System.out.println(sum);
    }


}
