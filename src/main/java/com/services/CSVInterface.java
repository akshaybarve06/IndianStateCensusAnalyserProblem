package com.services;

import com.exception.CSVBuilderException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

// Introduced CSV_Interface
public interface CSVInterface {
    public <E> Iterator<E> getIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;
    public <E> List<E> getList(Reader reader, Class<E> csvClass) throws CSVBuilderException;
}
