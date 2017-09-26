package com.gexne.bottombardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Person> origin = new ArrayList<>();

    }

    private class Person implements Cloneable {
        private int id;
        private String name;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
