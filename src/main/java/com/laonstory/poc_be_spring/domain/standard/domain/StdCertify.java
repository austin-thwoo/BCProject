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
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_STD_CERTIFY")
public class StdCertify {

  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private StdNation stdNation;

  @OneToMany(mappedBy = "stdCertify", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<StdMachine> machines = new ArrayList<>();


    public static StdCertify create(String name, StdNation stdNation) {
      return StdCertify.builder().name(name).stdNation(stdNation).build();
    }


  public void addMachine(StdMachine stdMachine) {
      this.machines.add(stdMachine);
  }

    public void updateName(String name) {
      this.name=name;
    }
}
