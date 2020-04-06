package com.services;

import com.exception.CSVBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

// Introduced OenCSV Class
public class OpenCSV implements CSVInterface {

    @Override
    public <E> Iterator<E> getIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException {
    try{
        CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.iterator();
    }catch (IllegalStateException e){
        throw new CSVBuilderException( "Unable To Parse File", CSVBuilderException.TypeOfExceptionThrown.UNABLE_TO_PARSE_EXCEPTION);
    }
    }
    @Override
    public <E> List<E> getList(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        try {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        }catch (IllegalStateException e){
            throw new CSVBuilderException( "Unable To Parse File", CSVBuilderException.TypeOfExceptionThrown.UNABLE_TO_PARSE_EXCEPTION);
        }
    }
}