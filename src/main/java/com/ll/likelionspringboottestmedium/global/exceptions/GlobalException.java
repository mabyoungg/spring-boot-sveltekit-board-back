package com.ll.likelionspringboottestmedium.global.exceptions;

import com.ll.likelionspringboottestmedium.global.rsData.RsData;
import com.ll.likelionspringboottestmedium.standard.base.Empty;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final RsData<Empty> rsData;

    public GlobalException(String resultCode, String msg) {
        super("resultCode=" + resultCode + ",msg=" + msg);
        this.rsData = RsData.of(resultCode, msg);
    }
}
