package com.example.androidnotesapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnotesapp.R;
import com.example.androidnotesapp.databinding.ItemNoteBinding;
import com.example.androidnotesapp.model.Note;
import com.example.androidnotesapp.model.NoteViewModel;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.NotesViewholder> {

    private List<Note> note_list;
    private final NoteViewModel noteViewModel;

    public GalleryAdapter(List<Note> noteList, NoteViewModel noteViewModel) {
        this.note_list = noteList;
        this.noteViewModel = noteViewModel;
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

        holder.itemView.setOnClickListener(v -> {
            noteViewModel.selectNote(note);
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_fragmentGalleryNotes_to_fragmentViewNote);
        });

        holder.binding.editNoteButton.setOnClickListener(v -> {
            noteViewModel.selectNote(note);
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_fragmentGalleryNotes_to_fragmentDetailNote);
        });

        holder.binding.deleteNoteButton.setOnClickListener(v -> {
            noteViewModel.delete(note);
            Toast.makeText(v.getContext(), "Nota eliminada", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return (note_list != null) ? note_list.size() : 0;
    }

    // ViewHolder
    static class NotesViewholder extends RecyclerView.ViewHolder {
        ItemNoteBinding binding;

        public NotesViewholder(@NonNull ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void updateNotes(List<Note> notes) {
        this.note_list = notes;
        notifyDataSetChanged();
    }
}