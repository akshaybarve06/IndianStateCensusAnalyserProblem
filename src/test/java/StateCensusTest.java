import com.exception.CSVBuilderException;
import com.google.gson.Gson;
import com.model.StateCensusCSV;
import com.model.StateDataCSV;
import com.services.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusTest {

    //**************************** FOR INDIAN STATE CENSUS ***********************************

    // TC-1 READ CSV FILE IF NUMBER OF RECORDS MATCHES
    StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser( );

    @Test
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue(){
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser( );
            int numberOfRecords = stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        }catch(CSVBuilderException e){
        }
    }

    // TC-2 IF FILENAME INCORRECT THEN THROW CUSTOM EXCEPTION
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFile_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/stateCensus.csv";
        try {
           stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }
    // TC-3 IF FILE CORRECT BUT TYPE IS NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException()  {
        final String CSV_FILE_PATH = "src/test/resources/StateCensus.jpg";
        try {
           stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }
    // TC-4 IF FILE DELIMITERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusCSV_WhenImproperFileNameDelimiter_ReturnsException()  {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusDataCopy.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }
    // TC-5 IF FILE HEADERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusCSV_WhenImproperFileHeaders_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusDataCopy2.csv";
        try {
                   stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }

    //**************************** FOR INDIAN STATE CODE ***********************************

    // TC-1 READ CSV FILE IF NUMBER OF RECORDS MATCHES
    @Test
    public void givenStateCode_WhenTrue_NumberOfRecordShouldMatch() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            int numberOfRecords = stateCensusAnalyzer.loadData(CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch (CSVBuilderException e) {
        }
    }

    // TC-2 IF FILENAME INCORRECT THEN THROW CUSTOM EXCEPTION
    @Test
    public void givenStateCodeAnalyserFile_WhenImproperFile_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCodeData.csv";
        try {
           stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }

    // TC-3 IF FILE CORRECT BUT TYPE IS NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeAnalyserFile_WhenImproperFileName_ReturnsException() {
         final String CSV_FILE_PATH = "src/test/resources/StateCode.jpg";
        try {
           stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }

    // TC-4 IF FILE DELIMITERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeCSV_WhenImproperFileNameDelimiter_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCodeCopy.csv";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }

    // TC-5 IF FILE HEADERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeCSV_WhenImproperFileHeaders_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCodeCopy2.csv";
        try {
                   stateCensusAnalyzer.loadData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSorted_ReturnSortedResult(){
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
            String SortedData = stateCensusAnalyzer.SortedStateCensusData();
            StateCensusCSV[] censusCSV = new Gson().fromJson(SortedData, StateCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        }
        catch (CSVBuilderException e){
        }
    }
    @Test
    public void givenStateCodeData_WhenSorted_ShouldReturnSortedList(){
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            stateCensusAnalyzer.loadData(CSV_FILE_PATH);
            String SortedData = stateCensusAnalyzer.SortedStateCensusData();
            StateDataCSV[] StateCodes = new Gson().fromJson(SortedData, StateDataCSV[].class);
            Assert.assertEquals("AD", StateCodes[0].StateCode);
        }catch(CSVBuilderException e){
        }
    }
    @Test
    public void givenStateCensusData_WhenSortByPopulation_ReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.sortedDataPopulationWise();
            StateCensusCSV[] stateCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusCSV[].class);
            Assert.assertEquals(199812341, stateCensusCSV[0].Population);
        } catch ( CSVBuilderException e) {
            e.getStackTrace();
        }
    }
    @Test
    public void givenStateCensusData_WhenSortByDensity_ReturnSortedResult() throws CSVBuilderException{
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            stateCensusAnalyzer.loadRecords(CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.sortedDataDensityWise();
            StateCensusCSV[] stateCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusCSV[].class);
            Assert.assertEquals(1102, stateCensusCSV[0].DensityPerSqKm);
        } catch ( CSVBuilderException e) {
            e.getStackTrace();
        }
    }
}
