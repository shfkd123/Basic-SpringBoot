package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService; //new로 객체생성하지 말고 스프링 컨테이너에 연결하자!

    //필드 주입 ex) @Autowired private final MemberService memberService;

    //command + N >> setter 생성
    //setter 주입 >> public으로 생성되기 때문에 누군가 값을 바꿀 수 있는 단점이 있다.
    /*@Autowired
    public void setMemberSerivce(MemberService memberSerivce) {
        this.memberSerivce = memberSerivce;
    }*/

    @Autowired // 생성자 주입 - 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 거의 사용한다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        //System.out.println("member : " +  member.getName()) ;

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> memList = memberService.findMembers();
        model.addAttribute("members", memList);
        return "/members/memberList";
    }
}
