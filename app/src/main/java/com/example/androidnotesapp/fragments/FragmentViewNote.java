package com.example.androidnotesapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidnotesapp.R;
import com.example.androidnotesapp.databinding.FragmentViewNoteBinding;
import com.example.androidnotesapp.model.Note;
import com.example.androidnotesapp.model.NoteViewModel;


public class FragmentViewNote extends Fragment {

    FragmentViewNoteBinding binding;
    private NoteViewModel noteViewModel;
    public FragmentViewNote() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentViewNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);

        noteViewModel.getSelectedNote().observe(getViewLifecycleOwner(), note -> {
            if(note!=null){
                binding.viewNoteTitle.setText(note.getTitle());
                binding.viewNoteContent.setText(note.getContent());
            }

        });



        binding.editNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note currentnote = noteViewModel.getSelectedNote().getValue();

                if(currentnote!=null){
                    String category = currentnote.getCategory();
                    NavController navController = Navigation.findNavController(view);

                    switch (category.toLowerCase()){
                        case "standard":
                            navController.navigate(R.id.action_fragmentViewNote_to_fragmentDetailNote);
                            break;
                        case "shopping list":
                            navController.navigate(R.id.action_fragmentViewNote_to_fragmentShoppingListNote);
                            break;
                        default:
                            Toast.makeText(requireContext(), "Categoria no encontrads", Toast.LENGTH_SHORT).show();

                    }



                }


            }
        });


    }
}