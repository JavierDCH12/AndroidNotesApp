package com.example.androidnotesapp.dbManager;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.androidnotesapp.model.Note;
import com.example.androidnotesapp.model.NoteDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    private final NoteDAO noteDAO;
    private final LiveData<List<Note>> allNotes;
    private final ExecutorService executorService;

    public NoteRepository(Application application){
        NoteDatabase db = NoteDatabase.getInstance(application);
        noteDAO = db.noteDao();
        allNotes = noteDAO.getAllNotes();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Note note){
        executorService.execute(() -> noteDAO.insertNote(note));
    }

    public void update(Note note){
        executorService.execute(() -> noteDAO.updateNote(note));
    }

    public void delete(Note note){
        executorService.execute(() -> noteDAO.deleteNote(note));
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }



}
