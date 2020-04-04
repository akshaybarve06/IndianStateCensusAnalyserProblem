package com.services;

import com.exception.CensusAnalyserCustomException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

// Introduced OenCSV Class

public class OpenCSV extends CSVInterface {

    public static <E> Iterator<E> CSVfileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserCustomException {
        return getCSVToBeen(reader,csvClass).iterator();
    }

    // Introduce CSVBeanBuilder
    public static <E> CsvToBean<E> getCSVToBeen(Reader reader, Class<E> csvClass) throws CensusAnalyserCustomException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean;
        } catch (RuntimeException e) {
            throw new CensusAnalyserCustomException("DELIMITER OR HEADER INCORRECT..",CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        }
    }
}