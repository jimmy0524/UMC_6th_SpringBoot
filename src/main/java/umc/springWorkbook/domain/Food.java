package umc.springWorkbook.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.springWorkbook.domain.mapping.FoodLike;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(10)")
    private String name;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodLike> foodLikeList = new ArrayList<>();
}
