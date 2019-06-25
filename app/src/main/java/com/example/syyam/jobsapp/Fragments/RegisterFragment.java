package com.example.syyam.jobsapp.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.syyam.jobsapp.API;
import com.example.syyam.jobsapp.MainActivity;
import com.example.syyam.jobsapp.Models.Login;
import com.example.syyam.jobsapp.Models.Register;
import com.example.syyam.jobsapp.Models.params.LoginParam;
import com.example.syyam.jobsapp.Models.params.RegisterParam;
import com.example.syyam.jobsapp.R;
import com.example.syyam.jobsapp.Utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.syyam.jobsapp.Utils.Config.MY_PREFS_NAME;

public class RegisterFragment extends Fragment {

    private EditText email, password, userName, fName, lName;
    private Button submitBtn;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);


        email = (EditText) rootView.findViewById(R.id.email);
        fName = (EditText) rootView.findViewById(R.id.fName);
        userName = (EditText) rootView.findViewById(R.id.userName);
        lName = (EditText) rootView.findViewById(R.id.lName);
        password = (EditText) rootView.findViewById(R.id.password);
        submitBtn = (Button) rootView.findViewById(R.id.submitBtn);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(email.getText().toString())
                        && !TextUtils.isEmpty(password.getText().toString())
                        && !TextUtils.isEmpty(fName.getText().toString())
                        && !TextUtils.isEmpty(lName.getText().toString())
                        && !TextUtils.isEmpty(userName.getText().toString()))

                    getData(email.getText().toString(),
                            password.getText().toString(),
                            userName.getText().toString(),
                            lName.getText().toString(),
                            fName.getText().toString());
                else
                    Toast.makeText(getContext(), "Please fill all of the required fields.", Toast.LENGTH_LONG).show();

            }
        });


        return rootView;


    }

    public void getData(String email, String password, String userName, String lName, String fName) {

        Retrofit build = new Retrofit
                .Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API retrofitController = build.create(API.class);


        final SharedPreferences.Editor editor = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();


        RegisterParam registerParam = new RegisterParam();
        registerParam.setEmail(email);
        registerParam.setPassword(password);
        registerParam.setFirstname(fName);
        registerParam.setLastname(lName);
        registerParam.setUsername(userName);

        //Config.getToken(getContext())
        Call<Register> loginCall = retrofitController.getRegister(registerParam);
        loginCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                if (response != null) {
                    Register list = response.body();

                    if (response.body() != null) {
                        editor.putString("token", list.getToken().toString());
                        editor.apply();

                        Toast.makeText(getContext(), "Successfully Signed Un. ", Toast.LENGTH_LONG).show();

                        Intent l = new Intent(getContext(), MainActivity.class);
                        getActivity().finish();
                        startActivity(l);

                    } else {
                        Toast.makeText(getContext(), "Incorrect email or password", Toast.LENGTH_LONG).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(getContext(), "Error signing in", Toast.LENGTH_LONG).show();
            }
        });
    }
}
