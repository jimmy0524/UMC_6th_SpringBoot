package umc.springWorkbook.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.springWorkbook.domain.common.BaseEntity;
import umc.springWorkbook.domain.enums.FoodType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(40)")
    private String address;

    private Double latitude;

    private Double longitude;

    private Float averageStar;

    @Column(columnDefinition = "VARCHAR(10)")
    @Enumerated(EnumType.STRING)
    private FoodType category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regin_id")
    private Region region;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
