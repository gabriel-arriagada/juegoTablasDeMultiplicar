package com.example.gabo.myapplication;

import java.util.Random;

/**
 * Created by gabo on 31-01-16.
 */
public class ResultadoFalso {

    private Random random;

    public ResultadoFalso(){
        random = new Random();
    }

    public String resultadoFalso(int resultado, int tabla){
        String resultadoFalso = null;
        int resultadoRandom = resultado;

        while(resultadoRandom == resultado){
            resultadoRandom = random.nextInt(tabla*10);
        }

        resultadoFalso = String.valueOf(resultadoRandom);
        return resultadoFalso;
    }
}
