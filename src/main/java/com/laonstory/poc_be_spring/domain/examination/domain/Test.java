package com.laonstory.poc_be_spring.domain.examination.domain;

import com.laonstory.poc_be_spring.domain.examination.common.TestApproveType;
import com.laonstory.poc_be_spring.domain.examination.dto.request.TestCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "T_TEST")
public class Test {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Examination examination;


    private String testProductLine;

    private String testGuaranteeLine;


    private String testSubject;
    private String testPartName;
    private String testDetailStandard;
    private String testDirectorName;
    private String testComment;

    @Enumerated(EnumType.STRING)
    private TestApproveType testApproveType;

    public static Test place(TestCommand testCommand) {
        return Test.builder()
                .testProductLine(testCommand.getTestProductLine())
                .testGuaranteeLine(testCommand.getTestGuaranteeLine())
                .testSubject(testCommand.getTestSubject())
                .testPartName(testCommand.getTestPartName())
                .testDetailStandard(testCommand.getTestDetailStandard())
                .testDirectorName(testCommand.getTestDirectorName())
                .testComment(testCommand.getTestComment())
                .testApproveType(testCommand.getTestApproveType())
                .build();

    }

    public void addExamination(Examination examination) {
        this.examination=examination;

    }
}
