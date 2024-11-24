package com.example.androidnotesapp.model;

public class ShoppingItem {

    private String content;
    private boolean isChecked;

    public ShoppingItem(String content, boolean isChecked) {
        this.content = content;
        this.isChecked = isChecked;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return content;
    }
}
