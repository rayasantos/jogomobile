import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SettingsActivityTest {

    private SettingsActivity settingsActivity;

    @Before
    public void setUp() {
        settingsActivity = Mockito.spy(SettingsActivity.class);
    }

    @Test
    public void testLoadSettings() {
        SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);
        SharedPreferences sharedPreferences = mock(SharedPreferences.class);
        when(sharedPreferences.edit()).thenReturn(editor);
        when(editor.putString(anyString(), anyString())).thenReturn(editor);
        when(editor.apply()).thenReturn(true);

        settingsActivity.onCreate(null);

        // Simular a alteração de uma configuração
        settingsActivity.onPreferenceChange(sharedPreferences, "theme_choice", "dark");

        // Verificar se a configuração foi salva corretamente
        verify(editor).putString("theme_choice", "dark");
        verify(editor).apply();
    }
}
