package umc.springWorkbook.web.converter;

import umc.springWorkbook.domain.Member;
import umc.springWorkbook.domain.enums.Gender;
import umc.springWorkbook.web.dto.MemberRequest;
import umc.springWorkbook.web.dto.MemberResponse;

import java.sql.Timestamp;
import java.util.ArrayList;

public class MemberConverter {

    public static Member toMember(MemberRequest.JoinDto request) {
        return Member.builder()
                .address(request.getAddress())
                .gender(Gender.valueOf(request.getGender()))
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .foodLikeList(new ArrayList<>())
                .build();
    }
    public static MemberResponse.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponse.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
