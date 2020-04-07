package com.adapter;

import com.DAO.CensusDAO;
import com.exception.StateCensusException;
import com.DTO.USCensusCSV;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateCensusException {
        return super.loadCensusData(USCensusCSV.class, csvFilePath[0]);
    }
}