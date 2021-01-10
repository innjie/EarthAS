package ericson.lg.mobile.earthas.ui.confusion;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import ericson.lg.mobile.earthas.R;

public class ConfusionFragment extends Fragment {

    private Confusion confusion;
    private Button btnSearch;
    private ListView lvSearch;
    private List<Confusion> confusionList;
    private Cursor cursor;
    private EditText editText;
    private View root;
    private ConfusionAdapter adapter;
    private String item;
    private RecyclerView recyclerConfusion;
    private LinearLayoutManager layoutManager;
    private String url_list = "https://dbibkwfit6.execute-api.us-east-2.amazonaws.com/earthAS/unknownlist/list";
    private String url_find = "https://dbibkwfit6.execute-api.us-east-2.amazonaws.com/earthAS/unknownlist/find?atValue=";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_confusion, container, false);

        recyclerConfusion = root.findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerConfusion.setLayoutManager(layoutManager);

        adapter = new ConfusionAdapter();
        recyclerConfusion.setAdapter(adapter);

        Log.d("onCreateView", "success in Confusion Fragment");
        parseList();

        editText = root.findViewById(R.id.edit_item);
        btnSearch = root.findViewById(R.id.button_search);
        btnSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = editText.getText().toString();
                parseFind();
            }
        });

        return root;
    }
    void parseList() {
        String url = url_list;

        try {
            new RestAPITask().execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void parseFind() {
        String url = url_find;

        try {
            new RestAPITask().execute((url + URLEncoder.encode(item, "UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void parseOpen() {
        //find collection & parsing
        String apiAddress = root.getResources().getString(R.string.url) + root.getResources().getString(R.string.url_box_open);
        String region = "seoul";
        try {
            new RestAPITask().execute(apiAddress + URLEncoder.encode(region, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class RestAPITask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            adapter.clearItem();
        }

        @Override
        protected String doInBackground (String... Strings) {
            String result = null;
            Log.d("URL", Strings[0]);

            try {
                result = downloadContents(Strings[0]);
            } catch (Exception e) {
                Log.e("RestAPI", "GET Failed: " + e.getMessage());
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            parse(result);
            adapter.notifyDataSetChanged();
        }
    }
    protected String downloadContents(String address) {
        HttpURLConnection conn = null;
        InputStream stream = null;
        String result = null;
        Log.d("URL", address);

        try {
            URL url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
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

    public void parse (String json) {
        try {
            JSONObject object = new JSONObject(json);
            JSONArray jArray = object.getJSONArray("Items");

            Log.d("arrayLength", String.valueOf(jArray.length()));
            JSONObject jConfusion;
            Confusion confusion;

            for(int i = 0; i < jArray.length(); i++) {
                jConfusion = jArray.getJSONObject(i);
                confusion = new Confusion();

                confusion.setName(jConfusion.getJSONObject("confusion_name").getString("S"));
                confusion.setType(jConfusion.getJSONObject("type").getString("S"));

                adapter.addItem(confusion);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}