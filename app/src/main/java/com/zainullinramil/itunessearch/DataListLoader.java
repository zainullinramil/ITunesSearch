package com.zainullinramil.itunessearch;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by user on 30.03.2016.
 */
public class DataListLoader extends AsyncTaskLoader<ArrayList<ItemSearch>> {

    public static String LOG_TAG = "my_log";
    public static String adressJSONmain = "https://itunes.apple.com/search?term=";
    private ArrayList<ItemSearch> arrItem;
    String searchJSON = "";

    public DataListLoader(Context context, String stext) {
        super(context);
        searchJSON = stext;
    }

    @Override
    public ArrayList<ItemSearch> loadInBackground() {

        //Парсим json-строку и заполняем ArrayList
        arrItem = new ArrayList<ItemSearch>();
        JsonContainer itemS;


        String strJson = jsonGet(searchJSON);
        Log.d(LOG_TAG, "strJson = " + strJson);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        itemS = gson.fromJson(strJson, JsonContainer.class);
        arrItem = itemS.getResults();
        return arrItem;
    }

    @Override
    public void deliverResult(ArrayList<ItemSearch> data) {

        if (isReset()) {
            // An async query came in while the loader is stopped.  We
            // don't need the result.
            if (data != null) {
                onReleaseResources(data);
            }
        }
        ArrayList<ItemSearch> oldItem = data;
        arrItem = data;

        if (isStarted()) {
            // If the Loader is currently started, we can immediately
            // deliver its results.
            super.deliverResult(data);
        }
        if (oldItem != null) {
            onReleaseResources(oldItem);
        }

    }

    @Override
    protected void onStartLoading() {
        if (arrItem != null) {
            // If we currently have a result available, deliver it
            // immediately.
            deliverResult(arrItem);
        }


        if (takeContentChanged() || arrItem == null) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(ArrayList<ItemSearch> data) {
        super.onCanceled(data);
        onReleaseResources(data);
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();

        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (arrItem != null) {
            onReleaseResources(arrItem);
            arrItem = null;
        }
    }
    protected void onReleaseResources(ArrayList<ItemSearch> apps) {}

    private class JSONTask extends AsyncTask<String, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(String... urls) {
            // получаем данные с внешнего ресурса
            try {
                URL url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return resultJson;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);


        }
    }

    // получаем строку JSON c заданного адреса adressJSONmain + название листа
    private String jsonGet(String srchjson) {
        JSONTask jsonTask = null;
        jsonTask = new JSONTask();
        jsonTask.execute(adressJSONmain + srchjson);

        String strJson = null;
        try {
            strJson = jsonTask.get();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return strJson;
    }

}
