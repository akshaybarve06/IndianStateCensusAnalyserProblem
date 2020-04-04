package com.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

// Introduced OenCSV Class
public class OpenCSV implements CSVInterface {

    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) {
        CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                .withType(csvClass)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator<E> csvUserIterator = csvToBean.iterator();
        return csvUserIterator;
    }

    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader)
                .withType(csvClass)
                .withIgnoreLeadingWhiteSpace(true);
        return csvToBeanBuilder.build().parse();
    }
}