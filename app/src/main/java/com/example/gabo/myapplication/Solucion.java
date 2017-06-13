package com.example.gabo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Solucion extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private Contexto contexto;
    private Button button;
    private int [] numerosDesordenados;
    private int [] botonesDesordenados;
    private LinearLayout linearLayout;
    private TextView pregunta;
    private TextView oportunidad;
    private TextView tiempo;
    private int tabla;
    private int colorId;
    private int indiceTabla;
    private int resultado;
    private Button[] arrayButtons;
    private ResultadoFalso resultadoFalso;
    ActualizarPregunta actualizarPregunta;
    MediaPlayer mediaPlayer;
    LinearLayout botones;
    private int oportunidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solucion);

        Intent intent = getIntent();
        tabla = intent.getIntExtra("tabla", 0);
        colorId = intent.getIntExtra("color", 0);
        getSupportActionBar().setTitle("Tabla del " + tabla);
        linearLayout = (LinearLayout)findViewById(R.id.layoutBase);
        linearLayout.setBackgroundColor(colorId);
        pregunta = (TextView)findViewById(R.id.pregunta);
        oportunidad = (TextView)findViewById(R.id.oportunidades);
        tiempo = (TextView)findViewById(R.id.tiempo);
        botones = (LinearLayout)findViewById(R.id.botones);
        resultadoFalso = new ResultadoFalso();

        //llenar le array con numeros aleatorios
        numerosDesordenados = new int[12];
        contexto = new Contexto(new EvitarCero());
        contexto.ejecutarAlgoritmo(numerosDesordenados, 13);

        resultado = 0;
        indiceTabla = 0;
        oportunidades = 2;

        arrayButtons = new Button[4];
        arrayButtons[0] = (Button)findViewById(R.id.button11);
        arrayButtons[1] = (Button)findViewById(R.id.button12);
        arrayButtons[2] = (Button)findViewById(R.id.button13);
        arrayButtons[3] = (Button)findViewById(R.id.button14);


        countDownTimer = new CountDownTimer(11000, 1000) {

            public void onTick(long millisUntilFinished) {
                tiempo.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                tiempo.setText("");
                finalizar("Tiempo!");
            }

        }.start();


        actualizarPregunta = new ActualizarPregunta();
        actualizarPregunta.execute();

    }

    public void mensaje(String mensaje){
        Context context = getApplicationContext();
        CharSequence text = mensaje;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    public void clickActualizar(View view){

        CharSequence textoBoton = "";
        final int id = view.getId();

        switch (id) {
            case R.id.button11:
                button = (Button)findViewById(R.id.button11);
                textoBoton = button.getText();
                break;
            case R.id.button12:
                button = (Button)findViewById(R.id.button12);
                textoBoton = button.getText();
                break;
            case R.id.button13:
                button = (Button)findViewById(R.id.button13);
                textoBoton = button.getText();
                break;
            case R.id.button14:
                button = (Button)findViewById(R.id.button14);
                textoBoton = button.getText();
                break;
        }

        if(Integer.parseInt(String.valueOf(textoBoton)) == resultado) {

            mediaPlayer = MediaPlayer.create(Solucion.this, R.raw.correcto);
            mediaPlayer.start();

            actualizarPregunta = new ActualizarPregunta();
            actualizarPregunta.execute();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            });

            countDownTimer.cancel();
            countDownTimer.start();

        }else{
            //mensaje("¡Respuesta incorrecta!");
            oportunidades--;
            oportunidad.setText("Quedan "+oportunidades+" oport.");
            if(oportunidades == 0){
                finalizar("Perdiste!");
                countDownTimer.cancel();
                tiempo.setText("");
            }
        }


    }


    class ActualizarPregunta extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params) {

            if(indiceTabla <= 11){

                botonesDesordenados = new int[4];
                contexto = new Contexto(new PermitirCero());
                contexto.ejecutarAlgoritmo(botonesDesordenados, 4);

                resultado = tabla*numerosDesordenados[indiceTabla];

                publishProgress("actualizar",
                        tabla + "x" + numerosDesordenados[indiceTabla],
                        resultadoFalso.resultadoFalso(resultado, tabla),
                        resultadoFalso.resultadoFalso(resultado, tabla),
                        resultadoFalso.resultadoFalso(resultado, tabla),
                        String.valueOf(resultado));

                indiceTabla = indiceTabla + 1;
            }else{
                publishProgress("finalizar");
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(values[0].equals("actualizar")){
                pregunta.setText(values[1]);
                arrayButtons[botonesDesordenados[0]].setText(values[2]);
                arrayButtons[botonesDesordenados[1]].setText(values[3]);
                arrayButtons[botonesDesordenados[2]].setText(values[4]);
                arrayButtons[botonesDesordenados[3]].setText(values[5]);
            }else{
                finalizar("Ganaste!");
                countDownTimer.cancel();
                tiempo.setText("");
            }
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(String s) {
            botonesDesordenados = null;
            super.onPostExecute(s);
        }
    }

    public void finalizar(String mensaje){
        pregunta.setTextSize(50);
        pregunta.setText(mensaje);
        animar(false);
        botones.setVisibility(View.GONE);
    }


    public void animar(boolean mostrar)
    {
        AnimationSet set = new AnimationSet(true);
        Animation animation = null;
        if (mostrar)
        {
            //desde la esquina inferior derecha a la superior izquierda
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        }
        else
        {    //desde la esquina superior izquierda a la esquina inferior derecha
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        }
        //duración en milisegundos
        animation.setDuration(500);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);

        botones.setLayoutAnimation(controller);
        botones.startAnimation(animation);
    }

}

