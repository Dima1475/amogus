package com.example.tele6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tele6.databinding.ActivityMainBinding;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    String login, password;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = binding.teLogin.getText().toString();
                password = binding.tePassword.getText().toString();
                if (login.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Заполните все строчки", Toast.LENGTH_SHORT).show();
                    return;
                }
                tryToLogin(login, password);
            }
        };
        View.OnClickListener addNewTaskClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(MainActivity.this, AddNewTaskActivity.class);
                startActivity(n);
            }
        };
        binding.btnAddTask.setOnClickListener(addNewTaskClick);
        binding.btnLogin.setOnClickListener(onClickListener);
    }

    public void tryToLogin(String log, String pas) {
        new LoginTsk().execute(log, pas);
    }

    public class LoginTsk extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String text = null;
            try {
                URL url = new URL("http://192.168.144.11:50712/api/Auth?login=" + strings[0] + "&password=" + strings[1]);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
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
                SharedPreferences shared = getSharedPreferences("userData", MODE_PRIVATE);
                shared.edit().putString("Login", binding.teLogin.getText().toString()).apply();
                s += "asd";
                Log.d("asd", s);
                Intent i = new Intent(MainActivity.this, ItemList.class);
                startActivity(i);

        }
    }
}


