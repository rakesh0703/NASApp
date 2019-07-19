package com.example.rakesh.nasa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

      public void processs(String s){
          try{
              JSONObject obj = new JSONObject(s);
              String str = obj.getString("explanation");
              String strimage =obj.getString("url");
              TextView txt = (TextView) findViewById(R.id.text);
              txt.setText(str);
              ImageView img =(ImageView) findViewById(R.id.imgg);
              Picasso.with(this)
                      .load(strimage)
                      .resize(300,300)
                      .into(img);
          }
          catch(JSONException e){
              Log.wtf("json",e);

          }

      }

    public void btnshow(View view) {
        EditText dateedit = (EditText) findViewById(R.id.editdate);
        String date=dateedit.getText().toString();
        String url = "https://api.nasa.gov/planetary/apod?api_key=HxvTCYK6uxggMO49gYMAzo2fwZFa87v0mFxHjl9v"+"&date="+date;

        System.out.println(date);
        System.out.println(url);
        Ion.with(this)
                .load(url)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        processs(result);
                    }
                });
    }
}
