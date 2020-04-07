package com.DTO;

import com.opencsv.bean.CsvBindByName;

// StateCensusCSV POJO class
public class StateCensusCSV {

    //Variables to Bind with Header of CSV

    @CsvBindByName(column = "StateName")
    public String StateName;

    @CsvBindByName(column = "Population")
    public long Population;

    @CsvBindByName(column = "AreaInSqKm")
    public long AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    public long DensityPerSqKm;

    public StateCensusCSV(){
    }

    public StateCensusCSV(String name, long population, long area, long density ){
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
