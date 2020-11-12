package ericson.lg.mobile.earthas.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ericson.lg.mobile.earthas.R;

public class InfoFragment extends Fragment {
    private TextView tvInfoCollection;
    private TextView tvInfoConfusion;
    private TextView tvInfoOpened;

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_info, container, false);

        tvInfoCollection = root.findViewById(R.id.text_info_collection);
        tvInfoConfusion = root.findViewById(R.id.text_info_confusion);
        tvInfoOpened = root.findViewById(R.id.text_info_opened);


        tvInfoCollection.setText(
                "1. 자동으로 일반 수거함을 열 수 있습니다.. \n" +
                "1. 블루투스를 연결하세요. \n" +
                "2. 원하는 버튼을 누르세요 \n" +
                "3. 주의사항과 품목을 확인하세요 \n" +
                "4. 확인 버튼을 누르면 수거함이 열립니다.");

        tvInfoConfusion.setText(
                "- 분류 기준이 모호한 품명은 검색 기능을 활용할 수 있습니다.\n" +
                "1. 분류 기준이 모호한 품명을 검색하세요.\n" +
                "2. 검색하여 나온 품목을 클릭하세요.\n" +
                "3. 팝업창의 open 버튼을 클릭하면 해당 품목의 수거함이 열립니다. ");

        tvInfoOpened.setText(
                "- 현재 열려있는 수거함 목록을 확인할 수 있습니다.\n" +
                "1. 블루투스를 연결하세요.\n" +
                "2. 열려있는 수거함 중 원하는 수거함을 클릭하세요.\n" +
                "3. 팝업창의 close 버튼을 클릭하면 해당 수거함이 닫힙니다. \n" +
                "4. 전체 수거함 닫기를 원하시는 경우 아래쪽 all close 버튼을 누르면 전체 수거함이 닫힙니다.");

        return root;
    }
}
