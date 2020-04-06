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


    List<CensusDAO> list = null;
    Map<String, CensusDAO> map = null;

    //CONSTRUCTOR
    public StateCensusAnalyser() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
    }


    //METHOD TO LOAD RECORDS OF CSV FILE

    public int loadRecords(String path) throws CSVBuilderException {
        int numberOfRecords = 0;
        String extension = path.substring(path.lastIndexOf(".") + 1);
        if (!extension.equals("csv")) {
            throw new CSVBuilderException("Given File Not Found ",CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        }
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path)))
        {
            OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<StateCensusCSV> stateCensusCSVIterator = csvBuilder.getCSVFileIterator(reader, StateCensusCSV.class);
            while (stateCensusCSVIterator.hasNext()) {
                CensusDAO censusDAO = new CensusDAO(stateCensusCSVIterator.next());
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

    public int loadData(String path) throws CSVBuilderException {
        int numberOfRecords = 0;
        String extension = path.substring(path.lastIndexOf(".") + 1);
        if (!extension.equals("csv")) {
            throw new CSVBuilderException("Given File Not Found ",CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        }
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path))
        ) {
            OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<StateDataCSV> stateDataCSVIterator = csvBuilder.getCSVFileIterator(reader, StateDataCSV.class);
            while (stateDataCSVIterator.hasNext()) {
                StateDataCSV stateDataCSV = stateDataCSVIterator.next();
                CensusDAO censusDAO = map.get(stateDataCSV.StateName);
                if (censusDAO == null)
                    continue;
                censusDAO.StateCode = stateDataCSV.StateCode;
            }
            numberOfRecords = map.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("Given File Not Found ", CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("Check Delimiters Or Headers", CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfRecords;
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

    public String sortedDataPopulationWise() throws CSVBuilderException
    {
        if (list == null || list.size() == 0)
        {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfExceptionThrown.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(CensusDAO -> CensusDAO.Population);
        this.sortData(censusComparator);
        Collections.reverse(list);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

    //METHOD FOR SORTING STATE CENSUS DATA CSV FILE BY DENSITY WISE
    public String sortedDataDensityWise() throws CSVBuilderException {
        if (list == null || list.size() == 0) {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfExceptionThrown.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.DensityPerSqkm);
        this.sortData(censusComparator);
        Collections.reverse(list);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }
    //METHOD FOR SORTING STATE CENSUS DATA CSV FILE BY AREA WISE
    public String sortedDataAreaWise() throws CSVBuilderException {
        if (list == null || list.size() == 0) {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfExceptionThrown.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.AreaInSqKm);
        this.sortData(censusComparator);
        Collections.reverse(list);
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
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

