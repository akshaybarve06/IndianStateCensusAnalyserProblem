package com.services;

import com.exception.CensusAnalyserCustomException;
import com.model.StateCensusCSV;
import com.model.StateDataCSV;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

//Combined Both StateCensus and StateCode Classes Into One
public class StateCensusAnalyser  {

    OpenCSV openCSV = new OpenCSV();
    // Method For Reading Data From STATECENSUS File
    public Integer readFile(String filePath) throws Exception{
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            Iterator<StateCensusCSV> stateCSVIterator = openCSV.CSVfileIterator(reader, StateCensusCSV.class);
            Iterable<StateCensusCSV> csvIterable = () -> stateCSVIterator;
            int numOfRecords = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numOfRecords;
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserCustomException("ENTERED FILE IS NOT FOUND..",CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        } catch (RuntimeException e){
            throw new CensusAnalyserCustomException("DELIMITER OR HEADER INCORRECT..",CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        }
    }

    // Method For Load data from STATECODECSV file
    public Integer loadIndianStateCodeData (String csvFilePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterator<StateDataCSV> statesCSVIterator = OpenCSV.CSVfileIterator(reader, StateDataCSV.class);
            Iterable<StateDataCSV> iterableStateCode = () -> statesCSVIterator;
            int countRecord = (int) StreamSupport.stream(iterableStateCode.spliterator(), false).count();
            return countRecord;
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserCustomException("ENTERED FILE IS NOT FOUND..",CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        } catch (RuntimeException e){
            throw new CensusAnalyserCustomException("DELIMITER OR HEADER INCORRECT..",CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        }
    }
}

