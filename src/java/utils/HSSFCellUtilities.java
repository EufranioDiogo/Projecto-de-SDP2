/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author adilson
 * @author aires
 */
public class HSSFCellUtilities
{

    /**
     *
     * @param cell
     * @return
     */
    public static boolean isCellEmpty(final HSSFCell cell)
    {
        // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) retorna celula null
        if (cell == null)
        {
//System.err.println("0: HSSFCellUtilities.isCellEmpty()");		
            return true;
        }
//System.err.println("1: HSSFCellUtilities.isCellEmpty()");
        if (cell.getCellType() == CellType.BLANK)
        {
//System.err.println("2: HSSFCellUtilities.isCellEmpty()");			
            return true;
        }

        boolean rt = cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty();
//System.err.println("3: HSSFCellUtilities.isCellEmpty()\trt: " + rt);		
		return rt;
    }

    public static String getStringCellValue(final HSSFCell cell)
    {
        if (cell == null)
        {
            return null;
        }
        CellType type = cell.getCellType();
        // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) retorna celula null

        if (null != type)
        {
            switch (type)
            {
                case BLANK:
                    return null;
                case STRING:
                    String str = cell.getStringCellValue();
                    if (str == null || str.length() == 0 || str.trim().isEmpty())
                    {
                        return null;
                    }
                    return str;
                case FORMULA:
                case NUMERIC:
                    int tmpPk;
                    try
                    {
                        tmpPk = (int) cell.getNumericCellValue();
                    }
                    catch (Exception e)
                    {
                        return null;
                    }
                    return ("" + tmpPk).trim();
                default:
                    break;
            }
        }
        return null;
    }

    private static Integer getIntegerValue(final HSSFCell cell)
    {
        Integer tmpPk;

        //LogFile.warnMsg(null, "0: HSSFCellUtilities.getIntegerValue()");
        String str = cell.getStringCellValue();
        if (str == null || str.length() == 0 || str.trim().isEmpty())
        {
            //LogFile.warnMsg(null, "1: HSSFCellUtilities.getIntegerValue()");
            return null;
        }
        try
        {
            //LogFile.warnMsg(null, "3: HSSFCellUtilities.getIntegerValue()");
            tmpPk = Integer.parseInt(str);
            return tmpPk;
        }
        catch (NumberFormatException ex)
        {
            //LogFile.warnMsg(null, "4: HSSFCellUtilities.getIntegerValue()");
            return null;
        }
    }

    public static Double getDoubleCellValue(final HSSFCell cell)
    {
        String str;
        //LogFile.warnMsg(null, "0: HSSFCellUtilities.getIntCellValue()");
        if (cell == null)
        {
            //LogFile.warnMsg(null, "1: HSSFCellUtilities.getIntCellValue()");
            return null;
        }
        double tmpPk;
        CellType type = cell.getCellType();
        // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) retorna celula null
        //LogFile.warnMsg(null, "2: HSSFCellUtilities.getIntCellValue()\ttype: " + HSSFCellUtilities.cellTypeToString(cell));
        switch (type)
        {
                //LogFile.warnMsg(null, "3: HSSFCellUtilities.getIntCellValue()");
                
            case FORMULA:
            case NUMERIC:
                //LogFile.warnMsg(null, "4: HSSFCellUtilities.getIntCellValue()");
                try
                {
                    return cell.getNumericCellValue();
                    //LogFile.warnMsg(null, "5: HSSFCellUtilities.getIntCellValue()");
                   
                }
                catch (Exception e)
                {
                    //LogFile.warnMsg(null, "6: HSSFCellUtilities.getIntCellValue()");
                    return null;
                }
            default:
                //LogFile.warnMsg(null, "7: HSSFCellUtilities.getIntCellValue()");
                return null;
        }
    }

    public static Integer getIntCellValue(final HSSFCell cell)
    {
        String str;
        //LogFile.warnMsg(null, "0: HSSFCellUtilities.getIntCellValue()");
        if (cell == null)
        {
            //LogFile.warnMsg(null, "1: HSSFCellUtilities.getIntCellValue()");
            return null;
        }
        int tmpPk;
        CellType type = cell.getCellType();
        // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) retorna celula null
        //LogFile.warnMsg(null, "2: HSSFCellUtilities.getIntCellValue()\ttype: " + HSSFCellUtilities.cellTypeToString(cell));
        switch (type)
        {
            case BLANK:
                //LogFile.warnMsg(null, "3: HSSFCellUtilities.getIntCellValue()");
                return null;
            case FORMULA:
            case NUMERIC:
                //LogFile.warnMsg(null, "4: HSSFCellUtilities.getIntCellValue()");
                try
                {
                    tmpPk = (int) cell.getNumericCellValue();
                    //LogFile.warnMsg(null, "5: HSSFCellUtilities.getIntCellValue()");
                    return tmpPk;
                }
                catch (Exception e)
                {
                    //LogFile.warnMsg(null, "6: HSSFCellUtilities.getIntCellValue()");
                    return null;
                }
            case STRING:
            default:
                //LogFile.warnMsg(null, "7: HSSFCellUtilities.getIntCellValue()");
                return getIntegerValue(cell);
        }
    }

    public static String cellTypeToString(final HSSFCell cell)
    {
        if (cell == null)
        {
            return null;
        }

        switch (cell.getCellType())
        {
            case _NONE:
                return "NONE";
            case NUMERIC:
                return "NUMERIC";
            case STRING:
                return "STRING";
            case FORMULA:
                return "FORMULA";
            case BLANK:
                return "BLANK";
            case BOOLEAN:
                return "BOOLEAN";
            case ERROR:
                return "ERROR";
            default:
                return "STRANGE_TYPE";
        }
    }

}
