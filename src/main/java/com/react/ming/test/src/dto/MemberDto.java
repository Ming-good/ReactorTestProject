package com.react.ming.test.src.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@ToString
public class MemberDto {

    Long memberId;
    String username;
    int age;
}
