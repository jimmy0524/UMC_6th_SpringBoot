package umc.springWorkbook.web.Controller;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.springWorkbook.apiPayload.ApiResponse;
import umc.springWorkbook.domain.Member;
import umc.springWorkbook.domain.Mission;
import umc.springWorkbook.domain.Review;
import umc.springWorkbook.domain.mapping.MemberMission;
import umc.springWorkbook.service.MemberCommandService;
import umc.springWorkbook.service.MemberQueryService;
import umc.springWorkbook.validation.annotation.AlreadyMission;
import umc.springWorkbook.validation.annotation.CheckPage;
import umc.springWorkbook.validation.annotation.ExistStore;
import umc.springWorkbook.web.converter.MemberConverter;
import umc.springWorkbook.web.converter.StoreConverter;
import umc.springWorkbook.web.dto.MemberRequestDto;
import umc.springWorkbook.web.dto.MemberResponseDTO;
import umc.springWorkbook.web.dto.StoreResponseDTO;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Validated
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    //회원가입
    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> joinAPI(@RequestBody @Valid MemberRequestDto.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    //미션을 도전중인 미션으로 추가
    @PostMapping("/{memberId}/mission/{missionId}")
    public ApiResponse<MemberResponseDTO.MissionChallengeResultDTO> challengeAPI(@PathVariable Long memberId, @AlreadyMission  @PathVariable Long missionId) {
        MemberMission memberMission = memberCommandService.challengeMission(memberId, missionId);
        return ApiResponse.onSuccess(MemberConverter.toMissionChallengeResultDTO(memberMission));
    }

    //내가 작성한 리뷰 조회 (토큰 받는다고 가정)
    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 조회 API",description = "내가 작성한 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1페이지부터 넘겨주세요"),
    })
    public ApiResponse<MemberResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "memberId") Long memberId, @CheckPage @RequestParam(name = "page") Integer page){
        Integer realPage = page - 1;
        Page<Review> reviewList = memberQueryService.getReviewList(memberId, realPage);
        return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDTO(reviewList));
    }

    //내가 진행중인 미션 목록
    @GetMapping("/{memberId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1페이지부터 넘겨주세요"),
    })
    public ApiResponse<MemberResponseDTO.MissionPreViewListDTO> getMissionList(@ExistStore @PathVariable(name = "memberId") Long memberId, @CheckPage @RequestParam(name = "page") Integer page){
        Integer realPage = page - 1;
        Page<MemberMission> missionList = memberQueryService.getOngoingMissionList(memberId, realPage);
        return ApiResponse.onSuccess(MemberConverter.missionPreViewListDTO(missionList));
    }
}
