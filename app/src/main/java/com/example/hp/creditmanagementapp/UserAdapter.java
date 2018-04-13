package com.example.hp.creditmanagementapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.creditmanagementapp.Activity.MainActivity;
import com.example.hp.creditmanagementapp.Activity.UserDetailsActivity;
import com.example.hp.creditmanagementapp.Database.UserDatabase;

import butterknife.BindView;

import static com.example.hp.creditmanagementapp.Activity.SelectUserActivity.fid;
import static com.example.hp.creditmanagementapp.Activity.SelectUserActivity.tid;
import static com.example.hp.creditmanagementapp.Activity.UserDetailsActivity.transfering_Amount;

/**
 * Created by hp on 12-04-2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {

    int ids[];
    String names[];
    String emails[];
    int amounts[];

    UserDatabase userDatabase;

    public UserAdapter(int[] ids,String[] names, String[] emails, int[] amounts) {
        this.names = names;
        this.ids = ids;
        this.amounts = amounts;
        this.emails = emails;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.id.setText(""+ids[position]);
        holder.name.setText(names[position]);
        holder.email.setText(emails[position]);
        holder.amount.setText("Rs."+amounts[position]);
    }

    @Override
    public int getItemCount() {
        return ids.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id,name,email,amount;
        public MyHolder(View itemView) {
            super(itemView);
        id=(TextView)itemView.findViewById(R.id.id);
        name=(TextView)itemView.findViewById(R.id.name);
        email=(TextView)itemView.findViewById(R.id.email);
        amount=(TextView)itemView.findViewById(R.id.amount);
        itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           if (view.getContext().getClass()==MainActivity.class) {
               Intent intent = new Intent(view.getContext(), UserDetailsActivity.class);
               intent.putExtra("id", "" + ids[getPosition()]);
               intent.putExtra("name", names[getPosition()]);
               intent.putExtra("email", emails[getPosition()]);
               intent.putExtra("amount", "" + amounts[getPosition()]);
               view.getContext().startActivity(intent);
           }
        else {
               Intent intent =new Intent(view.getContext(),MainActivity.class);
               intent.putExtra("message"," Credit Transfered Successfully");
               //need from id,to id,amount variables
               //userdatabase object
               //UpdateAmount()
               userDatabase=new UserDatabase(view.getContext());
               userDatabase.UpdateAmount(fid,ids[getPosition()],transfering_Amount);
               view.getContext().startActivity(intent);

           }
        }
    }
}
