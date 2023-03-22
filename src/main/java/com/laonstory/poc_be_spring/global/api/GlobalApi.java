package com.laonstory.poc_be_spring.global.api;


import java.io.File;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;


@RestController
@RequiredArgsConstructor
@Slf4j
public class GlobalApi {

    @Value("${app.filePath}")
    private String PATH;

    //
//    private final SmsSendService smsSendService;
//
//    @PostMapping("/sms")
//    public ApiResponse<String> send(@AuthenticationPrincipal User user,
//                                    @RequestBody SmsRequest dto) throws IOException, ParseException {
//
//
//        return new ApiResponse<>(smsSendService.send(user,dto));
//
//    }
    @ApiOperation(value = "파일보기")
    @GetMapping(value = "/fileview")
    public ResponseEntity<Resource> view(@RequestParam String path) throws IOException {

        Resource resource = loadAsResource(path);
        Path paths = Paths.get(resource.getURI());
        String contentType = Files.probeContentType(paths);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, contentType);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(resource);
    }


    public Resource loadAsResource(String filename){
        try {

            Path file = Path.of(PATH, filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {

                throw new RuntimeException("file error");
            }
        } catch (MalformedURLException e) {

            throw new RuntimeException("file error");
        }

    }
    @ApiOperation(value = "base")
    @GetMapping(value = "/base")
    public String base(@RequestParam String path)throws  IOException{
//        File file = new File(PATH + path);
        Path of = Path.of(PATH + path);
        byte[] bytes = Files.readAllBytes(of);
        return new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
    }
}
