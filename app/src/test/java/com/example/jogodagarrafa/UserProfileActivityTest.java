package com.example.jogodagarrafa;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UserProfileActivityTest {

    private UserProfileActivity userProfileActivity;
    private EditText editTextName;
    private EditText editTextEmail;
    private ImageView imageViewProfile;
    private Button buttonSave;

    @Before
    public void setUp() {
        userProfileActivity = Mockito.spy(UserProfileActivity.class);
        editTextName = new EditText(userProfileActivity);
        editTextEmail = new EditText(userProfileActivity);
        imageViewProfile = new ImageView(userProfileActivity);
        buttonSave = new Button(userProfileActivity);
    }

    @Test
    public void testTakePictureIntent() {
        imageViewProfile.performClick();
        Intent expectedIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        assertEquals(expectedIntent.resolveActivity(userProfileActivity.getPackageManager()), null);
    }

    @Test
    public void testOnActivityResult() {
        Intent resultIntent = new Intent();
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        resultIntent.putExtra("data", bitmap);

        userProfileActivity.onActivityResult(1, Activity.RESULT_OK, resultIntent);

        assertNotNull(imageViewProfile.getDrawable());
    }

    @Test
    public void testSaveProfile() {
        editTextName.setText("John Doe");
        editTextEmail.setText("john.doe@example.com");
        buttonSave.performClick();

        assertEquals("John Doe", editTextName.getText().toString());
        assertEquals("john.doe@example.com", editTextEmail.getText().toString());
    }
}
