package com.example.jogodagarrafa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ImageView garrafa;
    private int ultimaDirecao;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Carregar tema das preferências
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        garrafa = findViewById(R.id.garrafa);
        garrafa.setOnClickListener(v -> girarGarrafa());
    }

    private void girarGarrafa() {
        Random random = new Random();
        int novaDirecao = random.nextInt(1800);
        float eixoX = garrafa.getWidth() / 2;
        float eixoY = garrafa.getHeight() / 2;

        int speed = 2500; // Velocidade padrão ou obtida das preferências, se aplicável

        Animation girar = new RotateAnimation(ultimaDirecao, novaDirecao, eixoX, eixoY);
        girar.setDuration(speed);
        girar.setFillAfter(true);

        ultimaDirecao = novaDirecao;
        garrafa.startAnimation(girar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Estou jogando Jogo Da Garrafa!");
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}















