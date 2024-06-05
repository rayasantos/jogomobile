package com.example.jogodagarrafa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup radioGroupTheme;
    private RadioButton radioDefault;
    private RadioButton radioLight;
    private RadioButton radioDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Carregar tema das preferências
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String themeChoice = prefs.getString("theme_choice", "default");
        switch (themeChoice) {
            case "light":
                setTheme(R.style.Theme_JogoDaGarrafa_Light);
                break;
            case "dark":
                setTheme(R.style.Theme_JogoDaGarrafa_Dark);
                break;
            default:
                setTheme(R.style.Theme_JogoDaGarrafa);
                break;
        }

        setContentView(R.layout.activity_settings);

        // Inicializar as Views
        radioGroupTheme = findViewById(R.id.radioGroup_theme);
        radioDefault = findViewById(R.id.radio_default);
        radioLight = findViewById(R.id.radio_light);
        radioDark = findViewById(R.id.radio_dark);

        // Carregar as preferências
        switch (themeChoice) {
            case "light":
                radioLight.setChecked(true);
                break;
            case "dark":
                radioDark.setChecked(true);
                break;
            default:
                radioDefault.setChecked(true);
                break;
        }

        // Configurar o listener de mudança de tema
        radioGroupTheme.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radio_light:
                    setThemeChoice("light");
                    break;
                case R.id.radio_dark:
                    setThemeChoice("dark");
                    break;
                default:
                    setThemeChoice("default");
                    break;
            }
        });
    }

    private void setThemeChoice(String themeChoice) {
        // Salvar a escolha do tema nas preferências
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString("theme_choice", themeChoice);
        editor.apply();
        // Reiniciar a atividade para aplicar o novo tema
        recreate();
    }
}









