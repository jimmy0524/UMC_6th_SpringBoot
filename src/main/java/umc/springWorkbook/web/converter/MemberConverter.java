package umc.springWorkbook.web.converter;

import org.springframework.data.domain.Page;
import umc.springWorkbook.domain.Member;
import umc.springWorkbook.domain.Mission;
import umc.springWorkbook.domain.Review;
import umc.springWorkbook.domain.enums.Gender;
import umc.springWorkbook.domain.mapping.MemberMission;
import umc.springWorkbook.web.dto.MemberRequestDto;
import umc.springWorkbook.web.dto.MemberResponseDTO;
import umc.springWorkbook.web.dto.StoreResponseDTO;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static Member toMember(MemberRequestDto.JoinDto request) {

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

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static MemberResponseDTO.MissionChallengeResultDTO toMissionChallengeResultDTO(MemberMission memberMission) {
        return MemberResponseDTO.MissionChallengeResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static MemberResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return MemberResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .content(review.getContent())
                .storeName(review.getStore().getName())
                .storeId(review.getStore().getId())
                .build();
    }

    public static MemberResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<MemberResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(MemberConverter::reviewPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static MemberResponseDTO.MissionPreViewDTO missionPreViewDTO(MemberMission mission){
        return MemberResponseDTO.MissionPreViewDTO.builder()
                .point(mission.getMission().getPoint())
                .content(mission.getMission().getContent())
                .deadline(mission.getMission().getDeadline())
                .createdAt(mission.getMission().getCreatedAt())
                .StoreName(mission.getMission().getStore().getName())
                .storeId(mission.getMission().getStore().getId())
                .build();
    }

    public static MemberResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<MemberMission> missionList){

        List<MemberResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(MemberConverter::missionPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
