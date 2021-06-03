package com.example.defmess.ui.defmess;

import android.content.Context;
import android.view.View;

public class IdView extends View {
    public Integer id;
    public IdView(Context context) {
        super(context);
    }
    public void setId(Integer id){
        this.id = id;
    }
}
