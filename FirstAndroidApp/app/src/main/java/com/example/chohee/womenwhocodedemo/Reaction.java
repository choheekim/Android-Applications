package com.example.chohee.womenwhocodedemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Chohee on 8/29/16.
 */
public class Reaction extends AppCompatActivity {

    Button backButton;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reaction);

        textView = (TextView) findViewById(R.id.display_input);
        textView.setText(getIntent().getStringExtra("input_text"));

        moveToReactionView();
    }

    private void moveToReactionView() {

        final Context context = this;

        backButton = (Button) findViewById(R.id.back_button);

        backButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context,MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }



}
