package com.example.sudoku_sergiopujol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private class Cell {
        int value;
        boolean fixed; //Valores que no se podran cambiar durante el sudoku
        Button bt;
        Spinner sp;

        int arr1[][] = {{8,9,6,5,1,4,7,2,3},
                {3,4,2,9,6,7,5,8,1},
                {7,1,5,8,3,2,4,9,6},
                {2,3,9,1,7,6,8,4,5},
                {5,6,8,4,9,3,2,1,7},
                {1,7,4,2,5,8,6,3,9},
                {4,5,3,6,8,1,9,7,2},
                {6,8,1,7,2,9,3,5,4},
                {9,2,7,3,4,5,1,6,8}};

        public Cell(int initValue, Context context) {
            value = initValue;
            if (value!=0) fixed=true;
            else fixed=false;
            sp=new Spinner(context);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        View view = findViewById(R.id.v1);
        view.setText("s");


    }
}