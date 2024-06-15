package umc.springWorkbook.domain;

import lombok.*;
import umc.springWorkbook.domain.common.BaseEntity;
import umc.springWorkbook.domain.enums.AskState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Ask extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(40)")
    private String title;

    @Column(columnDefinition = "VARCHAR(500)")
    private String content;

    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'WAITING'")
    private AskState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "ask", cascade = CascadeType.ALL)
    private List<Image> imageList = new ArrayList<>();
}
