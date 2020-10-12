package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;



public class MainActivity extends AppCompatActivity {



    private TextView textView;

    private Button buttonJson, button11,button22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT); // вертикальная



        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // do something
                downloadAndShowJson(textView);
            }
        };

       final Handler handler = new Handler();
        handler.postDelayed(runnable, 200);


        this.textView = (TextView) this.findViewById(R.id.textView);

        this.buttonJson = (Button) this.findViewById(R.id.button_json);

        this.button11 = (Button) this.findViewById(R.id.button);

        this.button22 = (Button) this.findViewById(R.id.button2);

        this.buttonJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadAndShowJson(v);
            }
        });

        this.button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.zg.com/exchange/737/CRP/USDT"));
                startActivity(browserIntent);
            }
        });

        this.button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://cryptoncoin.cash"));
                startActivity(browserIntent);

            }
        });
    }







    private boolean checkInternetConnection() {
        // Get Connectivity Manager
        ConnectivityManager connManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Details about the currently active default data network
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
            return false;
        }
        Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show();
        return true;
    }





    // When user click on the "Download Json".
    public void downloadAndShowJson(View view) {
        boolean networkOK = this.checkInternetConnection();
        if (!networkOK) {
            return;
        }
        String jsonUrl = "https://crypton.idyll.info/api/price_current";

        // Create a task to download and display json content.
        DownloadJsonTask task = new DownloadJsonTask(this.textView);

        // Execute task (Pass jsonUrl).
        task.execute(jsonUrl);
    }



}

