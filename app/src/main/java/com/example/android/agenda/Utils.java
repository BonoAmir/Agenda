package com.example.android.agenda;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Utils {


    static String image_link;
    static String book_title;
    static String author_name;
    static String publisher;
    static String description;
    static String date;



    public static ArrayList<DataClass> fetchBooksData(String StirngURL)
    {
        String Jsonresponse="";
        ArrayList<DataClass> books=new ArrayList<DataClass>();
        URL url=createUrl(StirngURL);
        try {
            Jsonresponse=makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        books=extractFeatureFromJson(Jsonresponse);

        return books;


    }

    private static URL createUrl(String stringURL)


    {

        URL url=null;
        try {
             url = new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }


    private static String makeHttpRequest(URL url) throws IOException {
        String JsonResponse="";

        HttpURLConnection httpURLConnection = null;

        InputStream inputStream=null;

        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpURLConnection.getResponseCode()==200)
        {
            inputStream=httpURLConnection.getInputStream();
            JsonResponse=readFromStream(inputStream);

        }

        httpURLConnection.disconnect();

        inputStream.close();



        return JsonResponse;

    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output=new StringBuilder();

        InputStreamReader inputStreamReader=new InputStreamReader(inputStream,Charset.forName("UTF-8"));
        BufferedReader reader=new BufferedReader(inputStreamReader);
        String line = reader.readLine();

        while (line !=null)
        {
            output.append(line);
            line=reader.readLine();
        }

        reader.close();

        return output.toString();
    }

    private static ArrayList<DataClass> extractFeatureFromJson(String output)
    {


        if (TextUtils.isEmpty(output))
        {
            return null ;
        }


        ArrayList<DataClass> books= new ArrayList();

        try {
            JSONObject jsonObject=new JSONObject(output);

            JSONArray jsonArray=jsonObject.getJSONArray("items");

            for(int i =0 ; i<jsonArray.length();i++)
            {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);


                JSONObject info=jsonObject1.getJSONObject("volumeInfo");


                book_title=info.getString("title");


                if (info.has("authors"))
                {
                    author_name=info.getString("authors");
                }
                else
                {
                    author_name="not known";
                }

                if (info.has("publisher"))
                {
                    publisher=info.getString("publisher");

                }
                else
                {
                    publisher="Not known";
                }

                if (info.has("publishedDate"))
                {
                    date=info.getString("publishedDate");
                }
                else
                {
                    date="Not known";
                }

                if (info.has("description"))
                {
                    description=info.getString("description");
                }

                if (info.has("imageLinks"))
                {
                    JSONObject img=info.getJSONObject("imageLinks");
                    image_link=img.getString("thumbnail");
                }
                else
                {
                    image_link="";
                }

                books.add(new DataClass(image_link,  book_title,  author_name,  publisher,  description,  date));

            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }



}
