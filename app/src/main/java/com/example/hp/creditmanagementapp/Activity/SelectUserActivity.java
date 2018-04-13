package com.example.hp.creditmanagementapp.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hp.creditmanagementapp.Database.UserDatabase;
import com.example.hp.creditmanagementapp.R;
import com.example.hp.creditmanagementapp.UserAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectUserActivity extends AppCompatActivity {

    UserDatabase userDatabase;
    UserAdapter userAdapter;
    static public int fid;
    static public int tid;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    String names[]=new String[9];
    int ids[]= new int[9];
    int amounts[]=new int[9];
    String emails[]=new String[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        fid = Integer.parseInt(bundle.getString("fid"));

        userDatabase=new UserDatabase(this);
        IntialiseData();
        userAdapter=new UserAdapter(ids,names,emails,amounts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);
    }

    private void IntialiseData() {
        Cursor cursor=userDatabase.getAllExceptOne(fid);
        int i=0;
        while (cursor.moveToNext()){
            ids[i]=cursor.getInt(0);
            names[i]=cursor.getString(1);
            emails[i]=cursor.getString(2);
            amounts[i]=cursor.getInt(3);
            i++;
        }
    }

}
