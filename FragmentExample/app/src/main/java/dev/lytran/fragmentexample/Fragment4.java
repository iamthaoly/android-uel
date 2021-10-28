package dev.lytran.fragmentexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class Fragment4 extends Fragment {

    EditText edtFrag4;
    Button btnFrag4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_4, container, false);
        edtFrag4 = v.findViewById(R.id.edtFrag4);
        btnFrag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = getActivity().findViewById(R.id.edtFrag3);
                editText.setText(edtFrag4.getText().toString());
            }
        });
        return v;

    }
}