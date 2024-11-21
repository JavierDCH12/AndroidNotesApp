package com.example.androidnotesapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidnotesapp.R;
import com.example.androidnotesapp.adapters.GalleryAdapter;
import com.example.androidnotesapp.databinding.FragmentGalleryNotesBinding;
import com.example.androidnotesapp.model.NoteViewModel;


public class FragmentGalleryNotes extends Fragment {

    FragmentGalleryNotesBinding binding;
    private NoteViewModel noteViewModel;
    private GalleryAdapter galleryAdapter;


    public FragmentGalleryNotes() {
        // Required empty public constructor
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        binding.recyclerGalleryNotes.setLayoutManager(gridLayoutManager);

        binding.recyclerGalleryNotes.setAdapter(galleryAdapter);




    }//ONVIEWCREATED END


}//END FRAGMENT