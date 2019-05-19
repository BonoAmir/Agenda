package com.example.android.agenda;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText search ;
    Button btn ;
    ImageView img ;
    ProgressBar bar ;
    ListView listView;
    Adapter madapter;
    String GOOGLE_API=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=(EditText)findViewById(R.id.search);
        btn=(Button)findViewById(R.id.btn);
        img=(ImageView)findViewById(R.id.img);
        bar=(ProgressBar)findViewById(R.id.bar);
        listView=(ListView)findViewById(R.id.listview);

        bar.setVisibility(View.INVISIBLE);

         madapter=new Adapter(getApplicationContext(),0,new ArrayList<DataClass>());

         listView.setAdapter(madapter);

        final InputMethodManager im=(InputMethodManager)getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);





         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 String s=search.getText().toString();

                 if (s==null || TextUtils.isEmpty(s))
                 {
                     Toast.makeText(getApplicationContext(),"please enter book name",Toast.LENGTH_LONG).show();
                 }
                 else
                 {
                     img.setVisibility(View.GONE);
                     GOOGLE_API="https://www.googleapis.com/books/v1/volumes?q="+s;

                     bookClass task =new bookClass();
                     task.execute(GOOGLE_API);

                     View view=getCurrentFocus();
                     im.hideSoftInputFromWindow(view.getWindowToken(),0);
                     search.setText("");
                 }




             }
         });





    }

    public class bookClass extends AsyncTask<String,Void,ArrayList<DataClass>>
    {

        @Override
        protected ArrayList<DataClass> doInBackground(String... strings) {

            if (strings.length<1 || strings[0]==null)
            {
                return null;
            }

            ArrayList<DataClass> books= Utils.fetchBooksData(strings[0]);

            return books;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList<DataClass> dataClasses) {

            madapter.clear();

           if (dataClasses !=null)
           {
               bar.setVisibility(View.INVISIBLE);
               madapter.addAll(dataClasses);
           }
        }
    }
}
