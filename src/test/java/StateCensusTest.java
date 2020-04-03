import com.exception.CensusAnalyserCustomException;
import com.services.StateCensusAnalyser;
import com.services.StateDataCSVAnalyser;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class StateCensusTest {
    private static  String INPUT_CSV_FILE_PATH = "src/test/resources/StateCensusDataCopy.csv" ;
    public static String CSV_FILE_PATH = "src/test/resources/StateCode.csv";

    // TC 1.1 READ CSV FILE IF NUMBER OF RECORDS MATCHES
    @Test
    public void givenStateCensusCSV_WhenConditionTrue_ReturnNumberOfRecordMatch() throws CensusAnalyserCustomException {
        INPUT_CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        StateCensusAnalyser censusAnalyser=new StateCensusAnalyser(INPUT_CSV_FILE_PATH);
        int noOfRecords=censusAnalyser.loadData();
        Assert.assertEquals(29,noOfRecords);
    }
    // TC 1.2 IF FILENAME INCORRECT THEN THROW CUSTOM EXCEPTION
    @Test
    public void givenStateCensusCSV_WhenImproperFileName_ReturnsException() throws IOException{
        INPUT_CSV_FILE_PATH = "src/test/resources/StateCensus.jpg";
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(INPUT_CSV_FILE_PATH);
            censusAnalyser.loadData();
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION,e.typeOfException);
            System.out.println("Wrong File Given");
        }
    }
    // TC 1.3 IF FILE CORRECT BUT TYPE IS NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusCSV_WhenImproperFileNameExtension_ReturnsException() throws IOException{
        INPUT_CSV_FILE_PATH = "src/test/resources/StateCensusData.jpg";
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(INPUT_CSV_FILE_PATH);
            censusAnalyser.loadData();
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION,e.typeOfException);
            System.out.println("Wrong File Given");
        }
    }
    // TC 1.4 IF DELIMITER OF FILE IS NOT MATCHES THEN RETURN INCORRECT DELIMITER EXCEPTION
    @Test
    public void givenStateCensusAnalyserFile_WhenIncorrectDelimiters_ReturnsException()  {
        INPUT_CSV_FILE_PATH = "src/test/resources/stateCensusData.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(INPUT_CSV_FILE_PATH);
        try {
            censusAnalyser.loadData();
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
            System.out.println("Wrong Delimiter File Given");
        }
    }
    // TC 1.5 IF HEADER OF FILE IS NOT MATCHES THEN THROWS DELIMITER HEADER INCORRECT EXCEPTION
    @Test
    public void givenStateCensusAnalyserFile_WhenIncorrectHeader_ReturnsException() {
        INPUT_CSV_FILE_PATH = "src/test/resources/StateCensusDataCopy.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(INPUT_CSV_FILE_PATH);
        try {
            censusAnalyser.loadData();
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION, e.typeOfException);
            System.out.println("Delimiter Correct But CSV Header Incorrect");
        }
    }
    // TC 2.1 READ ALL RECORDS OR DATA FROM STATECODE.CSV FILE
    @Test
    public void givenStateCodeWhenTrue_NumberOfRecordMatch() throws CensusAnalyserCustomException {
        CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        StateDataCSVAnalyser csvStates = new StateDataCSVAnalyser(CSV_FILE_PATH);
        int noOfRecords = csvStates.LoadStateCodeCSVData();
        Assert.assertEquals(37, noOfRecords);
    }
    // TC 2.2 IF GIVEN INPUT FILE IS INCORRECT THEN THROW FILE NOT FOUND EXCEPTION
    @Test
    public void givenStateCodeWhenFalse_ReturnExceptionFileNotFount()  {
        CSV_FILE_PATH = "src/test/resources/StateCodeDataCSV.csv";
        StateDataCSVAnalyser csvStates = new StateDataCSVAnalyser(CSV_FILE_PATH);
        try {
            csvStates.LoadStateCodeCSVData();
        } catch (CensusAnalyserCustomException e)
        {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
            System.out.println("File Given as Input, Is Not Found ");
        }
    }
    //TC 2.3 IF FILE CORRECT BUT TYPE IS NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCode_WhenImproperFileType_ReturnException(){
        CSV_FILE_PATH = "/src/test/resources/StateCode.jpg";
        StateDataCSVAnalyser csvStates = new StateDataCSVAnalyser(CSV_FILE_PATH);
        try {
            csvStates.LoadStateCodeCSVData();
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
            System.out.println("File With Wrong Type Given As Input ");
        }
    }
    // TC 2.4
    @Test // handle delimiter exception
    public void givenStateCode_WhenImproperDelimiter_ReturnException(){
        CSV_FILE_PATH = "src/test/resources/StateCodeCopy.csv";
        StateDataCSVAnalyser csvStates = new StateDataCSVAnalyser(CSV_FILE_PATH);
        try {
            csvStates.LoadStateCodeCSVData();
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION, e.typeOfException);
            System.out.println("File With Wrong Delimiter Given As Input ");
        }
    }

}
