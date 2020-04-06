package com.adapter;

import com.DAO.CensusDAO;
import com.exception.CSVBuilderException;
import com.model.USCensusCSV;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CSVBuilderException {
        return super.loadCensusData(USCensusCSV.class, csvFilePath[0]);
    }
}