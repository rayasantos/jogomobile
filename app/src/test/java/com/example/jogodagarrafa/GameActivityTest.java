package com.example.jogodagarrafa;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.mockito.Mockito.*;

import missing.namespace.R;

public class GameActivityTest {

    private GameActivity gameActivity;
    private ImageView garrafa;

    @Before
    public void setUp() {
        gameActivity = Mockito.spy(GameActivity.class);
        garrafa = new ImageView(gameActivity);
    }

    @Test
    public void testGirarGarrafa() {
        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(1000);
        gameActivity.onCreate(null);
        gameActivity.girarGarrafa();
        verify(garrafa).startAnimation(any());
    }

    @Test
    public void testOnOptionsItemSelected_Settings() {
        MenuItem settingsItem = mock(MenuItem.class);
        when(settingsItem.getItemId()).thenReturn(R.id.action_settings);

        gameActivity.onOptionsItemSelected(settingsItem);

        Intent expectedIntent = new Intent(gameActivity, SettingsActivity.class);
        verify(gameActivity).startActivity(expectedIntent);
    }

    @Test
    public void testOnOptionsItemSelected_Share() {
        MenuItem shareItem = mock(MenuItem.class);
        when(shareItem.getItemId()).thenReturn(R.id.action_share);

        gameActivity.onOptionsItemSelected(shareItem);

        Intent expectedIntent = new Intent(Intent.ACTION_SEND);
        expectedIntent.putExtra(Intent.EXTRA_TEXT, "Estou jogando Jogo Da Garrafa!");
        expectedIntent.setType("text/plain");

        verify(gameActivity).startActivity(any(Intent.class));
    }
}
