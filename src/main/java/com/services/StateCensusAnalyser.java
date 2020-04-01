package com.services;

import com.model.StateCensusCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.Iterator;
import static java.nio.file.Files.newBufferedReader;

    public class StateCensusAnalyser {
        public static void main(String args[]){
            System.out.println("Welcome To Indian States Census Analyser Problem");
        }
        public static final String CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
        int noOfRecords=0;
        public int loadData() throws IOException{
            try(Reader reader = newBufferedReader(Paths.get(CSV_FILE_PATH)); ){
                CsvToBean<StateCensusCSV> csvStateCensusBeanObj = new CsvToBeanBuilder(reader)
                        .withType(StateCensusCSV.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                Iterator<StateCensusCSV> stateCensusCSVIterator = csvStateCensusBeanObj.iterator();
                while (stateCensusCSVIterator.hasNext()) {
                    StateCensusCSV stateCensusCSV = stateCensusCSVIterator.next();
                    System.out.println("State: " + stateCensusCSV.getState());
                    System.out.println("Area: " + stateCensusCSV.getAreaInSqKm());
                    System.out.println("Density: " + stateCensusCSV.getDensityPerSqKm());
                    System.out.println("Population: " + stateCensusCSV.getPopulation());
                    System.out.println("---------------------------------");
                    noOfRecords++;
                }
            }
            return noOfRecords;
        }
    }
