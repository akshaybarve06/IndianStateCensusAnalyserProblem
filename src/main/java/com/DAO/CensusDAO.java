package com.DAO;

import com.DTO.StateCensusCSV;
import com.DTO.StateDataCSV;
import com.DTO.USCensusCSV;
import com.services.CensusAnalyser;

import java.util.Comparator;

public class CensusDAO {
    public String StateID;
    public String StateName;
    public long Population;
    public long AreaInSqKm;
    public long DensityPerSqKm;
    public String StateCode;
    public int TIN;
    public int SrNo;

    public CensusDAO(StateCensusCSV stateCensusCSV)
    {
        this.StateName = stateCensusCSV.StateName;
        this.Population = stateCensusCSV.Population;
        this.AreaInSqKm = stateCensusCSV. AreaInSqKm;
        this.DensityPerSqKm = stateCensusCSV. DensityPerSqKm;
    }
    public CensusDAO(StateDataCSV stateDataCSV)
    {
        this.SrNo = stateDataCSV.SrNo;
        this.StateName = stateDataCSV.StateName;
        this.TIN = stateDataCSV.TIN;
        this.StateCode = stateDataCSV.StateCode;
    }
    public CensusDAO(USCensusCSV usCensusCSV)
    {
        this.StateID = usCensusCSV.StateID;
        this.StateName = usCensusCSV.StateName;
        this.Population = usCensusCSV.Population;
        this.AreaInSqKm = usCensusCSV.Area;
        this.DensityPerSqKm = usCensusCSV.PopulationDensity;
    }
    public static Comparator<CensusDAO> getSortComparator(CensusAnalyser.SortingMode mode)
    {
        if (mode.equals(CensusAnalyser.SortingMode.STATENAME))
            return Comparator.comparing(census -> census.StateName);
        if (mode.equals(CensusAnalyser.SortingMode.POPULATION))
            return Comparator.comparing(CensusDAO::getPopulation).reversed();
        if (mode.equals(CensusAnalyser.SortingMode.AREA))
            return Comparator.comparing(CensusDAO::getAreaInSqKm).reversed();
        if (mode.equals(CensusAnalyser.SortingMode.DENSITY))
            return Comparator.comparing(CensusDAO::getDensityPerSqkm).reversed();
        return null;
    }

    public long getPopulation() {
        return Population;
    }

    public long getAreaInSqKm() {
        return AreaInSqKm;
    }

    public long getDensityPerSqkm() {
        return DensityPerSqKm;
    }
    public Object getCensusDTO(CensusAnalyser.Country country)
    {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new StateCensusCSV(StateName, Population, AreaInSqKm, DensityPerSqKm);
        if (country.equals(CensusAnalyser.Country.US))
            return new USCensusCSV(StateName,StateCode, Population, AreaInSqKm, DensityPerSqKm);
        return null;
    }
}
