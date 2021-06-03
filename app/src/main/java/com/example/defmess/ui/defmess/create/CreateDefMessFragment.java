package com.example.defmess.ui.defmess.create;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.defmess.DateUtils;
import com.example.defmess.R;
import com.example.defmess.databinding.FragmentCreateDefmessBinding;
import com.example.defmess.ui.defmess.DefMess;
import com.example.defmess.ui.main.MainViewModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class CreateDefMessFragment extends Fragment {
    private FragmentCreateDefmessBinding binding;
    MainViewModel mainViewModel;
    Calendar dateTime = Calendar.getInstance();
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Date date;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentCreateDefmessBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding.setViewModel(mainViewModel);
        if (!mainViewModel.isLogin()) {
            Navigation.findNavController(root).navigate(R.id.nav_reg_log);
        }

        TextView appearance = binding.createDefMessAppearance;
        TextView description = binding.createDefMessDescription;
        TextView location = binding.createDefMessLocation;
        TextView textViewDate = binding.createDefMesTextViewDate;
        TextView textViewTime = binding.createDefMesTextViewTime;

        Button buttDate = binding.createDefMesButtDate;
        Button buttTime = binding.createDefMesButtTime;
        Button buttCreate = binding.createDefMesButtCreate;
        DateUtils dateUtils = new DateUtils();

        buttDate.setOnClickListener((v) -> {
            new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dateTime.set(year, month, dayOfMonth);
                    textViewDate.setText(dateUtils.toDateString(dateTime.getTime()));
                }
            },
                    dateTime.get(Calendar.YEAR),
                    dateTime.get(Calendar.MONTH),
                    dateTime.get(Calendar.DAY_OF_MONTH)).show();
        });

        buttTime.setOnClickListener((v) -> {
            new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    dateTime.set(Calendar.MINUTE, minute);
                    textViewTime.setText(dateUtils.toTimeString(dateTime.getTime()));
                }
            },
                    dateTime.get(Calendar.HOUR_OF_DAY),
                    dateTime.get(Calendar.MINUTE),
                    true).show();
        });

        buttCreate.setOnClickListener((v) -> {
            try {

                if (mainViewModel.createDefMess(new DefMess(appearance.getText().toString(),
                        location.getText().toString(),
                        description.getText().toString(),
                        dateUtils.toDateTimeString(Calendar.getInstance().getTime()),
                        dateUtils.toDateTimeString(dateTime.getTime())
                ))){
                    Navigation.findNavController(root).navigate(R.id.nav_profile);
                }
            } catch (InterruptedException | ExecutionException | JSONException | IOException e) {
                e.printStackTrace();
            }
        });

        return root;
    }
}
