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
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.iterator();
    }
    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass)  {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();

    }
}