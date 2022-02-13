/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author aires
 */
public class DataUtils
{

    public static String getDataActual()
    {
        String dataString = "";
        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        dataString += formatDate.format(data);
        return dataString;
    }

    public static Date strToDate(String data)
    {
        if (data == null)
        {
            return null;
        }

        Date dataF = null;
        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long timestamp = dateFormat.parse(data).getTime();
            dataF = new Date(timestamp);
        } catch (ParseException pe)
        {
            System.err.println("Erro ao converter String em data: " + pe.getLocalizedMessage());
        }
        return dataF;
    }

  /*  public static Date getDataActualFull()
    {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        
    }*/
    public static Date strToDateFull(String data)
    {
        if (data == null)
        {
            return null;
        }

        Date dataF = null;
        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            long timestamp = dateFormat.parse(data).getTime();
            dataF = new Date(timestamp);
        } catch (ParseException pe)
        {
            System.err.println("Erro ao converter String em data: " + pe.getLocalizedMessage());
        }
        return dataF;
    }

    public static String formataData(Date data)
    {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        calendar.setTime(data);
        return sdf.format(calendar.getTime());
    }

    public final static String[] prazoUnidades
        =
        {
            "Data (dia-mes-ano)", "dias", "semanas", "meses", "anos", "sem limite"
        };

    public static boolean isSemLimite(String st)
    {
        return st.equals(prazoUnidades[5]);
    }

    public static boolean isData(String st)
    {
        return st.equals(prazoUnidades[0]);
    }

    public static String[] getValidPrazoUnidades()
    {
        return new String[]
        {
            prazoUnidades[1],
            prazoUnidades[2],
            prazoUnidades[3],
            prazoUnidades[4]
        };
    }

    /**
     * Adiciona quantidade de dias na data.
     *
     * @param data
     * @param qtd
     * @return
     */
    public static Date addDias(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, qtd);
        return cal.getTime();
    }

    public static Date addSemanas(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.WEEK_OF_YEAR, qtd);
        return cal.getTime();
    }

    /**
     * Adiciona quantidade de meses na data.
     *
     * @param data
     * @param qtd
     * @return
     */
    public static Date addMeses(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MONTH, qtd);
        return cal.getTime();
    }

    /**
     * Adiciona quantidade de anos na data.
     *
     * @param data
     * @param qtd
     * @return
     */
    public static Date addAnos(Date data, int qtd)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.YEAR, qtd);
        return cal.getTime();
    }

    public static Date addDate(Date data, int dias, int meses, int anos)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        cal.add(Calendar.YEAR, anos);
        cal.add(Calendar.MONTH, meses);
        cal.add(Calendar.DAY_OF_MONTH, dias);

        return cal.getTime();
    }


    public static String toStringFull(Date d)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy HH:mm", new Locale("pt", "PT"));
        return sdf.format(d);
    }

    public static String toString(Date d)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy", new Locale("pt", "PT"));
        return sdf.format(d);
    }

    public static String dataTimeAgoraFull()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy HH:mm", new Locale("pt", "PT"));
        return sdf.format(new Date());
    }

    public static String dataTimeAgora()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy", new Locale("pt", "PT"));
        return sdf.format(new Date());
    }

    public static boolean isToday(Date d)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        int dia = c.get(Calendar.DATE);
        int mes = c.get(Calendar.MONTH) + 1;
        int ano = c.get(Calendar.YEAR);

        c.setTime(new Date());

        int diaHoje = c.get(Calendar.DATE);
        if (dia != diaHoje)
        {
            return false;
        }

        int mesHoje = c.get(Calendar.MONTH) + 1;
        if (mes != mesHoje)
        {
            return false;
        }

        int anoHoje = c.get(Calendar.YEAR);
        return ano == anoHoje;
    }
}
