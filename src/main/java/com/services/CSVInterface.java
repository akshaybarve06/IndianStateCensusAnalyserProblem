package com.services;

import com.exception.CSVBuilderException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

// Introduced CSV_Interface
public interface CSVInterface {
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass)  ;
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass)  ;
}
