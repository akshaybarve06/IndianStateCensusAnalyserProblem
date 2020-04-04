package com.services;

import com.exception.CSVBuilderException;
import com.model.StateCensusCSV;
import com.model.StateDataCSV;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
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
    public Integer loadIndianStateCodeData (String csvFilePath) throws CSVBuilderException {
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
}

