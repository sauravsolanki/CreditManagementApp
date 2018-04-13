package com.example.hp.creditmanagementapp.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.hp.creditmanagementapp.Database.UserDatabase;
import com.example.hp.creditmanagementapp.R;
import com.example.hp.creditmanagementapp.UserAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    String names[] = new String[10];
    int ids[] = new int[10];
    int amounts[] = new int[10];
    String emails[] = new String[10];

    static public UserDatabase userDatabase;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        userDatabase = new UserDatabase(this);
        intialiseAll();
        userAdapter = new UserAdapter(ids, names, emails, amounts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Toast.makeText(this, bundle.getString("message"), Toast.LENGTH_SHORT).show();
            userAdapter.notifyDataSetChanged();
        }
    }

    private void intialiseAll() {
        Cursor cursor = userDatabase.getAll();
        int i = 0;
        while (cursor.moveToNext()) {
            ids[i] = cursor.getInt(0);
            names[i] = cursor.getString(1);
            emails[i] = cursor.getString(2);
            amounts[i] = cursor.getInt(3);
            i++;
        }


    }
}
