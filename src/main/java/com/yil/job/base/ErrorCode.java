/*
 * Copyright (c) 2022. Tüm hakları Yasin Yıldırım'a aittir.
 */

package com.yil.job.base;


import lombok.Getter;

@Getter
public enum ErrorCode {
    // Job için error kodları girilecek
    JobNotFound(9000000, "Meslek bilgisi bulunamadı!"),
    ;

    private final int code;

    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
