package com.adapter;

import com.DAO.CensusDAO;
import com.exception.CSVBuilderException;
import com.model.StateCensusCSV;
import com.model.USCensusCSV;
import com.services.CSVBuilderFactory;
import com.services.OpenCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

import static java.nio.file.Files.newBufferedReader;

public abstract class CensusAdapter {
    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CSVBuilderException;

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCSVClass, String csvFilePath) throws CSVBuilderException
    {
        Map<String, CensusDAO> censusDAOMap = new HashMap<>();
        try (Reader reader = newBufferedReader(Paths.get(csvFilePath));)
        {
            OpenCSV csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> stateCensusIterator = csvBuilder.getIterator(reader, censusCSVClass);
            Iterable<E> stateCensuses = () -> stateCensusIterator;
            if (censusCSVClass.getName().contains("StateCensusCSV"))
            {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(StateCensusCSV.class::cast)
                        .forEach(censusCSV -> censusDAOMap.put(censusCSV.StateName, new CensusDAO(censusCSV)));
            } else if (censusCSVClass.getName().contains("USStateCensus"))
            {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> censusDAOMap.put(censusCSV.StateName, new CensusDAO(censusCSV)));
            }
            return censusDAOMap;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("Given File Not Found ", CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("Check Delimiters Or Headers", CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return censusDAOMap;
    }
}