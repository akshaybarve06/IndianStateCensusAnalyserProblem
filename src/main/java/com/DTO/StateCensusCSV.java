package com.DTO;

import com.opencsv.bean.CsvBindByName;

public class StateCensusCSV {

    //Variables to Bind with Header of CSV

    @CsvBindByName(column = "StateName", required=true)
    public String StateName;

    @CsvBindByName(column = "Population", required=true)
    public long Population;

    @CsvBindByName(column = "AreaInSqKm", required=true)
    public long AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required=true)
    public long DensityPerSqKm;

    public StateCensusCSV(String name, long population, long area, long density )
    {
        StateName=name;
        Population=population;
        AreaInSqKm=area;
        DensityPerSqKm= density;
    }
    @Override
    public String toString()
    {
        return "StateCensusCSV Data { StateName :"+StateName + "State Population : " +Population + ",State AreaInSqKm : " +AreaInSqKm + ",State DensityPerSqKm : " +DensityPerSqKm + "}";

    }
}
