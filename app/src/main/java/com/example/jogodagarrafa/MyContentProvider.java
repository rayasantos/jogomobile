package com.example.jogodagarrafa;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class MyContentProvider extends ContentProvider {

    // Autoridade do Content Provider
    private static final String AUTHORITY = "com.example.jogodagarrafa.provider";

    // URIs de acesso ao Content Provider
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/my_data");

    // Helper para manipular o arquivo
    private File dataFile;
    private File imageFile;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        dataFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "user_data.txt");
        imageFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "user_photo.jpg");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (uri.equals(CONTENT_URI)) {
            try {
                String name = "";
                if (dataFile.exists()) {
                    Scanner scanner = new Scanner(dataFile);
                    name = scanner.hasNext() ? scanner.nextLine() : "";
                    scanner.close();
                }

                String[] columns = {"name", "photo_path"};
                MatrixCursor cursor = new MatrixCursor(columns);
                cursor.addRow(new Object[]{name, imageFile.getAbsolutePath()});
                return cursor;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new IllegalArgumentException("URI desconhecida: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (uri.equals(CONTENT_URI)) {
            try {
                String name = values.getAsString("name");
                byte[] photo = values.getAsByteArray("photo");

                FileOutputStream fos = new FileOutputStream(dataFile);
                fos.write(name.getBytes());
                fos.close();

                FileOutputStream fosImage = new FileOutputStream(imageFile);
                fosImage.write(photo);
                fosImage.close();

                return ContentUris.withAppendedId(uri, 1);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new IllegalArgumentException("URI desconhecida: " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // Implemente a lógica de atualização aqui
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implemente a lógica de exclusão aqui
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // Implemente a lógica para retornar o tipo de dado aqui
        return null;
    }
}



