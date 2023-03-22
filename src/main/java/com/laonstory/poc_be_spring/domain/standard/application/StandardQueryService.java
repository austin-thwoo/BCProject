package com.laonstory.poc_be_spring.domain.standard.application;

import com.laonstory.poc_be_spring.domain.standard.domain.StdCertify;
import com.laonstory.poc_be_spring.domain.standard.domain.StdLine;
import com.laonstory.poc_be_spring.domain.standard.domain.StdMachine;
import com.laonstory.poc_be_spring.domain.standard.domain.StdNation;
import com.laonstory.poc_be_spring.domain.standard.dto.request.StandardCommand;
import com.laonstory.poc_be_spring.domain.standard.dto.request.StandardDeleteCommand;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdCertifyResponse;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdLineResponse;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdMachineResponse;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdNationResponse;
import com.laonstory.poc_be_spring.domain.standard.exception.StandardNameDuplicatedException;
import com.laonstory.poc_be_spring.domain.standard.persistance.*;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StandardQueryService {

    private final StandardRepositorySupport standardRepositorySupport;


    public List<StdNationResponse> findNationAll(User principal, String name) {
        List<StdNation> stdNationList = standardRepositorySupport.findNationAll(name);
        return stdNationList.stream().map(StdNationResponse::new).collect(Collectors.toList());
    }

    public List<StdCertifyResponse> findCertifyAll(User principal, Long stdNationId, String name) {
        List<StdCertify> stdCertifyList = standardRepositorySupport.findCertifyAll(stdNationId, name);
        return stdCertifyList.stream().map(StdCertifyResponse::new).collect(Collectors.toList());
    }

    public List<StdMachineResponse> findMachineAll(User principal, Long stdCertifyId, String name) {
        List<StdMachine> stdMachineList = standardRepositorySupport.findStdMachineAll(stdCertifyId, name);
        return stdMachineList.stream().map(StdMachineResponse::new).collect(Collectors.toList());
    }

    public List<StdLineResponse> findLineAll(User principal, Long stdMachineId, String name) {
        List<StdLine> stdLineList = standardRepositorySupport.findStdLineAll(stdMachineId, name);
        return stdLineList.stream().map(StdLineResponse::new).collect(Collectors.toList());
    }

}
