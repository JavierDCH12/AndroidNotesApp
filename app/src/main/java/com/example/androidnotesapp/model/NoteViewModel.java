package com.example.androidnotesapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidnotesapp.dbManager.NoteRepository;

import java.util.ArrayList;
import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private final MutableLiveData<Note> selectedNote = new MutableLiveData<>();
    private final LiveData<List<Note>> allNotes;
    private final NoteRepository noteRepository;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }


    public void selectNote(Note note){
        selectedNote.setValue(note);
    }

    public MutableLiveData<Note> getSelectedNote(){
        return selectedNote;
    }



    public void insert(Note note) {
        noteRepository.insert(note);
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }

    public void update(Note note) {
        noteRepository.update(note);
    }

    public void clearSelectedNote() {
        selectedNote.setValue(null);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }



}
