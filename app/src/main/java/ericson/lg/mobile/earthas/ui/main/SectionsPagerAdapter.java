package ericson.lg.mobile.earthas.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ericson.lg.mobile.earthas.R;
import ericson.lg.mobile.earthas.ui.collection.CollectionFragment;
import ericson.lg.mobile.earthas.ui.confusion.ConfusionFragment;
import ericson.lg.mobile.earthas.ui.info.InfoFragment;
import ericson.lg.mobile.earthas.ui.opened.OpenedFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_info, R.string.tab_collection, R.string.tab_confusion, R.string.tab_opened};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                InfoFragment infoFragment = new InfoFragment();
                return infoFragment;
            case 1:
                CollectionFragment collectionFragment = new CollectionFragment();
                return collectionFragment;
            case 2:
                ConfusionFragment confusionFragment = new ConfusionFragment();
                return confusionFragment;
            case 3:
                OpenedFragment openedFragment = new OpenedFragment();
                return openedFragment;
        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}