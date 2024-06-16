package umc.springWorkbook.domain;

import lombok.*;
import umc.springWorkbook.domain.common.BaseEntity;
import umc.springWorkbook.domain.enums.MissionState;
import umc.springWorkbook.domain.mapping.MemberMission;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(20)")
    private String ownerNum;

    private Long point;

    @Column(columnDefinition = "VARCHAR(50)")
    private String content;

    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
