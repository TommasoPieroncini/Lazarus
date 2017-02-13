package com.lazarus.app.lazarus;

import android.bluetooth.BluetoothClass;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //colors:
    //dark: #004DE6
    //light: #7FD6FF

    private float x1,x2;
    static final int MIN_DISTANCE = 180;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton bluetooth = (ImageButton) findViewById(R.id.bluetooth);
        ImageButton contacts = (ImageButton) findViewById(R.id.contacts);

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToContacts();
            }
        });

        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToBluetooth();
                //Toast.makeText(getBaseContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
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
                    moveToContacts();
                } else if (deltaX_right > MIN_DISTANCE) {
                    // Right to Left swipe
                    moveToBluetooth();
                } else {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lazarus_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void moveToContacts() {
        Intent intent = new Intent(getBaseContext(), Contacts.class);
        startActivity(intent);
    }

    private void moveToBluetooth() {
        ComponentName cn = new ComponentName("com.android.settings",
                "com.android.settings.bluetooth.BluetoothSettings");
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.setComponent(cn);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity( intent);
    }
}

/*<LinearLayout
        android:orientation="horizontal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:background="#000000">

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="100dp"
            android:layout_weight="0.5"
            app:srcCompat="@android:drawable/stat_sys_phone_call"
            android:layout_alignParentBottom="true"
            android:layout_alignParentLeft="true"
            android:layout_alignParentStart="true"
            android:id="@+id/contacts"
            android:paddingTop="5dp"
            android:paddingBottom="5dp"
            android:background="#004DE6"
            android:layout_marginRight="2dp" />

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="100dp"
            android:layout_weight="0.5"
            app:srcCompat="@android:drawable/stat_sys_data_bluetooth"
            android:layout_alignParentBottom="true"
            android:layout_alignParentRight="true"
            android:layout_alignParentEnd="true"
            android:id="@+id/bluetooth"
            android:paddingTop="5dp"
            android:paddingBottom="5dp"
            android:background="#004DE6"
            android:layout_marginLeft="2dp" />

    </LinearLayout>*/