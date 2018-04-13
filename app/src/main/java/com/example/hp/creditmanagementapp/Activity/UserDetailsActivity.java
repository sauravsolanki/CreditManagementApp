package com.example.hp.creditmanagementapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.creditmanagementapp.Database.UserDatabase;
import com.example.hp.creditmanagementapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDetailsActivity extends AppCompatActivity {

    @BindView(R.id.id)
    TextView tid;
    @BindView(R.id.name)
    TextView tname;
    @BindView(R.id.email)
    TextView temail;
    @BindView(R.id.Amount)
    TextView tAmount;
    @BindView(R.id.transfer_amount)
    EditText transferAmount;

     private UserDatabase userDatabase;
     static public int transfering_Amount;
    String id,name,email,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);
        userDatabase=new UserDatabase(this);


        Bundle extras=getIntent().getExtras();
        if (extras!=null){

            id=extras.getString("id");
            name=extras.getString("name");
            email=extras.getString("email");
            amount=extras.getString("amount");
            tid.setText(id);
            tname.setText(name);
            temail.setText(email);
            tAmount.setText(amount);
        }



    }

    @OnClick(R.id.transfer)
    public void onViewClicked() {
        if (transferAmount.getText().toString()!=null) {
            transfering_Amount = Integer.parseInt(transferAmount.getText().toString());

            if(transfering_Amount <= (userDatabase.getAmountDetails(Integer.parseInt(tid.getText().toString())))&&transfering_Amount>0) {
                Intent intent = new Intent(this, SelectUserActivity.class);
                intent.putExtra("fid", tid.getText().toString());
                startActivity(intent);
            }else {
                Toast.makeText(this, "You don't have enough balance to transfer", Toast.LENGTH_SHORT).show();
            }



//            Intent intent = new Intent(this, SelectUserActivity.class);
//            intent.putExtra("fid", tid.getText().toString());
//            startActivity(intent);

        }
        }
    }
