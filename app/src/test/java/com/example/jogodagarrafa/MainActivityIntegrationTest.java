import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(AndroidJUnit4.class)
public class MainActivityIntegrationTest {

    @Rule
    public IntentsTestRule<MainActivity> activityRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testShareAction() {
        // Clique na opção de compartilhar
        onView(withId(R.id.action_share)).perform(click());

        // Verificar se a Intent de compartilhamento foi acionada corretamente
        Intent expectedIntent = new Intent(Intent.ACTION_SEND);
        expectedIntent.putExtra(Intent.EXTRA_TEXT, "Estou jogando Jogo Da Garrafa!");
        expectedIntent.setType("text/plain");

        intended(hasAction(Intent.ACTION_SEND));
        intended(hasExtra(Intent.EXTRA_TEXT, "Estou jogando Jogo Da Garrafa!"));
        intended(hasType("text/plain"));
    }
}
