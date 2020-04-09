package com.services;

import com.exception.StateCensusException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

// Introduced OenCSV Class
public class OpenCSV implements CSVInterface {

    @Override
    public <E> Iterator<E> getIterator(Reader reader, Class<E> csvClass) throws StateCensusException
    {
        try
        {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(csvClass)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new StateCensusException( "Unable To Parse File", StateCensusException.TypeOfExceptionThrown.UNABLE_TO_PARSE_EXCEPTION);
        }
    }
    @Override
    public <E> List<E> getList(Reader reader, Class<E> csvClass) throws StateCensusException
    {
        try
        {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                .withType(csvClass)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
            return csvToBean.parse();
        } catch (IllegalStateException e) {
            throw new StateCensusException( "Unable To Parse File", StateCensusException.TypeOfExceptionThrown.UNABLE_TO_PARSE_EXCEPTION);
        }
    }
}