package com.example.androidnotesapp.categoriesFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidnotesapp.databinding.FragmentShoppingListNoteBinding;
import com.example.androidnotesapp.databinding.FragmentStandardBinding;
import com.example.androidnotesapp.model.ShoppingItem;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FragmentShoppingListNote extends Fragment {

    FragmentShoppingListNoteBinding binding;

    private List<ShoppingItem> shoppingList = new ArrayList<ShoppingItem>();
    private static final String PREFS_NAME = "shopping_prefs";
    private static final String PREFS_SHOPPING_LIST_KEY = "shopping_list";
    private Gson gson;
    private SharedPreferences sharedPreferences;

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

        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();

        loadShoppingList();

        binding.shoppingListAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                shoppingList.add(new ShoppingItem("", false));

            }
        });

        binding.saveShoppingListButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                shoppingList.add(new ShoppingItem("", false));
                saveShoppingList();

            }
        });




    }//ONVIEWCREATED END

    private void saveShoppingList() {
        String json = gson.toJson(shoppingList);
        sharedPreferences.edit().putString(PREFS_SHOPPING_LIST_KEY, json).apply();
    }

    private void loadShoppingList() {
        String json = gson.toJson(shoppingList);
        if (json != null) {
            Type type = new TypeToken<List<ShoppingItem>>() {}.getType();
            shoppingList = gson.fromJson(json, type);
        }
    }



}//FRAGMENT END