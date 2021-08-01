package com.vermouth.model;

public enum ErrorMSG {
    EXIST_ERROR("이미 존재하는 값입니다."),
    NOTEXIST_ERROR("값이 없습니다."),
    PARAM_ERROR("파라미터 오류입니다."),
    CHANGE_WARN("형식변경 경고입니다."),
    UNPREDICTABLE_ERROR("예상치못한 오류입니다.");

    private String msg;

    private ErrorMSG(String msg){
        this.msg = msg;
    }

    public String msg(){
        return msg;
    }
}