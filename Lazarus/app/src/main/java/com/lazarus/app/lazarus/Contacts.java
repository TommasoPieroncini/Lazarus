package com.lazarus.app.lazarus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Contacts extends AppCompatActivity {

    private float x1,x2;
    static final int MIN_DISTANCE = 180;
    private EditText[] contacts = new EditText[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Button done = (Button) findViewById(R.id.done_contacts);
        EditText contact1 = (EditText) findViewById(R.id.contact1);
        EditText contact2 = (EditText) findViewById(R.id.contact2);
        EditText contact3 = (EditText) findViewById(R.id.contact3);
        EditText contact4 = (EditText) findViewById(R.id.contact4);
        contacts[0] = contact1;
        contacts[1] = contact2;
        contacts[2] = contact3;
        contacts[3] = contact4;

        int i = 0;
        for (String c : LazarusSingleton.getInstance().getContacts()) {
            if (c == null) {
                c = "";
            }
            contacts[i].setText(c);
            i++;
        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToHome();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX_left = x2 - x1;
                float deltaX_right = x1 - x2;
                if (deltaX_left > MIN_DISTANCE) {
                    // Left to Right swipe
                } else if (deltaX_right > MIN_DISTANCE) {
                    // Right to Left swipe
                    moveToHome();
                } else {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void moveToHome() {
        String[] textValues = new String[LazarusSingleton.NUM_CONTACTS];
        int i = 0;
        for (EditText et : contacts) {
            textValues[i] = et.getText().toString();
            i++;
        }
        LazarusSingleton.getInstance().populateContacts(textValues);
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
}
