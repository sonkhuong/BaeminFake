package com.example.baeminfake.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;

import com.example.baeminfake.R;

public class LoginOptionsFragment extends Fragment {

    private TextView lgInsta, lgFaceb, lgMail;

    public LoginOptionsFragment() {

    }

    public static LoginOptionsFragment newInstance() {
        LoginOptionsFragment fragment = new LoginOptionsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_options_fragment, container, false);
        lgInsta = view.findViewById(R.id.with_insta);
        lgFaceb = view.findViewById(R.id.with_fb);
        lgMail = view.findViewById(R.id.with_mail);

        this.lgMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment current = new LoginFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.author_holder, current)
                        .addToBackStack(null)
                        .commit();
            }
        });

        this.lgFaceb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
