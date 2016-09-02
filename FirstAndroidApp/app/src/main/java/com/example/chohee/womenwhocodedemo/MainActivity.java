package com.example.chohee.womenwhocodedemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveToReactionView();
    }

    private void moveToReactionView() {

        final Context context = this;

        mButton = (Button) findViewById(R.id.summit_button);
        editText = (EditText) findViewById(R.id.text_field);

        mButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, Reaction.class);
                        intent.putExtra("input_text", editText.getText().toString());
                        startActivity(intent);
                    }
                }
        );
    }
}
