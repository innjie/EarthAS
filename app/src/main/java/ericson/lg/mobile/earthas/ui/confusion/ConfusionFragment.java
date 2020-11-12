package ericson.lg.mobile.earthas.ui.confusion;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import ericson.lg.mobile.earthas.R;

public class ConfusionFragment extends Fragment {

    private Button btnSearch;

    private RecyclerView recyclerConfusion;
    private LinearLayoutManager layoutManager;

    private ConfusionAdapter adapter;

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_confusion, container, false);

        recyclerConfusion = root.findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerConfusion.setLayoutManager(layoutManager);

        adapter = new ConfusionAdapter();
        recyclerConfusion.setAdapter(adapter);

        parsing();

        btnSearch = root.findViewById(R.id.button_search);
        btnSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return root;
    }

    void parsing() {
        try {
            new RestAPITask().execute(root.getResources().getString(R.string.url) + root.getResources().getString(R.string.url_confusion_list));
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
            JSONArray array = object.getJSONArray("Items");
            JSONObject jsonConfusion;
            Confusion confusion;

            for(int i = 0; i< array.length(); i++) {
                jsonConfusion = array.getJSONObject(i);
                confusion = new Confusion();

                confusion.setName(jsonConfusion.getString("name"));
                confusion.setType(jsonConfusion.getString("type"));

                adapter.addItem(confusion);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}