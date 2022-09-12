package hello.hellospring.serivce;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class MemberServiceTest {

    MemberService service;
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository(); // 각 테스트를 실행하기 전에(beforeEach) memberRepository를 생성하고 memberService에 넣는다. 같은 memberRepository를 사용 가능 (DI : dependency Injection)
        service = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void join(){ //회원가입() - 테스트코드는 한글로 함수명으로 작성해도 괜찮음
        //service - join : 동일한 이름이 없으면 return member.getId();

        Member member = new Member();
        member.setName("hello");
        Long  memID = service.join(member);

        assertThat(memID).isEqualTo(1);

        //-------------------------------------------//
        //given
        Member memver = new Member();
        member.setName("hello");

        //when
        Long saveId = service.join(member);

        //then : 우리가 저장한 데이터가 repository에 있는지를 검증하고
        Member findMember = service.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

        //테스트는 예외처리 테스트도 확인하는 것이 중요!
        //예외가 잘 터트려지는지도
    }

    @Test
    public void 중복_회원_예외() throws IllegalAccessException {
        //given
        Member member = new Member();
        member.setName("hello");
        Member member2 = new Member();
        member2.setName("hello");

        //when
        service.join(member);
/* 방법 1)
        try {
            service.join(member2);
            fail();
        } catch (IllegalAccessException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

// 방법 2)
        IllegalAccessException e = assertThrows(IllegalAccessException.class, () -> service.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
        //Assertions.assertThat(saveId).isEqualTo(saveId2);
    }

    @Test
    public void findMembers(){
        //findAll()값 리턴 -> arrayList로 Map에 있는 values 값 모두 리턴
        Member member = new Member();
        member.setName("memName");
        Long memId = service.join(member);

        List<Member> MemberList = service.findMembers();

        String name = MemberList.get(0).getName();
        assertThat("memName").isEqualTo(name);

    }

    @Test
    public void findOne(){
        //return -> memberId
        Member member = new Member();
        member.setName("mjKim");

        Long memId = service.join(member);

        Optional<Member> RealId = service.findOne(memId);
        assertThat(memId).isEqualTo(1);
    }
}
