package ericson.lg.mobile.earthas.ui.confusion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ericson.lg.mobile.earthas.R;

public class ConfusionFragment extends Fragment {
    private Button btnSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_confusion, container, false);

        btnSearch = root.findViewById(R.id.button_search);
        btnSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return root;
    }
}