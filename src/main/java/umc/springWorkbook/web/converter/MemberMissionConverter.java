package umc.springWorkbook.web.converter;

import umc.springWorkbook.domain.Member;
import umc.springWorkbook.domain.Mission;
import umc.springWorkbook.domain.mapping.MemberMission;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }
}
