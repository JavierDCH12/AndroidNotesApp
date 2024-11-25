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
import com.example.androidnotesapp.databinding.FragmentCategoryNoteBinding;
import com.example.androidnotesapp.model.Note;
import com.example.androidnotesapp.model.NoteViewModel;


public class FragmentCategoryNote extends Fragment {

    private FragmentCategoryNoteBinding binding;
    private NoteViewModel noteViewModel;
    private NavController navController;
    private static final String CATEGORY_STANDARD = "Standard";
    private static final String CATEGORY_SHOPPING_LIST = "Shopping List";
    private static final String EMPTY_STRING="";

    public FragmentCategoryNote() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        navController = Navigation.findNavController(view);

        binding.confirmCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = getSelectedCategory();

                if (category == null || category.isEmpty()) {
                    Toast.makeText(requireContext(), R.string.request_pick_category, Toast.LENGTH_SHORT).show();
                    return;
                }

                Note note = new Note(EMPTY_STRING, EMPTY_STRING, category);
                noteViewModel.selectNote(note);

                navigateToCategoryFragment(category);
            }
        });

        binding.cancelCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });
    }

    private String getSelectedCategory() {
        int selectedChipId = binding.categoryChipGroup.getCheckedChipId();
        if (selectedChipId != View.NO_ID) {
            com.google.android.material.chip.Chip selectedChip = binding.categoryChipGroup.findViewById(selectedChipId);
            return selectedChip.getText().toString();
        }
        return null;
    }

    private void navigateToCategoryFragment(String category) {
        if (CATEGORY_STANDARD.equalsIgnoreCase(category)) {
            navController.navigate(R.id.action_fragmentCategoryNote_to_fragmentStandarNote);
        } else if (CATEGORY_SHOPPING_LIST.equalsIgnoreCase(category)) {
            navController.navigate(R.id.action_fragmentCategoryNote_to_fragmentShoppingListNote);
        } else {
            Toast.makeText(requireContext(), R.string.err_unknown_category, Toast.LENGTH_SHORT).show();
        }
    }
}