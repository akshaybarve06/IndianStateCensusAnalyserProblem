import com.exception.CensusAnalyserCustomException;
import com.services.StateCensusAnalyser;
import com.services.StateDataCSVAnalyser;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class StateCensusTest {
    private static  String INPUT_CSV_FILE_PATH = "src/test/resources/StateCensusDataCopy.csv" ;
    public static String CSV_FILE_PATH = "src/test/resources/StateCode.csv";

    @Test
    public void givenStateCensusCSV_WhenConditionTrue_ReturnNumberOfRecordMatch() throws CensusAnalyserCustomException {
        INPUT_CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        StateCensusAnalyser censusAnalyser=new StateCensusAnalyser(INPUT_CSV_FILE_PATH);
        int noOfRecords=censusAnalyser.loadData();
        Assert.assertEquals(29,noOfRecords);
    }
    @Test
    public void givenStateCensusCSV_WhenImproperFileName_ReturnsException() throws IOException{
        INPUT_CSV_FILE_PATH = "src/test/resources/StateCensusData.jpg";
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(INPUT_CSV_FILE_PATH);
            censusAnalyser.loadData();
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION,e.typeOfException);
            System.out.println("Wrong File Given");
        }
    }
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
    @Test
    public void givenStateCodeWhenTrue_NumberOfRecordMatch() throws CensusAnalyserCustomException {
        CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        StateDataCSVAnalyser csvStates = new StateDataCSVAnalyser(CSV_FILE_PATH);
        int noOfRecords = csvStates.LoadStateCodeCSVData();
        Assert.assertEquals(37, noOfRecords);
    }
    @Test
    public void givenStateCodeWhenFalse_ReturnExceptionFileNotFount()  {
        CSV_FILE_PATH = "src/test/resources/StateCodeDataCSV.csv";
        StateDataCSVAnalyser csvStates = new StateDataCSVAnalyser(CSV_FILE_PATH);
        try{
            csvStates.LoadStateCodeCSVData();
        }catch (CensusAnalyserCustomException e){
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
            System.out.println("File Given as Input, Is Not Found ");
        }
    }
}
