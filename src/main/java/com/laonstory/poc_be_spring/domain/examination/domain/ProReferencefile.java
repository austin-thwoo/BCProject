package com.laonstory.poc_be_spring.domain.examination.domain;

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
@Table(name = "T_PRO_REFERENCE_FILE")
public class ProReferencefile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Examination examination;

    private String path;
    private String originalName;
    private long size;


    public static ProReferencefile create(String path, String originalName, long size) {
        return  ProReferencefile.builder()
                .path(path)
                .originalName(originalName)
                .size(size)
                .build();

    }

    public void addExamination(Examination examination) {
        this.examination=examination;
    }
}
