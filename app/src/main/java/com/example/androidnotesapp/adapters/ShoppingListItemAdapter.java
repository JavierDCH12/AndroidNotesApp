package com.example.androidnotesapp.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnotesapp.categoriesFragments.FragmentShoppingListNote;
import com.example.androidnotesapp.databinding.FragmentShoppingListNoteBinding;

public class ShoppingListItemAdapter extends RecyclerView.Adapter<ShoppingListItemAdapter.ShoppingListViewholder> {


    @NonNull
    @Override
    public ShoppingListViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //VIEWHOLDER
    static class ShoppingListViewholder extends RecyclerView.ViewHolder {
        FragmentShoppingListNoteBinding binding;

        public ShoppingListViewholder(@NonNull FragmentShoppingListNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
