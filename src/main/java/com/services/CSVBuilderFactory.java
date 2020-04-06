package com.services;

public class CSVBuilderFactory {
    public static OpenCSV createCSVBuilder(){
        return new OpenCSV();
    }
}