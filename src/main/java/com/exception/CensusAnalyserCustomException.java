package com.exception;

public class CensusAnalyserCustomException extends Exception{
    public enum TypeOfExceptionThrown{
        FILE_NOT_FOUND_EXCEPTION,
        DELIMITER_INCORRECT_EXCEPTION
    }
    public TypeOfExceptionThrown typeOfException;

    public CensusAnalyserCustomException(TypeOfExceptionThrown typeOfException){
        this.typeOfException = typeOfException;
    }
}
