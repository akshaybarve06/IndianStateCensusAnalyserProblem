package com.DTO;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV
{
        @CsvBindByName(column = "State Id" )
        public String StateID;

        @CsvBindByName(column = "StateName", required=true)
        public String StateName;

        @CsvBindByName(column = "Population Density", required=true)
        public long PopulationDensity;

        @CsvBindByName(column = "Population", required=true)
        public long Population;

        @CsvBindByName(column = "Total area", required=true)
        public long Area;

        public USCensusCSV(String stateId, String state, long population, long totalArea, long populationDensity)
        {
                this.StateID = stateId;
                this.StateName = state;
                this.Population = population;
                this.Area = totalArea;
                this.PopulationDensity = populationDensity;
        }
        @Override
        public String toString() {
                return "USCensusCSV{ StateID= "+ StateID + "State=" + StateName + "Population=" + Population + ", Area=" + Area + ", PopulationDensity=" + PopulationDensity + '}';
        }
}
