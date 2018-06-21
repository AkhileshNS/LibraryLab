package com.theinappropriategeneration.librarylab;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class TestingLabActvity extends AppCompatActivity {

    //Constants
    private final static int RESULT_LOAD_IMG = 123;

    //Context
    Context context = TestingLabActvity.this; //TODO: Change this with the name of the activity

    //Layouts
    TableLayout layout;
    LayoutInflater inflater;

    //Views
    Button  submitButton;                                                 // Common
    ImageView image;                                                      // Product
    TextInputLayout titleBox,descBox,priceBox,quantityBox,idBox;          //
    EditText nameET ,descET, priceET, quantityET, idET;                           //
    Button conditionButton, supplierButton;                 //
    TextInputLayout nameBox,addressBox,emailBox, mobilenoBox, companyBox; // Supplier [or] Customer
    EditText name, address, email, mobileno, company;                     //

    //Results
    String imagePath = "";   //Product
    String condition = "";   //Supplier [or] Customer

    //Controllers
    int selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_lab);

        layout = findViewById(R.id.dynamicLayout);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //TODO: get selection from the application class

        setLayoutBasedOnSelection();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RESULT_LOAD_IMG){
            if(resultCode == RESULT_OK){
                Uri selectedImage = data.getData();

                final String path = "inventory/" + UUID.randomUUID() + ".jpg";
                //TODO: Uncomment this
                /* StorageReference ref = FirebaseStorage.getInstance().getReference().child(path);
                ref.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagePath = path;
                        Toast.makeText(getApplicationContext(),"Image Uploaded",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("ErrorHandler","Error uploading image");
                    }
                }); */
            }
        }

    }

    //==============================================================================================
    //==============================================================================================
    // Switch Cases

    public void setLayoutBasedOnSelection() {

        switch (selection) {
            case 0: createProductForm();
            break;

            case 1: createSupplierOrCustomerForm("Supplier");
            break;

            case 2: createSupplierOrCustomerForm("Customer");
            break;

            default:
                Log.d("ErrorHandler","Error choosing form");
        }

    }

    //==============================================================================================
    //==============================================================================================
    // All the form creation functions are here

    public void createProductForm() {

        //==========================================================================================
        //Declaration and creation of views to be populated

        ArrayList<View> elements = new ArrayList<>();
        int i=0;

        elements.add(inflater.inflate(R.layout.dynamicform_imageupload_item, layout, false));
        image = elements.get(i).findViewById(R.id.dynamic_imageupload_item);
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_title_item, layout, false));
        TextView tv = elements.get(i).findViewById(R.id.dynamic_title_item);
        tv.setText("Enter new Product Details");
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_line_item, layout, false));
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_edittext_item, layout, false));
        titleBox = elements.get(i).findViewById(R.id.dynamic_edittext_item);
        nameET = elements.get(i).findViewById(R.id.et);
        titleBox.setHint("Name");
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_edittext_item, layout, false));
        descBox = elements.get(i).findViewById(R.id.dynamic_edittext_item);
        descET = elements.get(i).findViewById(R.id.et);
        descET.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        descBox.setHint("Description");
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_dualedittexts_item, layout, false));
        priceBox = elements.get(i).findViewById(R.id.dynamic_edittext_item_1);
        priceET = elements.get(i).findViewById(R.id.et1);
        priceET.setInputType(InputType.TYPE_CLASS_NUMBER);
        priceBox.setHint("Price");
        quantityBox = elements.get(i).findViewById(R.id.dynamic_edittext_item_2);
        quantityET = elements.get(i).findViewById(R.id.et2);
        quantityET.setInputType(InputType.TYPE_CLASS_NUMBER);
        quantityBox.setHint("Quantity");
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_edittext_item, layout, false));
        idBox = elements.get(i).findViewById(R.id.dynamic_edittext_item);
        idET = elements.get(i).findViewById(R.id.et);
        idET.setInputType(InputType.TYPE_CLASS_NUMBER);
        idBox.setHint("Product ID");
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_subtext_item, layout, false));
        TextView tv1 = elements.get(i).findViewById(R.id.dynamic_subtext_item);
        tv1.setText("Note.long press here to open the barcode scanner");
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_button_item, layout, false));
        conditionButton = elements.get(i).findViewById(R.id.dynamic_button_item);
        conditionButton.setText("Choose Item Condition");
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_button_item, layout, false));
        supplierButton = elements.get(i).findViewById(R.id.dynamic_button_item);
        supplierButton.setText("Enter Supplier Details");
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_button_item, layout, false));
        submitButton = elements.get(i).findViewById(R.id.dynamic_button_item);
        submitButton.setText("Submit");

        //==========================================================================================
        //Add listeners for any of the items here

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create intent to Open Image applications like Gallery, Google Photos
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });

        conditionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items = {"No Issues","Under Repair","Damaged"};

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Choose Condition");
                alert.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        condition = items[item];
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });

        supplierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchChooserDialog();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: push product
                /* if(!imagePath.equals("") && !nameET.getText().toString().equals("") &&
                        priceET.getText().toString().equals("") && quantityET.getText().toString().equals("") &&
                        descET.getText().toString().equals("") && idET.getText().toString().equals("") &&
                        !condition.equals("") && supplier!=null) {

                    Product product = new Product();

                    //TODO: change parameters as required
                    product.setName(nameET.getText().toString());
                    product.setPrice(Integer.parseInt(elements.get(5).getResult1()));
                    product.setQuantity(Integer.parseInt(elements.get(6).getResult1()));
                    product.setDesc(elements.get(7).getResult1());
                    product.setID(elements.get(8).getResult1());
                    product.setCondition(elements.get(10).getResult1());
                    product.setSupplier(supplier);
                    product.setImagePath(elements.get(0).getResult1());
                    ((MyAppData) this.getApplication()).pushProduct(product);

                    Transaction transaction = new Transaction();
                    transaction.setProductName(product.getName());
                    transaction.setProductID(product.getID());
                    transaction.setDate(getCurrentDate());
                    transaction.setTime(getCurrentTime());
                    transaction.setIsSupply(1);
                    transaction.setSupplier(supplier);
                    transaction.setQuantity(product.getQuantity());
                    transaction.setPrice(product.getPrice());
                    ((MyAppData)this.getApplication()).pushTransaction(transaction);

                } */
            }
        });

        //==========================================================================================
        //Adding of views to the layout

        /* layout.addView(element0);
        layout.addView(element0_5);
        layout.addView(dash);
        layout.addView(element1);
        layout.addView(element2);
        layout.addView(element3);
        layout.addView(element4);
        layout.addView(element5);
        layout.addView(element6);
        layout.addView(element7);
        layout.addView(element8); */

        for (View v : elements){
            layout.addView(v);
        }

    }

    public void createSupplierOrCustomerForm(String s){

        //==========================================================================================
        //Declaration and creation of view to be populated

        ArrayList<View> elements = new ArrayList<>();
        int i = 0;

        elements.add(inflater.inflate(R.layout.dynamicform_title_item,layout,false));
        TextView tv1 = elements.get(i).findViewById(R.id.dynamic_title_item);
        tv1.setText("Enter " + s + " Details");
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_line_item,layout,false));
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_edittext_item, layout, false));
        nameBox = elements.get(i).findViewById(R.id.dynamic_edittext_item);
        name = elements.get(i).findViewById(R.id.et);
        nameBox.setHint("Name");
        name.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_edittext_item, layout, false));
        addressBox = elements.get(i).findViewById(R.id.dynamic_edittext_item);
        address = elements.get(i).findViewById(R.id.et);
        addressBox.setHint("Address");
        address.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_edittext_item, layout, false));
        emailBox = elements.get(i).findViewById(R.id.dynamic_edittext_item);
        email = elements.get(i).findViewById(R.id.et);
        emailBox.setHint("Email");
        email.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_edittext_item, layout, false));
        mobilenoBox = elements.get(i).findViewById(R.id.dynamic_edittext_item);
        mobileno = elements.get(i).findViewById(R.id.et);
        mobilenoBox.setHint("Mobile Number");
        mobileno.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_edittext_item, layout, false));
        companyBox = elements.get(i).findViewById(R.id.dynamic_edittext_item);
        company = elements.get(i).findViewById(R.id.et);
        companyBox.setHint("Supplier Company");
        company.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        i++;

        elements.add(inflater.inflate(R.layout.dynamicform_button_item, layout, false));
        submitButton = elements.get(i).findViewById(R.id.dynamic_button_item);
        submitButton.setText("Submit");

        //==========================================================================================
        //Add listeners for any of the items here

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: push customer/supplier depending on selection
                if (!name.getText().toString().equals("") && !address.getText().toString().equals("") &&
                        !mobileno.getText().toString().equals("") && !email.getText().toString().equals("") &&
                        !company.getText().toString().equals("")) {

                    if (selection==1) {

                        /* TODO: Change Parameters as required
                        Supplier supplier = new Supplier();
                        supplier.setName(elements.get(3).getResult1());
                        supplier.setAddress(elements.get(4).getResult1());
                        supplier.setEmail(elements.get(5).getResult1());
                        supplier.setMobileNo(elements.get(6).getResult1());
                        supplier.setCompany(elements.get(7).getResult1());
                        ((MyAppData)this.getApplication()).pushSupllier(supplier);
                        */

                    } else {

                        /* TODO: Change Parameters as required
                        Customer customer = new Customer();
                        customer.setName(elements.get(3).getResult1());
                        customer.setAddress(elements.get(4).getResult1());
                        customer.setEmail(elements.get(5).getResult1());
                        customer.setMobileNo(elements.get(6).getResult1());
                        customer.setCompany(elements.get(7).getResult1());
                        ((MyAppData)this.getApplication()).pushCustomer(customer);
                        */

                    }

                }

            }
        });

        //==========================================================================================
        //Adding of views to the layout

        for (View v : elements){
            layout.addView(v);
        }

    }

    //==============================================================================================
    //==============================================================================================
    // Helper Functions

    public String getCurrentDate() {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        return df.format(c);

    }

    public String getCurrentTime() {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa",Locale.ENGLISH);
        return  simpleDateFormat.format(c);

    }

    private void launchChooserDialog() {

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Choose Supplier");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_singlechoice);
        //TODO: Context variables have no effect here . Uncomment and replace with required Activity Name
        /* final ArrayList<Supplier> suppliers = ((MyAppData)FormActvity.this.getApplication()).getSuppliers();
        for (int i=0;i<suppliers.size();i++){
            arrayAdapter.add(suppliers.get(i).getName());
        }
        arrayAdapter.add("Add New");

        alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                assert strName != null;
                if(strName.equals("Add New")){
                    launchSupplierDialog();
                } else {
                    supplier = suppliers.get(which);
                }
            }
        });
        alert.show(); */

    }

    private void launchSupplierDialog() {

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText nameBox = new EditText(context);
        nameBox.setHint("Name");
        layout.addView(nameBox);

        final EditText addressBox = new EditText(context);
        addressBox.setHint("Address");
        layout.addView(addressBox);

        final EditText emailBox = new EditText(context);
        emailBox.setHint("Email");
        layout.addView(emailBox);

        final EditText MobileNoBox = new EditText(context);
        MobileNoBox.setHint("Mobile Number");
        layout.addView(MobileNoBox);

        final EditText CompanyBox = new EditText(context);
        CompanyBox.setHint("Company");
        layout.addView(CompanyBox);

        final AlertDialog alert = new AlertDialog.Builder(context)
                .setTitle("Enter Supplier Details")
                .setView(layout)
                .setPositiveButton("Create",null)
                .setNegativeButton("Cancel",null)
                .create();

        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                Button button1 = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(nameBox.getText().toString().equals("") || addressBox.getText().toString().equals("") ||
                                MobileNoBox.getText().toString().equals("") || emailBox.getText().toString().equals("") ||
                                CompanyBox.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_SHORT).show();
                        } else {

                            //TODO: Uncomment this
                            /*

                            supplier = new Supplier();
                            supplier.setName(nameBox.getText().toString());
                            supplier.setAddress(addressBox.getText().toString());
                            supplier.setCompany(CompanyBox.getText().toString());
                            supplier.setEmail(emailBox.getText().toString());
                            supplier.setMobileNo(MobileNoBox.getText().toString());
                            elements.get(11).setResult1(supplier.getName());

                            */

                            alert.dismiss();
                        }
                    }
                });
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert.dismiss();
                    }
                });
            }
        });

        alert.show();

    }

}
