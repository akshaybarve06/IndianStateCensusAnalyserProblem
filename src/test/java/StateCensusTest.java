import com.DAO.CensusDAO;
import com.exception.StateCensusException;

import com.google.gson.Gson;
import com.DTO.StateCensusCSV;
import com.services.CensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import static com.services.CensusAnalyser.Country.INDIA;
import static com.services.CensusAnalyser.Country.US;

public class StateCensusTest
{
    final String INDIAN_STATE_CENSUS_CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    final String INDIAN_STATE_CENSUS_INCORRECT_CSV_FILE_PATH = "src/test/resources/stateCensus.csv";
    final String INDIAN_STATE_CENSUS_INCORRECT_TYPE_CSV_FILE_PATH = "src/test/resources/StateCensus.jpg";
    final String INDIAN_STATE_CENSUS_INCORRECT_DELIMITER_CSV_FILE_PATH = "src/test/resources/StateCensusDataCopy.csv";
    final String INDIAN_STATE_CENSUS_INCORRECT_HEADER_CSV_FILE_PATH = "src/test/resources/StateCensusDataCopy2.csv";

    final String INDIAN_STATE_CODE_CSV_FILE_PATH = "src/test/resources/StateCode.csv";
    final String INDIAN_STATE_CODE_INCORRECT_CSV_FILE_PATH = "src/test/resources/StateCodeData.csv";
    final String INDIAN_STATE_CODE_INCORRECT_TYPE_CSV_FILE_PATH = "src/test/resources/StateCode.jpg";
    final String INDIAN_STATE_CODE_INCORRECT_DELIMITER_CSV_FILE_PATH = "src/test/resources/StateCodeCopy.csv";
    final String INDIAN_STATE_CODE_INCORRECT_HEADER_CSV_FILE_PATH = "src/test/resources/StateCodeCopy2.csv";

    final String US_STATE_CENSUS_CSV_FILE_PATH = "src/test/resources/USCensusData.csv";


    CensusAnalyser indianCensusAnalyzer = new CensusAnalyser( INDIA);
    CensusAnalyser usCensusAnalyzer = new CensusAnalyser(US);

