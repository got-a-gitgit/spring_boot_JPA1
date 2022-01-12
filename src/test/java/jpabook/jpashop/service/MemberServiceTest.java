package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    
    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("Jo");

        //when
        Long saveId = memberService.join(member);

        //then
        Assert.assertEquals(member,memberRepository.findOne(saveId));
        
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_가입() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("Jo");

        Member member2 = new Member();
        member2.setName("Jo");

        //when
        Long saveId1 = memberService.join(member1);
        Long saveId2 = memberService.join(member2);

        //then
        fail("중복 회원 가입 예외가 발생해야한다.");
    }
}