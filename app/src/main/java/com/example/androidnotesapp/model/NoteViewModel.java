package com.example.androidnotesapp.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class NoteViewModel extends ViewModel {

    private final MutableLiveData<Note> selectedNote = new MutableLiveData<>();
    private final MutableLiveData<List<Note>> note_list = new MutableLiveData<>(new ArrayList<>());



    public void selectNote(Note note){
        selectedNote.setValue(note);
    }

    public MutableLiveData<Note> getSelectedNote(){
        return selectedNote;
    }

    public MutableLiveData<List<Note>> getNoteList(){
        return note_list;
    }

    public void addNote(Note note){
        List<Note> currentList = new ArrayList<>(note_list.getValue());
        currentList.add(note);
        note_list.setValue(currentList);
    }

    public void deleteNote(Note note){
        List<Note> currentList = new ArrayList<>(note_list.getValue());
        currentList.remove(note);
        note_list.setValue(currentList);
    }

    public void updateSelectedNote(Note updatedNote) {
        List<Note> currentList = new ArrayList<>(note_list.getValue());
        for (int i = 0; i < currentList.size(); i++) {
            if (currentList.get(i).getId() == updatedNote.getId()) {
                currentList.set(i, updatedNote);
            }
        }
        note_list.setValue(currentList);
    }



}
