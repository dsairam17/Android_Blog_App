package com.example.dsair.techfortechie;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String, Void, String> {
    Context context;
    ProgressDialog progressDialog;

    public BackgroundTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String login_url = "http://www.techfortechie.com/api/login_api.php";
        String register_url = "http://www.techfortechie.com/api/signup_api.php";
        String action = params[0];
        if(action.equals("login")){
            String email = params[1];
            String password = params[2];
            try{
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            }
            catch(MalformedURLException e){
                e.printStackTrace();
                Log.i("BackgroundTask.java", "MalformedURLException");
                return "Unable to contact server. Make sure you have an ACTIVE INTERNET CONNECTION";
            }
            catch(IOException e){
                e.printStackTrace();
                Log.i("BackgroundTask.java", "IOException");
                return "Unable to contact server. Make sure you have an ACTIVE INTERNET CONNECTION";
            }
        }
        else if(action.equals("register"))
        {
            String email = params[1];
            String username = params[2];
            String password = params[3];
            try{
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            }
            catch(MalformedURLException e){
                e.printStackTrace();
                Log.i("BackgroundTask.java", "MalformedURLException");
                return "Unable to contact server. Make sure you have an ACTIVE INTERNET CONNECTION";
            }
            catch(IOException e){
                e.printStackTrace();
                Log.i("BackgroundTask.java", "IOException");
                return "Unable to contact server. Make sure you have an ACTIVE INTERNET CONNECTION";
            }

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        if(s.equals("true")){
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
        }
        else if(s.equals("false")){
            Toast.makeText(context, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
        else if(s.equals("registration_true")){
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT).show();
        }
        else if(s.equals("registration_false")){
            Toast.makeText(context, "Email or Username already exists", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }


    }
}
