package com.example.sudoku_sergiopujol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private class Cell {
        int value;
        boolean fixed; //Valores que no se podran cambiar durante el sudoku
        Button bt;
        Spinner sp;
        TableLayout tl;

        int arr1[][] = {{8,9,6,5,1,4,7,2,3},
                {3,4,2,9,6,7,5,8,1},
                {7,1,5,8,3,2,4,9,6},
                {2,3,9,1,7,6,8,4,5},
                {5,6,8,4,9,3,2,1,7},
                {1,7,4,2,5,8,6,3,9},
                {4,5,3,6,8,1,9,7,2},
                {6,8,1,7,2,9,3,5,4},
                {9,2,7,3,4,5,1,6,8}};

        public Cell(int initValue, Context THIS) {
            value = initValue;
            if (value!=0) fixed=true;
            else fixed=false;
            bt=new Button(THIS);
            //sp=new Spinner(context);

            if(fixed) bt.setText(String.valueOf(value));
        }
    }

    Cell[][] table;
    String input;
    TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        input = "8 9 6 5 1 4 7 2 3" +
                "3 4 2 9 6 7 5 8 1" +
                "7 1 5 8 3 2 4 9 6" +
                "2 3 9 1 7 6 8 4 5" +
                "5 6 8 4 9 3 2 1 7" +
                "1 7 4 2 5 8 6 3 9" +
                "4 5 3 6 8 1 9 7 2" +
                "6 8 1 7 2 9 3 5 4" +
                "9 2 7 3 4 5 1 6 8";

        String[] split = input.split(" ");
        table = new Cell[9][9];
        tl = new TableLayout(this);
        for (int i = 0; i<9;i++) {
            TableRow tr = new TableRow(this);
            for (int j = 0; j < 9; j++) {
                String s = split[i*0+j];
                Character c=s.charAt(0);
                table[i][j]= new Cell(c=='?'?0:c-'0',this);
                tr.addView(table[i][j].bt);
            }
            tl.addView(tr);
        }
        tl.setShrinkAllColumns(true);
        tl.setStretchAllColumns(true);
        setContentView(tl);


    }
}