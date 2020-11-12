package ericson.lg.mobile.earthas.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import ericson.lg.mobile.earthas.ui.collection.CollectionFragment;
import ericson.lg.mobile.earthas.ui.confusion.ConfusionFragment;
import ericson.lg.mobile.earthas.ui.info.InfoFragment;
import ericson.lg.mobile.earthas.ui.opened.OpenedFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private int index;

    private View root;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        root = null;

        switch (index){
            case 0:
                InfoFragment infoFragment = new InfoFragment();
                root = infoFragment.onCreateView(inflater, container, savedInstanceState);
                break;
            case 1:
                CollectionFragment collectionFragment = new CollectionFragment();
                root = collectionFragment.onCreateView(inflater, container, savedInstanceState);
                break;
            case 2:
                ConfusionFragment confusionFragment = new ConfusionFragment();
                root = confusionFragment.onCreateView(inflater, container, savedInstanceState);
                break;
            case 3:
                OpenedFragment openedFragment = new OpenedFragment();
                root = openedFragment.onCreateView(inflater, container, savedInstanceState);
                break;
        }

        return root;
    }
}