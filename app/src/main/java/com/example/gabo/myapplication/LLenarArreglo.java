package com.example.gabo.myapplication;

import java.util.Random;

/**
 * Created by gabo on 01-02-16.
 */
public abstract class LLenarArreglo {

    protected Random random;

    public LLenarArreglo(){
        random = new Random();
    }

    abstract void llenarArreglo(int[] arreglo, int maximoRandom);

}
