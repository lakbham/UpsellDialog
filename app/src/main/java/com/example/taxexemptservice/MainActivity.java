package com.example.taxexemptservice;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.lang.*;
import androidx.appcompat.app.AlertDialog;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setIcon(R.drawable.upsellicon);
       // Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setTitle("Upsell Demo");
        getSupportActionBar().setSubtitle("Worry free Shopping");
       // getSupportActionBar().setIcon(R.drawable.upsellicon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // UI widgets button and

        Button bOpenAlertDialog = findViewById(R.id.openAlertDialogButton);
        final TextView tvSelectedItemsPreview = findViewById(R.id.selectedItemPreview);

        // initialise the list items for the alert dialog
        final String[] listItems = new String[]{"Add 2 Glossy B & W pages for $1", "Add 2 Glossy Color pages for $2 ", "Add 5 Glossy B & W pages for $2", "Add 2 Glossy Color pages for $3"};
        final boolean[] checkedItems = new boolean[listItems.length];

        // copy the items from the main list to the selected item list
        // Selected items should be previewed
        final List<String> selectedItems = Arrays.asList(listItems);

        // handle the Open Alert Dialog button
        bOpenAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //it unchecks/refreshes the chkboxes
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;
                }

                // initially set the null for the text preview
                tvSelectedItemsPreview.setText(null);

                // initialise the alert dialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // set the title for the alert dialog
                builder.setTitle("Choose Items to Upsell");

                // set the icon for the alert dialog
                builder.setIcon(R.drawable.upsellicon);

                // now this is the function which sets the alert dialog for multiple item selection ready
                builder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked;
                        String currentItem = selectedItems.get(which);
                    }
                });

                // alert dialog shouldn't be cancellable
                builder.setCancelable(false);

                // handle the positive button of the dialog
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvSelectedItemsPreview.setText("The selected items are, " + "\n\n");
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {
                                tvSelectedItemsPreview.setText("\n"+tvSelectedItemsPreview.getText() + selectedItems.get(i) + ",\n");
                            }
                        }
                    }
                });

                // handle the negative button of the alert dialog
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                        }
                    }
                });

                // handle the neutral button of the dialog to clear
                // the selected items boolean checkedItem
                builder.setNeutralButton("CLEAR ALL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                        }
                    }
                });

                // create the builder
                builder.create();

                // create the alert dialog with the
                // alert dialog builder instance
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Objects.requireNonNull(alertDialog.getWindow()).setGravity(Gravity.CENTER_VERTICAL);    }
        });
    }
}










