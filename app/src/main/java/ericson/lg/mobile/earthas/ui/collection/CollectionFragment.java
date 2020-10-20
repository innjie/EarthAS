package ericson.lg.mobile.earthas.ui.collection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ericson.lg.mobile.earthas.R;

public class CollectionFragment extends Fragment {
    private Button btnOpen;
    private Button btnGeneral;
    private Button btnPaper;
    private Button btnGlass;
    private Button btnCan;
    private Button btnPlastic;
    private Button btnVinyl;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_collection, container, false);

        btnOpen = root.findViewById(R.id.button_open);
        btnGeneral = root.findViewById(R.id.button_general);
        btnPaper = root.findViewById(R.id.button_paper);
        btnGlass = root.findViewById(R.id.button_glass);
        btnCan = root.findViewById(R.id.button_can);
        btnPlastic = root.findViewById(R.id.button_plastic);
        btnVinyl = root.findViewById(R.id.button_vinyl);

        btnOpen.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnGeneral.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnGeneral.setBackgroundResource(R.drawable.button_radius_sub2);
            }
        });

        btnPaper.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPaper.setBackgroundResource(R.drawable.button_radius_sub2);

            }
        });

        btnGlass.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnGlass.setBackgroundResource(R.drawable.button_radius_sub2);

            }
        });

        btnCan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCan.setBackgroundResource(R.drawable.button_radius_sub2);

            }
        });

        btnPlastic.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPlastic.setBackgroundResource(R.drawable.button_radius_sub2);

            }
        });

        btnVinyl.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnVinyl.setBackgroundResource(R.drawable.button_radius_sub2);

            }
        });

        return root;
    }
}