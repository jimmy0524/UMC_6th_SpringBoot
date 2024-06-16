package umc.springWorkbook.web.Controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.springWorkbook.apiPayload.ApiResponse;
import umc.springWorkbook.domain.Member;
import umc.springWorkbook.domain.mapping.MemberMission;
import umc.springWorkbook.service.MemberCommandService;
import umc.springWorkbook.validation.annotation.AlreadyMission;
import umc.springWorkbook.web.converter.MemberConverter;
import umc.springWorkbook.web.dto.MemberRequest;
import umc.springWorkbook.web.dto.MemberResponse;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Validated
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    //회원가입
    @PostMapping("/join")
    public ApiResponse<MemberResponse.JoinResultDTO> joinAPI(@RequestBody @Valid MemberRequest.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    //미션을 도전중인 미션으로 추가
    @PostMapping("/{memberId}/mission/{missionId}")
    public ApiResponse<MemberResponse.MissionChallengeResultDTO> challengeAPI(@PathVariable Long memberId, @AlreadyMission  @PathVariable Long missionId) {
        MemberMission memberMission = memberCommandService.challengeMission(memberId, missionId);
        return ApiResponse.onSuccess(MemberConverter.toMissionChallengeResultDTO(memberMission));
    }
}
