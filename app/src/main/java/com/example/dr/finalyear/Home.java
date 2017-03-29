package com.example.dr.finalyear;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by DR on 3/29/2017.
 */
public class Home extends Fragment {
    String username;
    public Home() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        Button attn = (Button) view.findViewById(R.id.attendence);
        Button image = (Button) view.findViewById(R.id.image);
        username=getActivity().getIntent().getStringExtra("username");
        if(username!=null)
        {
            attn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("username",username);
                    getActivity().startActivity(intent);
                }
            });
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(), "yet not implemented!!!", Toast.LENGTH_LONG).show();
                }
            });
        }
        else
        {
            getActivity().finish();
            Toast.makeText(getActivity().getApplicationContext(), "Incorrect Username or password !!!\n or Check your Internet connection", Toast.LENGTH_LONG).show();
        }
        return view;
    }

}
