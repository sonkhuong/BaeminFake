package com.example.baeminfake.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import static com.example.baeminfake.activity.MainActivity.user;

public class InfoFragment extends Fragment {

    private EditText edphone, edname, edmail, edpass, ednewpass;
    private TextView udphone, udname, udmail, udpass, turnback, verifi, logout, titlepass, cancelupdate;
    private CardView newpass;
    private int confirmpassword = 0;
    private FirebaseAuth firebaseAuth;

    public InfoFragment() {

    }

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
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
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        init(view);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

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
                        udphone.setText("Cập nhật");
                    } else {
                        edphone.setEnabled(false);
                        udphone.setText("Chỉnh sửa");
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
                        udname.setText("Cập nhật");
                    } else {
                        if (edname.getText().toString().equals(firebaseUser.getDisplayName()) != true) {
                            getChange(1, edname.getText().toString());
                        }
                        edname.setEnabled(false);
                        udname.setText("Chỉnh sửa");
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
                        udmail.setText("Cập nhật");
                    } else {
                        if (edmail.getText().toString().equals(firebaseUser.getEmail()) != true) {
                            getChange(2, edmail.getText().toString());
                        }
                        edmail.setEnabled(false);
                        udmail.setText("Chỉnh sửa");
                    }
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
                        titlepass.setText("Mật khẩu mới");
                        edpass.setEnabled(true);
                        cancelupdate.setVisibility(View.VISIBLE);
                        udpass.setText("Cập nhật");
                    } else {
                        String p = edpass.getText().toString();
                        String np = ednewpass.getText().toString();

                        if (p.length() < 6) {
                            edpass.setError("Độ dài mật khẩu phải lớn hơn 6 kí tự");
                            return;
                        }

                        if (p.equals(np) != true) {
                            ednewpass.setError("Mật khẩu bạn vừa nhập không trùng khớp");
                            return;
                        }

                        getChange(3, edpass.getText().toString());
                        cancelupdate.setVisibility(View.GONE);
                        newpass.setVisibility(View.GONE);
                        titlepass.setText("Mật khẩu");
                        edpass.setEnabled(false);
                        udpass.setText("Chỉnh sửa");
                    }
                }
            }
        });

        this.cancelupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelupdate.setVisibility(View.GONE);
                newpass.setVisibility(View.GONE);
                titlepass.setText("Mật khẩu");
                edpass.setEnabled(false);
                udpass.setText("Chỉnh sửa");
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

    public void init(View view) {
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
        logout = view.findViewById(R.id.logout);
        edphone.setText(user.getPhone());
        edmail.setText(user.getEmail());
        edname.setText(user.getName());
        edpass.setText(null);
        edphone.setEnabled(false);
        edname.setEnabled(false);
        edmail.setEnabled(false);
        edpass.setEnabled(false);
    }

    public void getDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Xác nhận mật khẩu!");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog, (ViewGroup) getView(), false);
        EditText inputpass = view.findViewById(R.id.repass);
        builder.setView(view);

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebaseAuth.signInWithEmailAndPassword(user.getEmail(), inputpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            confirmpassword = 1;
                            Toast.makeText(getActivity(), "Xác thực mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Xác thực mật khẩu thất bại! Vui lòng thử lại", Toast.LENGTH_SHORT).show();
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

    public void getChange(int x, String n) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Xác nhận thay đổi");
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

    public void getLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Cập nhật dữ liệu");
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

    public void updateName(FirebaseUser current, String name) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
//                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        current.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            getLogout();
                        }
                    }
                });
    }

    public void updateEmail(FirebaseUser current, String mail) {
        current.updateEmail(mail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            getLogout();
                        }
                    }
                });
    }

    public void updatePhone() {

    }

    public void updatePass(FirebaseUser current, String pass) {
        current.updatePassword(pass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            getLogout();
                        }
                    }
                });
    }
}
