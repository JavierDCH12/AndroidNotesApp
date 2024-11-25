package com.example.androidnotesapp.categoriesFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androidnotesapp.R;
import com.example.androidnotesapp.adapters.ShoppingListItemAdapter;
import com.example.androidnotesapp.databinding.FragmentShoppingListNoteBinding;
import com.example.androidnotesapp.model.Note;
import com.example.androidnotesapp.model.NoteViewModel;
import com.example.androidnotesapp.model.ShoppingItem;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FragmentShoppingListNote extends Fragment {

    FragmentShoppingListNoteBinding binding;

    private List<ShoppingItem> shoppingList = new ArrayList<ShoppingItem>();
    private static final String PREFS_NAME = "shopping_prefs";
    private static final String PREFS_SHOPPING_LIST_KEY = "shopping_list";
    private Gson gson;
    private SharedPreferences sharedPreferences;
    private static final String EMPTY_PARAMETER = "";
    private NoteViewModel noteViewModel;

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

        NavController navController = Navigation.findNavController(view);
        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();

        loadShoppingList();

        ShoppingListItemAdapter adapter = new ShoppingListItemAdapter(shoppingList);
        binding.shoppingListRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.shoppingListRecyclerView.setAdapter(adapter);

        Note selectedNote = noteViewModel.getSelectedNote().getValue();
        if (selectedNote != null && selectedNote.getCategory().equals("shopping_list")) {
            shoppingList = convertJsonToList(selectedNote.getContent());
            adapter.updateList(shoppingList);
        }

        binding.shoppingListAddButton.setOnClickListener(v -> {
            shoppingList.add(new ShoppingItem("", false));
            adapter.notifyItemInserted(shoppingList.size() - 1);
            binding.shoppingListRecyclerView.scrollToPosition(shoppingList.size() - 1);
        });

        binding.saveShoppingListButton.setOnClickListener(v -> {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
            String title = "Shopping List - " + currentDate;
            String jsonContent = convertListToJson(shoppingList);

            Note shoppingNote;
            if (selectedNote != null) {
                shoppingNote = selectedNote;
                shoppingNote.setContent(jsonContent);
            } else {
                shoppingNote = new Note(title, jsonContent, "shopping_list");
            }

            noteViewModel.insert(shoppingNote);
            Toast.makeText(requireContext(), "Lista guardada con éxito", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.action_fragmentShoppingListNote_to_fragmentGalleryNotes);
        });

        binding.cancelShoppingListButton.setOnClickListener(v -> requireActivity().onBackPressed());
    }
//ONVIEWCREATED END

    private List<ShoppingItem> convertJsonToList(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<ShoppingItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private String convertListToJson(List<ShoppingItem> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }



    private void loadShoppingList() {
        String json = gson.toJson(shoppingList);
        if (json != null) {
            Type type = new TypeToken<List<ShoppingItem>>() {}.getType();
            shoppingList = gson.fromJson(json, type);
        }
    }



}//FRAGMENT END