package umc.springWorkbook.web.Controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.springWorkbook.apiPayload.ApiResponse;
import umc.springWorkbook.domain.Member;
import umc.springWorkbook.service.MemberService.MemberCommandService;
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
}
