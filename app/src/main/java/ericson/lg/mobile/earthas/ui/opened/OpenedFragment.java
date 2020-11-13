package ericson.lg.mobile.earthas.ui.opened;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import ericson.lg.mobile.earthas.R;

public class OpenedFragment extends Fragment {
    private Button btnAllClose;
    private TextView tvRegion;

    private ArrayList<String> openList;

    private RecyclerView recyclerOpened;
    private LinearLayoutManager layoutManager;

    private OpenedAdapter adapter;

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_opened, container, false);

        tvRegion = root.findViewById(R.id.text_region);

        recyclerOpened = root.findViewById(R.id.recycler_opened);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerOpened.setLayoutManager(layoutManager);

        adapter = new OpenedAdapter();
        recyclerOpened.setAdapter(adapter);

        parsingStatus();

        btnAllClose = root.findViewById(R.id.button_allclose);
        btnAllClose.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openList에 저장된 타입들 바디로
            }
        });

        return root;
    }

    void parsingStatus() {
        try {
            //String url = root.getResources().getString(R.string.url) + root.getResources().getString(R.string.url_box_status) + URLEncoder.encode(query, "UTF-8");
            String url = root.getResources().getString(R.string.url) + root.getResources().getString(R.string.url_box_status) + "seoul";
            new RestAPITask().execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class RestAPITask extends AsyncTask<String, Void, String> {

        //수행 전
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... Strings) {
            String result = null;

            try {
                result = downloadContents(Strings[0]);

            }
            catch (Exception e) {
                // Error calling the rest api
                Log.e("REST_API", "GET method failed: " + e.getMessage());
                e.printStackTrace();
            }

            return result;
        }

        //작업 완료
        @Override
        protected void onPostExecute(String result) {
            Log.d("parsing", result);
            parse(result);

            adapter.notifyDataSetChanged();
        }
    }

    /* 주소(address)에 접속하여 문자열 데이터를 수신한 후 반환 */
    protected String downloadContents(String address) {
        HttpURLConnection conn = null;
        InputStream stream = null;
        String result = null;

        try {
            URL url = new URL(address);
            conn = (HttpURLConnection)url.openConnection();
            stream = getNetworkConnection(conn);
            result = readStreamToString(stream);
            if (stream != null) stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }

    // URLConnection 을 전달받아 연결정보 설정 후 연결, 연결 후 수신한 InputStream 반환
    private InputStream getNetworkConnection(HttpURLConnection conn) throws Exception {
        // 클라이언트 아이디 및 시크릿 그리고 요청 URL 선언
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setDoInput(true);
        conn.setRequestProperty("content-type", "application/json");

        if (conn.getResponseCode() != HttpsURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code: " + conn.getResponseCode());
        }

        return conn.getInputStream();
    }

    /* InputStream을 전달받아 문자열로 변환 후 반환 */
    protected String readStreamToString(InputStream stream){
        StringBuilder result = new StringBuilder();

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String readLine = bufferedReader.readLine();

            while (readLine != null) {
                result.append(readLine + "\n");
                readLine = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    //json parsing
    public void parse(String json){
        try{
            JSONObject object = new JSONObject(json);
            JSONObject jsonOpened = object.getJSONObject("Item");
            Opened opened;
            String[] typeName = {"general", "paper", "plastic", "can", "glass", "vinyl"};

            openList = new ArrayList<>();

            tvRegion.setText(jsonOpened.getString("region"));

            for(int i = 0; i < typeName.length ; i++) {
                if(jsonOpened.getBoolean(typeName[i])){
                    opened = new Opened(typeName[i]);

                    adapter.addItem(opened);

                    openList.add(typeName[i]);
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}