package com.model;

import com.opencsv.bean.CsvBindByName;

    public class StateCensusCSV {

        //BINDING THE COLUMN NAME IN CsvBindByName CLASS
        @CsvBindByName(column = "State")
        private String state;

        @CsvBindByName(column = "Population")
        private String population;

        @CsvBindByName(column = "AreaInSqKm")
        private String AreaInSqKm;

        @CsvBindByName(column = "DensityPerSqKm")
        private String DensityPerSqKm;

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
