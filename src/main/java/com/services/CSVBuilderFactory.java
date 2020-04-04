package com.services;

public class CSVBuilderFactory {
    public static OpenCSV createCsvBuilder(){
        return new OpenCSV();
    }
}