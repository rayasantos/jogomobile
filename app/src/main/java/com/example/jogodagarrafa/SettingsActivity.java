package com.example.jogodagarrafa;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import missing.namespace.R;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Registrar o ouvinte de mudança de preferências
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Método chamado quando uma preferência é alterada
        if (key.equals("theme_choice")) {
            // Exemplo: Lidar com a mudança no tema
            String themeChoice = sharedPreferences.getString(key, "default");
            // Faça algo com o novo valor do tema...
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remover o ouvinte de mudança de preferências
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onPreferenceChange(SharedPreferences sharedPreferences, String themeChoice, String dark) {
    }
}
