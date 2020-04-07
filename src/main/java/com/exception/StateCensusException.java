package com.exception;

public class StateCensusException extends Exception{
    public enum TypeOfExceptionThrown{
        FILE_NOT_FOUND_EXCEPTION,
        DELIMITER_HEADER_INCORRECT_EXCEPTION,
        CENSUS_DATA_NOT_FOUND_EXCEPTION,
        UNABLE_TO_PARSE_EXCEPTION,
        INVALID_COUNTRY_EXCEPTION

        }
    public TypeOfExceptionThrown typeOfException;

    public StateCensusException(String message, TypeOfExceptionThrown typeOfException) {
        super(message);
        this.typeOfException = typeOfException;
    }
}
