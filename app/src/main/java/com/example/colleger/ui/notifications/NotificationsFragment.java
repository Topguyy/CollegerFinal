package com.example.colleger.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.colleger.LoginActivity;
import com.example.colleger.RegisterActivity;
import com.example.colleger.R;
import com.example.colleger.databinding.FragmentNotificationsBinding;
import com.google.firebase.auth.FirebaseAuth;

public class NotificationsFragment extends Fragment {
    TextView userNameAppear, userMailAppear;
    Button logoutBtn;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userNameAppear = root.findViewById(R.id.userNameAppearrr);
        userMailAppear = root.findViewById(R.id.userMailAppearrr);
        SharedPreferences sp = getContext().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String name = sp.getString("name", " ");
        String email = sp.getString("email", " ");
        userNameAppear.setText(name);
        userMailAppear.setText(email);
        logoutBtn = root.findViewById(R.id.loginBtn);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}