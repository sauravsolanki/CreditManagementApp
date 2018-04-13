package com.example.hp.creditmanagementapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hp on 13-04-2018.
 */

public class UserDatabase extends SQLiteOpenHelper {

    static final private String DB_NAME = "credit";
    static final private String DB_TABLE_USER = "user";
    static final private String DB_TABLE_TRANSFER = "transfer";
    static final private int DB_VER = 1;


    SQLiteDatabase myDb;

    public UserDatabase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + DB_TABLE_USER + " (id int primary key ,name varchar(30),email varchar(30),ccredit int);");
        Log.i("Table USER", ":created ");

        sqLiteDatabase.execSQL("create table " + DB_TABLE_TRANSFER + " (fid int ,tid int ,amount_transferred int,Foreign key(fid) references " + DB_TABLE_USER + "(id),Foreign key(tid) references " + DB_TABLE_USER + "(id));");
        Log.i("Table TRANSFER", ":created");

        sqLiteDatabase.execSQL("insert into " + DB_TABLE_USER + "(id,name,email,ccredit) values (1,'Saurav Solanki','sausol.solanki@cuk.com',1000);");
        sqLiteDatabase.execSQL("insert into " + DB_TABLE_USER + "(id,name,email,ccredit) values (2,'Gaurav Chatterjee','g.chatterjee@aol.com',1000);");
        sqLiteDatabase.execSQL("insert into " + DB_TABLE_USER + "(id,name,email,ccredit) values (3,'John Doe','john@gmail.com',1000);");
        sqLiteDatabase.execSQL("insert into " + DB_TABLE_USER + "(id,name,email,ccredit) values (4,'Peter Feranandise','peter@gmail.com',1000);");
        sqLiteDatabase.execSQL("insert into " + DB_TABLE_USER + "(id,name,email,ccredit) values (5,'Akshata P.','ap123@hotmail.com',1000);");
        sqLiteDatabase.execSQL("insert into " + DB_TABLE_USER + "(id,name,email,ccredit) values (6,'Rahul Kumar','rahulk@gmail.com',1000);");
        sqLiteDatabase.execSQL("insert into " + DB_TABLE_USER + "(id,name,email,ccredit) values (7,'Kartikey Singh','kartikey@gmail.com',1000);");
        sqLiteDatabase.execSQL("insert into " + DB_TABLE_USER + "(id,name,email,ccredit) values (8,'Abhijeet Ranabat','abhi.ranabat@yahoo.com',1000);");
        sqLiteDatabase.execSQL("insert into " + DB_TABLE_USER + "(id,name,email,ccredit) values (9,'G. L. Bajaj','glbajaj@gmail.com',1000);");
        sqLiteDatabase.execSQL("insert into " + DB_TABLE_USER + "(id,name,email,ccredit) values (10,'Nanam Talwar','naman@rediff.com',1000);");

        Log.i("Table","Data entered with 10 entry");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + DB_TABLE_USER);
        sqLiteDatabase.execSQL("Drop table if exists " + DB_TABLE_TRANSFER);
    }

    public Cursor getAll(){
        myDb=getReadableDatabase();
        String query="select * from "+DB_TABLE_USER;
        Cursor cursor=myDb.rawQuery(query,null);
        return cursor;
    }
    public Cursor getAllExceptOne(int fid){
        myDb=getReadableDatabase();
        String query="select * from "+DB_TABLE_USER+" where id <> ?";
        String[] stringArg= new String[]{fid+""};
        Cursor cursor=myDb.rawQuery(query,stringArg);
        return cursor;
    }

    public void UpdateAmount(int fromId, int toId,int tAmount){
        myDb=getWritableDatabase();
        String query1="Update user set ccredit=ccredit-"+tAmount+" where id= ?";
        String[] stringArgs1= new String[]{fromId+""};
        myDb.execSQL(query1,stringArgs1);

        String query2="Update user set ccredit =ccredit+"+tAmount+" where id= ?";
        String[] stringArgs2= new String[]{toId+""};
        myDb.execSQL(query2,stringArgs2);

        String insert_query="insert into "+DB_TABLE_TRANSFER+"(fid,tid,amount_transferred) values("+fromId+","+toId+","+tAmount+");";
        myDb.execSQL(insert_query);
    }
    public int getAmountDetails(int id){
        myDb=getReadableDatabase();
        String query="select ccredit from "+DB_TABLE_USER+" where id = ?";
        String[] stringArg= new String[]{id+""};
        Cursor cursor=myDb.rawQuery(query,stringArg);
        cursor.moveToNext();
         return cursor.getInt(0);
    }

}
