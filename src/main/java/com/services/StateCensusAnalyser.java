package com.services;

import com.exception.CSVBuilderException;
import com.google.gson.Gson;
import com.model.StateCensusCSV;
import com.model.StateDataCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

//Combined Both StateCensus and StateCode Classes Into One
public class StateCensusAnalyser  {

    OpenCSV openCSV = new OpenCSV();
    // Method For Reading Data From STATECENSUS File

    public Integer readFile(String filePath) throws Exception{
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath)) )
        {
            List<StateCensusCSV> listCSVfile = openCSV.getCSVFileList(reader,StateCensusCSV.class);
            return listCSVfile.size();
        }  catch (NoSuchFileException e) {
            throw new CSVBuilderException("FIVEN FILE IS NOT FOUND..",CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        }
        catch (RuntimeException e){
            throw new CSVBuilderException("DELIMITER OR HEADER INCORRECT..",CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        }catch (Exception e) {
            e.getStackTrace();
        }
        return (null);
    }

    // Method For Load data from STATECODECSV file
    public Integer loadIndianStateCodeData (String csvFilePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)) )
        {
            List<StateDataCSV> listCSVfile = openCSV.getCSVFileList(reader,StateDataCSV.class);
            return listCSVfile.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("ENTERED FILE IS NOT FOUND..",CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        } catch (RuntimeException e){
            throw new CSVBuilderException("DELIMITER OR HEADER INCORRECT..",CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        }catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    // Sorting the StateCensus Data
    public String getStateWiseSortedData(String path) throws CSVBuilderException{
        try (Reader reader = Files.newBufferedReader(Paths.get(path))
        ) {
            List<StateCensusCSV> listCSVfile = (List<StateCensusCSV>) openCSV.getCSVFileIterator(reader,StateCensusCSV.class);
            Comparator<StateCensusCSV> censusComparator = Comparator.comparing(Census -> Census.getState());
            this.sort(listCSVfile, censusComparator);
            String sortedStateCensusjson = new Gson().toJson(listCSVfile);
            return sortedStateCensusjson;

        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("ENTERED FILE IS NOT FOUND..",CSVBuilderException.TypeOfExceptionThrown.FILE_NOT_FOUND_EXCEPTION);
        } catch (RuntimeException e){
            throw new CSVBuilderException("DELIMITER OR HEADER INCORRECT..",CSVBuilderException.TypeOfExceptionThrown.DELIMITER_HEADER_INCORRECT_EXCEPTION);
        } catch ( IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sort(List<StateCensusCSV> censusList, Comparator<StateCensusCSV> censusComparator) {
        for (int i = 0; i < censusList.size()-1; i++)
        {
            for(int j = 0; j < censusList.size()-i-1; j++)
            {
                StateCensusCSV census1 = censusList.get(j);
                StateCensusCSV census2= censusList.get(j+1);
                if(censusComparator.compare(census1,census2) > 0)
                {
                    censusList.set(j, census2);
                    censusList.set(j+1, census1);
                }
            }
        }
    }
}

