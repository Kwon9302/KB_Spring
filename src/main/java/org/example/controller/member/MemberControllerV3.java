package org.example.controller.member;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.member.MemberDtoListV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/member/v3")
public class MemberControllerV3 {
    private MemberDtoListV2 memberList;

    @Autowired
    public MemberControllerV3(MemberDtoListV2 memberDtoList){
        this.memberList = memberDtoList;

    }

    @GetMapping("/show")
    private String memberList(Model model) {
        log.info("=========> 회원 목록 조회 페이지 호출, /member/v3/show");
        model.addAttribute("memberList", memberList.getMemberDtoList());
        return "member-show3";
    }

    @GetMapping("/form")
    private String memberForm() {
        log.info("=========> 회원 목록 조회 페이지 호출, /member/v3/show");
        return "member-form3";
    }

    @PostMapping("/form/save")
    public String memberSave(@RequestParam("id") String id,
                             @RequestParam("name") String name,
                             Model model){
        log.info("=====> 회원 추가 request 호출, /member/form/save");

        memberList.addList(id,name);
        model.addAttribute("memberList", memberList.getMemberDtoList());
        return "member-show3";
    }
}
