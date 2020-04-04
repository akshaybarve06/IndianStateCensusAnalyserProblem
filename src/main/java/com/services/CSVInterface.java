package com.services;

import com.exception.CensusAnalyserCustomException;
import java.io.Reader;
import java.util.Iterator;

// Introduced CSV_Interface
public class CSVInterface {
    public <E> Iterator<E> getCSVfileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserCustomException {
        return null;
    }
}
