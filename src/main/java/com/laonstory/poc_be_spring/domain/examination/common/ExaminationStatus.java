package com.laonstory.poc_be_spring.domain.examination.common;

public enum ExaminationStatus {
    APPLY("접수"),


    APPROVE("접수승인"),//api로 하나 만들어야 하고 이후부턴 고객은 수정불가
    //->접수현황
    //->접수현황

    BEFORE_REVIEW("검토전"),//접수승인을 하고난 후 뭐라도 작성하면 -> 접수승인 검증로직

    AFTER_REVIEW("고객검토"),//고객검토 버튼 ->api 개발

    COMPLETED("검토완료"),//검토완료 버튼 api 개발
    // ->고객검토
    // -> 업로드및 검토


    AllCOMPLETED("최종완료"),//최종승인 버튼 api 개발
    //성적서 현황
    ETC("기타");


//진행사태 ex) 접수, 접수승인==작성전, 검토전, 고객검토 ,검토완료, 최종완료
    private final String value;

    ExaminationStatus(String value) {
        this.value = value;

    }
}
