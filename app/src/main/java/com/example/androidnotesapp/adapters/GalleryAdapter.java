package com.example.androidnotesapp.adapters;

import android.util.Log;
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
import com.example.androidnotesapp.model.ShoppingItem;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
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
    public void onBindViewHolder(@NonNull NotesViewholder holder, int position) {
        Note note = note_list.get(position);

        // Configurar el título de la nota
        holder.binding.noteTitleText.setText(note.getTitle());

        // Configurar el contenido de la nota
        if ("shopping list".equalsIgnoreCase(note.getCategory())) {
            holder.binding.noteContentText.setText("Lista de Compras: " + formatShoppingList(note.getContent()));
        } else {
            holder.binding.noteContentText.setText(note.getContent());
        }

        // Navegar al fragmento correspondiente al hacer clic en el ítem
        holder.itemView.setOnClickListener(v -> {
            noteViewModel.selectNote(note);
            String category = note.getCategory();

            NavController navController = Navigation.findNavController(v);

            if ("shopping list".equalsIgnoreCase(category)) {
                navController.navigate(R.id.action_fragmentGalleryNotes_to_fragmentShoppingListNote);
            } else if ("standard".equalsIgnoreCase(category)) {
                navController.navigate(R.id.action_fragmentGalleryNotes_to_fragmentStandarNote);
            }
        });

        // Configurar el botón de edición
        holder.binding.editNoteButton.setOnClickListener(v -> {
            String category = note.getCategory();
            noteViewModel.selectNote(note);
            NavController navController = Navigation.findNavController(v);

            if ("shopping list".equalsIgnoreCase(category)) {
                navController.navigate(R.id.action_fragmentGalleryNotes_to_fragmentShoppingListNote);
            } else if ("standard".equalsIgnoreCase(category)) {
                navController.navigate(R.id.action_fragmentGalleryNotes_to_fragmentStandarNote);
            } else {
                Toast.makeText(v.getContext(), "Categoría no reconocida", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el botón de eliminar
        holder.binding.deleteNoteButton.setOnClickListener(v -> {
            noteViewModel.delete(note);
            notifyItemRemoved(position);
            Toast.makeText(v.getContext(), "Nota eliminada", Toast.LENGTH_SHORT).show();
        });
    }



    @Override
    public int getItemCount() {
        return (note_list != null) ? note_list.size() : 0;
    }

    private String formatShoppingList(String jsonContent) {
        try {
            Log.d("GalleryAdapter", "Contenido del JSON: " + jsonContent);

            Gson gson = new Gson();
            Type type = new TypeToken<List<ShoppingItem>>() {}.getType();
            List<ShoppingItem> shoppingList = gson.fromJson(jsonContent, type);

            StringBuilder formattedList = new StringBuilder();
            for (ShoppingItem item : shoppingList) {
                formattedList.append("• ").append(item.getContent());
                if (item.isChecked()) {
                    formattedList.append(" ✓");
                }
                formattedList.append("\n");
            }
            return formattedList.toString().trim();
        } catch (Exception e) {
            Log.e("GalleryAdapter", "Error al formatear el JSON", e);

            return "Error al cargar la lista";
        }
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