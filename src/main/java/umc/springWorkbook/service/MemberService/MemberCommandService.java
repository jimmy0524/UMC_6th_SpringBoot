package umc.springWorkbook.service.MemberService;

import umc.springWorkbook.domain.Member;
import umc.springWorkbook.web.dto.MemberRequest;

public interface MemberCommandService {
    Member joinMember(MemberRequest.JoinDto request);
}

