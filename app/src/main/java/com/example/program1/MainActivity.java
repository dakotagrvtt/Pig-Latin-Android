package com.example.program1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*BONUSES DONE: Changed app name to "Pig Latin" in strings.xml file, decode Pig Latin,
       correct capitalization, clear button, and different way of changing text (uses caesar cipher).
     */
    public void displayClick(View v){
        TextView displayTV = findViewById(R.id.pigTV);
        EditText displayET = findViewById(R.id.pigET);
        //create String for plaintext
        String plainText = displayET.getText().toString();

        //Pig Latin cipher
        //If word starts with a vowel, apply "way" at the end of the word
        if(plainText.charAt(0) == 'a' || plainText.charAt(0) == 'e' || plainText.charAt(0) == 'i' || plainText.charAt(0) == 'o' ||
                plainText.charAt(0) == 'u' || plainText.charAt(0) == 'A' ||  plainText.charAt(0) == 'E' ||
                plainText.charAt(0) == 'I' ||  plainText.charAt(0) == 'O' ||  plainText.charAt(0) == 'U'){
            plainText = "Pig Latin: " + plainText + "way";
        }
        else{
            //Uses regex in .split to keep vowel while splitting the word into two
            String[] consonantText = plainText.split("(?=[aeiouAEIOU])", 2);
            //1st part of word up to the first vowel
            String begin = consonantText[0];
            byte[] beginASCII = begin.getBytes();
            //the rest of the word
            String end = consonantText[1].toLowerCase();
            byte[] endASCII = end.getBytes();
            /*using ASCII, determine if the first letter of the 1st part of the word is capitalized
              -if yes, capitalize the beginning of the 2nd section of the split word. This will be
                the beginning of the word when converted to Pig Latin
              -otherwise print the word without capitalization
             */
            if(beginASCII[0] > 65 && beginASCII[0] < 90) {
                endASCII[0] -= 32;
                String endChar = new String(endASCII);
                plainText = "Pig Latin: " + endChar + begin.toLowerCase() + "ay";
            }
            else{
                plainText = "Pig Latin: " + end + begin.toLowerCase() + "ay";
            }
        }
        displayTV.setText(plainText);
    }

    //decoding pig latin
    /*
    -checks text in Pig Latin's Edit View for if 3rd to last letter is 'w', then just removes "way"
    from the string.
    -else simply extracts 3rd to last letter and concatenates it to the front of the original string
     */
    public void decodePig(View d){
        TextView displayTV = findViewById(R.id.pigTV);
        EditText displayET = findViewById(R.id.pigET);

        String pigText = displayET.getText().toString().toLowerCase();
        int pigV = pigText.length() - 3;
        if(pigText.charAt(pigV) == 'w'){
            String[] split = pigText.split("(?=way)",2);
            pigText = pigText.replace(split[1], "");
        }
        else{
            char consonant = pigText.charAt(pigText.length() - 3);
            pigText = consonant + pigText.substring(0, pigText.length()-3);
        }
        displayTV.setText("Pig Latin: " + pigText);
    }

    //Caesar cipher
    public void caesarButton(View ci){
        TextView displayTV = findViewById(R.id.caesarTV);
        EditText displayET = findViewById(R.id.caesarET);

        String caesarText = displayET.getText().toString();
        caesarText = caesarText.toLowerCase();
        byte[] caesarChar = caesarText.getBytes();
        for(int i = 0; i < caesarChar.length; i++){
            caesarChar[i]++;
            if(caesarChar[i] > 122){
                caesarChar[i] -= 26;
            }
        }
        String caesarTextNew = new String(caesarChar);
        displayTV.setText("Caesar Cipher +1: " + caesarTextNew);
    }

    //CLEAR: reverts text views back to original text upon opening the app
    public void clearClick(View c){
        TextView displayTV = findViewById(R.id.pigTV);
        TextView displayTV2 = findViewById(R.id.caesarTV);
        displayTV.setText("Pig Latin: ");
        displayTV2.setText("Caesar Cipher +1: ");
    }
}