package com.laonstory.poc_be_spring.domain.examination.domain;


import com.laonstory.poc_be_spring.domain.examination.dto.request.RequirementCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "T_REQUIREMENT")
public class Requirement {

  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Examination examination;

  private String reqContent;

  private LocalDateTime reqCreatedDate;


    public static Requirement create(String requirementContent, Examination examination) {
      return Requirement.builder()
              .examination(examination)
              .reqContent(requirementContent)
              .reqCreatedDate(LocalDateTime.now())
              .build();
    }
}
