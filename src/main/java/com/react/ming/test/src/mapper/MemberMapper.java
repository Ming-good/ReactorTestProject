package com.react.ming.test.src.mapper;

import com.react.ming.test.src.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    //Member 저장
    int insertMember(MemberDto member);

    //Member 조회
    MemberDto selectMemeber(@Param("memberId") Long memberId);
}
