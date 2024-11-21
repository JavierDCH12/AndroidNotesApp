package com.example.androidnotesapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnotesapp.databinding.ItemNoteBinding;
import com.example.androidnotesapp.model.Note;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.NotesViewholder> {

    private final List<Note> note_list;

    public GalleryAdapter(List<Note> noteList) {
        note_list = noteList;
    }

    @NonNull
    @Override
    public GalleryAdapter.NotesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemNoteBinding binding = ItemNoteBinding.inflate(inflater, parent, false);
        return new NotesViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.NotesViewholder holder, int position) {

        Note note = note_list.get(position);

        holder.binding.noteTitleText.setText(note.getTitle());
        holder.binding.noteContentText.setText(note.getContent());

    }

    @Override
    public int getItemCount() {
        return note_list.size();
    }




    //BINDING OF THE SINGLE NOTE VIEWHOLDER
    static class NotesViewholder extends RecyclerView.ViewHolder{
        ItemNoteBinding binding;

        public NotesViewholder(@NonNull ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
