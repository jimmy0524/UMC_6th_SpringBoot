package umc.springWorkbook.domain;

import lombok.*;
import umc.springWorkbook.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(225)")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ask_id")
    private Ask ask;

    public void setReview(Review review) {
        this.review = review;
        if (!review.getImageList().contains(this)) {
            review.getImageList().add(this);
        }
    }
}
