package com.example.learncode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.learncode.activity.algo.AlgoActivity;
import com.example.learncode.activity.android.AndroidActivity;
import com.example.learncode.activity.c.CActivity;
import com.example.learncode.activity.cpp.CppActivity;
import com.example.learncode.activity.css.CssActivity;
import com.example.learncode.activity.ds.DsActivity;
import com.example.learncode.activity.html.HtmlActivity;
import com.example.learncode.activity.java.JavaActivity;
import com.example.learncode.activity.javascript.JavaScriptActivity;
import com.example.learncode.activity.python.PythonActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void java(View view) {
        Intent i=new Intent(MainActivity.this, JavaActivity.class);
        startActivity(i);
    }

    public void python(View view) {
        Intent i=new Intent(MainActivity.this, PythonActivity.class);
        startActivity(i);
    }

    public void javascript(View view) {
        Intent i=new Intent(MainActivity.this, JavaScriptActivity.class);
        startActivity(i);
    }

    public void cpp(View view) {
        Intent i=new Intent(MainActivity.this, CppActivity.class);
        startActivity(i);
    }

    public void c(View view) {
        Intent i=new Intent(MainActivity.this, CActivity.class);
        startActivity(i);
    }

    public void html(View view) {
        Intent i=new Intent(MainActivity.this, HtmlActivity.class);
        startActivity(i);
    }

    public void css(View view) {
        Intent i=new Intent(MainActivity.this, CssActivity.class);
        startActivity(i);
    }

    public void android(View view) {
        Intent i=new Intent(MainActivity.this, AndroidActivity.class);
        startActivity(i);
    }

    public void ds(View view) {
        Intent i=new Intent(MainActivity.this, DsActivity.class);
        startActivity(i);

    }

    public void algo(View view) {
        Intent i=new Intent(MainActivity.this, AlgoActivity.class);
        startActivity(i);
    }
}