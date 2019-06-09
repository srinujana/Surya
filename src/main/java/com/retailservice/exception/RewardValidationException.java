package com.retailservice.exception;

/**
 * Created by Surya on 06/08/2019.
 */
public class RewardValidationException extends RuntimeException {

    public RewardValidationException(String errorCode) {
        super(errorCode);
    }

}
