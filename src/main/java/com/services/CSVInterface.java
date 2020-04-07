package com.services;

import com.exception.StateCensusException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

// Introduced CSV_Interface
public interface CSVInterface {
    <E> Iterator<E> getIterator(Reader reader, Class<E> csvClass) throws StateCensusException;
    <E> List<E> getList(Reader reader, Class<E> csvClass) throws StateCensusException;
}
