package com.example.jogodagarrafa;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SettingsActivityTest {

    private SettingsActivity settingsActivity;

    @Before
    public void setUp() {
        settingsActivity = Mockito.spy(SettingsActivity.class);
    }

    @Test
    public void testLoadSettings() {
        // Configuração do mock do SharedPreferences.Editor
        Editor editor = mock(Editor.class);
        when(editor.putString(anyString(), anyString())).thenReturn(editor); // Mock do método putString

        // Configuração do mock do SharedPreferences
        SharedPreferences sharedPreferences = mock(SharedPreferences.class);
        when(sharedPreferences.edit()).thenReturn(editor); // Mock do método edit

        // Simulando a chamada do método onCreate
        settingsActivity.onCreate(null);

        // Simulando a alteração de uma configuração
        settingsActivity.onPreferenceChange(sharedPreferences, "theme_choice", "dark");

        // Verificando se a configuração foi salva corretamente
        verify(editor).putString("theme_choice", "dark");
        verify(editor).apply();
    }
}
