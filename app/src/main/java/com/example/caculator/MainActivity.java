package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add24ButtonsForMe();
    }

    private void add24ButtonsForMe() {
        LinearLayout[]  linearLayouts = add6LinearLayout();
        add4Buttons(linearLayouts[0], "%", "CE", "C", "BkSp");
        add4Buttons(linearLayouts[1], "1/x", "^2", "sqrt", "/");
        add4Buttons(linearLayouts[2], "7", "8", "9", "x");
        add4Buttons(linearLayouts[3], "4", "5", "6", "-");
        add4Buttons(linearLayouts[4], "1", "2", "3", "+");
        add4Buttons(linearLayouts[5], "+/-", "0", ",", "=");

        addLayoutsToMain(linearLayouts);
    }

    private void addLayoutsToMain(LinearLayout[] linearLayouts) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutMain);

        for (int i = 0; i < linearLayouts.length; i++)
            linearLayout.addView(linearLayouts[i]);
    }

    private void add4Buttons(LinearLayout linearLayout, String s0, String s1, String s2, String s3) {
        linearLayout.addView(createButton(s0));
        linearLayout.addView(createButton(s1));
        linearLayout.addView(createButton(s2));
        linearLayout.addView(createButton(s3));
    }

    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setId(genNextId());

        // Tạo một LayoutParams để đặt layout_weight
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                0, // Chiều rộng sẽ được tính toán bằng cách sử dụng layout_weight
                LinearLayout.LayoutParams.MATCH_PARENT // Chiều cao có thể tự động
        );
        layoutParams.weight = 1; // Đặt layout_weight cho button (mỗi button chiếm 1 phần diện tích bằng nhau)
        button.setLayoutParams(layoutParams);

        return button;
    }

    private int nextId = 65000;
    private int genNextId() {
        return nextId++;
    }

    private LinearLayout[] add6LinearLayout() {
        LinearLayout[] linearLayouts = new LinearLayout[6];
        for (int i = 0; i < linearLayouts.length; i++)
        {
            linearLayouts[i] = new LinearLayout(this);
            linearLayouts[i].setOrientation(LinearLayout.HORIZONTAL);
        }
        return linearLayouts;
    }
}