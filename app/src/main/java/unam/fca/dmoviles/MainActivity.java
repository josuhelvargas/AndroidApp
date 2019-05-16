package unam.fca.dmoviles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{

    private final static int IMAGE_WIDTH = 400;
    TextView textViewResult;
    Button  buttonResult;
    EditText editTextNumero1, editTextNumero2;
    double numero1 = 0;
    double numero2 = 0;
    double differencePercentage;
    double result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         buttonResult = (Button) findViewById(R.id.button);
         editTextNumero1 = (EditText) findViewById(R.id.editText);
         editTextNumero2 = (EditText) findViewById(R.id.editText2);
         textViewResult = (TextView) findViewById(R.id.textViewResult);


        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    numero1 = Float.parseFloat(editTextNumero1.getText().toString());
                    numero2 = Float.parseFloat(editTextNumero2.getText().toString());
                    result  =  calculateDifferenceAsPercentage(numero1,numero2);
                    textViewResult.setText(Double.toString(result));

                } catch (NullPointerException exception) {
                    String message = exception.getMessage();

                }
            }
        });
    }



    public double calculateDifferenceAsPercentage(double numero1, double numero2) { //it should be string?
        try{

            if(numero1 != 0 && numero2 != 0) {
                differencePercentage = (numero2 * 100.00)/numero1;
            }else{
               return 0;
            }
            return differencePercentage;
        }
        catch(ArithmeticException exception){
            exception.getMessage();
            return differencePercentage;
        }
    }





}





//Actividades
//a) Ejecuta la aplicacion y familiarizate con el layout definido en R.layout.activity_main
//b) Crea la logica para calcular la diferencia relativa en porcentaje entre dos numeros
//c) La formula para calcular la diferencia relativa en porcentaje esta en: https://www.calculatorsoup.com/calculators/algebra/percent-change-calculator.php
//d) La aplicacion debe calcular la diferencia relativa en porcentaje de los valores contenidos en "editText" y "editText2"
//e) Al hacer click en el boton "button", la aplicacion debera calcular dicha diferencia y poner el resultado en el campo "textViewResult"
//f) La aplicacion debera manejar adecuadamente el cambio de rotacion para el resultado y los campos de texto
//e) Anota tus respuestas y las capturas de pantalla en un documento en Word
//f) Sube tu codigo al repositorio.
//g) Sube el documento Word a la plataforma Moodle. Incluye la liga a tu repositorio