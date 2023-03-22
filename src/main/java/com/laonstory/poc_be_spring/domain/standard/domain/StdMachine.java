package com.laonstory.poc_be_spring.domain.standard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "T_STD_MACHINE")
public class StdMachine {

  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private StdCertify stdCertify;

  @OneToMany(mappedBy = "stdMachine", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<StdLine> lines = new ArrayList<>();


  public static StdMachine create(String name, StdCertify stdCertify) {
    return StdMachine.builder().name(name).stdCertify(stdCertify).build();
  }

  public void addLine(StdLine stdLine) {
    this.lines.add(stdLine);
  }

    public void updateName(String name) {
    this.name=name;
    }
}
