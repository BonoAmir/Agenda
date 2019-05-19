package com.example.android.agenda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class Adapter extends ArrayAdapter<DataClass>{


TextView bookTitle,authorName;
ImageView bookImage ;


    public Adapter(Context context, int resource , ArrayList<DataClass> books) {
        super(context, resource,books);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View view=convertView;

        if (view==null)
        {
            view=LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

         final DataClass dataClass=getItem(position);


        bookTitle=(TextView)view.findViewById(R.id.bookTitle);

        authorName=(TextView)view.findViewById(R.id.authorName);

        bookImage=(ImageView)view.findViewById(R.id.bookImage);

        bookTitle.setText(dataClass.getBook_title());

        authorName.setText(dataClass.getAuthor_name());


        if (dataClass.getImage_link().length() != 0)
        {
            Picasso.get()
                    .load(dataClass.getImage_link())
                    .resize(50, 50)
                    .placeholder(R.drawable.books)
                    .into(bookImage);

        }
        else
        {
         bookImage.setImageResource(R.drawable.books);

        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), bookInfo.class);
                intent.putExtra("title",dataClass.getBook_title());
                intent.putExtra("author",dataClass.author_name);
                intent.putExtra("publisher",dataClass.getPublisher());
                intent.putExtra("date",dataClass.getDate());
                intent.putExtra("description",dataClass.getDescription());
                intent.putExtra("image",dataClass.getImage_link());
                getContext().startActivity(intent);






            }
        });


        return view;





    }
}
