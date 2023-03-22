package com.laonstory.poc_be_spring.domain.examination.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_EXAMINATION_HISTORY")
public class ExaminationHistory {


  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Examination examination;

  private String content;

  private LocalDateTime createdDate;


    public static ExaminationHistory create(Examination examination, String content) {
      ExaminationHistory examinationHistory =new ExaminationHistory();
      examinationHistory.examination=examination;
      examinationHistory.content=content;
      examinationHistory.createdDate=LocalDateTime.now();
      return examinationHistory;

//      return ExaminationHistory.builder()
//              .examination(examination)
//              .content(content)
//              .createdDate(LocalDateTime.now())
//              .build();
    }

  public Long getId() {
    return id;
  }

  public Examination getExamination() {
    return examination;
  }

  public String getContent() {
    return content;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
}
