package org.example.dto.member;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberDtoListV2 {
    private List<MemberDto> memberDtoList;

    public MemberDtoListV2() {
        this.memberDtoList = new ArrayList<>();
        this.addList("1","ohhyun");

    }
    public void addList(String id, String name) {
        memberDtoList.add(new MemberDto(id, name));
    }

    public List<MemberDto> getMemberDtoList() {
        return memberDtoList;
    }
}
