package com.example.john5.emojiconvertor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button read;
    EditText name;
    EditText editText,emojiSelector;
    String text,value;
    char ch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int emoji = 0x1F601;
        final String em = String.valueOf(Character.toChars(emoji));

        read = (Button) findViewById(R.id.btnReadTxtFile);
        name = (EditText) findViewById(R.id.txtFile);
        editText = (EditText) findViewById(R.id.value);
        /*emojiSelector = (EditText) findViewById(R.id.emojiSelector);
        emojiSelector.setHint(getResources().getString(R.string.select_emoji)+ " " +  em + " )");
    */    read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BufferedReader bufferedReader;
                StringBuilder texte = new StringBuilder();

                    text = "";
                    value = editText.getText().toString();

                    texte.append('.');
                    texte.append('\n');
                for (int i = 0; i <value.length() ; i++) {
                    ch = value.charAt(i);
                    texte.append('\n');

                    if (ch == ((char) (ch & 95)) || Character.isDigit(ch)) {


                        try {

                            InputStream inputStream = getAssets().open(ch + ".txt");
                            int size = inputStream.available();
                            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            String line = bufferedReader.readLine();

                            while (line != null) {

                                texte.append(line);
                                texte.append('\n');

//                        name.setText(line);
                                line = bufferedReader.readLine();

                            }
                            bufferedReader.close();

                            text = String.valueOf(texte);
                            text = text.replaceAll("[*]", em);
                            name.setText(text);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{

                        try {

                            InputStream inputStream = getAssets().open("sml" + ch + ".txt");
                            int size = inputStream.available();
                            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            String line = bufferedReader.readLine();

                            while (line != null) {

                                texte.append(line);
                                texte.append('\n');

                                line = bufferedReader.readLine();

                            }
                            bufferedReader.close();
                            int emoji = 0x1F601;
                            String em = String.valueOf(Character.toChars(emoji));
                            text = String.valueOf(texte);
                            text = text.replaceAll("[*]",em);
                            name.setText(text);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });
    }
}
