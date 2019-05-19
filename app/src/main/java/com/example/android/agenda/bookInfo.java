package com.example.android.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class bookInfo extends AppCompatActivity {
    TextView title2 , author , publisher2, date2,description2 ;
    ImageView bkImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        title2=(TextView)findViewById(R.id.bookTitle2);
        author=(TextView)findViewById(R.id.authorName2);
        publisher2=(TextView)findViewById(R.id.publisher);
        date2=(TextView)findViewById(R.id.date);
        description2=(TextView)findViewById(R.id.description);

        bkImage=(ImageView)findViewById(R.id.bookImage2);


        String title=getIntent().getStringExtra("title");
        title2.setText(title);

        author.setText(getIntent().getStringExtra("author"));

        publisher2.setText(getIntent().getStringExtra("publisher"));

        date2.setText(getIntent().getStringExtra("date"));

        description2.setText(getIntent().getStringExtra("description"));

        Picasso.get()
                .load(getIntent().getStringExtra("image"))
                .resize(50, 50)
                .placeholder(R.drawable.books)
                .into(bkImage);






    }
}
