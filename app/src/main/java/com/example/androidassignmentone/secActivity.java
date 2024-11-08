package com.example.androidassignmentone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class secActivity extends AppCompatActivity {

    private Animation top,bottom;
    private  ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        @SuppressLint("WrongViewCast") RecyclerView recyclerView=findViewById(R.id.recyclerView);

        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Obesity",">= 30.0"));

        items.add(new Item("Underweight","0.0 - 18.5"));
        items.add(new Item("Normal weight Good","18.6 - 24.9"));
        items.add(new Item("Underweight","0.0 - 18.5"));
        items.add(new Item("Underweight","0.0 - 18.5"));
        items.add(new Item("Obesity",">= 30.0"));

        items.add(new Item("Obesity","0.0 - 18.5"));
        items.add(new Item("Underweight","0.0 - 18.5"));

        items.add(new Item("Obesity",">= 30.0"));
        items.add(new Item("Underweight","0.0 - 18.5"));
        items.add(new Item("Obesity",">= 30.0"));
        items.add(new Item("Obesity","0.0 - 18.5"));
        items.add(new Item("Underweight","0.0 - 18.5"));

        items.add(new Item("Obesity",">= 30.0"));

        items.add(new Item("Obesity","0.0 - 18.5"));
        items.add(new Item("Underweight","0.0 - 18.5"));

        items.add(new Item("Obesity",">= 30.0"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

    }

    @Override
    protected void onResume() {
        super.onResume();
        imageView = findViewById(R.id.fastfood);
        Animation top = AnimationUtils.loadAnimation(this, R.anim.anim);
        imageView.startAnimation(top);
    }
}