package com.laonstory.poc_be_spring.domain.examination.dto.response;


import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import com.laonstory.poc_be_spring.domain.examination.domain.ProReferencefile;
import com.laonstory.poc_be_spring.domain.examination.domain.model.Sample;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProReferenceFileResponse {

    private Long id;
    private String path;
    private String originalName;
    private long size;




    public ProReferenceFileResponse(ProReferencefile proReferencefile){

      this.id=proReferencefile.getId();
      this.path= proReferencefile.getPath();
      this.originalName= proReferencefile.getOriginalName();
      this.size= proReferencefile.getSize();

    }
}
