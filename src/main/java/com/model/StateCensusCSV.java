package com.model;

import com.opencsv.bean.CsvBindByName;

// StateCensusCSV POJO class
public class StateCensusCSV {

    //Default Constructor
    public StateCensusCSV(){
    }
    //Parameterized Constructor
    public StateCensusCSV(String state, String population, String areaInSqKm, String densityPerSqKm) {
        this.state = state;
        this.population = population;
        AreaInSqKm = areaInSqKm;
        DensityPerSqKm = densityPerSqKm;
    }

    //Variables to Bind with Header of CSV
    @CsvBindByName(column = "State")
    private String state;

    @CsvBindByName(column = "Population")
    private String population;

    @CsvBindByName(column = "AreaInSqKm")
    private String AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    private String DensityPerSqKm;

    // Getters and Setters
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getPopulation() {
        return population;
    }
    public void setPopulation(String population) {
        this.population = population;
    }
    public String getAreaInSqKm() {
        return AreaInSqKm;
    }
    public void setAreaInSqKm(String areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }
    public String getDensityPerSqKm() {
        return DensityPerSqKm;
    }
    public void setDensityPerSqKm(String densityPerSqKm) {
        DensityPerSqKm = densityPerSqKm;
    }
}
