package com.model;

import com.opencsv.bean.CsvBindByName;

// StateCensusCSV POJO class
public class StateCensusCSV {

    //Variables to Bind with Header of CSV

    @CsvBindByName(column = "StateName")
    public String StateName;

    @CsvBindByName(column = "Population")
    public int Population;

    @CsvBindByName(column = "AreaInSqKm")
    public int AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    public int DensityPerSqKm;

    public StateCensusCSV(String name, int population, int area, int density ){
        StateName=name;
        Population=population;
        AreaInSqKm=area;
        DensityPerSqKm= density;
    }
    @Override
    public String toString(){
        return "StateCensusCSV Data { StateName :"+StateName + "State Population : " +Population + ",State AreaInSqKm : " +AreaInSqKm + ",State DensityPerSqKm : " +DensityPerSqKm + "}";

    }
}
