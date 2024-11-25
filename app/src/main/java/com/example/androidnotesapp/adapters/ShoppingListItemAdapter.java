package com.example.androidnotesapp.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnotesapp.databinding.FragmentShoppingListNoteBinding;
import com.example.androidnotesapp.databinding.ItemNoteBinding;
import com.example.androidnotesapp.databinding.ItemShoppingListBinding;
import com.example.androidnotesapp.model.ShoppingItem;

import java.util.List;

public class ShoppingListItemAdapter extends RecyclerView.Adapter<ShoppingListItemAdapter.ShoppingListViewholder> {

    private List<ShoppingItem> shoppingList;

    public ShoppingListItemAdapter(List<ShoppingItem> shoppingList) {
        this.shoppingList = shoppingList;
    }


    @NonNull
    @Override
    public ShoppingListViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemShoppingListBinding binding = ItemShoppingListBinding.inflate(inflater, parent, false);
        return new ShoppingListViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewholder holder, int position) {
        ShoppingItem shoppingItem = shoppingList.get(position);
        holder.binding.shoppingListCheckBox.setChecked(shoppingItem.isChecked());
        holder.binding.shoppingListItemEditText.setText(shoppingItem.getContent());

        holder.binding.shoppingListCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            shoppingItem.setChecked(isChecked);
        });

        holder.binding.shoppingListItemEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                shoppingItem.setContent((charSequence.toString()));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    //VIEWHOLDER
    static class ShoppingListViewholder extends RecyclerView.ViewHolder {
        ItemShoppingListBinding binding;

        public ShoppingListViewholder(@NonNull ItemShoppingListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void updateList(List<ShoppingItem> newList) {
        shoppingList.clear();
        shoppingList.addAll(newList);
        notifyDataSetChanged();
    }


}
