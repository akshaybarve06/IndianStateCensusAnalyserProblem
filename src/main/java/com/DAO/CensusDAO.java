package com.DAO;

import com.model.StateCensusCSV;
import com.model.StateDataCSV;
import com.model.USCensusCSV;

public class CensusDAO {
    public String StateID;
    public String StateName;
    public long Population;
    public long AreaInSqKm;
    public int DensityPerSqKm;
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
}
