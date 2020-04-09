package com.adapter;

import com.DAO.CensusDAO;
import com.exception.StateCensusException;
import com.services.CensusAnalyser;

import java.util.Map;

public class CensusAdapterFactory
{
    public static Map<String, CensusDAO> getCensusData(CensusAnalyser.Country country, String[] csvFilePath) throws StateCensusException
    {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new IndianCensusAdapter().loadCensusData(csvFilePath);
        else if (country.equals(CensusAnalyser.Country.US))
            return new USCensusAdapter().loadCensusData(csvFilePath);
        else
            throw new StateCensusException( "You Have Entered Invalid country", StateCensusException.TypeOfExceptionThrown.INVALID_COUNTRY_EXCEPTION);
    }
}