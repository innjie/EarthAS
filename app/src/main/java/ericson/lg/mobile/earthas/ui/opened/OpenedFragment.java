package ericson.lg.mobile.earthas.ui.opened;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ericson.lg.mobile.earthas.R;

public class OpenedFragment extends Fragment {
    private Button btnAllClose;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_opened, container, false);

        btnAllClose = root.findViewById(R.id.button_allclose);
        btnAllClose.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return root;
    }
}