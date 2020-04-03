package com.exception;

public class CensusAnalyserCustomException extends Exception{
    public enum TypeOfExceptionThrown{
        FILE_NOT_FOUND_EXCEPTION,
        DELIMITER_HEADER_INCORRECT_EXCEPTION
    }
    public TypeOfExceptionThrown typeOfException;

    public CensusAnalyserCustomException(String message, TypeOfExceptionThrown typeOfException) {
        super(message);
        this.typeOfException = typeOfException;
    }
}
