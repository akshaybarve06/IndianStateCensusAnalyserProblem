package com.services;

import com.DAO.CensusDAO;
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

    List<CensusDAO> list = null;
    Map<String, CensusDAO> map = null;

    OpenCSV openCSV = new OpenCSV();

    public StateCensusAnalyser() {
        this.map = new HashMap<>();
    }
    //METHOD TO LOAD RECORDS OF CSV FILE
    public int loadRecords(String path) throws CSVBuilderException
    {
        int numberOfRecords = 0;
        String extension = path.substring(path.lastIndexOf(".") + 1);
        if (!extension.equals("csv")) {
            throw new CSVBuilderException("Given File Not Found ",CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(path)))
        {
            OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<StateCensusCSV> StateCensusCSVIterator = csvBuilder.getCSVFileIterator(reader, StateCensusCSV.class);
            while (StateCensusCSVIterator.hasNext()) {
                StateCensusCSV stateCensusCSV=StateCensusCSVIterator.next();
                CensusDAO censusDAO = new CensusDAO(StateCensusCSVIterator.next());
                this.map.put(censusDAO.State, censusDAO);
                list = map.values().stream().collect(Collectors.toList());
            }
            numberOfRecords = map.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("Given File Not Found ",CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        } catch (RuntimeException e){
            throw new CSVBuilderException("Check Delimiters Or Headers",CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        } catch (Exception e) {
        e.getStackTrace();
    }
    return numberOfRecords;
    }
    //METHOD TO LOAD RECORDS OF STATE CODE
    public int loadData(String path) throws CSVBuilderException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(path)))
        {
            OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<StateDataCSV> StateCensusCSVIterator = csvBuilder.getCSVFileIterator(reader, StateDataCSV.class);
            while (StateCensusCSVIterator.hasNext()) {
                StateDataCSV stateDataCSV = StateCensusCSVIterator.next();
                CensusDAO censusDAO=map.get(stateDataCSV.StateName);
                this.map.put(censusDAO.StateCode, censusDAO);
                list = map.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = map.size();
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
    public String SortedStateCensusData() throws CSVBuilderException
    {
        if (list == null || list.size() == 0)
        {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfExceptionThrown.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        Comparator<CensusDAO> comparator = Comparator.comparing(CensusDAO -> CensusDAO.Population);
        this.sortData(comparator);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
        }

    public String sortedPopulationWiseData() throws CSVBuilderException
    {
        if (list == null || list.size() == 0)
        {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfExceptionThrown.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(CensusDAO -> CensusDAO.Population);
        this.sortData(censusComparator);
        Collections.reverse(list);
        String sortedStateCodeJson = new Gson().toJson(list);
        return sortedStateCodeJson;
    }

    private void sortData(Comparator<CensusDAO> csvComparator)
    {
        for (int i = 0; i < list.size() - 1; i++)
        {
            for (int j = 0; j < list.size() - i - 1; j++)
            {
                CensusDAO census1 = list.get(j);
                CensusDAO census2 = list.get(j + 1);
                if (csvComparator.compare(census1,census2) > 0) {
                    list.set(j, census2);
                    list.set(j + 1, census1);
                }
            }
        }
    }
}

