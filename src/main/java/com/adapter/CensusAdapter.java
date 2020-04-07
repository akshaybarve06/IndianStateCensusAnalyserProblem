package com.adapter;

import com.DAO.CensusDAO;
import com.exception.StateCensusException;
import com.DTO.StateCensusCSV;
import com.DTO.USCensusCSV;
import com.services.CSVBuilderFactory;
import com.services.OpenCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;


import static java.nio.file.Files.newBufferedReader;

public abstract class CensusAdapter {

    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateCensusException;

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCSVClass, String csvFilePath) throws StateCensusException {
        Map<String, CensusDAO> censusDAOMap = new HashMap<>();
        try (Reader reader = newBufferedReader(Paths.get(csvFilePath));) {
            OpenCSV csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> stateCensusIterator = csvBuilder.getIterator(reader, censusCSVClass);
            Iterable<E> stateCensuses = () -> stateCensusIterator;
            if (censusCSVClass.getName().contains("StateCensusCSV")) {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(StateCensusCSV.class::cast)
                        .forEach(censusCSV -> censusDAOMap.put(censusCSV.StateName, new CensusDAO(censusCSV)));
            }
            else if (censusCSVClass.getName().contains("USCensusCSV")) {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> censusDAOMap.put(censusCSV.StateName, new CensusDAO(censusCSV)));
            }
            return censusDAOMap;
        } catch (RuntimeException e) {
            throw new StateCensusException("Check Delimiters Or Headers", StateCensusException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return censusDAOMap;
    }
}