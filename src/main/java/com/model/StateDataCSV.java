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

        public StateDataCSV(int no, String name, String code, int tin){
            SrNo=no;
            StateName=name;
            StateCode=code;
            TIN=tin;
        }
        public String toString(){
            return "StateCodeCSV { " + "State Number :" +SrNo + ", State Name : " +StateName + ", State Code : " +StateCode + ", TIN : " +TIN + "}";
        }
}
