package com.laonstory.poc_be_spring.global.dto.response;



import com.laonstory.poc_be_spring.domain.examination.domain.ProReferencefile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {


    private Long id;

    private String path;


    public ImageResponse(ProReferencefile filee){

//        this.id = careImage.getId();
//
//        this.path = careImage.getPath();

    }


}
