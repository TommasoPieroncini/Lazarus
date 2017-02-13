package com.lazarus.app.lazarus;

import android.content.Context;

/**
 * Created by tommaso on 2/13/17.
 */
public class LazarusSingleton {

    private static LazarusSingleton ourInstance = new LazarusSingleton();

    private static Context currContext;

    private static String[] contacts;

    public static final int NUM_CONTACTS = 4;

    public static LazarusSingleton getInstance(Context c) {
        currContext = c;
        return ourInstance;
    }

    public static LazarusSingleton getInstance() {
        return ourInstance;
    }

    private LazarusSingleton() {
        contacts = new String[4];
        contacts[0] = "911";
    }

    public void populateContacts(String[] contactList) {
        int i = 0;
        for (String c : contactList) {
            contacts[i] = c;
            i++;
        }
    }

    public String[] getContacts() {
        return contacts;
    }
}
