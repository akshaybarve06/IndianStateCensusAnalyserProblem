package com.services;

import com.exception.CSVBuilderException;
import com.google.gson.Gson;
import com.model.StateCensusCSV;
import com.model.StateDataCSV;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

//Combined Both StateCensus and StateCode Classes Into One
public class StateCensusAnalyser <E>{
        //VARIABLES
        private String CSV_FILE_PATH;
        private final Class<E> csvClass;
        List<E> csvUserList = null;

        OpenCSV openCSV = new OpenCSV();

        public StateCensusAnalyser(String path, Class<E> csvClass) {
            this.CSV_FILE_PATH=path;
            this.csvClass = csvClass;
        }

    //METHOD TO LOAD RECORDS OF CSV FILE
        public int loadRecords() throws CSVBuilderException {
            try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
                OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
                csvUserList = csvBuilder.getCSVFileList(reader, csvClass);
                return csvUserList.size();
            } catch (NoSuchFileException e) {
                throw new CSVBuilderException(e.getMessage(),CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
            } catch (RuntimeException e){
                throw new CSVBuilderException(e.getMessage(),CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
            } catch (Exception e) {
            e.getStackTrace();
        }
        return 0;
    }
        public String SortedCensusData() {
            Comparator<StateCensusCSV> CSVComparator = Comparator.comparing(census -> census.state);
            this.sort((Comparator<E>) CSVComparator);
            String SortedCSVJson = new Gson().toJson(csvUserList);
            return SortedCSVJson;
        }

    public String SortedStateCodeData() {
        Comparator<StateDataCSV> CodeComparator = Comparator.comparing(code -> code.StateCode);
        this.sort((Comparator<E>) CodeComparator);
        String SortedCodeJson = new Gson().toJson(csvUserList);
        return SortedCodeJson;
    }

    private void sort(Comparator<E> csvComparator) {
        for (int i = 0; i < csvUserList.size() - 1; i++) {
            for (int j = 0; j < csvUserList.size() - i - 1; j++) {
                E census1 = csvUserList.get(j);
                E census2 = csvUserList.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    csvUserList.set(j, census2);
                    csvUserList.set(j + 1, census1);
                }
            }
        }
    }
}

