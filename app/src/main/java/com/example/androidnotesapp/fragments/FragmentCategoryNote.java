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

    FragmentCategoryNoteBinding binding;
    private NoteViewModel noteViewModel;
    NavController navController;
    private String selectedCategory;

    public FragmentCategoryNote() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCategoryNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);

        navController = Navigation.findNavController(view);

        binding.confirmaCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = getSelectedCategory();



                if(category.isEmpty() || category==null){
                    Toast.makeText(requireContext(), "Por favor, elige una categoría", Toast.LENGTH_SHORT).show();
                    return;

                }
                Note note = new Note("", "", category);



                noteViewModel.selectNote(note);

                chooseCategoryNav();

            }
        });




        binding.cancelCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();


            }
        });



    }//END ONVIEWCREATED

    //HOW TO GET SPECIFIC CHIP IN CHIP GROUP
    private String getSelectedCategory() {
        int selectedChipId = binding.categoryChipGroup.getCheckedChipId();
        if (selectedChipId != View.NO_ID) {
            com.google.android.material.chip.Chip selectedChip = binding.categoryChipGroup.findViewById(selectedChipId);
            return selectedChip.getText().toString();
        }
        return null;
    }

    private void setUpSelectedCategoryString(){
        binding.chipStandardNote.setOnClickListener(v -> selectedCategory = "standard");
        binding.chipShoppingList.setOnClickListener(v -> selectedCategory = "shopping_list");
    }


    private void chooseCategoryNav() {

        switch (selectedCategory) {
            case "standard":
                navController.navigate(R.id.action_fragmentCategoryNote_to_fragmentDetailNote);
                break;

            case "shopping_list":
                navController.navigate(R.id.action_fragmentCategoryNote_to_fragmentShoppingListNote);
                break;

            default:
                Toast.makeText(requireContext(), "Categoría desconocida", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}