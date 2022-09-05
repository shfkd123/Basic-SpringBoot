package hello.hellospring.controller;

import hello.hellospring.service.MemberSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberSerivce memberSerivce; //new로 객체생성하지 말고 스프링 컨테이너에 연결하자!
    @Autowired
    public MemberController(MemberSerivce memberSerivce) {
        this.memberSerivce = memberSerivce;
    }
}
