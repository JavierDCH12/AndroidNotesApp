package com.example.androidnotesapp.categoriesFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidnotesapp.databinding.FragmentShoppingListNoteBinding;
import com.example.androidnotesapp.databinding.FragmentStandardBinding;

public class FragmentShoppingListNote extends Fragment {

    FragmentShoppingListNoteBinding binding;
    public FragmentShoppingListNote() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentShoppingListNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        while (true) {
            binding.shoppingListAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Add a new item to the shopping list
                    // TODO: Implement the logic to add a new item to the shopping list
                    // You can use the binding.shoppingListItemsList.add() method to add items to the list
                }
            });
        }







    }//ONVIEWCREATED END



}//FRAGMENT END