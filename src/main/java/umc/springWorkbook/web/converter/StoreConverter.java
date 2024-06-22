package umc.springWorkbook.web.converter;

import org.springframework.data.domain.Page;
import umc.springWorkbook.domain.Mission;
import umc.springWorkbook.domain.Review;
import umc.springWorkbook.domain.Store;
import umc.springWorkbook.web.dto.StoreRequestDTO;
import umc.springWorkbook.web.dto.StoreResponseDTO;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


public class StoreConverter {

    public static Review toReview(StoreRequestDTO.ReviewDTO request) {
        return Review.builder()
                            .content(request.getContent())
                            .star(request.getStar())
                            .build();
    }

    public static Store toStore(StoreRequestDTO.CreateDTO request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .category(request.getCategory()).build();
    }

    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return StoreResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static StoreResponseDTO.CreateStoreResultDTO toCreateStoreResultDTO(Store store) {
        return StoreResponseDTO.CreateStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .content(review.getContent())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDTO.MissionPreViewDTO missionPreViewDTO(Mission mission){
        return StoreResponseDTO.MissionPreViewDTO.builder()
                .point(mission.getPoint())
                .content(mission.getContent())
                .deadline(mission.getDeadline())
                .createdAt(mission.getCreatedAt())
                .StoreName(mission.getStore().getName())
                .build();
    }

    public static StoreResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionList){

        List<StoreResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(StoreConverter::missionPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
