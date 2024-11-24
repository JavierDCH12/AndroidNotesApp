package com.example.androidnotesapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidnotesapp.R;
import com.example.androidnotesapp.adapters.GalleryAdapter;
import com.example.androidnotesapp.databinding.FragmentGalleryNotesBinding;
import com.example.androidnotesapp.model.Note;
import com.example.androidnotesapp.model.NoteViewModel;

import java.util.ArrayList;
import java.util.List;


public class FragmentGalleryNotes extends Fragment {

    FragmentGalleryNotesBinding binding;
    private NoteViewModel noteViewModel;
    private GalleryAdapter galleryAdapter;
    private List<Note> notes_list = new ArrayList<>();



    public FragmentGalleryNotes() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGalleryNotesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);

        galleryAdapter = new GalleryAdapter(new ArrayList<>(), noteViewModel);
        binding.recyclerGalleryNotes.setAdapter(galleryAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        binding.recyclerGalleryNotes.setLayoutManager(gridLayoutManager);

        noteViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
            Log.d("FragmentGalleryNotes", "Notes received: " + notes.size());

            if (notes == null || notes.isEmpty()) {
                binding.recyclerGalleryNotes.setVisibility(View.GONE);
                binding.emptyNotesText.setVisibility(View.VISIBLE);
            } else {
                binding.emptyNotesText.setVisibility(View.GONE);
                binding.recyclerGalleryNotes.setVisibility(View.VISIBLE);
                galleryAdapter.updateNotes(notes);
            }
        });

        binding.addNote.setOnClickListener(view1 -> {
            noteViewModel.selectNote(null);
            NavController navController = Navigation.findNavController(view1);
            navController.navigate(R.id.action_fragmentGalleryNotes_to_fragmentCategoryNote);
        });
    }//ONVIEWCREATED END


}//END FRAGMENT