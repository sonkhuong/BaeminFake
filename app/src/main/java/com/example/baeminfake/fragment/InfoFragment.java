package com.example.baeminfake.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.baeminfake.R;
import com.example.baeminfake.activity.SplashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.jetbrains.annotations.NotNull;

import static com.example.baeminfake.activity.SplashActivity.login_code;

public class InfoFragment extends Fragment {

    private EditText edphone, edname, edmail, edpass, ednewpass;
    private TextView udphone, udname, udmail, udpass, turnback, verifi, logout, titlepass, cancelupdate, textverifi;
    private CardView newpass;
    private int confirmpassword = 0;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public InfoFragment() {

    }

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getLogout();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        init(view);

        this.turnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        this.udphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmpassword == 0) {
                    getDialog();
                } else {
                    if (edphone.isEnabled() == false) {
                        edphone.setEnabled(true);
                        udphone.setText("C???p nh???t");
                    } else {
                        edphone.setEnabled(false);
                        udphone.setText("Ch???nh s???a");
                    }
                }
            }
        });

        this.udname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmpassword == 0) {
                    getDialog();
                } else {
                    if (edname.isEnabled() == false) {
                        edname.setEnabled(true);
                        udname.setText("C???p nh???t");
                    } else {
                        if (edname.getText().toString().equals(firebaseUser.getDisplayName()) != true) {
                            getChange(1, edname.getText().toString());
                        }
                        edname.setEnabled(false);
                        udname.setText("Ch???nh s???a");
                    }
                }
            }
        });

        this.udmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmpassword == 0) {
                    getDialog();
                } else {
                    if (edmail.isEnabled() == false) {
                        edmail.setEnabled(true);
                        udmail.setText("C???p nh???t");
                    } else {
                        if (edmail.getText().toString().equals(firebaseUser.getEmail()) != true) {
                            getChange(2, edmail.getText().toString());
                        }
                        edmail.setEnabled(false);
                        udmail.setText("Ch???nh s???a");
                    }
                }
            }
        });

        this.verifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseUser.isEmailVerified() != true) {
                    firebaseUser.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Verify email send! Confirm the mail and restart this application!", Toast.LENGTH_LONG).show();
                                        Handler h = new Handler();
                                        h.postDelayed(runnable, 3000);
                                    }
                                }
                            });
                }
            }
        });

        this.udpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmpassword == 0) {
                    getDialog();
                } else {
                    if (edpass.isEnabled() == false) {
                        newpass.setVisibility(View.VISIBLE);
                        titlepass.setText("M???t kh???u m???i");
                        edpass.setEnabled(true);
                        cancelupdate.setVisibility(View.VISIBLE);
                        udpass.setText("C???p nh???t");
                    } else {
                        String p = edpass.getText().toString();
                        String np = ednewpass.getText().toString();

                        if (p.length() < 6) {
                            edpass.setError("????? d??i m???t kh???u ph???i l???n h??n 6 k?? t???");
                            return;
                        }

                        if (p.equals(np) != true) {
                            ednewpass.setError("M???t kh???u b???n v???a nh???p kh??ng tr??ng kh???p");
                            return;
                        }

                        getChange(3, edpass.getText().toString());
                        cancelupdate.setVisibility(View.GONE);
                        newpass.setVisibility(View.GONE);
                        titlepass.setText("M???t kh???u");
                        edpass.setEnabled(false);
                        udpass.setText("Ch???nh s???a");
                    }
                }
            }
        });

        this.cancelupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelupdate.setVisibility(View.GONE);
                newpass.setVisibility(View.GONE);
                titlepass.setText("M???t kh???u");
                edpass.setEnabled(false);
                udpass.setText("Ch???nh s???a");
            }
        });

        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(), SplashActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void init(View view) {
        turnback = view.findViewById(R.id.back_to_profile1);
        edphone = view.findViewById(R.id.edit_phone);
        edname = view.findViewById(R.id.edit_name);
        edmail = view.findViewById(R.id.edit_mail);
        titlepass = view.findViewById(R.id.title_pass);
        edpass = view.findViewById(R.id.edit_password);
        ednewpass = view.findViewById(R.id.edit_newpass);
        cancelupdate = view.findViewById(R.id.cancel_update_pass);
        newpass = view.findViewById(R.id.newpass);
        udphone = view.findViewById(R.id.update_phone);
        udmail = view.findViewById(R.id.update_mail);
        udname = view.findViewById(R.id.update_name);
        udpass = view.findViewById(R.id.update_password);
        verifi = view.findViewById(R.id.verifi_email);
        textverifi = view.findViewById(R.id.text_verifi_email);
        logout = view.findViewById(R.id.logout);
        edpass.setText(null);
        edphone.setEnabled(false);
        edname.setEnabled(false);
        edmail.setEnabled(false);
        edpass.setEnabled(false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        edphone.setText(firebaseUser.getPhoneNumber());
        edmail.setText(firebaseUser.getEmail());
        edname.setText(firebaseUser.getDisplayName());

        if (login_code == 1) {
            Toast.makeText(getActivity(), "Kh??ng c?? quy???n ch???nh s???a th??ng tin khi ????ng nh???p b???ng facebook!", Toast.LENGTH_LONG).show();
            udname.setEnabled(false);
            udmail.setEnabled(false);
            udphone.setEnabled(false);
            udpass.setEnabled(false);
            verifi.setEnabled(false);
            textverifi.setText("B???n ??ang th???c hi???n ????ng nh???p b???ng t??i kho???n Facebook v?? kh??ng c?? quy???n ch???nh s???a th??ng tin c?? nh??n!");
            verifi.setText("");
        } else {
            if (firebaseUser.isEmailVerified()) {
                verifi.setEnabled(false);
                textverifi.setText("B???n ???? x??c th???c email chot tia kho???n n??y. Hi???n t???i email c?? th??? s??? d???ng ????? qu??n m???t kh???u v?? nhi???u ti???n ??ch kh??c.");
                verifi.setText("");
            } else {
                textverifi.setText("B???n s??? nh???n ???????c nhi???u th??ng b??o quan tr???ng v?? d??ng email cho vi???c ?????t l???i m???t kh???u.");
                verifi.setText("X??C TH???C EMAIL");
            }
        }
    }

    private void getDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("X??c nh???n m???t kh???u!");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog, (ViewGroup) getView(), false);
        EditText inputpass = view.findViewById(R.id.repass);
        builder.setView(view);

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebaseAuth.signInWithEmailAndPassword(firebaseUser.getEmail(), inputpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            confirmpassword = 1;
                            Toast.makeText(getActivity(), "X??c th???c m???t kh???u th??nh c??ng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "X??c th???c m???t kh???u th???t b???i! Vui l??ng th??? l???i", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void getChange(int x, String n) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("X??c nh???n thay ?????i?");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.confirm_change_dialog, (ViewGroup) getView(), false);
        builder.setView(view);

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (x) {
                    case 1: {
                        updateName(firebaseAuth.getCurrentUser(), n);
                        break;
                    }
                    case 2: {
                        updateEmail(firebaseAuth.getCurrentUser(), n);
                        break;
                    }
                    case 3: {
                        updatePass(firebaseAuth.getCurrentUser(), n);
                        break;
                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void getLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Restart ???ng d???ng ????? update d??? li???u m???i?");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.logout_dialog, (ViewGroup) getView(), false);
        builder.setView(view);

        builder.setPositiveButton("Log out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(), SplashActivity.class));
                getActivity().finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void updateName(FirebaseUser current, String name) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
//                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        current.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Handler h = new Handler();
                            h.postDelayed(runnable, 1500);
                        }
                    }
                });
    }

    private void updateEmail(FirebaseUser current, String mail) {
        current.updateEmail(mail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Handler h = new Handler();
                            h.postDelayed(runnable, 1500);
                        }
                    }
                });
    }

    private void updatePhone() {

    }

    private void updatePass(FirebaseUser current, String pass) {
        current.updatePassword(pass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Handler h = new Handler();
                            h.postDelayed(runnable, 1500);
                        }
                    }
                });
    }
}
