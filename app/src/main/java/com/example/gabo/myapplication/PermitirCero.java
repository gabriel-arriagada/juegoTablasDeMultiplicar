package com.example.gabo.myapplication;

import android.util.Log;

/**
 * Created by gabo on 01-02-16.
 */
public class PermitirCero extends LLenarArreglo {

    @Override
    void llenarArreglo(int[] arreglo, int maximoRandom)
    {
        convertirArreglo(arreglo);

        int numero;
        int indice = 0;
        boolean repetido = false;

        for(int i = 0; i < arreglo.length; i++)
        {
            numero = random.nextInt(maximoRandom);

            //Log.w("RANDOM",numero + " ");

            while (indice < arreglo.length)
            {
                //Log.d("NUMERO",arreglo[indice] + " ");

                if(numero == arreglo[indice])
                {//Log.e("REPETIDO",numero+"");
                    repetido = true;
                    break;
                }
                indice ++;

            }

            if(!repetido)
            {
                //Log.w("AGREGO",numero + " ");
                arreglo[i] = numero;
            }
            else
            {
                i--;
                repetido = false;
            }
            indice = 0;
        }
    }

    public void convertirArreglo(int [] arreglo){
        int indice = 0;
        while(indice < arreglo.length){
            arreglo[indice] = -1;
            indice++;
        }
    }

}
