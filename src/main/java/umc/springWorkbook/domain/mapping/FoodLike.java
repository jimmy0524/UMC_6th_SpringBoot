package umc.springWorkbook.domain.mapping;

import lombok.*;
import umc.springWorkbook.domain.Food;
import umc.springWorkbook.domain.Member;
import umc.springWorkbook.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    public void setMember(Member member){
        if(this.member != null){
            member.getFoodLikeList().remove(this);
            }
        this.member = member;
        member.getFoodLikeList().add(this);
    }

    public void setFood(Food food){
        this.food = food;
    }
}
