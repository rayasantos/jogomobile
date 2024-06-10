package com.example.jogodagarrafa;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MainActivityTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = Mockito.spy(MainActivity.class);
    }

    @Test
    public void testMainActivityLaunch() {
        assertNotNull(mainActivity);
        assertEquals("MainActivity", mainActivity.getClass().getSimpleName());
    }

    @Test
    public void testNavigateToGameActivity() {
        mainActivity.onCreate(null);

        Intent expectedIntent = new Intent(mainActivity, GameActivity.class);
        mainActivity.findViewById(R.id.btn_start_game).performClick();

        verify(mainActivity).startActivity(expectedIntent);
    }

    @Test
    public void testNavigateToSettingsActivity() {
        MenuItem settingsItem = mock(MenuItem.class);
        when(settingsItem.getItemId()).thenReturn(R.id.action_settings);

        mainActivity.onOptionsItemSelected(settingsItem);

        Intent expectedIntent = new Intent(mainActivity, SettingsActivity.class);
        verify(mainActivity).startActivity(expectedIntent);
    }

    @Test
    public void testShareApp() {
        MenuItem shareItem = mock(MenuItem.class);
        when(shareItem.getItemId()).thenReturn(R.id.action_share);

        mainActivity.onOptionsItemSelected(shareItem);

        Intent expectedIntent = new Intent(Intent.ACTION_SEND);
        expectedIntent.putExtra(Intent.EXTRA_TEXT, "Estou jogando Jogo Da Garrafa!");
        expectedIntent.setType("text/plain");

        verify(mainActivity).startActivity(any(Intent.class));
    }

    @Test
    public void testNavigateToUserProfileActivity() {
        MenuItem userProfileItem = mock(MenuItem.class);
        when(userProfileItem.getItemId()).thenReturn(R.id.action_user_profile);

        mainActivity.onOptionsItemSelected(userProfileItem);

        Intent expectedIntent = new Intent(mainActivity, UserProfileActivity.class);
        verify(mainActivity).startActivity(expectedIntent);
    }
}
