/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Claudio
 */
public class ExcelGenerico
{

    public static HSSFSheet getHSSFSheet(String path, String sheetName)
    {
        InputStream excelFileToRead = null;
        try
        {
            excelFileToRead = new FileInputStream(path);
            HSSFWorkbook wb = new HSSFWorkbook(excelFileToRead);
            return wb.getSheet(sheetName);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Erro ao ler Arquivo " + ex.getMessage() + "  " + ex);
        }
        catch (IOException ex)
        {
            System.out.println("Erro ao ler Arquivo " + ex.getMessage() + "  " + ex);
        }
        return null;
    }

}
