package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("lznXF5jsZ1M3LxCpFvQauMNuEHKoeyZJAv4g6ZGA")
                // if defined
                .clientKey("wmyPkbJ3GZDNOgPyh3hRg9rQlVZwCX85gMhxOWKU")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
