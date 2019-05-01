package com.example.musicae.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.musicae.R;

public class ReportActivity extends AppCompatActivity {

    private Button sendBtn;
    private TextInputLayout title, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        title = findViewById(R.id.title);
        body = findViewById(R.id.body);

        sendBtn = findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendReport(v);
            }
        });
    }

    public void pedir(View view) {

        String mensaje = getInfoResumen();

        TextView resumen = findViewById(R.id.resumenTV);
        resumen.setText(mensaje);

    }

    public String getInfoResumen(){
        String Title = title.getEditText().getText().toString();
        if( Title.matches("")) Title = "Title";

        String Body = body.getEditText().getText().toString();
        if( Body.matches("")) Title = "Body";

        return "Resumen\n-------\nTitle: " + Title + "\nBody " + Body;
    }


    public void sendReport(View view){

        String resumen = getInfoResumen();
        String asunto = "Report Musicae";

        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:")); // Para qe sólo apps de correo puedan gestionarlo
        i.putExtra(Intent.EXTRA_EMAIL, new String[] { "report@musicae.app" });
        i.putExtra(Intent.EXTRA_SUBJECT, asunto);
        i.putExtra(Intent.EXTRA_TEXT, resumen);
        // Sólo se lanzará el activity si hay apps disponibles en el sistema para ejecutarla.
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }
}
