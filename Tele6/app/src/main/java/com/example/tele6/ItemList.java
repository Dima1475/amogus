package com.example.tele6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

public class ItemList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    JSONArray array;
    String login = null;
    ActivityListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = binding.rvMain;
        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        login = sharedPreferences.getString("Login", null);
        tryToLoginTask(login);
    }

    public void tryToLoginTask(String login){
        new LoginTask().execute(login);
    }

    public class LoginTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            String text = null;
            try {
                URL url = new URL("http://192.168.144.11:50712/api/DeskTasks?userLogin=" + strings[0]);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                Scanner scanner = new Scanner(http.getInputStream());
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
            try {
                array = new JSONArray(s);
                ArrayList<Item> items = new ArrayList<>();
                for (int i = 0; i < array.length(); i++){
                    JSONObject jsObject = array.getJSONObject(i);
                    Item item = new Item();
                    item.title = jsObject.getString("Title");
                    item.description = jsObject.getString("Description");
                    item.idStatus = jsObject.getInt("IdStatus");

                    items.add(item);

                    layoutManager = new LinearLayoutManager(ItemList.this);
                    MainAdapter adapter = new MainAdapter(items);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }

                Log.d("a", "size: " + items.size());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}