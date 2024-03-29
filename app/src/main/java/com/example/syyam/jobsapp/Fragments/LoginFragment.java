package com.example.syyam.jobsapp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syyam.jobsapp.API;
import com.example.syyam.jobsapp.ColorFinderAdapter;
import com.example.syyam.jobsapp.MainActivity;
import com.example.syyam.jobsapp.Models.ColorFinder;
import com.example.syyam.jobsapp.Models.Login;
import com.example.syyam.jobsapp.Models.params.LoginParam;
import com.example.syyam.jobsapp.R;
import com.example.syyam.jobsapp.Utils.Config;
import com.example.syyam.jobsapp.Utils.Extras;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.syyam.jobsapp.Utils.Config.MY_PREFS_NAME;

public class LoginFragment extends Fragment {


    private EditText email, password;
    private Button submitBtn;
    private TextView forgotbtn;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        email = (EditText) rootView.findViewById(R.id.email);
        password = (EditText) rootView.findViewById(R.id.password);
        submitBtn = (Button) rootView.findViewById(R.id.submitBtn);
        forgotbtn = rootView.findViewById(R.id.forgotbtn);
        forgotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword();
            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(email.getText().toString()) && !TextUtils.isEmpty(password.getText().toString()))
                    getData(email.getText().toString(), password.getText().toString());
                else
                    Toast.makeText(getContext(), "Please fill all of the required fields.", Toast.LENGTH_LONG).show();

            }
        });


        return rootView;


    }

    public void getData(String email, String password) {

        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);


        final SharedPreferences.Editor editor = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();


        LoginParam loginParam = new LoginParam();
        loginParam.setEmail(email);
        loginParam.setPassword(password);

        //Config.getToken(getContext())
        Call<Login> loginCall = retrofitController.getLogin(loginParam);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if (response != null) {
                    Login list = response.body();

                    if (response.body() != null) {
                        editor.putString("token", list.getToken().toString());
                        editor.apply();

                        Toast.makeText(getContext(), "Successfully Logged In. ", Toast.LENGTH_LONG).show();

                        Intent l = new Intent(getContext(), MainActivity.class);
                        getActivity().finish();
                        startActivity(l);

                    } else {
                        Toast.makeText(getContext(), "Incorrect email or password", Toast.LENGTH_LONG).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getContext(), "Error signing in", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void forgotPassword() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        if (!userInputDialogEditText.getText().toString().isEmpty()) {
                            Extras.showLoader(getContext());
                            Retrofit build = new Retrofit
                                    .Builder()
                                    .baseUrl(Config.BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            API retrofitController = build.create(API.class);
                            Call<ResponseBody> forgotPasswordCall = retrofitController.forgotPassword(userInputDialogEditText.getText().toString());
                            forgotPasswordCall.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    Extras.hideLoader();
                                    if (response != null) {

//                                       Toast.makeText(getContext(), , Toast.LENGTH_SHORT).show();
                                        Log.i("response", response.message());
                                    } else {
                                        Toast.makeText(getContext(), "Incorrect email or password", Toast.LENGTH_LONG).show();
                                    }


                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Extras.hideLoader();
                                    Toast.makeText(getContext(), "Error signing in", Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {

                        }


                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }

}
