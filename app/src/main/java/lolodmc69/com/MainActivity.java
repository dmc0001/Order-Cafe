package lolodmc69.com;

/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package lolodmc69.com
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText name = (EditText) findViewById(R.id.PersonName_view);
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCreamCheckBox_view);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolateCheckBox_view);
        String value = name.getText().toString();
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean chocolate = chocolateCheckBox.isChecked();
        int price = calculePrice(chocolate, hasWhippedCream);
        displayMessage(creatOrderSummary(value, quantity, price, hasWhippedCream, chocolate));

        Log.v("MainActivity", "Has whipped cream : " + hasWhippedCream);
        Log.v("MainActivity", "Chocolate : " + chocolate);
        Log.v("MainActivity", "Name : " + value);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL,"lorenzomedici2001@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order cafee for : " + name);
        intent.putExtra(Intent.EXTRA_TEXT, creatOrderSummary(value, quantity, price, hasWhippedCream, chocolate));

    }

    /**
     * Create summary of order.
     *
     * @param price of order
     * @param cream is whether or not the user wants  whipped cream topping
     * @param choco is whether or not the user wants  chocolate topping
     * @return text summary
     */
    private String creatOrderSummary(String name,int quantity,int price, boolean cream,boolean choco) {
        return("Name : "+name+"\n"+"Quantity : "+quantity+"\nWhipped cream : "+cream+"\nchocolate : "+choco+"\nTotal : "+price+" $\nThank you");
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        if(number<0) {number=0;}
        TextView quantityTextView = findViewById(R.id.number_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method is called to calcule final Price .
     */
private int calculePrice(boolean choco, boolean cream){
int basePrice=5;
if (cream){basePrice=basePrice+1;}
    if (choco){basePrice=basePrice+2;}
       return quantity*basePrice;
}

    /**
     * This method displays the given message on the screen.
     */
    private void displayMessage(String msg) {
        TextView messageTextView = findViewById(R.id.total_text_view);
        messageTextView.setText("" + msg);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view){

            quantity++;
            display(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view){
            quantity--;
            display(quantity);

    }


}