package com.example.pallavinishanth.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    static int numberOfCoffees = 0;
    String message = "Free";
    static int price=0;
    String Name ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText name = (EditText) findViewById(R.id.editText);
        Name = name.getText().toString();

        CheckBox WCCheckbox = (CheckBox) findViewById(R.id.Whippedcream_checkBox);

        boolean hasWC=  WCCheckbox.isChecked();
        //display(numberOfCoffees);
        message = "Name: " + Name;

        message +="\nAdd Whipped Cream? " + hasWC;

        message +="\nQuantity:  "+ numberOfCoffees;

        if(hasWC){

            price = (numberOfCoffees*5) + (1 * numberOfCoffees) ;
        }else {
            price = numberOfCoffees*5;
        }


        message +="\nTotal: $"+ price;
        message += "\nThank You!";
        //displayMessage(message);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, "");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Java Order to " + Name);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if(intent.resolveActivity(getPackageManager()) != null){

            startActivity(intent);
        }

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    public void increment(View view){

        numberOfCoffees++;
        display(numberOfCoffees);
    }

    public void decrement(View view){

        numberOfCoffees--;
        display(numberOfCoffees);
    }
}
