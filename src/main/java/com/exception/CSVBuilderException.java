package com.exception;

public class CSVBuilderException extends Exception{
    public enum TypeOfExceptionThrown{
        FILE_NOT_FOUND_EXCEPTION,
        DELIMITER_HEADER_INCORRECT_EXCEPTION
    }
    public TypeOfExceptionThrown typeOfException;

    public CSVBuilderException(String message, TypeOfExceptionThrown typeOfException) {
        super(message);
        this.typeOfException = typeOfException;
    }
}
