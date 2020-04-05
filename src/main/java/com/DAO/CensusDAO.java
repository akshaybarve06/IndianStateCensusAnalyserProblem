package com.DAO;

import com.model.StateCensusCSV;

public class CensusDAO {
    public String state;
    public int population;
    public int area;
    public int density;
    public String stateCode;

    public CensusDAO(StateCensusCSV stateCensusCSV) {
        this.state = stateCensusCSV.state;
        this.population = stateCensusCSV.population;
        this.area = stateCensusCSV. AreaInSqKm;
        this.density = stateCensusCSV. DensityPerSqKm;
    }
}
