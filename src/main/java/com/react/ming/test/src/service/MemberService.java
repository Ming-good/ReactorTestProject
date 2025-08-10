package com.react.ming.test.src.service;

import com.react.ming.test.src.dto.MemberDto;
import com.react.ming.test.src.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    /**
     * insert member - mybatis
     *
     * @param memberDto the member dto
     * @return the int
     */
    @Transactional
    public int insertMember(MemberDto memberDto) {

        int result = memberMapper.insertMember(memberDto);

        return result;
    }


    /**
     * select member - mybatis
     *
     * @param memberId the memberId
     * @return the member
     */
    @Transactional
    public MemberDto selectMember(long memberId) {

        MemberDto selectMemeber = memberMapper.selectMemeber(memberId);

        return selectMemeber;
    }
}
