package com.services;

import com.exception.CSVBuilderException;
import com.google.gson.Gson;
import com.model.StateCensusCSV;
import com.model.StateDataCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

//Combined Both StateCensus and StateCode Classes Into One
public class StateCensusAnalyser <E>{

        //VARIABLES
        private String CSV_FILE_PATH;

        List<StateCensusCSV> StateCensusCSVList = null;
        List<StateDataCSV> StateDataCSVList = null;

        Map<String, StateCensusCSV> StateCensusCSVMap = null;
        Map<String, StateDataCSV> StateDataCSVMap = null;

        OpenCSV openCSV = new OpenCSV();

        public StateCensusAnalyser(String path, Class<E> csvClass) {
            this.CSV_FILE_PATH=path;
            this.StateCensusCSVMap = new HashMap<>();
            this.StateDataCSVMap = new HashMap<>();
        }
    //METHOD TO LOAD RECORDS OF CSV FILE
        public int loadRecords() throws CSVBuilderException {
            try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
                OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
                Iterator<StateCensusCSV> StateCensusCSVIterator = csvBuilder.getCSVFileIterator(reader, StateCensusCSV.class);
                while (StateCensusCSVIterator.hasNext()) {
                    StateCensusCSV csvStatesCensus = StateCensusCSVIterator.next();
                    this.StateCensusCSVMap.put(csvStatesCensus.state, csvStatesCensus);
                    StateCensusCSVList = StateCensusCSVMap.values().stream().collect(Collectors.toList());
                }
                int numberOfRecords = StateCensusCSVMap.size();
                return numberOfRecords;
            } catch (NoSuchFileException e) {
                throw new CSVBuilderException("Given File Not Found ",CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
            } catch (RuntimeException e){
                throw new CSVBuilderException("Check Delimiters Or Headers",CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
            } catch (Exception e) {
            e.getStackTrace();
        }
        return 0;
        }
    //METHOD TO LOAD RECORDS OF STATE CODE
    public int loadData() throws CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<StateDataCSV> StateCensusCSVIterator = csvBuilder.getCSVFileIterator(reader, StateDataCSV.class);
            while (StateCensusCSVIterator.hasNext()) {
                StateDataCSV StateDataCSV = StateCensusCSVIterator.next();
                this.StateDataCSVMap.put(StateDataCSV.StateCode, StateDataCSV);
                StateDataCSVList = StateDataCSVMap.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = StateDataCSVMap.size();
            return numberOfRecords;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("Given File Not Found ", CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("Check Delimiters Or Headers", CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String SortedCensusData() throws CSVBuilderException {
        if (StateCensusCSVList == null || StateCensusCSVList.size() == 0) {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfExceptionThrown.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        Comparator<StateCensusCSV> comparator = Comparator.comparing(StateCensusCSV -> StateCensusCSV.state);
        this.sortData(comparator, StateCensusCSVList);
        String sortedStateCensusJson = new Gson().toJson(StateCensusCSVList);
        return sortedStateCensusJson;
        }

    public String SortedStateCodeData() throws CSVBuilderException {
        if (StateDataCSVList == null || StateDataCSVList.size() == 0) {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfExceptionThrown.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        Comparator<StateDataCSV> comparator = Comparator.comparing(StateDataCSV -> StateDataCSV.StateCode);
        this.sortData(comparator, StateDataCSVList);
        String sortedStateCodeJson = new Gson().toJson(StateDataCSVList);
        return sortedStateCodeJson;
    }

    private<E> void sortData(Comparator<E> csvComparator, List<E> list)
    {
        for (int i = 0; i < list.size() - 1; i++)
        {
            for (int j = 0; j < list.size() - i - 1; j++)
            {
                E census1 = list.get(j);
                E census2 = list.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    list.set(j, census2);
                    list.set(j + 1, census1);
                }
            }
        }
    }
}

