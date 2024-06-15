package umc.springWorkbook.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@DiscriminatorValue("MISSION")
public class MissionAlarm extends Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(40)")
    private String title;

    @Column(columnDefinition = "VARCHAR(200)")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id")
    private Alarm alarm;
}
