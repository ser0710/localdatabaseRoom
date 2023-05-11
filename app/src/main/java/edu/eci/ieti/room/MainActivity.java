package edu.eci.ieti.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Random;
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
//        User user = new User();
//        user.firstName = "HOLA";
//        user.lastName = "ADIOS";
        UserDao userDao = db.userDao();
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
//                userDao.insert(user);
                TextView textView = findViewById(R.id.lastNameTextView);
                List<User> users = userDao.getAll();
                Random random = new Random();
                int randomIndex = random.nextInt(users.size());
                String firstName = users.get(randomIndex).firstName;
                String lastName = users.get(randomIndex).lastName;
                String put = firstName + " " + lastName;
                textView.setText(put);
            }
        });



    }
}