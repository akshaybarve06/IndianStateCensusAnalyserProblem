package com.services;

import com.exception.CensusAnalyserCustomException;
import com.model.StateCensusCSV;
import com.model.StateDataCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser <E> {

    public int readFile(String filePath) throws CensusAnalyserCustomException
    {
        int noOfRecords = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
        ) {
            Iterator<StateCensusCSV> stateCSVIterator = this.getCSVfileIterator(reader, StateCensusCSV.class);
            Iterable<StateCensusCSV> csvIterable = () -> stateCSVIterator;
            int numOfRecords = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            int numOfrecords;
            return numOfRecords;
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserCustomException("ENTERED FILE IS NOT FOUND..",CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        }
        catch (RuntimeException e){
            throw new CensusAnalyserCustomException("DELIMITER OR HEADER INCORRECT..",CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return noOfRecords;
    }

    public Integer loadIndianStateCodeData (String csvFilePath) throws CensusAnalyserCustomException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterator<StateDataCSV> statesCSVIterator = this.getCSVfileIterator(reader, StateDataCSV.class);
            Iterable<StateDataCSV> iterableStateCode = () -> statesCSVIterator;
            int countRecord = (int) StreamSupport.stream(iterableStateCode.spliterator(), false).count();
            return countRecord;
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserCustomException("ENTERED FILE IS NOT FOUND..",CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        }
        catch (RuntimeException e){
            throw new CensusAnalyserCustomException("DELIMITER OR HEADER INCORRECT..",CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (null);
    }

    private <E > Iterator < E > getCSVfileIterator(Reader reader, Class < E > csvClass) throws
            CensusAnalyserCustomException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (RuntimeException e){
            throw new CensusAnalyserCustomException("DELIMITER OR HEADER INCORRECT..",CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (null);
    }
}

