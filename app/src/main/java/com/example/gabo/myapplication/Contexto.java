package com.example.gabo.myapplication;

/**
 * Created by gabo on 01-02-16.
 */
public class Contexto {

    private final LLenarArreglo strategy;

    public Contexto(LLenarArreglo strategy) {
        this.strategy = strategy;
    }

    public void ejecutarAlgoritmo(int [] arreglo, int maximoRandom){
        strategy.llenarArreglo(arreglo, maximoRandom);
    }

}
