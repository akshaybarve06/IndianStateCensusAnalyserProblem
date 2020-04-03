import com.exception.CensusAnalyserCustomException;
import com.services.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusTest {

    StateCensusAnalyser censusAnalyserObject=new StateCensusAnalyser();

    //**************************** FOR INDIAN STATE CENSUS ***********************************

    // TC-1 READ CSV FILE IF NUMBER OF RECORDS MATCHES
    @Test
    public void givenStateCensusCSV_WhenConditionTrue_ReturnNumberOfRecordMatch() throws Exception {
        Integer noOfRecords = censusAnalyserObject.readFile("src/test/resources/StateCensusData.csv");
        Assert.assertEquals((Integer) 29, noOfRecords);
    }

    // TC-2 IF FILENAME INCORRECT THEN THROW CUSTOM EXCEPTION
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFile_ReturnsException() throws Exception {
        try {
            censusAnalyserObject.readFile("src/test/resources/StateCensus.csv");
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }

    // TC-3 IF FILE CORRECT BUT TYPE IS NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() throws Exception {
        try {
            censusAnalyserObject.readFile("src/test/resources/StateCensusData.jpg");
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }

    // TC-4 IF FILE DELIMITERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusCSV_WhenImproperFileNameDelimiter_ReturnsException() throws Exception {
        try {
            censusAnalyserObject.readFile("src/test/resources/StateCensusDataCopy.csv");
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }

    // TC-5 IF FILE HEADERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusCSV_WhenImproperFileHeaders_ReturnsException() throws Exception {
        try {
            censusAnalyserObject.readFile("src/test/resources/StateCensusDataCopy2.csv");
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }
    //**************************** FOR INDIAN STATE CODE ***********************************

    // TC-1 READ CSV FILE IF NUMBER OF RECORDS MATCHES
    @Test
    public void givenStateCode_WhenTrue_NumberOfRecordShouldMatch() throws Exception {
        Integer result = censusAnalyserObject.loadIndianStateCodeData("src/test/resources/StateCode.csv");
        Assert.assertEquals((Integer) 37, result);
    }

    // TC-2 IF FILENAME INCORRECT THEN THROW CUSTOM EXCEPTION
    @Test
    public void givenStateCodeAnalyserFile_WhenImproperFile_ReturnsException() throws Exception {
        try {
            censusAnalyserObject.loadIndianStateCodeData("src/test/resources/StateCodeData.csv");
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }

    // TC-3 IF FILE CORRECT BUT TYPE IS NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeAnalyserFile_WhenImproperFileName_ReturnsException() throws Exception {
        try {
            censusAnalyserObject.loadIndianStateCodeData("src/test/resources/StateCode.jpg");
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }

    // TC-4 IF FILE DELIMITERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeCSV_WhenImproperFileNameDelimiter_ReturnsException() throws Exception {
        try {
            censusAnalyserObject.loadIndianStateCodeData("src/test/resources/StateCodeCopy.csv");
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }

    // TC-5 IF FILE HEADERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeCSV_WhenImproperFileHeaders_ReturnsException() throws Exception {
        try {
            censusAnalyserObject.loadIndianStateCodeData("src/test/resources/StateCodeCopy2.csv");
        } catch (CensusAnalyserCustomException e) {
            Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }
}
