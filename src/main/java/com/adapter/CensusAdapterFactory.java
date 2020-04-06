package com.adapter;

import com.DAO.CensusDAO;
import com.exception.CSVBuilderException;
import com.services.CensusAnalyser;

import java.util.Map;

public class CensusAdapterFactory {
    public static Map<String, CensusDAO> getCensusData(CensusAnalyser.Country country, String[] csvFilePath) throws CSVBuilderException {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new IndianCensusAdapter().loadCensusData(csvFilePath);
        else if (country.equals(CensusAnalyser.Country.US))
            return new USCensusAdapter().loadCensusData(csvFilePath);
        else
            throw new CSVBuilderException( "You Have Entered Invalid country", CSVBuilderException.TypeOfExceptionThrown.INVALID_COUNTRY_EXCEPTION);
    }
}