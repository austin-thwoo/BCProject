package com.laonstory.poc_be_spring.domain.examination.domain;

import com.laonstory.poc_be_spring.domain.examination.common.PicMangeType;
import com.laonstory.poc_be_spring.domain.examination.dto.request.PicCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "T_PIC")
public class Pic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Examination examination;

    private String picDetailStandard;
    private String picManagerName;

    @Enumerated(EnumType.STRING)
    private PicMangeType picManageType;

    private LocalDateTime picManageDate;
    private String picComment;


    public static Pic place(PicCommand picCommand) {
        return Pic.builder()
                .picDetailStandard(picCommand.getPicDetailStandard())
                .picManagerName(picCommand.getPicManagerName())
                .picManageType(picCommand.getPicManageType())
                .picManageDate(LocalDateTime.now())
                .picComment(picCommand.getPicComment())
                .build();

    }

    public void addExamination(Examination examination) {
        this.examination=examination;

    }
}
