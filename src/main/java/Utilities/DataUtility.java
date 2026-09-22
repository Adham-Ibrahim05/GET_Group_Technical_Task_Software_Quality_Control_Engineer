package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Properties;


public class DataUtility {

    public static final String TestJsonData_Path = "src/test/resources/";
    public static final String TestData_Path = "src/test/resources/";

    //TODO: Read from json file method
    public static String GetJsonData(String jsonFilename, String field) {
        try {
            FileReader reader = new FileReader(TestJsonData_Path + jsonFilename + ".json");
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    //TODO: get Input Stream method
    private static FileInputStream getInputStream(String filename){

        String fileName = (TestData_Path + filename + ".xlsx");
        FileInputStream file = null;
        File srcfile = new File(fileName);
        try {
            file = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    //TODO: Read from Excel file method
    public static Object[][] GetExcelDataFromFile(String excelfilename , String sheetname){

        FileInputStream file = getInputStream(excelfilename);
        String[][] strArray;
        try {
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheet(sheetname);

            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = sheet.getRow(0).getLastCellNum();

            strArray = new String[noOfRows][noOfCols];

            for (int i = 0; i < noOfRows; i++) {

                for (int j = 0; j < noOfCols; j++) {
                    XSSFRow row = sheet.getRow(i);
                    strArray[i][j] = row.getCell(j).toString();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return strArray;
    }

    //TODO: Read from properties file method
    public static String GetPropertiesDataFromFile(String PropertiesFilename, String key) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(TestData_Path + PropertiesFilename + ".properties"));
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //TODO: Read Write Data Into Json File method
    public static void WriteDataIntoJsonFile(String filename,String key,String value){

        JSONObject obj = new JSONObject();
        obj.put(key,value);

        try {
            FileWriter file = new FileWriter(TestData_Path + filename + ".json");
            file.write(obj.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
