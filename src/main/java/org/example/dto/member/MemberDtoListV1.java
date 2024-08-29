package org.example.dto.member;

import java.util.ArrayList;
import java.util.List;

public class MemberDtoListV1 {
    private static MemberDtoListV1 instance;
    private List<MemberDto> memberDtoList;

    public MemberDtoListV1() {
        this.memberDtoList = new ArrayList<>();
        this.addList("1","ohhyun");

    }

    public static synchronized MemberDtoListV1 getInstance() {
     if (instance == null) {
         instance = new MemberDtoListV1();
     }
        return instance;
    }

    public void addList(String id, String name) {
        memberDtoList.add(new MemberDto(id, name));
    }

    public List<MemberDto> getMemberDtoList() {
        return memberDtoList;
    }
}
