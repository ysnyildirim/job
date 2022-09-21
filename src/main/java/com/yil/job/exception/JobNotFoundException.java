package com.yil.job.exception;

import com.yil.job.base.ApiException;
import com.yil.job.base.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@ApiException(code = ErrorCode.JobNotFound)
public class JobNotFoundException extends Exception {
}
