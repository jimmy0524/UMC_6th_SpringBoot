package umc.springWorkbook.domain;

import lombok.*;
import umc.springWorkbook.domain.common.BaseEntity;
import umc.springWorkbook.domain.enums.MissionState;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'WAITING'")
    @Enumerated(EnumType.STRING)
    private MissionState state;

    private Long point;

    @Column(columnDefinition = "VARCHAR(50)")
    private String content;

    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
