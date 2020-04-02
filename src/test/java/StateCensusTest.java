import com.exception.CensusAnalyserCustomException;
import com.services.StateCensusAnalyser;

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class StateCensusTest {

    private static final String INPUT_CSV_FILE_PATH = "./src/test/resources/StateCensusData.jpg";

        @Test
        public void givenStateCensusCSV_WhenConditionTrue_ReturnNumberOfRecordMatch() throws IOException, CensusAnalyserCustomException {
            StateCensusAnalyser censusAnalyser=new StateCensusAnalyser(INPUT_CSV_FILE_PATH);
            int noOfRecords=censusAnalyser.loadData();
            Assert.assertEquals(29,noOfRecords);
        }
        @Test
        public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() throws IOException{
            try {
                StateCensusAnalyser censusAnalyser = new StateCensusAnalyser(INPUT_CSV_FILE_PATH);
                censusAnalyser.loadData();
            } catch (CensusAnalyserCustomException e) {
                Assert.assertEquals(CensusAnalyserCustomException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION,e.typeOfException);
                System.out.println("Wrong Input File");
            }
        }
}
