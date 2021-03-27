package com.example.application;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class HttpsAsyncTask extends AsyncTask<Object, Void, ArrayList<Reseau>>  {
    String host ;
    String text ;
    ArrayList<Reseau> ListReseau = new ArrayList<Reseau>();
    MyAdapter adapter;

    @Override
    public ArrayList<Reseau> doInBackground(Object... objects) {
        host = (String) objects[0];
        ListReseau = (ArrayList<Reseau>) objects[1];
        adapter = (MyAdapter) objects[2];

        try{
            URL url = new URL(host);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            if(urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK)
            {
                InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader input = new BufferedReader(isr);
                StringBuilder stringBuilder = new StringBuilder();

                while((text= input.readLine()) != null)
                {
                    stringBuilder.append(text);
                }

                String jsonStr = stringBuilder.toString();



                //Parsage
                JSONObject jsonRoot = new JSONObject(jsonStr);
                JSONArray jsonRecords = jsonRoot.getJSONArray("records");



                for(int i = 0 ; i < jsonRecords.length() ; i++)
                {
                    JSONObject object =jsonRecords.getJSONObject(i);
                    JSONObject jsonFields = object.getJSONObject("fields");



                    if(jsonFields.has("nom_reg") && jsonFields.has("code_op") && jsonFields.has("technologies") && jsonFields.has("nom_op"))
                    {
                        String NomRegion = jsonFields.getString("nom_reg");
                        String CodePostale = jsonFields.getString("code_op");
                        String Technologies = jsonFields.getString("technologies");
                        String NomOperateur = jsonFields.getString("nom_op");

                        JSONObject jsonGeometry = object.getJSONObject("geometry");
                        JSONArray jsonCoordonee = jsonGeometry.getJSONArray("coordinates");

                        String Longitude = jsonCoordonee.get(0).toString();
                        String Latitude = jsonCoordonee.get(1).toString();

                        Reseau tmp = new Reseau (NomRegion,CodePostale,Technologies,NomOperateur,Latitude,Longitude);

                        ListReseau.add(tmp);



                    }
                }

                Log.d("TEST",ListReseau.toString());

                input.close();
                urlConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ListReseau;
    }


    @Override
    protected void onPostExecute(ArrayList<Reseau> reseaus) {
        super.onPostExecute(reseaus);
        adapter.notifyDataSetChanged();
    }
}




