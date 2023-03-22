package com.laonstory.poc_be_spring.domain.standard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "T_STD_LINE")
public class StdLine {

  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private StdMachine stdMachine;


    public static StdLine create(String name, StdMachine stdMachine) {
      return StdLine.builder().name(name).stdMachine(stdMachine).build();
   }

    public void updateName(String name) {
        this.name=name;
    }
}
