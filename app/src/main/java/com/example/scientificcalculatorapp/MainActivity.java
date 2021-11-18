package com.example.scientificcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import com.example.scientificcalculatorapp.databinding.ActivityMainBinding;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.etNumbers.setShowSoftInputOnFocus(false);


        binding.btnZero.setOnClickListener(v -> updateText(getResources().getText(R.string.zeroText).toString()));
        binding.btnOne.setOnClickListener(v -> updateText(getResources().getText(R.string.oneText).toString()));
        binding.btnTwo.setOnClickListener(v -> updateText(getResources().getText(R.string.twoText).toString()));
        binding.btnThree.setOnClickListener(v -> updateText(getResources().getText(R.string.threeText).toString()));
        binding.btnFour.setOnClickListener(v -> updateText(getResources().getText(R.string.fourText).toString()));
        binding.btnFive.setOnClickListener(v -> updateText(getResources().getText(R.string.fiveText).toString()));
        binding.btnSix.setOnClickListener(v -> updateText(getResources().getText(R.string.sixText).toString()));
        binding.btnSeven.setOnClickListener(v -> updateText(getResources().getText(R.string.sevenText).toString()));
        binding.btnEight.setOnClickListener(v -> updateText(getResources().getText(R.string.eightText).toString()));
        binding.btnNine.setOnClickListener(v -> updateText(getResources().getText(R.string.nineText).toString()));

        binding.btnSum.setOnClickListener(v -> updateText(getResources().getText(R.string.addText).toString()));
        binding.btnSub.setOnClickListener(v -> updateText(getResources().getText(R.string.subtractText).toString()));
        binding.btnMult.setOnClickListener(v -> updateText(getResources().getText(R.string.multiplyText).toString()));
        binding.btnDiv.setOnClickListener(v -> updateText(getResources().getText(R.string.divideText).toString()));
        binding.btnDot.setOnClickListener(v -> updateText(getResources().getText(R.string.decimalText).toString()));

        //equal

        binding.btnEqual.setOnClickListener(v -> {
            String userExp=binding.etNumbers.getText().toString();
            userExp=userExp.replaceAll(getResources().getText(R.string.divideText).toString(),"/");
            userExp=userExp.replaceAll(getResources().getText(R.string.multiplyText).toString(),"*");


            Expression exp=new Expression(userExp);
            String res=String.valueOf(exp.calculate());

            binding.tvShowRes.setText(userExp);
            binding.etNumbers.setText(res);
            binding.etNumbers.setSelection(res.length());


        });

        binding.btnClear.setOnClickListener(v -> {
            binding.etNumbers.setText("");
            binding.tvShowRes.setText("");
        });

        binding.btnOpenBracket.setOnClickListener(v -> updateText(getResources().getText(R.string.parenthesesOpenText).toString()));
        binding.btnCloseBracket.setOnClickListener(v -> updateText(getResources().getText(R.string.parenthesesCloseText).toString()));

        binding.btnDelete.setOnClickListener(v -> {
            int cursorPos=binding.etNumbers.getSelectionStart();
            int textLen=binding.etNumbers.getText().length();
            if(cursorPos!=0 && textLen!=0){
                SpannableStringBuilder builder=(SpannableStringBuilder) binding.etNumbers.getText();
                builder.replace(cursorPos-1,cursorPos,"");
                binding.etNumbers.setText(builder);
                binding.etNumbers.setSelection(cursorPos-1);
            }
        });


        binding.btnSign.setOnClickListener(v -> updateText("sin("));
        binding.btnCos.setOnClickListener(v -> updateText("cos("));
        binding.btnTan.setOnClickListener(v -> updateText("tan("));

        binding.btnArcsign.setOnClickListener(v -> updateText("arcsin("));
        binding.btnArccos.setOnClickListener(v -> updateText("arccos("));
        binding.btnArctan.setOnClickListener(v -> updateText("arctan("));

        binding.btnLn.setOnClickListener(v -> updateText("ln("));
        binding.btnLog.setOnClickListener(v -> updateText("log("));
        binding.btnSqrt.setOnClickListener(v -> updateText("sqrt("));

        binding.btnAbs.setOnClickListener(v -> updateText("abs("));
        binding.btnPi.setOnClickListener(v -> updateText("pi"));
        binding.btnExp.setOnClickListener(v -> updateText("e"));

        binding.btnSquare.setOnClickListener(v -> updateText("^(2)"));
        binding.btnXy.setOnClickListener(v -> updateText("^("));
        binding.btnPrime.setOnClickListener(v -> updateText("ispr("));


    }

    private void updateText(String str){
        String oldStr=binding.etNumbers.getText().toString();
        int cursorPos=binding.etNumbers.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        binding.etNumbers.setText(String.format("%s%s%s",leftStr,str,rightStr));
        binding.etNumbers.setSelection(cursorPos+str.length());

    }

}