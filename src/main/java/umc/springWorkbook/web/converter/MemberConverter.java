package umc.springWorkbook.web.converter;

import umc.springWorkbook.domain.Member;
import umc.springWorkbook.domain.enums.Gender;
import umc.springWorkbook.web.dto.MemberRequest;
import umc.springWorkbook.web.dto.MemberResponse;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class MemberConverter {

    public static Member toMember(MemberRequest.JoinDto request) {

        // LocalDate 객체 생성
        LocalDate birthDate = LocalDate.of(request.getBirthYear(), request.getBirthMonth(), request.getBirthDay());

        // LocalDate를 Date로 변환
        Date birthDateLegacy = Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Date를 Timestamp로 변환
        Timestamp birthTimestamp = new Timestamp(birthDateLegacy.getTime());

        return Member.builder()
                .address(request.getAddress())
                .gender(Gender.valueOf(request.getGender()))
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .foodLikeList(new ArrayList<>())
                .birth(birthTimestamp)
                .name(request.getName())
                .build();
    }
    public static MemberResponse.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponse.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
