import com.exception.CSVBuilderException;
import com.google.gson.Gson;
import com.DTO.StateCensusCSV;
import com.DTO.StateDataCSV;
import com.DTO.USCensusCSV;
import com.services.CensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusTest {

    // **************************** FOR INDIAN STATE CENSUS ***********************************

    // TC-1 READ CSV FILE IF NUMBER OF RECORDS MATCHES
    CensusAnalyser censusAnalyzer = new CensusAnalyser( );

    @Test
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue(){
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            int numberOfRecords = censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        }catch(CSVBuilderException e){
        }
    }

    // TC-2 IF FILENAME INCORRECT THEN THROW CUSTOM EXCEPTION
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFile_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/stateCensus.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }
    // TC-3 IF FILE CORRECT BUT TYPE IS NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException()  {
        final String CSV_FILE_PATH = "src/test/resources/StateCensus.jpg";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }
    // TC-4 IF FILE DELIMITERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusCSV_WhenImproperFileNameDelimiter_ReturnsException()  {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusDataCopy.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }
    // TC-5 IF FILE HEADERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusCSV_WhenImproperFileHeaders_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusDataCopy2.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }

    // **************************** FOR INDIAN STATE CODE ***********************************

    // TC-1 READ CSV FILE IF NUMBER OF RECORDS MATCHES
    @Test
    public void givenStateCode_WhenTrue_NumberOfRecordShouldMatch() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            int numberOfRecords = censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch (CSVBuilderException e) {
        }
    }

    // TC-2 IF FILENAME INCORRECT THEN THROW CUSTOM EXCEPTION
    @Test
    public void givenStateCodeAnalyserFile_WhenImproperFile_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCodeData.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }

    // TC-3 IF FILE CORRECT BUT TYPE IS NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeAnalyserFile_WhenImproperFileName_ReturnsException() {
         final String CSV_FILE_PATH = "src/test/resources/StateCode.jpg";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }

    // TC-4 IF FILE DELIMITERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeCSV_WhenImproperFileNameDelimiter_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCodeCopy.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }

    // TC-5 IF FILE HEADERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeCSV_WhenImproperFileHeaders_ReturnsException() {
        final String CSV_FILE_PATH = "src/test/resources/StateCodeCopy2.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }
    // ************************************ SORTING ****************************************

    // Test Case For Sorting Data By State Name
    @Test
    public void givenIndianStateCensusData_WhenSorted_ReturnSortedResult(){
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String SortedData = censusAnalyzer.getSortedStateCensusData();
            StateCensusCSV[] censusCSV = new Gson().fromJson(SortedData, StateCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].StateName);
        }
        catch (CSVBuilderException e){
            e.getStackTrace();
        }
    }
    // Test Case For Sorting Data By StateCode
    @Test
    public void givenStateCodeData_WhenSorted_ShouldReturnSortedList(){
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String SortedData = censusAnalyzer.getSortedStateCensusData();
            StateDataCSV[] StateCodes = new Gson().fromJson(SortedData, StateDataCSV[].class);
            Assert.assertEquals("AD", StateCodes[0].StateCode);
        }catch(CSVBuilderException e){
            e.getStackTrace();
            System.out.println("sdbngfldnv");
        }
    }
    // Test Case For Sorting Data By Population
    @Test
    public void givenStateCensusData_WhenSortByPopulation_ReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.sortedDataPopulationWise();
            StateCensusCSV[] stateCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusCSV[].class);
            Assert.assertEquals(19981234, stateCensusCSV[0].Population);
        } catch ( CSVBuilderException e) {
            e.getStackTrace();
        }
    }
    // Test Case For Sorting Data By Density
    @Test
    public void givenStateCensusData_WhenSortByDensity_ReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.sortedDataDensityWise();
            StateCensusCSV[] stateCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusCSV[].class);
            Assert.assertEquals(1102, stateCensusCSV[0].DensityPerSqKm);
        } catch ( CSVBuilderException e) {
            e.getStackTrace();
        }
    }
    // Test Case For Sorting Data By Area
    @Test
    public void givenStateCensusData_WhenSortByArea_ReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.INDIA, CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.sortedDataAreaWise();
            StateCensusCSV[] stateCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusCSV[].class);
            Assert.assertEquals(342239, stateCensusCSV[0].AreaInSqKm);
        } catch ( CSVBuilderException e) {
            e.getStackTrace();
        }
    }
    // Test Case For Load Records From USCensusCSV Data
    @Test
    public void givenUSCensusData_WhenTrue_RecordShouldMatch() {
        final String CSV_FILE_PATH = "src/test/resources/USCensusData.csv";
        try {
            int noOfRecords = censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.US, CSV_FILE_PATH);
            Assert.assertEquals(52, noOfRecords);
        } catch (CSVBuilderException e) {
        }
    }
    // Test Case For Sort The Data of USCensusCSV By Population
    @Test
    public void givenUSCensusData_WhenSortedByPopulation_ReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/USCensusData.csv";
        try {
            censusAnalyzer.loadStateCensusCSVData(CensusAnalyser.Country.US, CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.sortedDataPopulationWise();
            USCensusCSV[] csvUsCensus = new Gson().fromJson(sortedCensusData,USCensusCSV[].class);
            Assert.assertEquals("California",csvUsCensus[0].StateName);
        } catch ( CSVBuilderException e) {
            e.printStackTrace();
        }
    }


}
