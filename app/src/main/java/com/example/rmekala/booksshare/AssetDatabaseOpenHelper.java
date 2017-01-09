package com.example.rmekala.booksshare;

/**
 * Created by rmekala on 6/18/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AssetDatabaseOpenHelper {

    static SQLiteDatabase database;
    private static final String DB_NAME = "BookShare.db";

    private static Context context;

    public AssetDatabaseOpenHelper(Context context) {
        this.context = context;
    }

    public static SQLiteDatabase openDatabase() {
        File dbFile = context.getDatabasePath(DB_NAME);

        if (!dbFile.exists()) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
    }

    private static void copyDatabase(File dbFile) throws IOException {
        InputStream is = context.getAssets().open(DB_NAME);
        OutputStream os = new FileOutputStream(dbFile);

        byte[] buffer = new byte[1024];
        while (is.read(buffer) > 0) {
            os.write(buffer);
        }

        os.flush();
        os.close();
        is.close();
    }

    public static void getDetailsFromDB(Context mContext)
    {
        try{
//            Cursor details = database.rawQuery("SELECT * FROM ");
        }catch (Exception e)
        {
            Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
