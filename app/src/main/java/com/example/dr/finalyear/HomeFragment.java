package com.example.dr.finalyear;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by DR on 3/20/2017.
 */
public class HomeFragment extends Fragment {
    String name, password, email,department,designation,phone,username,specialization;
    TextView nameTV, emailTV, passwordTV,desigTV,departTV,specialTV,phoneTV,usernameTV;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        nameTV = (TextView)view.findViewById(R.id.ac_name);
        desigTV=(TextView)view.findViewById(R.id.ac_desig);
        specialTV=(TextView)view.findViewById(R.id.ac_special);
        phoneTV=(TextView)view.findViewById(R.id.ac_phone);
        emailTV = (TextView) view.findViewById(R.id.ac_email);
        departTV=(TextView)view.findViewById(R.id.ac_depart);
        usernameTV=(TextView)view.findViewById(R.id.ac_username);
        passwordTV=(TextView)view.findViewById(R.id.ac_password);

        name = getActivity().getIntent().getStringExtra("name");
        designation=getActivity().getIntent().getStringExtra("designation");
        department=getActivity().getIntent().getStringExtra("department");
        specialization=getActivity().getIntent().getStringExtra("specialization");
        email=getActivity().getIntent().getStringExtra("email");
        phone=getActivity().getIntent().getStringExtra("phone");
        username=getActivity().getIntent().getStringExtra("username");
        password=getActivity().getIntent().getStringExtra("password");

        if(username!=null) {
            nameTV.setText("Welcome   " + name);
            desigTV.setText("Designation " + designation);
            specialTV.setText("Field of Specialization " + specialization);
            phoneTV.setText("Phone No. " + phone);
            emailTV.setText("Email Id" + email);
            departTV.setText("DEPARTMENT " + department );
            usernameTV.setText("USERNAME " + username);
            passwordTV.setText("PASSWORD " + password);
        }
        else
        {
            getActivity().getFragmentManager().popBackStackImmediate();
            getActivity().finish();
            Toast.makeText(getActivity().getApplicationContext(), "Incorrect Username or password !!! ", Toast.LENGTH_LONG).show();

        }
        return view;
    }
}
