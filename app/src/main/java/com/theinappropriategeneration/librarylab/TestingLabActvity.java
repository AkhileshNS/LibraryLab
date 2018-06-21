package com.theinappropriategeneration.librarylab;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

public class TestingLabActvity extends AppCompatActivity {

    TableLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_lab);

        layout = findViewById(R.id.dynamicLayout);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View newView1 = inflater.inflate(R.layout.dynamicform_edittext_item, null);
        TextInputLayout tv = newView1.findViewById(R.id.dynamic_edittext_item);
        tv.setHint("It Works!");
        View newView2 = inflater.inflate(R.layout.dynamicform_edittext_item, null);
        TextInputLayout tv1 = newView2.findViewById(R.id.dynamic_edittext_item);
        tv1.setHint("It Works!");
        layout.addView(newView1, 0);

        EditText et = newView2.findViewById(R.id.et);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("Name")) {
                    Log.d("Handler","TableLayout");
                }
            }
        });

        layout.addView(newView2, 1);

        View newView3 = inflater.inflate(R.layout.dynamicform_title_item,null);
        TextView tv3 = newView3.findViewById(R.id.dynamic_title_item);
        tv3.setText("New Title");
        layout.addView(newView3);

    }
}
