package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello");
        return "hello";

    }

    @GetMapping("hello-mvc")
    //ResponseBody가 따로 없어서 그대로 viewResolver에게 던져서 view를 찾아달라고 한다.
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody // http 응답 body에 직접 넣겠다.  view를 거치지 않고 직접 내려줌.. 넘겨주는 name 값이 그대로 return 됨.
    public String helloString(@RequestParam(value = "name", required = true) String name){
        return "hello " + name; // 문자열을 직접 넘김 (StringConverter)
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //command+ shift + enter
        hello.setName(name);
        return hello; //객체가 반환이면 defualt값인 json형식으로 반환 됨. (JsonConverter)
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
