/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author hermann
 */
public class FileManager
{

    /*
     abre um ficheiro excel de extensao xls
     */

    /**
     *
     * @param filename
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static HSSFWorkbook openXLS_File(String filename) throws FileNotFoundException, IOException
    {
        InputStream ExcelFileToRead = new FileInputStream(filename);
        return new HSSFWorkbook(ExcelFileToRead);
    }

    /**
     *
     * @param filename
     * @param sheetName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static HSSFSheet getSheetFromXLS_File(String filename, String sheetName) throws FileNotFoundException, IOException
    {
        HSSFWorkbook hSSFWorkbook = openXLS_File(filename);
        return hSSFWorkbook.getSheet(sheetName);
    }

    /**
     *
     * @param filename
     * @param sheetIndex
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static HSSFSheet getSheetFromXLS_File(String filename, int sheetIndex) throws FileNotFoundException, IOException
    {
        HSSFWorkbook hSSFWorkbook = openXLS_File(filename);
        return hSSFWorkbook.getSheetAt(sheetIndex);
    }

    /**
     *
     * @param filename
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static HSSFSheet getSheetFromXLS_File(String filename) throws FileNotFoundException, IOException
    {
        HSSFWorkbook hSSFWorkbook = openXLS_File(filename);
        return hSSFWorkbook.getSheetAt(0);
    }

    public static boolean isCellEmpty(final Cell cell)
    {
        // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) retorna celula null
        if (cell == null)
        {
            return true;
        }

        if (cell.getCellType() == CellType.BLANK)
        {
            return true;
        }

        return false;
    }

    /**
     * Copia a imagem anexada no diretório do servidor 
     * @param fileName
     * @param in 
     */
    public static void copiarImagens(String fileName, InputStream in)
    {
        try
        {
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.err.println( fileName + " file created!");
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

}
