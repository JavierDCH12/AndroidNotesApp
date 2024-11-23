package com.example.androidnotesapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidnotesapp.R;
import com.example.androidnotesapp.databinding.FragmentDetailNoteBinding;
import com.example.androidnotesapp.model.Note;
import com.example.androidnotesapp.model.NoteViewModel;


public class FragmentDetailNote extends Fragment {

    FragmentDetailNoteBinding   binding;
    private NoteViewModel noteViewModel;
    public FragmentDetailNote() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);

        noteViewModel.getSelectedNote().observe(getViewLifecycleOwner(), note->{
            if(note!=null){
                binding.editNoteTitle.setText(note.getTitle());
                binding.editNoteContent.setText(note.getContent());
            }
            else{
                binding.editNoteTitle.setText(" ");
                binding.editNoteContent.setText(" ");
            }
        });


        binding.cancelNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = Navigation.findNavController(view);
                navController.popBackStack(); //go back to previous view

            }
        });


        binding.saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = Navigation.findNavController(view);

                String title = binding.editNoteTitle.getText().toString().trim();
                String content = binding.editNoteContent.getText().toString().trim();

                if (title.isEmpty() || content.isEmpty()) {
                    Toast.makeText(requireContext(), "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                Note currentNote;

                if (noteViewModel.getSelectedNote().getValue() != null) {
                    Note selectedNote = noteViewModel.getSelectedNote().getValue();
                    selectedNote.setTitle(title);
                    selectedNote.setContent(content);
                    currentNote = selectedNote;
                    noteViewModel.update(currentNote);
                    Toast.makeText(requireContext(), "Nota actualizada con éxito", Toast.LENGTH_SHORT).show();

                } else {
                    currentNote = new Note(title, content, null);
                    noteViewModel.insert(currentNote);
                    Toast.makeText(requireContext(), "Nota guardada con éxito", Toast.LENGTH_SHORT).show();
                }


                navController.popBackStack();

            }
        });



    }//ONVIEWCREATED END


}//FRAGMENT END