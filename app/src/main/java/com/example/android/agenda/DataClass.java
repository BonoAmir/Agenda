package com.example.android.agenda;

public class DataClass {


     String image_link;
      String book_title;
     String author_name;
     String publisher;
     String description;
     String date;


    public DataClass( ) {

    }

    public DataClass(String image_link, String book_title, String author_name, String publisher, String description, String date) {

        this.image_link = image_link;
        this.book_title = book_title;
        this.author_name = author_name;
        this.publisher = publisher;
        this.description = description;
        this.date = date;
    }



    public  String getImage_link() {
        return image_link;
    }

    public  String getBook_title() {
        return book_title;
    }

    public  String getAuthor_name() {
        return author_name;
    }

    public  String getPublisher() {
        return publisher;
    }

    public  String getDescription() {
        return description;
    }

    public  String getDate() {
        return date;
    }
}
