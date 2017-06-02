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
    String oper1, oper2, oper3;
    int flagAction;
    double result;
    int[] btn_id;
    Button[] btn_array;
    boolean flagPoint; //точка
    boolean firstOperation;
    boolean op3on; //проверка введено ли второе число
    boolean stepOne; //проверка первое ли матем. действие

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
        stepOne = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tvDisplay", tvDisplay.getText().toString());
        outState.putString("oper1", oper1);
        outState.putString("oper2", oper2);
        outState.putString("oper3", oper3);
        outState.putInt("flagAction", flagAction);
        outState.putDouble("result", result);
        outState.putBoolean("flagPoint", flagPoint);
        outState.putBoolean("firstOperation", firstOperation);
        outState.putBoolean("op3on", op3on);
        outState.putBoolean("stepOne", stepOne);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tvDisplay.setText(savedInstanceState.getString("tvDisplay"));
        oper1 = savedInstanceState.getString("oper1");
        oper2 = savedInstanceState.getString("oper2");
        oper3 = savedInstanceState.getString("oper3");
        flagAction = savedInstanceState.getInt("flagAction");
        result = savedInstanceState.getDouble("result");
        flagPoint = savedInstanceState.getBoolean("flagPoint");
        firstOperation = savedInstanceState.getBoolean("firstOperation");
        op3on = savedInstanceState.getBoolean("op3on");
        stepOne = savedInstanceState.getBoolean("stepOne");
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
                    if (oper1.equals("0")){break;}
                    if (oper1.length()!=0)ClickNumber("0");
                }else{
                    if (!(oper2.equals("0")))ClickNumber("0");
                }
                break;

            case R.id.btnPlus:
                if (!op3on) {
                        flagAction = 1;
                        flagPoint = false;
                        break;
                    }else {
                    if (stepOne) {
                        equal();
                        stepOne = false;
                        flagAction = 1;
                        break;
                            }else {
                             nextEqual();
                             flagAction = 1;
                        break;
                        }

                    }

            case R.id.btnMinus:
                if (!op3on) {
                    flagAction = 2;
                    flagPoint = false;
                    break;
                }else {
                    if (stepOne) {
                        equal();
                        stepOne = false;
                        flagAction = 2;
                        break;
                    }else {
                        nextEqual();
                        flagAction = 2;
                        break;
                    }

                }

            case R.id.btnMulti:
                if (!op3on) {
                    flagAction = 3;
                    flagPoint = false;
                    break;
                }else {
                    if (stepOne) {
                        equal();
                        stepOne = false;
                        flagAction = 3;
                        break;
                    }else {
                        nextEqual();
                        flagAction = 3;
                        break;
                    }

                }

            case R.id.btnDiv:
                if (!op3on) {
                    flagAction = 4;
                    flagPoint = false;
                    break;
                }else {
                    if (stepOne) {
                        equal();
                        stepOne = false;
                        flagAction = 4;
                        break;
                    }else {
                        nextEqual();
                        flagAction = 4;
                        break;
                    }

                }

            case R.id.btnEqual:
                if (stepOne){
                    equal();
                    break;
                }else {
                    nextEqual();
                    break;
                }

            case R.id.btnC:{
                clearVariables();
                showNumber(oper1);
                break;
            }

            case R.id.btnSqr:{
                if (!op3on) {
                    if (flagAction == 0) {
                        if (oper1.length() == 0) oper1 = "0";
                        result = Math.pow(Double.parseDouble(oper1), 2);
                        showNumber(procNumber(result));
                        op3on = true;
                    }
                    break;
                }else {
                    result = Math.pow(result, 2);
                    showNumber(procNumber(result));
                    break;
                }
            }

            case R.id.btnRadical:{
                if (!op3on) {
                    if (flagAction == 0) {
                        if (oper1.length() == 0) oper1 = "0";
                        result = Math.sqrt(Double.parseDouble(oper1));
                        showNumber(procNumber(result));
                        op3on = true;
                    }
                    break;
                }else {
                    result = Math.sqrt(result);
                    showNumber(procNumber(result));
                    break;
                }
            }

            case R.id.btnPlusMinus:{
                if (!op3on) {
                    if (flagAction == 0) {
                        if (oper1.length() == 0) oper1 = "0";
                        double plusMinus = Double.parseDouble(oper1);
                        plusMinus = plusMinus * -1;
                        oper1 = String.valueOf(plusMinus);
                        showNumber(procNumber(Double.parseDouble(oper1)));
                        break;
                    } else {
                        if (oper2.length() == 0) oper2 = "0";
                        double plusMinus = Double.parseDouble(oper2);
                        plusMinus = plusMinus * -1;
                        oper2 = String.valueOf(plusMinus);
                        showNumber(procNumber(Double.parseDouble(oper2)));
                        break;
                    }
                }else {
                    result = result * -1;
                    showNumber(procNumber(result));
                    break;
                }
            }


            case R.id.btnDot:
                if (!flagPoint){
                    if (flagAction==0){             //проверяем первое число или второе
                        if (oper1.length()!=0){     //если что-то введено
                            ClickNumber(".");       //ставим точку
                        }else{ClickNumber("0.");}
                        flagPoint = true;
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

    private void equal() {

        long op1;
        long op2;

        if (oper1.length()==0)oper1="0";
        if (oper2.length()==0)oper2="0";
        switch (flagAction){
            case 1:
                op1 = (long) (Double.parseDouble(oper1) * 1000000000);
                op2 = (long) (Double.parseDouble(oper2) * 1000000000);

                if (firstOperation){
                    result = ((double) (op1 + op2))/1000000000;
                } else {
                    result = result +((double) (op2/1000000000));
                }
                showNumber(procNumber(result));
                firstOperation = false;
                op3on = true;
                break;

            case 2:
                op1 = (long) (Double.parseDouble(oper1) * 1000000000);
                op2 = (long) (Double.parseDouble(oper2) * 1000000000);
                if (firstOperation){
                    result = ((double) (op1 - op2))/1000000000;
                } else {
                    result = result -((double) (op2/1000000000));
                }
                showNumber(procNumber(result));
                firstOperation = false;
                op3on = true;
                break;

            case 3:
                if (firstOperation){
                    result = Double.parseDouble(oper1) * Double.parseDouble(oper2);
                } else {
                    result = result * Double.parseDouble(oper2);
                }
                showNumber(procNumber(result));
                firstOperation = false;
                op3on = true;
                break;

            case 4:
                if (Double.parseDouble(oper2)!=0){
                    if (firstOperation){
                        result = Double.parseDouble(oper1) / Double.parseDouble(oper2);
                    }else {
                        result = result / Double.parseDouble(oper2);
                    }
                    showNumber(procNumber(result));
                    firstOperation = false;
                    op3on = true;
                    break;
                } else {
                    Toast.makeText(this,"делить на 0 нельзя товарищь",Toast.LENGTH_LONG).show();}
        }
    }

    private void nextEqual() {
        long op1;
        long op2;

        if (oper3.length()==0)oper3="0";

        switch (flagAction){
            case 1:
                op1 = (long) (result * 1000000000);
                op2 = (long) (Double.parseDouble(oper3) * 1000000000);
                result = ((double) (op1 + op2))/1000000000;
                showNumber(procNumber(result));
                oper3 = "";
                break;

            case 2:
                op1 = (long) (result * 1000000000);
                op2 = (long) (Double.parseDouble(oper3) * 1000000000);
                result = ((double) (op1 - op2))/1000000000;
                showNumber(procNumber(result));
                oper3 = "";
                break;

            case 3:
                result = result * Double.parseDouble(oper3);
                showNumber(procNumber(result));
                firstOperation = false;
                oper3 = "";
                break;

            case 4:
                if (Double.parseDouble(oper3)!=0){
                    result = result / Double.parseDouble(oper3);
                    showNumber(procNumber(result));
                    firstOperation = false;
                    oper3 = "";
                    break;

                } else {
                    Toast.makeText(this,"делить на 0 нельзя товарищь",Toast.LENGTH_LONG).show();}
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
        if (firstOperation) {
            if (flagAction == 0) {
                if (checkOver(oper1)) {
                    Toast.makeText(this, "limit", Toast.LENGTH_LONG).show();
                } else {
                    oper1 = oper1 + num;
                    showNumber(oper1);
                }
            } else {
                if (checkOver(oper2)) {
                    Toast.makeText(this, "limit", Toast.LENGTH_LONG).show();
                } else {
                    oper2 = oper2 + num;
                    showNumber(oper2);
                    op3on = true;
                }

            }
        }else {
            if (checkOver(oper3)) {
                Toast.makeText(this, "limit", Toast.LENGTH_LONG).show();
            } else {
                oper3 = oper3 + num;
                showNumber(oper3);
                op3on = true;
            }
        }
    }

    private boolean checkOver(String var) {
        int corr = 0;
        if (var.contains("."))corr = 1;
        return var.length() - corr == 10;
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
        oper3 = "";
        op3on = false;
        flagAction = 0;
        result = 0;
        flagPoint = false;
        firstOperation = true;
        stepOne = true;
    }

}
