package dev.lytran.fragmentexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment3 extends Fragment {

    EditText edtFrag3;
    Button btnFrag3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        edtFrag3 = view.findViewById(R.id.edtFrag3);
        btnFrag3 = view.findViewById(R.id.btnFrag3);

        btnFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtContent = getActivity().findViewById(R.id.edtContent);
                edtContent.setText(edtFrag3.getText().toString());
            }
        });
        return view;
    }

    public void setContent(String text) {
        edtFrag3.setText(text);
    }
}