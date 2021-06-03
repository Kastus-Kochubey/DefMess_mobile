package com.example.defmess;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.defmess.databinding.ActivityMainBinding;
import com.example.defmess.ui.main.MainViewModel;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
//    public static String AccessCode = "";


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    SharedPreferences pref;


    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.bind(findViewById(R.id.drawer_layout));
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(mainViewModel);


        setSupportActionBar(binding.appBarMain.toolbar);


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

//        UserFragment userFragment = new UserFragment();
//        FragmentManager fragmentManager = new FragmentManager() {};
////        fragmentManager.addFragmentOnAttachListener(new );
//        userFragment.getLayoutInflater().inflate(R.layout.fragment_user, (ViewGroup) userFragment.getView(), false);
//        navigationView.addHeaderView(userFragment.getView());

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_main, R.id.nav_profile)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        try {
            loadData();
            if (mainViewModel.isLogin()) {
                navController.navigate(R.id.nav_main);
                saveData();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.e("onSaveInstanceState", "qq");
//        try {
//            JsonFilesManager manager = new JsonFilesManager(getApplicationContext());
//            outState.putString("data", mainViewModel.getData());
//        } catch (JSONException | IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("onSaveInstanceState", "qq");
        try {
            saveData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        try {
//            outState.putString("data", mainViewModel.getData());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    public void saveData() throws JSONException {
        Log.e("saveData", "q");
        pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String jwt_code = mainViewModel.getJwt_code();
        if (jwt_code == null){
            editor.clear().apply();
        } else {
            editor.putString("jwt_code", jwt_code).apply();
        }
//        editor.apply();

    }

    public void loadData() throws JSONException {
        pref = getPreferences(MODE_PRIVATE);
        mainViewModel.setJwt_code(pref.getString("jwt_code", null));
        String jwt_code = mainViewModel.getJwt_code();
        if (jwt_code == null) {
            Log.e("loadData", "null jwt_code");
        } else {
            Log.e("loadData", jwt_code);
        }
    }

//    @Override
//    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        Log.e("onRestoreInstanceState", savedInstanceState.getString("data"));
////        mainViewModel.setData(savedInstanceState.getString("data"));
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            saveData();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}