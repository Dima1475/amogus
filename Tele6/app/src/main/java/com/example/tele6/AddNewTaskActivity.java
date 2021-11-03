package com.example.tele6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tele6.databinding.ActivityAddNewTaskBinding;
import com.example.tele6.databinding.ActivityListBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class AddNewTaskActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;

    JSONArray array;
    View view;

    ActivityAddNewTaskBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityAddNewTaskBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        tasksExecute();
    }

    public void tasksExecute(){ new Tasks().execute(); }

    public class Tasks extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String text = null;
            try {
                URL url = new URL("http://192.168.144.11:50712/api/Lots");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                Scanner scanner = new Scanner(httpURLConnection.getInputStream());
                scanner.useDelimiter("\\A");
                text = scanner.nextLine();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return text;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("asd", "asd  " + s);
            try {
                array = new JSONArray(s);
                ArrayList<ItemLot> lots = new ArrayList<>();
                for(int i = 0; i < array.length(); i++){
                    JSONObject object = array.getJSONObject(i);
                    ItemLot lot = new ItemLot();
                    lot.gigabytesToTrade = object.getInt("GigabytesToTrade");
                    lot.price = object.getDouble("Price");

                    lots.add(lot);
                }
                layoutManager = new LinearLayoutManager(AddNewTaskActivity.this);
                LotsAdapter adapter = new LotsAdapter(lots);
                bind.rvLotsList.setLayoutManager(layoutManager);
                bind.rvLotsList.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}