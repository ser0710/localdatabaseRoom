package edu.eci.ieti.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.eci.ieti.room.DAO.UserDao;
import edu.eci.ieti.room.entities.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
        AppDatabase db = AppDatabase.getInstance(context);
        User user = new User();
        user.firstName = "HOLA";
        user.lastName = "ADIOS";
        UserDao userDao = db.userDao();
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insert(user);
                TextView textView = findViewById(R.id.lastNameTextView);
                textView.setText(userDao.findLastNameByName("HOLA").lastName);
                Log.d("MainActivity", userDao.findLastNameByName("HOLA").lastName);
            }
        });



    }
}