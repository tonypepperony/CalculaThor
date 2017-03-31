package com.tonyappl.calculathor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero;
    Button btnPlus, btnMinus, btnEqual, btnMulti, btnDiv, btnPlusMinus, btnDot, btnRadical, btnC, btnSquare;
    TextView tvDisplay;
    String oper1, oper2;
    int flagAction;
    double result;
    int[] btn_id;
    Button[] btn_array;
    boolean flagPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_id = new int[]{R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,R.id.btn5,R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn0, R.id.btnPlus, R.id.btnMinus,R.id.btnMulti, R.id.btnDiv, R.id.btnEqual,R.id.btnPlusMinus,R.id.btnDot, R.id.btnRadical, R.id.btnC, R.id.btnSqr};
        btn_array = new Button[]{btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero, btnPlus, btnMinus, btnMulti, btnDiv, btnEqual, btnPlusMinus, btnDot, btnRadical, btnC, btnSquare};

        tvDisplay = (TextView) findViewById(R.id.tv_display);

        for (int i = 0; i < btn_array.length; i++) {
            btn_array[i] = (Button) findViewById(btn_id[i]);
            btn_array[i].setOnClickListener(this);
        }

        clearVariables();
        showNumber(oper1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                ClickNumber("1");
                break;
            case R.id.btn2:
                ClickNumber("2");
                break;
            case R.id.btn3:
                ClickNumber("3");
                break;
            case R.id.btn4:
                ClickNumber("4");
                break;
            case R.id.btn5:
                ClickNumber("5");
                break;
            case R.id.btn6:
                ClickNumber("6");
                break;
            case R.id.btn7:
                ClickNumber("7");
                break;
            case R.id.btn8:
                ClickNumber("8");
                break;
            case R.id.btn9:
                ClickNumber("9");
                break;

            case R.id.btn0:
                if (flagAction == 0){
                    if (oper1.length()!=0)ClickNumber("0");
                }else{
                    if (!(oper2.equals("0")))ClickNumber("0");
                }
                break;

            case R.id.btnPlus:
                flagAction = 1;
                flagPoint = false;
                break;

            case R.id.btnMinus:
                flagAction = 2;
                flagPoint = false;
                break;

            case R.id.btnMulti:
                flagAction = 3;
                flagPoint = false;
                break;

            case R.id.btnDiv:
                flagAction = 4;
                flagPoint = false;
                break;

            case R.id.btnEqual:
                long op1;
                long op2;

                if (oper1.length()==0)oper1="0";
                if (oper2.length()==0)oper2="0";
                switch (flagAction){
                    case 1:
                        op1 = (long) (Double.parseDouble(oper1) * 1000000000);
                        op2 = (long) (Double.parseDouble(oper2) * 1000000000);
                        result = ((double) (op1 + op2))/1000000000;
                        showNumber(procNumber(result));
                        clearVariables();
                        break;
                    case 2:
                        op1 = (long) (Double.parseDouble(oper1) * 1000000000);
                        op2 = (long) (Double.parseDouble(oper2) * 1000000000);
                        result = ((double) (op1 - op2))/1000000000;
                        showNumber(procNumber(result));
                        clearVariables();
                        break;
                    case 3:
                        result = Double.parseDouble(oper1) * Double.parseDouble(oper2);
                        showNumber(procNumber(result));
                        clearVariables();
                        break;
                    case 4:
                        if (Double.parseDouble(oper2)!=0){
                            result = Double.parseDouble(oper1) / Double.parseDouble(oper2);
                            showNumber(procNumber(result));
                            clearVariables();
                            break;
                        } else {
                            Toast.makeText(this,"Ошибка",Toast.LENGTH_LONG).show();}
                }
                break;

            case R.id.btnC:{
                clearVariables();
                showNumber(oper1);
                break;
            }

            case R.id.btnSqr:{
                if (flagAction == 0){
                    if (oper1.length() == 0)oper1="0";
                    result = Math.pow(Double.parseDouble(oper1),2);
                    showNumber(procNumber(result));
                    clearVariables();
                }
                break;
            }

            case R.id.btnRadical:{
                if (flagAction==0){
                    if (oper1.length() == 0)oper1="0";
                    result = Math.sqrt(Double.parseDouble(oper1));
                    showNumber(procNumber(result));
                    clearVariables();
                }
                break;
            }

//            case R.id.btnPlusMinus:{
//                oper1 = -1*oper1;
//                showNumber(procNumber(oper1));
//            }


            case R.id.btnDot:
                if (!flagPoint){
                    if (flagAction==0){
                        if (oper1.length()!=0){
                            ClickNumber(".");
                        }else{ClickNumber("0.");}
                    }else {
                        if(oper2.length() != 0){
                            ClickNumber(".");
                        }else{ClickNumber("0.");}
                        flagPoint = true;
                    }
                }
                break;
        }
    }

    private String procNumber(double res) {
        String num;
        String prenum;
        long part_int = (long) (res - res%1);
        long part_frac = Math.round(res%1 * 1000000000);

        if (part_frac == 0) {
            num = Long.toString(part_int);
            if (num.length() > 10) {
                num = "owerflow";
            }
        }else{
            if (Long.toString(part_int).length() > 9){
                num = "owerflow";
            } else {
                prenum = Double.toString(res);
                if (prenum.length()>10){
                    num = prenum.substring(0, 9) + "..";
                }else {
                    num = prenum;
                }
            }

        }
        while(num.substring(num.length()-1, num.length()).equals("0") & num.contains(".")){
            num = num.substring(0, num.length()-1);
        }

        return num;
    }

    private void ClickNumber(String num) {
        if(flagAction == 0){
            if (checkOver(oper1)){
                Toast.makeText(this,"limit", Toast.LENGTH_LONG).show();
            }else {
                oper1 = oper1 + num;
                showNumber(oper1);
            }
        }else{
            if (checkOver(oper2)){
                Toast.makeText(this,"limit", Toast.LENGTH_LONG).show();
            }else {
                oper2 = oper2 + num;
                showNumber(oper2);
            }

        }
    }

    private boolean checkOver(String var) {
        int corr = 0;
        if (var.contains("."))corr = 1;
        if (var.length()-corr == 10){
            return true;
        }else {
            return false;
        }
    }

    private void showNumber(String number) {
        if (number.length()==0)number = "0";
        tvDisplay.setText(number);
        if (number.equals("error")){
            Toast.makeText(this, "overflow", Toast.LENGTH_LONG).show();
        }
    }

    private void clearVariables() {
        oper1 = "";
        oper2 = "";
        flagAction = 0;
        result = 0;
        flagPoint = false;
    }

}
