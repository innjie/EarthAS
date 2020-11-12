package ericson.lg.mobile.earthas.ui.confusion;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ericson.lg.mobile.earthas.R;

public class ConfusionFragment extends Fragment {

    private Confusion confusion;

    private Button btnSearch;

    private ListView lvSearch;
    private List<Confusion> confusionList;

    private Cursor cursor;
    private SimpleCursorAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_confusion, container, false);

        confusionList = new ArrayList<>();
        lvSearch = root.findViewById(R.id.list_search);
        //cursor = ;
//        adapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_2, cursor,
  //                                          new String[]{"name", "type"}, new int[]{android.R.id.text1, android.R.id.text2});

        btnSearch = root.findViewById(R.id.button_search);
        btnSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return root;
    }

}