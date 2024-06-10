package com.example.jogodagarrafa;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // Aqui você pode acessar as preferências e aplicar as configurações necessárias
        // Exemplo:
        // String themeChoice = prefs.getString("theme_choice", "default");
    }
}
