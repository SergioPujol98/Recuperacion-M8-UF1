package com.example.sudoku_sergiopujol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

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
                    if(correct()) {
                        tv.setText("");
                    } else {
                        tv.setText("Número repetido!");
                    }
                    if (completed()) {
                        if(resuelto()) {
                            tv.setText("Felicidades! Has completado correctamente tu SUDOKU!!");
                        } else {
                            tv.setText("Hay errores en tu SUDOKU");
                        }
                    }
                }
            });
        }
    }

    boolean resuelto() {
        Cell table1;
        int arr1[][] = {{8,9,6,5,1,4,7,2,3},
                {3,4,2,9,6,7,5,8,1},
                {7,1,5,8,3,2,4,9,6},
                {2,3,9,1,7,6,8,4,5},
                {5,6,8,4,9,3,2,1,7},
                {1,7,4,2,5,8,6,3,9},
                {4,5,3,6,8,1,9,7,2},
                {6,8,1,7,2,9,3,5,4},
                {9,2,7,3,4,5,1,6,8}};

        for (int i=0; i<9;i++) {
            for (int j = 0; j < 9; j++) {
                table1 = new Cell(arr1[i][j],this);
                if (table[i][j]!= table1) {
                    return false;
                }
            }
        }
        return true;
    }


    boolean completed() {
        for (int i=0; i<9;i++) {
            for (int j=0;j<9;j++){
                if (table[i][j].value==0) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean correct(int i1, int j1, int i2, int j2) {
        boolean[] seen = new boolean[10];
        for (int i = 1; i<=9; i++) seen[i]=false;
        for(int i=i1; i<i2;i++) {
            for(int j = j1; j<j2;j++) {
                int value=table[i][j].value;
                if(value!=0) {
                    if (seen[value]) return false;
                    seen[value]=true;
                }
            }
        }
        return true;
    }
    boolean correct() {
        for(int i=0; i<9; i++) {
            if(!correct(i,0,i+1,9)) return false;
        }
        for (int j=0;j<9; j++) {
            if (!correct(0,j,9,j+1)) return false;
        }
        for (int i=0; i<3;i++) {
            for (int j=0; j<3;j++) {
                if (!correct(3*i,3*j,3*i+3,3*j+3)) return false;
            }
        }
        return true;
    }


    Cell[][] table;
    String input;
    TableLayout tl;
    TextView tv;
    LinearLayout linLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      int arr1[][] = {{8,9,6,5,1,4,7,2,3},
                {3,4,2,9,6,7,5,8,1},
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

        table = new Cell[9][9];
        tl = new TableLayout(this);
        for (int i = 0; i<9;i++) {
            TableRow tr = new TableRow(this);
            for (int j = 0; j < 9; j++) {
                int num = arr2[i][j];
                table[i][j]= new Cell(num,this);
                tr.addView(table[i][j].bt);
            }
            tl.addView(tr);
        }

        tl.setShrinkAllColumns(true);
        tl.setStretchAllColumns(true);
        tv = new TextView(this);
        linLay=new LinearLayout(this);
        linLay.addView(tl);
        linLay.addView(tv);
        linLay.setOrientation(LinearLayout.VERTICAL);
        setContentView(linLay);


    }


}