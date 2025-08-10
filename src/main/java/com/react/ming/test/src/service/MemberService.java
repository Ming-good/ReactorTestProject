package com.react.ming.test.src.service;

import com.react.ming.part2.util.Log;
import com.react.ming.test.src.dto.MemberDto;
import com.react.ming.test.src.mapper.CursorHandler;
import com.react.ming.test.src.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    private final SqlSessionFactory factory;

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
    public void selectMember(long memberId) {
        Flux.range(0, 5)
            .flatMapSequential(ym ->
                            Flux.using(
                                    () -> {
                                        SqlSession session = factory.openSession(ExecutorType.BATCH);
                                        MemberMapper memberMapperBatch = session.getMapper(MemberMapper.class);
                                        Cursor<MemberDto> cursor = memberMapperBatch.selectMemeber();
                                        return new CursorHandler<MemberDto>(session, cursor);
                                    },
                                    cur -> Flux.fromIterable(cur.cursor),     // 커서 → Flux (단일 소비)
                                    h -> {
                                        try {
                                            h.close();
                                        } catch (Exception ignore) {
                                        }
                                    }
                            ).subscribeOn(Schedulers.boundedElastic()),
                    /*concurrency*/ 4
            ).filter(dto -> dto.getMemberId()%10000 == 0)
            .subscribe(dto -> Log.info("AA: {}", dto));

    }
}
