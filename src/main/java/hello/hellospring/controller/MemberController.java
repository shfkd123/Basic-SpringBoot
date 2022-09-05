package hello.hellospring.controller;

import hello.hellospring.service.MemberSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberSerivce memberSerivce; //new로 객체생성하지 말고 스프링 컨테이너에 연결하자!

    //필드 주입 ex) @Autowired private final MemberService memberService;

    //command + N >> setter 생성
    //setter 주입 >> public으로 생성되기 때문에 누군가 값을 바꿀 수 있는 단점이 있다.
    /*@Autowired
    public void setMemberSerivce(MemberService memberSerivce) {
        this.memberSerivce = memberSerivce;
    }*/

    @Autowired // 생성자 주입 - 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 거의 사용한다.
    public MemberController(MemberSerivce memberSerivce) {
        this.memberSerivce = memberSerivce;
    }
}
