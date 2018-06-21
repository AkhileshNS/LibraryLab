package com.theinappropriategeneration.librarylab;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

public class TestingLabActvity extends AppCompatActivity {

    TableLayout layout;
    LayoutInflater inflater;

    ImageView image;
    TextInputLayout titleBox,descBox,priceBox,quantityBox,idBox;
    EditText titleET, descET, priceET, quantityET, idET;
    Button conditionButton, supplierButton, submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_lab);

        layout = findViewById(R.id.dynamicLayout);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        createProductForm();

    }

    public void createProductForm() {

        //==========================================================================================
        //Declaration and creation of view to be populated

        View element0 = inflater.inflate(R.layout.dynamicform_imageupload_item, null );
        image = element0.findViewById(R.id.dynamic_imageupload_item);

        View element1 = inflater.inflate(R.layout.dynamicform_edittext_item, null);
        titleBox = element1.findViewById(R.id.dynamic_edittext_item);
        titleBox.setHint("Name");

        View element2 = inflater.inflate(R.layout.dynamicform_edittext_item, null);
        descBox = element2.findViewById(R.id.dynamic_edittext_item);
        descET = element2.findViewById(R.id.et);
        descET.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        descBox.setHint("Description");

        View element3 = inflater.inflate(R.layout.dynamicform_dualedittexts_item, null);
        priceBox = element3.findViewById(R.id.dynamic_edittext_item_1);
        priceET = element3.findViewById(R.id.et1);
        priceET.setInputType(InputType.TYPE_CLASS_NUMBER);
        priceBox.setHint("Price");
        quantityBox = element3.findViewById(R.id.dynamic_edittext_item_2);
        quantityET = element3.findViewById(R.id.et2);
        quantityET.setInputType(InputType.TYPE_CLASS_NUMBER);
        quantityBox.setHint("Quantity");

        View element4 = inflater.inflate(R.layout.dynamicform_edittext_item, null);
        idBox = element4.findViewById(R.id.dynamic_edittext_item);
        idET = element4.findViewById(R.id.et);
        idET.setInputType(InputType.TYPE_CLASS_NUMBER);
        idBox.setHint("Product ID");

        View element5 = inflater.inflate(R.layout.dynamicform_subtext_item, null);
        TextView tv = element5.findViewById(R.id.dynamic_subtext_item);
        tv.setText("Note.long press here to open the barcode scanner");

        View element6 = inflater.inflate(R.layout.dynamicform_button_item, null);
        conditionButton = element6.findViewById(R.id.dynamic_button_item);
        conditionButton.setText("Choose Item Condition");

        View element7 = inflater.inflate(R.layout.dynamicform_button_item, null);
        supplierButton = element7.findViewById(R.id.dynamic_button_item);
        supplierButton.setText("Enter Supplier Details");

        View element8 = inflater.inflate(R.layout.dynamicform_button_item, null);
        submitButton = element8.findViewById(R.id.dynamic_button_item);
        submitButton.setText("Submit");

        //==========================================================================================
        //Adding of views to the layout

        layout.addView(element0);
        layout.addView(element1);
        layout.addView(element2);
        layout.addView(element3);
        layout.addView(element4);
        layout.addView(element5);
        layout.addView(element6);
        layout.addView(element7);
        layout.addView(element8);

    }

}
