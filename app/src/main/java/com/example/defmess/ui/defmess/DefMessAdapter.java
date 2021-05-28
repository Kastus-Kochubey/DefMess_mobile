package com.example.defmess.ui.defmess;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.defmess.JsonFilesManager;
import com.example.defmess.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DefMessAdapter extends RecyclerView.Adapter<DefMessAdapter.DefMessHolder> {

    List<DefMess> list = new ArrayList<>();

    public DefMessAdapter(List<DefMess> list) {
        this.list = list;
    }
    public DefMessAdapter(JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Log.e("JSArrToDMAdapter", jsonObject.toString());
            DefMess defMess = new DefMess(jsonObject);
            list.add(defMess);
        }
    }

    @NonNull
    @NotNull
    @Override
    public DefMessHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_defmess, parent, false);
        return new DefMessHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DefMessHolder holder, int position) {
        DefMess defMess = list.get(position);
        holder.name.setText(defMess.end_date);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class DefMessHolder extends RecyclerView.ViewHolder {
        TextView name;
        public DefMessHolder(@NonNull @NotNull View view) {
            super(view);
            name = view.findViewById(R.id.textView3);
        }
    }
}
