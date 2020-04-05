package com.model;

import com.opencsv.bean.CsvBindByName;
//StateDataCSV POJO Class
public class StateDataCSV {

    // Variable To Bind With CSV File Header
        @CsvBindByName(column = "SrNo")
        public int SrNo;

        @CsvBindByName(column = "StateName")
        public String StateName;

        @CsvBindByName(column = "StateCode")
        public String StateCode;

        @CsvBindByName(column = "TIN")
        public int TIN;
}
