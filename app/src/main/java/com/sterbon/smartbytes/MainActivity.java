package com.sterbon.smartbytes;

import android.app.NotificationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView mdata, mtotal, mdays, musage;
    Button re;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DataExtract().execute();

        mdata = (TextView) findViewById(R.id.lw);
        mtotal = (TextView) findViewById(R.id.td);
        mdays = (TextView) findViewById(R.id.dl);
        musage=(TextView) findViewById(R.id.eg);
        re = (Button) findViewById(R.id.refresh);

        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DataExtract().execute();
            }

        });

    }

    public class DataExtract extends AsyncTask<Void, Void, Void> {

        String total1,total2,data,day;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("http://122.160.230.125:8080/planupdate/").get();

                Elements tdata = doc.select("#template > div.main > div.middle_panel > div.box1 > ul > li:nth-child(1) > div.description > p > font:nth-child(2) > span");
                for (Element ele : tdata.select("span")) {
                    total1 = ele.text();
                }
                Elements tdata2 = doc.select("#template > div.main > div.middle_panel > div.box1 > ul > li:nth-child(1) > div.description > p > font:nth-child(3) > span");
               for (Element ele : tdata2.select("span")) {
                   total2 = ele.text();
               }
                Elements datal = doc.select("#template > div.main > div.middle_panel > div.box1 > ul > li:nth-child(2) > div.description > p > span");
                for (Element ele : datal.select("span")) {
                    data = ele.text();
                }
                Elements days = doc.select("#template > div.main > div.middle_panel > div.box1 > ul > li:nth-child(3) > div.description > p > span");
                for (Element ele : days.select("span")) {
                    day = ele.text();
                }


            } catch (IOException e) {
                
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mdata.setText(data);
            Float data_l=convert(data);
            mdays.setText(day);
            Float num1=convert(total1);
            Float num2=convert(total2);
            Float res=num1+num2;
            String new_n=Float.toString(res);
            mtotal.setText(new_n);
            Float day_l=convert(day);
            Float use=(data_l/day_l);
            String use_l=Float.toString(use);
            musage.setText(use_l);
        }

        private Float convert(String st){
            int ind = st.indexOf(" ");
            String str = st.substring(0, ind);
            return Float.parseFloat(str);

        }
    }
}