package umc.springWorkbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springWorkbook.domain.Member;
import umc.springWorkbook.domain.Mission;
import umc.springWorkbook.domain.Review;
import umc.springWorkbook.domain.Store;
import umc.springWorkbook.domain.enums.MissionState;
import umc.springWorkbook.domain.mapping.MemberMission;
import umc.springWorkbook.repository.MemberMissionRepository;
import umc.springWorkbook.repository.MemberRepository;
import umc.springWorkbook.repository.MissionRepository;
import umc.springWorkbook.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository missionRepository;

    public Page<Review> getReviewList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId).get();

        Page<Review> MemberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return MemberPage;
    }

    public Page<MemberMission> getOngoingMissionList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId).get();
        Page<MemberMission> MissionPage = missionRepository.findAllByMemberAndStatus(member, MissionState.ONGOING, PageRequest.of(page, 10));
        return MissionPage;
    }
}
