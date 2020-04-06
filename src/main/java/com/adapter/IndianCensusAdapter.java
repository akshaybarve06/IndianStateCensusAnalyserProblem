package com.adapter;

import com.DAO.CensusDAO;
import com.exception.CSVBuilderException;
import com.DTO.StateCensusCSV;
import com.DTO.StateDataCSV;
import com.services.CSVBuilderFactory;
import com.services.OpenCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndianCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CSVBuilderException {
        Map<String, CensusDAO> censusDAOMap = super.loadCensusData(StateCensusCSV.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusDAOMap;
        return loadStateCodeCSVData(censusDAOMap, csvFilePath[1]);
    }

    private Map<String, CensusDAO> loadStateCodeCSVData(Map<String, CensusDAO> censusDAOMap, String csvFilePath) throws CSVBuilderException
    {
        String extension = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!extension.equals("csv"))
        {
            throw new CSVBuilderException("Given File Not Found ", CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            OpenCSV csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<StateDataCSV> stateCodeIterator = csvBuilder.getIterator(reader, StateDataCSV.class);
            Iterable<StateDataCSV> stateCodes = () -> stateCodeIterator;
            StreamSupport.stream(stateCodes.spliterator(), false)
                    .filter(StateDataCSV -> censusDAOMap.get(StateDataCSV.StateName) != null)
                    .forEach(StateDataCSV -> censusDAOMap.get(StateDataCSV.StateName).StateCode = StateDataCSV.StateCode);
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("Given File Not Found ", CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("Check Delimiters Or Headers", CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return censusDAOMap;
    }
}