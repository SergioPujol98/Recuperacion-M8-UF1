package com.example.sudoku_sergiopujol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private class Cell {
        int value;
        boolean fixed; //Valores que no se podran cambiar durante el sudoku
        Button bt;
        Spinner sp;
        TableLayout tl;



        public Cell(int initValue, Context THIS) {
            value = initValue;
            if (value!=0) fixed=true;
            else fixed=false;
            bt=new Button(THIS);

            if(fixed) bt.setText(String.valueOf(value));
            else bt.setTextColor(Color.BLUE);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fixed) return;
                    value++;
                    if(value>9) value= 1;
                    bt.setText(String.valueOf(value));
                }
            });
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



      int arr1[][] = {{8,9,6,5,1,4,7,2,3},
                {3,4,0,9,6,7,5,8,1},
                {7,1,5,8,3,2,4,9,6},
                {2,3,9,1,7,6,8,4,5},
                {5,6,8,4,9,3,2,1,7},
                {1,7,4,2,5,8,6,3,9},
                {4,5,3,6,8,1,9,7,2},
                {6,8,1,7,2,9,3,5,4},
                {9,2,7,3,4,5,1,6,8}};

        int arr2[][] = {{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}};

        //Randomizador
        int contadorRandom = 0;
        while (contadorRandom < 15) {
            final int random = new Random().nextInt((8 - 0) + 1) + 0;
            final int random1 = new Random().nextInt((8 - 0) + 1) + 0;
            if (arr2[random][random1] == 0) {
                arr2[random][random1] = arr1[random][random1];
                contadorRandom++;
            }
        }

       // String[] split = input.split(" ");
        table = new Cell[9][9];
        tl = new TableLayout(this);
        for (int i = 0; i<9;i++) {
            TableRow tr = new TableRow(this);
            for (int j = 0; j < 9; j++) {
                //String s = split[i*0+j];
                //Character c=s.charAt(0);
                int num = arr2[i][j];
                table[i][j]= new Cell(num,this);
                tr.addView(table[i][j].bt);
            }
            tl.addView(tr);
        }

        tl.setShrinkAllColumns(true);
        tl.setStretchAllColumns(true);
        setContentView(tl);


    }


}