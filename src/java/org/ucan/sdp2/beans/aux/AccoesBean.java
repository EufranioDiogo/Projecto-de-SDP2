/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ucan.sdp2.beans.aux;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.sql.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author ed
 */
@Named()
@SessionScoped
public class AccoesBean implements Serializable {

    /**
     * Creates a new instance of AccoesBean
     */
    private String DB_URI = "jdbc:postgresql://localhost:5432/ucandb";
    private String USER_NAME = "postgres";
    private String USER_PASSWORD = "postgres";
    private String webRootPath;
    private String PORTFOLIO_NAME;
    private Connection connection = null;
    private FormulaEvaluator objFormulaEvaluator;
    private DataFormatter objDefaultFormat;

    public AccoesBean() {
    }

    
    public void see() {
        System.out.println("Entrou");
        try {
            loadPortfolio("Portfolio_EufranioDiogo_SDP2.xls");
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        
    }
    
    public void loadPortfolio(String portfolioName) {
        this.PORTFOLIO_NAME = this.getClass().getResource(portfolioName).getFile();
        try {
            this.connectToDatabase();
        } catch (Exception e) {
            Logger.getLogger(AccoesBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URI, USER_NAME, USER_PASSWORD);
            if (connection != null) {
                startupConfig();
            } else {
                goToErroPage();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccoesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void startupConfig() {
        try {
            String query = "TRUNCATE TABLE portfolio CASCADE";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            try {
                setupPortfolio();
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccoesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadPortfolio() {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URI, USER_NAME, USER_PASSWORD);
            if (connection != null) {
                System.out.println("Connexão estabelecida");
                startupConfig();
            } else {
                goToErroPage();
            }
        } catch (Exception e) {
            Logger.getLogger(AccoesBean.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void setupPortfolio() {
        File file = new File(PORTFOLIO_NAME);
        
        try {
            if (file.createNewFile()) {
                System.out.println("File Created");
            } else {
                System.out.println("File Exists");
            }
        } catch (IOException e) {
            System.out.println("IO Exception creating the file");
        }
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not founde");
        }
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        }
        objDefaultFormat = new DataFormatter();
        objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
        HSSFSheet workSheet = workbook.getSheetAt(0);
        HSSFRow row;
        Iterator<Row> rowIterator = workSheet.iterator();
        String previousItem = "";
        String actualItem = "";
        String designation = "";
        String splitCharacter = "\\.";
        String[] fatherList = new String[20];
        int actualFatherIndex = -1;
        Cell cell;
        while (rowIterator.hasNext()) {
            row = (HSSFRow) rowIterator.next();
            Iterator< Cell> cellIterator = row.cellIterator();
            boolean flag = false;
            int i = 0;
            while (i < 2 && cellIterator.hasNext()) {
                cell = cellIterator.next();
                objFormulaEvaluator.evaluate(cell); // This will evaluate the cell, And any type of cell will return string val-ue
                String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);
                //System.out.println("0 Home setupportfolio{}\tcellValueStr: " + cellValueStr);
                if (flag == false) {
                    actualItem = cellValueStr;
                } else {
                    designation = cellValueStr;
                }
                flag = !flag;
                i++;
            }
            System.out.println(actualItem + " - " + designation);
            boolean result = actualItem.length() == 1;
            if (result) {
                actualFatherIndex = -1;
                previousItem = actualItem;
                //System.out.println(previousItem);
                String query = "INSERT INTO portfolio(pk_portfolio, designacao) VALUES(?, ?);";
                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, actualItem);
                    statement.setString(2, designation);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("SQL EXCEPTION: " + e.toString());
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            } else {
                int actualSize = actualItem.split(splitCharacter).length;
                int previousSize = previousItem.split(splitCharacter).length;
                if (actualSize > previousSize) {
                    actualFatherIndex++;
                    fatherList[actualFatherIndex] = previousItem;
                } else if (actualSize < previousSize) {
                    int quantStepsBack = previousSize - actualSize;
                    actualFatherIndex -= quantStepsBack;
                }
                String query = "INSERT INTO portfolio(pk_portfolio, designacao, fk_portfolio) VALUES(?, ?, ?);";
                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, actualItem);
                    statement.setString(2, designation);
                    statement.setString(3, fatherList[actualFatherIndex]);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("SQL EXCEPTION: " + e.toString());
                    System.out.println(e.getMessage());
                }
                previousItem = actualItem;
            }
            System.out.println();
        }
        System.out.println("Saiu");
    }

    public void goToErroPage() {
        try {
            
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public int getQuantChar(String string, String character) {
        int i = 0;
        int quant = 0;
        for (; i < string.length(); i++) {
            if (string.charAt(i) + "" == character) {
                quant += 1;
            }
        }
        return quant;
    }

}