    // **************************** FOR INDIAN STATE CENSUS ***********************************
    // TC-1 READ CSV FILE IF NUMBER OF RECORDS MATCHES
    @Test
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue()
    {
        try {
            int numberOfRecords = indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        }catch(StateCensusException e){
            e.printStackTrace();
        }
    }
    // TC-2 IF FILENAME INCORRECT THEN THROW CUSTOM EXCEPTION
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFile_ShouldReturnsException()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CENSUS_INCORRECT_CSV_FILE_PATH);
        } catch (StateCensusException e) {
            Assert.assertEquals(StateCensusException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }
    // TC-3 IF FILE CORRECT BUT TYPE IS NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ShouldReturnsException()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CENSUS_INCORRECT_TYPE_CSV_FILE_PATH);
        } catch (StateCensusException e) {
            Assert.assertEquals(StateCensusException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }
    // TC-4 IF FILE DELIMITERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusCSV_WhenImproperFileNameDelimiter_ShouldReturnsException()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CENSUS_INCORRECT_DELIMITER_CSV_FILE_PATH);
        } catch (StateCensusException e) {
            Assert.assertEquals(StateCensusException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }
    // TC-5 IF FILE HEADERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCensusCSV_WhenImproperFileHeaders_ShouldReturnsException()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CENSUS_INCORRECT_HEADER_CSV_FILE_PATH);
        } catch (StateCensusException e) {
            Assert.assertEquals(StateCensusException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }
    // **************************** FOR INDIAN STATE CODE ***********************************
    // TC-1 READ CSV FILE IF NUMBER OF RECORDS MATCHES
    @Test
    public void givenStateCode_WhenTrue_NumberOfRecord_ShouldMatch()
    {
        try {
            int numberOfRecords = indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch (StateCensusException e) {
            e.printStackTrace();
        }
    }
    // TC-2 IF FILENAME INCORRECT THEN THROW CUSTOM EXCEPTION
    @Test
    public void givenStateCodeAnalyserFile_WhenImproperFile_ShouldReturnsException()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CODE_INCORRECT_CSV_FILE_PATH);
        } catch (StateCensusException e) {
            Assert.assertEquals(StateCensusException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }
    // TC-3 IF FILE CORRECT BUT TYPE IS NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeAnalyserFile_WhenImproperFileName_ShouldReturnsException()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CODE_INCORRECT_TYPE_CSV_FILE_PATH);
        } catch (StateCensusException e) {
            Assert.assertEquals(StateCensusException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION, e.typeOfException);
        }
    }
    // TC-4 IF FILE DELIMITERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeCSV_WhenImproperFileNameDelimiter_ShouldReturnsException()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CODE_INCORRECT_DELIMITER_CSV_FILE_PATH);
        } catch (StateCensusException e) {
            Assert.assertEquals(StateCensusException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }
    // TC-5 IF FILE HEADERS ARE NOT PROPER THEN THROW EXCEPTION
    @Test
    public void givenStateCodeCSV_WhenImproperFileHeaders_ShouldReturnsException()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CODE_INCORRECT_HEADER_CSV_FILE_PATH);
        } catch (StateCensusException e) {
            Assert.assertEquals(StateCensusException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION,e.typeOfException);
        }
    }
    // ************************************ INDIAN DATA SORT BY ****************************************
    // Test Case For Sorting Data By State Name
    @Test
    public void givenIndianStateCensusData_WhenSorted_ShouldReturnSortedResult()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CENSUS_CSV_FILE_PATH);
            String SortedData = indianCensusAnalyzer.sortedStateCensusData(CensusAnalyser.SortingMode.STATENAME);
            StateCensusCSV[] censusCSV = new Gson().fromJson(SortedData, StateCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].StateName);
        }
        catch (StateCensusException e){
            e.printStackTrace();
        }
    }
    // Test Case For Sorting Data By Population
    @Test
    public void givenStateCensusData_WhenSortByPopulation_ShouldReturnSortedResult()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = indianCensusAnalyzer.sortedStateCensusData(CensusAnalyser.SortingMode.POPULATION);
            StateCensusCSV[] stateCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusCSV[].class);
            Assert.assertEquals(199812341, stateCensusCSV[0].Population);
        } catch ( StateCensusException e) {
            e.printStackTrace();
        }
    }
    // Test Case For Sorting Data By Density
    @Test
    public void givenStateCensusData_WhenSortByDensity_ShouldReturnSortedResult()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = indianCensusAnalyzer.sortedStateCensusData(CensusAnalyser.SortingMode.DENSITY);
            StateCensusCSV[] stateCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusCSV[].class);
            Assert.assertEquals(1102, stateCensusCSV[0].DensityPerSqKm);
        } catch ( StateCensusException e) {
            e.printStackTrace();
        }
    }
    // Test Case For Sorting Data By Area
    @Test
    public void givenStateCensusData_WhenSortByArea_ShouldReturnSortedResult()
    {
        try {
            indianCensusAnalyzer.loadStateCensusCSVData(INDIA, INDIAN_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = indianCensusAnalyzer.sortedStateCensusData(CensusAnalyser.SortingMode.AREA);
            StateCensusCSV[] stateCensusCSV = new Gson().fromJson(sortedCensusData, StateCensusCSV[].class);
            Assert.assertEquals(342239, stateCensusCSV[0].AreaInSqKm);
        } catch ( StateCensusException e) {
            e.printStackTrace();
        }
    }
    // ************************************ US DATA SORT BY ****************************************
    // Test Case For Load Records From USCensusCSV Data
    @Test
    public void givenUSCensusData_WhenTrue_RecordShouldMatch() {
        try {
            int numberOfRecords = usCensusAnalyzer.loadStateCensusCSVData(US, US_STATE_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numberOfRecords);
        } catch (StateCensusException e) {
            e.printStackTrace();
        }
    }
    // Test Case For Sort The Data of USCensusCSV By Population
    @Test
    public void givenUSCensusData_WhenSortedByPopulation_ShouldReturnSortedResult()
    {
        try {
            usCensusAnalyzer.loadStateCensusCSVData(US, US_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = usCensusAnalyzer.sortedDataPopulationWise();
            CensusDAO[] censusDAO = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("California",censusDAO[0].StateName);
        } catch ( StateCensusException e) {
            e.printStackTrace();
        }
    }
    // Test Case For Sort The Data of USCensusCSV By PopulationDensity
    @Test
    public void givenTheUSCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult()
    {
        try {
            usCensusAnalyzer.loadStateCensusCSVData(US, US_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = usCensusAnalyzer.sortedStateCensusData(CensusAnalyser.SortingMode.DENSITY);
            CensusDAO[] censusDAO = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("District of Columbia",censusDAO[0].StateName);
        } catch (StateCensusException e) {
            e.printStackTrace();
        }
    }
    // Test Case For Sort The Data of USCensusCSV By Area
    @Test
    public void givenTheUSCensusData_WhenSortedOnArea_ShouldReturnSortedResult()
    {
        try {
            usCensusAnalyzer.loadStateCensusCSVData(US, US_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = usCensusAnalyzer.sortedStateCensusData(CensusAnalyser.SortingMode.AREA);
            CensusDAO[] censusDAO = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Alaska",censusDAO[0].StateName);
        } catch (StateCensusException e) {
            e.printStackTrace();
        }
    }
}