package com.laonstory.poc_be_spring.domain.standard.domain;

import com.laonstory.poc_be_spring.domain.standard.dto.request.StandardCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "T_STD_NATION")
public class StdNation {

  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "stdNation", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<StdCertify> stdCertifies = new ArrayList<>();




  public static StdNation create(StandardCommand standardCommand) {
    return StdNation.builder().name(standardCommand.getName()).build();
  }

  public void addCertify(StdCertify stdCertify) {
    this.stdCertifies.add(stdCertify);
  }

    public void updateName(String name) {
    this.name=name;
    }
}
