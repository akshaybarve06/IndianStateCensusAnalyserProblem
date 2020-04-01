import com.services.StateCensusAnalyser;

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class StateCensusTest {
        @Test
        public void givenStateCensusCSV_WhenConditionTrue_ReturnNumberOfRecordMatch() throws IOException{
            StateCensusAnalyser censusAnalyser=new StateCensusAnalyser();
            int noOfRecords=censusAnalyser.loadData();
            Assert.assertEquals(29,noOfRecords);
        }
}
