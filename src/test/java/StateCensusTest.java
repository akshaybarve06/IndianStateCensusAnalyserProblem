import com.exception.CensusAnalyserCustomException;
import com.services.StateCensusAnalyser;

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class StateCensusTest {
    private static  String INPUT_CSV_FILE_PATH = "src/test/resources/StateCensusDataCopy.csv" ;
    @Test
    public void givenStateCensusCSV_WhenConditionTrue_ReturnNumberOfRecordMatch() throws IOException, CensusAnalyserCustomException {
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
    public void givenStateCensusAnalyserFile_WhenIncorrectDelimiters_ReturnsException() throws IOException {
        INPUT_CSV_FILE_PATH = "src/test/resources/StateCensusDataCopy.csv";
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(INPUT_CSV_FILE_PATH);
        try {
            censusAnalyser.loadData();
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_INCORRECT_EXCEPTION,e.typeOfException);
            System.out.println("Wrong Delimiter File Given");
        }
    }

}
