package com.example.chohee.connectnetwork;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Chohee on 9/1/16.
 */
public class HttpExampleActivity extends Activity {

    private static final String DEBUG_TAGE = "HttpExample";
    private EditText urlText;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlText = (EditText) findViewById(R.id.myUrl);
        textView = (TextView) findViewById(R.id.myText);
    }

    //when user clicks the button, it calls AsyncTask.
    //Before attempting to fetch the url, check if the network is connected
    public void myClickHandler(View view) {

        String stringUrl = urlText.getText().toString();
        Log.v("URL :" ,stringUrl );
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute(stringUrl);
        }else {
            textView.setText("No network connection available");
        }
    }

    /**
     * Aafter calling connection, get an InputStream of the data by calling getInputStream()
     * @param myUrl
     * @return
     * @throws IOException
     */
    private String downloadUrl(String myUrl) throws IOException{

        InputStream inputStream = null;

        int len = 500;

        try {
            URL url = new URL(myUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.setConnectTimeout(15000 /* milliseconds */);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            connection.connect();
            int httpResponse = connection.getResponseCode();
            Log.d(DEBUG_TAGE, "The response is " + httpResponse);
            inputStream = connection.getInputStream();

            String content = readIt(inputStream, len);
            return  content;

        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * Convert the inputStream to a String
     * @param inputStream
     * @param len
     * @return
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public String readIt(InputStream inputStream, int len) throws IOException, UnsupportedEncodingException {

        Reader reader = null;
        reader = new InputStreamReader(inputStream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            //strings[0] = url
            return strings[0];
            /**
            try {
                //download url
                return downloadUrl(strings[0]);
            }catch (IOException e) {
                return "Unable to retreive web page, URL may be invalid.";
            }
             */

        }

        //this method display the results of the AsyncTask
        //is invoked on the UI thread after the background computation finishes
        @Override
        protected void onPostExecute(String result) {
            //textView.setText(result);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(result));
            startActivity(intent);
        }


    }


}
