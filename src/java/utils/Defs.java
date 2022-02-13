/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.context.FacesContext;

/**
 *
 * @author hermann
 */
public class Defs
{

    public static final String FILES_EXCEL = "filesExcel" + "/" ;
    public static final String PATH_PORTIFOLIO = getPathWEB_INF(FILES_EXCEL + "portfolio.xls");
    
    public static String ROOT_PATH_LINUX;
    public static String ROOT_PATH_WINDOWS_PADRAO;
    private static String rootPathWindows;

    public static String CAMINHO_FILES_EXCEL;
    public static String CAMINHO_IMAGENS;
    public static String IMAGENS;
    public static String CAMINHO_PORTFOLIO;

    private static String operatingSystem = null;

    private static boolean jaInicializado = false;

    public static void init()
    {
        if (jaInicializado)
        {
            return;
        }

        ROOT_PATH_LINUX = "/";
        ROOT_PATH_WINDOWS_PADRAO = "C:\\";
        rootPathWindows = ROOT_PATH_WINDOWS_PADRAO;

        IMAGENS = "imagens";

        CAMINHO_IMAGENS = getPathIMAGENS() + pathSeparator();

        operatingSystem = null;

        jaInicializado = true;

    }
    
    
    public static final String getPathWEB_INF(String filename)
    {
        String relativePath = pathSeparator() + "WEB-INF" + pathSeparator() + filename;
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
    }

    public static final String getPathIMAGENS()
    {
        String relativePath = pathSeparator() + "imagens";
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
    }

    public static final String getOsName()
    {
        if (operatingSystem == null)
        {
            operatingSystem = System.getProperty("os.name");
        }
        return operatingSystem;
    }

    public static boolean isWindows()
    {
        return getOsName().startsWith("Windows");
    }

    public static boolean isLinux() // and so on
    {
        return getOsName().startsWith("Linux");
    }

    /*
        returns pathSeparator() for Linux e "/" for Windows
     */
    public static final String pathSeparator()
    {
        return isWindows() ? "\\" : "/";
    }
}
