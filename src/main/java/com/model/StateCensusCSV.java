package com.model;

import com.opencsv.bean.CsvBindByName;

// StateCensusCSV POJO class
public class StateCensusCSV {

    //Variables to Bind with Header of CSV

    @CsvBindByName(column = "State")
    public String state;

    @CsvBindByName(column = "Population")
    public int Population;

    @CsvBindByName(column = "AreaInSqKm")
    public int AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    public int DensityPerSqKm;
}
