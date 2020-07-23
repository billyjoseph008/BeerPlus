package com.brainstormideas.beer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String costo;
    Contador contador = new Contador();
    ImageView agregar_button;
    ImageButton pagar_button;
    ImageButton facebook_button;
    Button quitar_button;
    Button reiniciar_button;
    TextView contador_txt;
    TextView derechos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        agregar_button = (ImageView) findViewById(R.id.agregar_button);
        pagar_button = (ImageButton) findViewById(R.id.pagar_button);
        quitar_button = (Button) findViewById(R.id.quitar_button);
        reiniciar_button = (Button) findViewById(R.id.reiniciar_button);
        contador_txt = (TextView) findViewById(R.id.contador_txt);
        derechos = (TextView)findViewById(R.id.derechos);
        contador_txt.setText(R.string.salud);
        facebook_button = (ImageButton) findViewById(R.id.facebook_button);


        consultarCosto();

        facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/brainstormideascool/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        agregar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador.incrementar();
                contador_txt.setText(String.valueOf(contador.getCantidadCervezas()));
                if(contador.getCantidadCervezas()==6){
                    Toast.makeText(getApplicationContext(), R.string.recomendacion1, Toast.LENGTH_SHORT).show();
                }
                if(contador.getCantidadCervezas()==12){
                    Toast.makeText(getApplicationContext(), R.string.recomendacion2, Toast.LENGTH_SHORT).show();
                }
                if (contador.getCantidadCervezas()==18){
                    Toast.makeText(getApplicationContext(), R.string.recomendacion3, Toast.LENGTH_SHORT).show();
                }
                if(contador.getCantidadCervezas() == 24){
                    Toast.makeText(getApplicationContext(), R.string.recomendacion4, Toast.LENGTH_SHORT).show();
                }
            }
        });

        quitar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contador.getCantidadCervezas()>=1){
                    contador.decrementar();
                    contador_txt.setText(String.valueOf(contador.getCantidadCervezas()));
                }else{
                    contador_txt.setText(R.string.salud);
                }

            }
        });

        reiniciar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaReiniciar();
            }
        });

        pagar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDeuda();
            }
        });


    }
    private void consultaReiniciar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.comenzar_cuenta);
        builder.setMessage(R.string.pregunta_cuenta);

        builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                contador.reiniciar();
                contador_txt.setText(R.string.salud);
                consultarCosto();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        builder.show();
    }

    private void consultarCosto(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.costo_cerveza);

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setGravity(Gravity.CENTER);
        input.setMaxLines(1);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        builder.setView(input);


        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.length()==0){
                    Toast.makeText(getApplicationContext(), R.string.dato_vacio,Toast.LENGTH_SHORT).show();
                    consultarCosto();
                }else{

                    costo = input.getText().toString();
                    double precio = Double.valueOf(costo);
                    contador.setPrecioCervezas(precio);
                }

            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    private void mostrarDeuda(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pago);
        builder.setMessage(getResources().getString(R.string.deuda_actual) + contador.pago());
        builder.setNeutralButton(R.string.endendido, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
