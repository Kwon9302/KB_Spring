package org.example.controller.member.v2;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.member.MemberDtoListV2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class MemberSaveControllerV2 {
    private MemberDtoListV2 memberList;

    public MemberSaveControllerV2(MemberDtoListV2 memberList) {
        this.memberList = memberList;
    }

    @RequestMapping(value = "/member/v2/form/save", method = RequestMethod.GET)
    public String process(@RequestParam("id") String id,
                          @RequestParam("name") String name,
                          Model model){
        log.info("=====> 회원 추가 request 호출, /member/form/save");

        memberList.addList(id,name);
        model.addAttribute("memberList", memberList.getMemberDtoList());
        return "member-show2";
    }
}
