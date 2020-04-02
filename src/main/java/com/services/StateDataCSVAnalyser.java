package com.services;

import com.model.StateDataCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateDataCSVAnalyser {
        private static String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        int noOfRecords = 0;
        public StateDataCSVAnalyser(String Path) {
            CSV_FILE_PATH = Path;
        }

        public int LoadStateCodeCSVData() throws IOException {
            try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));) {
                CsvToBean<StateDataCSV> csvStateCensuses = new CsvToBeanBuilder(reader)
                        .withType(StateDataCSV.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                Iterator<StateDataCSV> csvStatesIterator = csvStateCensuses.iterator();
                while (csvStatesIterator.hasNext()) {
                    StateDataCSV csvStates = csvStatesIterator.next();
                    System.out.println("SrNo :" + csvStates.getSrNo());
                    System.out.println("StateName :" + csvStates.getStateName());
                    System.out.println("TIN :" + csvStates.getTIN());
                    System.out.println("StateCode :" + csvStates.getStateCode());
                    System.out.println("--------------------------");
                    noOfRecords++;

                }
            }
            return noOfRecords;
        }
    }

