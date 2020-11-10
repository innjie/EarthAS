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
    private String selectType;

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
                buttonChange(selectType);
                btnGeneral.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "general";
            }
        });

        btnPaper.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChange(selectType);
                btnPaper.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "paper";
            }
        });

        btnPlastic.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChange(selectType);
                btnPlastic.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "plastic";
            }
        });

        btnGlass.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChange(selectType);
                btnGlass.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "glass";
            }
        });

        btnCan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChange(selectType);
                btnCan.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "can";
            }
        });

        btnVinyl.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChange(selectType);
                btnVinyl.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "vinyl";
            }
        });

        return root;
    }

    public void buttonChange(String selectType){
        if(selectType != null){
            switch (selectType){
                case "general":
                    btnGeneral.setBackgroundResource(R.drawable.button_radius_sub);
                    break;
                case "paper":
                    btnPaper.setBackgroundResource(R.drawable.button_radius_sub);
                    break;
                case "plastic":
                    btnPlastic.setBackgroundResource(R.drawable.button_radius_sub);
                    break;
                case "glass":
                    btnGlass.setBackgroundResource(R.drawable.button_radius_sub);
                    break;
                case "can":
                    btnCan.setBackgroundResource(R.drawable.button_radius_sub);
                    break;
                case "vinyl":
                    btnVinyl.setBackgroundResource(R.drawable.button_radius_sub);
                    break;
            }
        }
    }
}