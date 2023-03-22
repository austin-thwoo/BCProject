package com.laonstory.poc_be_spring.domain.standard.application;

import com.laonstory.poc_be_spring.domain.standard.domain.StdCertify;
import com.laonstory.poc_be_spring.domain.standard.domain.StdLine;
import com.laonstory.poc_be_spring.domain.standard.domain.StdMachine;
import com.laonstory.poc_be_spring.domain.standard.domain.StdNation;
import com.laonstory.poc_be_spring.domain.standard.dto.request.StandardDeleteCommand;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdCertifyResponse;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdLineResponse;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdMachineResponse;
import com.laonstory.poc_be_spring.domain.standard.dto.response.StdNationResponse;
import com.laonstory.poc_be_spring.domain.standard.exception.StandardNameDuplicatedException;
import com.laonstory.poc_be_spring.domain.standard.persistance.*;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.standard.dto.request.StandardCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StandardCommandService {
    private final StdNationJpaRepository stdNationJpaRepository;
    private final StdCertifyJpaRepository stdCertifyJpaRepository;
    private final StdMachineJpaRepository stdMachineJpaRepository;
    private final StdLineJpaRepository stdLineJpaRepository;
    private final StandardRepositorySupport standardRepositorySupport;


    public boolean saveNation(User principal, StandardCommand standardCommand) {
        stdNationNameCheck(standardCommand.getName());
        stdNationJpaRepository.save(StdNation.create(standardCommand));
        return true;

    }

    public boolean saveCertify(User principal, StandardCommand standardCommand) {
        stdCertifyNameCheck(standardCommand.getName(), standardCommand.getStdNationId());
        StdNation stdNation = getStdNationById(standardCommand.getStdNationId());
        StdCertify stdCertify = StdCertify.create(standardCommand.getName(), stdNation);
        stdNation.addCertify(stdCertify);
        stdCertifyJpaRepository.save(stdCertify);
        return true;
    }

    public boolean saveMachine(User principal, StandardCommand standardCommand) {
        stdMachineNameCheck(standardCommand.getName(), standardCommand.getStdCertifyId());
        StdCertify stdCertify = getStdCertifyById(standardCommand.getStdCertifyId());
        StdMachine stdMachine = StdMachine.create(standardCommand.getName(), stdCertify);
        stdCertify.addMachine(stdMachine);
        stdMachineJpaRepository.save(stdMachine);
        return true;
    }

    public boolean saveLine(User principal, StandardCommand standardCommand) {
        stdLineNameCheck(standardCommand.getName(), standardCommand.getStdMachineId());
        StdMachine stdMachine = getStdMachineById(standardCommand.getStdMachineId());
        StdLine stdLine = StdLine.create(standardCommand.getName(), stdMachine);
        stdMachine.addLine(stdLine);
        stdLineJpaRepository.save(stdLine);
        return true;
    }


    private StdNation getStdNationById(Long stdNationId) {
        return standardRepositorySupport.findStdNationById(stdNationId);
    }

    private StdCertify getStdCertifyById(Long stdCertifyId) {
        return standardRepositorySupport.findStdCertifyById(stdCertifyId);
    }

    private StdMachine getStdMachineById(Long stdMachineId) {
        return standardRepositorySupport.findStdMachineById(stdMachineId);
    }

    private StdLine getStdLineById(Long stdLineId) {
        return standardRepositorySupport.findStdLineById(stdLineId);
    }

    public boolean updateNation(User principal, StandardCommand standardCommand) {
        StdNation stdNation = getStdNationById(standardCommand.getStdNationId());
        stdNationNameCheck(standardCommand.getName());
        stdNation.updateName(standardCommand.getName());

        return true;
    }

    public boolean updateCertify(User principal, StandardCommand standardCommand) {
        StdCertify stdCertify = getStdCertifyById(standardCommand.getStdCertifyId());
        stdCertifyNameCheck(standardCommand.getName(), standardCommand.getStdNationId());
        stdCertify.updateName(standardCommand.getName());

        return true;
    }


    public boolean updateMachine(User principal, StandardCommand standardCommand) {
        StdMachine stdMachine = getStdMachineById(standardCommand.getStdMachineId());
        stdMachineNameCheck(standardCommand.getName(), standardCommand.getStdCertifyId());
        stdMachine.updateName(standardCommand.getName());

        return true;
    }

    public boolean updateLine(User principal, StandardCommand standardCommand) {
        StdLine stdLine = getStdLineById(standardCommand.getStdLineId());
        stdLineNameCheck(standardCommand.getName(), standardCommand.getStdMachineId());
        stdLine.updateName(standardCommand.getName());

        return true;
    }


    private void stdNationNameCheck(String name) {
        boolean exits = stdNationJpaRepository.existsByName(name);
        if (exits) {
            throw new StandardNameDuplicatedException(name);
        }
    }

    private void stdCertifyNameCheck(String name, Long stdNationId) {
        boolean exist = stdCertifyJpaRepository.existsByNameAndStdNationId(name, stdNationId);
        if (exist) {
            throw new StandardNameDuplicatedException(name);
        }
    }

    private void stdMachineNameCheck(String name, Long stdCertifyId) {
        boolean exist = stdMachineJpaRepository.existsByNameAndStdCertifyId(name, stdCertifyId);
        if (exist) {
            throw new StandardNameDuplicatedException(name);
        }
    }

    private void stdLineNameCheck(String name, Long stdMachineId) {
        boolean exist = stdLineJpaRepository.existsByNameAndStdMachineId(name, stdMachineId);
        if (exist) {
            throw new StandardNameDuplicatedException(name);
        }
    }


    public boolean deleteLine(User principal, StandardDeleteCommand deleteCommand) {

        stdLineJpaRepository.deleteAll(getStdLineListById(deleteCommand));
        return true;


    }

    private List<StdLine> getStdLineListById(StandardDeleteCommand deleteCommand) {
        return standardRepositorySupport.findStdLineAllById(deleteCommand.getId());
    }

    public boolean deleteMachine(User principal, StandardDeleteCommand deleteCommand) {

        stdMachineJpaRepository.deleteAll(getStdMachineListById(deleteCommand));
        return true;


    }

    private List<StdMachine> getStdMachineListById(StandardDeleteCommand deleteCommand) {
        return standardRepositorySupport.findStdMachineAllById(deleteCommand.getId());
    }

    public boolean deleteCertify(User principal, StandardDeleteCommand deleteCommand) {

        stdCertifyJpaRepository.deleteAll(getStdCertifyListById(deleteCommand));
        return true;
    }

    private List<StdCertify> getStdCertifyListById(StandardDeleteCommand deleteCommand) {
        return standardRepositorySupport.findCertifyAllById(deleteCommand.getId());
    }

    public boolean deleteNation(User principal, StandardDeleteCommand deleteCommand) {
        stdNationJpaRepository.deleteAll(getStdNationListById(deleteCommand));
        return true;
    }

    private List<StdNation> getStdNationListById(StandardDeleteCommand deleteCommand) {
        return standardRepositorySupport.findNationAllById(deleteCommand.getId());//        List<StdNation> stdNationList
    }

}
