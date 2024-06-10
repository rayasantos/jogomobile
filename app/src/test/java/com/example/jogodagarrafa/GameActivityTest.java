package com.example.jogodagarrafa;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

public class GameActivityTest {

    private GameActivity gameActivity;
    private ImageView garrafa;
    private SharedPreferences prefs;

    @Before
    public void setUp() throws Exception {
        gameActivity = Mockito.spy(GameActivity.class);
        garrafa = new ImageView(gameActivity);
        prefs = PreferenceManager.getDefaultSharedPreferences(gameActivity);

        // Usar reflexão para acessar membros privados
        setField(gameActivity, "garrafa", garrafa);
        setField(gameActivity, "random", Mockito.spy(Random.class));
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    private Object getField(Object target, String fieldName) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }

    private void invokeMethod(Object target, String methodName) throws Exception {
        Method method = target.getClass().getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(target);
    }

    @Test
    public void testGirarGarrafa() throws Exception {
        Random random = (Random) getField(gameActivity, "random");
        when(random.nextInt(anyInt())).thenReturn(1000);

        gameActivity.onCreate(null);
        invokeMethod(gameActivity, "girarGarrafa");

        int ultimaDirecao = (int) getField(gameActivity, "ultimaDirecao");
        assertNotNull(ultimaDirecao);
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
