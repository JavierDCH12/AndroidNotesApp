package com.example.androidnotesapp.dbManager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidnotesapp.model.Note;
import com.example.androidnotesapp.model.NoteDAO;

@Database(entities={Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract NoteDAO noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "notes_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
