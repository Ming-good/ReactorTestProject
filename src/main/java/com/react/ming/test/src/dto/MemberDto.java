package com.react.ming.test.src.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberDto {

    Long memberId;
    String username;
    int age;
}
