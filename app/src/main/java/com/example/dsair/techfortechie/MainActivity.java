package com.example.dsair.techfortechie;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Post> mPostList;
    RecyclerView recyclerView;
    String json_data="";
    JSONObject jsonObject;
    JSONArray jsonArray;
    Toolbar mToolbar;
    SharedPreferences sharedPreferences;
    boolean doubleBackPressedOnce=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Tech Feed");
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        sharedPreferences = getSharedPreferences("loginData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", null);
                        editor.putString("password", null);
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        MainActivity.this.finish();
                        return true;
                    default:
                        return MainActivity.super.onOptionsItemSelected(item);
                }
            }
        });
        new JsonTask().execute();
//        Log.e("MainActivity", json_data+"Hello");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void parseJson(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mPostList = new ArrayList<Post>();
        try {
            jsonObject = new JSONObject(json_data);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count=0;
            String title, content, author, image, permalink;
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                title = JO.getString("title");
                content = JO.getString("content");
                author = JO.getString("author");
                image = JO.getString("image");
                permalink = JO.getString("permalink");
                mPostList.add(new Post(count, title, content, permalink, image, author));
                count++;
            }
//            mPostList.add(new Post(1, "AMD Threadripper vs Intel Core-i9", "Temp Content", "amd-threadripper", R.drawable.placeholder_600x400, "sairam"));
//            mPostList.add(new Post(2, "Second Post", "Temp", "second-post", R.drawable.placeholder_600x400, "sairam"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PostAdapter adapter = new PostAdapter(MainActivity.this, mPostList);

        recyclerView.setAdapter(adapter);
    }

    class JsonTask extends AsyncTask<Void, Void, String>
    {
        ProgressDialog progressDialog;
        String mJsonUrl;
        String mJsonData;

        @Override
        protected void onPreExecute() {
            mJsonUrl = "http://techfortechie.com/api/posts_api.php";
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(mJsonUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((mJsonData = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(mJsonData+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            json_data = result;
//            Log.e("MainActivity", json_data+"Hello");
            if(result == null){
                Toast toast = Toast.makeText(MainActivity.this, "No Internet connection", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
                parseJson();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }

    @Override
    public void onBackPressed() {
        if(doubleBackPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackPressedOnce = true;
        Snackbar snackbar = Snackbar.make(findViewById(R.id.main_activity_layout), R.string.double_press_exit, Snackbar.LENGTH_SHORT);
        snackbar.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackPressedOnce = false;
            }
        }, 2000);
    }

}
