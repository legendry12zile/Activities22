package estacio.com.estacio_nov15;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText FName;
    EditText LName;
    EditText Score;
    DBHelper helper = new DBHelper(this);
    Cursor table;
    FirebaseDatabase db;
    DatabaseReference root;
    List<String> keylist;


    @Override
    protected void onStart() {
        super.onStart();
        root.child(String.valueOf(keylist)).setValue("eliz");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        root = db.getReference();
        //DBHelper helper = new DBHelper(this);
        //table = helper.selectRecord();
        FName = findViewById(R.id.etFName);
        LName = findViewById(R.id.etLName);
        Score = findViewById(R.id.etScore);

    }

    public void addRecord (View v ){
        String first = FName.getText().toString().trim();
        String last = LName.getText().toString().trim();
        int score = Integer.parseInt(Score.getText().toString().trim());
        boolean inserted;
        inserted = helper.insert(first,last,score);

        if (inserted == true){
           Toast.makeText(this, "Record INSERTED",Toast.LENGTH_LONG).show();
        } else Toast.makeText(this, "Record NOT INSERTED",Toast.LENGTH_LONG).show();
    }

    public void editRecord(View v){
        String id = table.getString(0);
        String first = FName.getText().toString().trim();
        String last = LName.getText().toString().trim();
        int score = Integer.parseInt(Score.getText().toString().trim());

        boolean updated = helper.update(id,first,last,score);
        if (updated == true){
            Toast.makeText(this, "Record updated", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record not updated", Toast.LENGTH_LONG).show();
    }

    public void deleteRecord(View v){
        String id = table.getString(0);
        boolean deleted = helper.delete(id);
        if (deleted == true){
            Toast.makeText(this, "Record deleted", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record deleted", Toast.LENGTH_LONG).show();
    }
    public void data (){
        FName.setText(table.getString( 1));
        LName.setText(table.getString( 2));
        Score.setText(table.getString( 3));
    }

    public void first (View v){
        table.moveToFirst();
        Toast.makeText(this, "Your first DATA",Toast.LENGTH_LONG).show();
        data();
    }
    public void last (View v){
        table.moveToLast();
        Toast.makeText(this, "Your last DATA",Toast.LENGTH_LONG).show();
        data();
    }
    public void prev (View v){
        table.moveToPrevious();
        if (table.isBeforeFirst()){
            Toast.makeText(this, "Record is at FIRST position",Toast.LENGTH_LONG).show();
        }else data();
    }
    public void next (View v){
        table.moveToNext();
        if (table.isAfterLast()){
            Toast.makeText(this, "Record is at LAST position",Toast.LENGTH_LONG).show();
        }else data();
    }

}

