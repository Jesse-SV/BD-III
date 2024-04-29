package com.example.danielmeuprojeto.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorData {
    //Static é usado para não ser necessário criar um objeto 
    public static String converterDateParaDataHora(Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return formatador.format(data);
    }
}
