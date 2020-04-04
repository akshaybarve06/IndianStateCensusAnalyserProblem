package com.model;

import com.opencsv.bean.CsvBindByName;

//StateDataCSV POJO  Class
public class StateDataCSV {

    // Variable To Bind With CSV File Header
    @CsvBindByName(column = "SrNo")
        private String SrNo;

        @CsvBindByName(column = "StateName")
        private String StateName;

        @CsvBindByName(column = "StateCode")
        private String StateCode;

        @CsvBindByName(column = "TIN")
        private String TIN;


    }
