package com.tesis.sistemaderecomendacion;

import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class CallHttp extends AsyncTask<String, Void, RecomendacionVm> {

    Notification.Listener _listenerlogin;
    Context _context;

    public CallHttp(Context context){
        _context=context;
    }

    public void  SetListener(Notification.Listener listener){
        _listenerlogin=listener;
    }

    @Override
    protected RecomendacionVm doInBackground(String... urls) {
        StringBuilder resul = null;
        int respuesta =0;
        URL url = null;
        String linea = null;

        try{//"https://sistemaderecomendacion.herokuapp.com/grupo/getTestRecomendacion"
            url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();

            resul = new StringBuilder();
            if(respuesta == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null){
                    resul.append(linea);
                }
            }
        }catch (Exception ex){}

        return objDatosJson(resul.toString());

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onPostExecute(RecomendacionVm result) {
        if(_listenerlogin!=null){
            _listenerlogin.onEvent(Notification.ID.GetData,result);
        }
        //return result;
    }

    /*private RecomendacionVm getMusic(){
        StringBuilder resul = null;
        int respuesta =0;
        URL url = null;
        String linea = null;
        try {
            url = new URL("https://sistemaderecomendacion.herokuapp.com/grupo/getTestRecomendacion");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();

            resul = new StringBuilder();
            if(respuesta == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null){
                    resul.append(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objDatosJson(resul.toString());
        //https://theaudiodb.com/api/v1/json/1/searchtrack.php?s=coldplay&t=yellow
    }*/

    private RecomendacionVm objDatosJson(String response){
        //int res = 0;
        //Gson gson = new Gson();
        try {
            JSONObject json = new JSONObject(response);
            RecomendacionVm canciones = new RecomendacionVm();
            ObjectMapper mapper = new ObjectMapper();
            canciones = mapper.readValue(json.toString(), RecomendacionVm.class);

            return canciones;
        }catch (Exception ex){
            return new RecomendacionVm();
        }
    }
}
