package com.DAO;

import com.DTO.StateCensusCSV;
import com.DTO.StateDataCSV;
import com.DTO.USCensusCSV;
import com.services.CensusAnalyser;

public class CensusDAO {
    public String StateID;
    public String StateName;
    public long Population;
    public long AreaInSqKm;
    public long DensityPerSqKm;
    public String StateCode;
    public float HousingDensity;
    public int TIN;
    public int SrNo;

    public CensusDAO(StateCensusCSV stateCensusCSV) {
        this.StateName = stateCensusCSV.StateName;
        this.Population = stateCensusCSV.Population;
        this.AreaInSqKm = stateCensusCSV. AreaInSqKm;
        this.DensityPerSqKm = stateCensusCSV. DensityPerSqKm;
    }
    public CensusDAO(StateDataCSV stateDataCSV) {
        this.StateName = stateDataCSV.StateName;
        this.SrNo = stateDataCSV.SrNo;
        this.TIN = stateDataCSV.TIN;
        this.StateCode = stateDataCSV.StateCode;
    }
    public CensusDAO(USCensusCSV usCensusCSV){
        this.StateID = usCensusCSV.StateID;
        this.StateName = usCensusCSV.StateName;
        this.Population = usCensusCSV.Population;
        this.AreaInSqKm= usCensusCSV.Area;
        this.DensityPerSqKm = usCensusCSV.PopulationDensity;
        this.HousingDensity = usCensusCSV.HousingDensity;
    }
    public long getPopulation() {
        return Population;
    }

    public void setPopulation(long population) {
        Population = population;
    }

    public long getAreaInSqKm() {
        return AreaInSqKm;
    }

    public void setAreaInSqKm(long areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }

    public long getDensityPerSqkm() {
        return DensityPerSqKm;
    }

    public void setDensityPerSqKm(long densityPerSqkm) {
        DensityPerSqKm = densityPerSqkm;
    }

    public Object getCensusDTO(CensusAnalyser.Country country) {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new StateCensusCSV(StateName, Population, AreaInSqKm, DensityPerSqKm);
        if (country.equals(CensusAnalyser.Country.US))
            return new USCensusCSV(StateCode, StateName, Population, AreaInSqKm, DensityPerSqKm);
        return null;
    }
}
