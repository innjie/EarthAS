package ericson.lg.mobile.earthas.ui.opened;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import ericson.lg.mobile.earthas.R;
import ericson.lg.mobile.earthas.ui.collection.CollectionFragment;

public class OpenedFragment extends Fragment {
    private Button btnAllClose;
    private TextView tvRegion;

    private ArrayList<String> openList;
    private ArrayList<String> selectList;

    private ListView lvOpened;

    private OpenedAdapter adapter;

    private View root;

    private Boolean status;
    private String body;

    private AlertDialog.Builder builder;

    private String apiAddress;
    private String region;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("in Opened Activity", "in Opened Activity");
        root = inflater.inflate(R.layout.fragment_opened, container, false);

        tvRegion = root.findViewById(R.id.text_region);

        builder = new AlertDialog.Builder(root.getContext());

        lvOpened = root.findViewById(R.id.list_opened);

        openList = new ArrayList<>();
        selectList = new ArrayList<>();

        adapter = new OpenedAdapter(root.getContext(), R.layout.list_opened, openList);
        lvOpened.setAdapter(adapter);

        parsingStatus();

        btnAllClose = root.findViewById(R.id.button_allclose);
        btnAllClose.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("Do you want to close all boxes?")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectList.addAll(openList);
                                openList.clear();
                                parsingClose();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

        lvOpened.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String type = openList.get(i);

                Log.d("clicked Item: ", type);

                builder.setMessage(type + " close")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectList.add(type);
                                openList.remove(i);
                                parsingClose();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

        return root;
    }

    void parsingStatus() {
        apiAddress = "https://dbibkwfit6.execute-api.us-east-2.amazonaws.com/earthAS/box/find";
        Log.d("status URL: ", apiAddress);
        region = "seoul";

        try {
            new RestAPITask().execute(apiAddress );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void parsingClose() {
        apiAddress = root.getResources().getString(R.string.url) + root.getResources().getString(R.string.url_box_close);
        status = false;
        region = "seoul";

        try {
            new RestAPITask().execute(apiAddress + URLEncoder.encode(region, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class RestAPITask extends AsyncTask<String, Void, String> {

        //수행 전
        @Override
        protected void onPreExecute() {
            if(!status){
                try {

                    body="{\"type\":"+selectList.toString()+"}";

                    Log.d("body", body);
                } catch (/*JSON*/Exception e) {
                    e.printStackTrace();
                }
            }
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
            if(status){
                Log.d("parsing", result);
                parse(result);

                adapter.setList(openList);
                adapter.notifyDataSetChanged();
            } else {
                adapter.setList(openList);
                adapter.notifyDataSetChanged();

                Toast.makeText(root.getContext(), selectList.toString() + " boxes close success", Toast.LENGTH_SHORT).show();
                selectList.clear();
            }
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
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setDoInput(true);
        conn.setRequestProperty("content-type", "application/json");

        if(status){
            conn.setRequestMethod("GET");
        } else{
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept", "application/json");

            writeStream(conn);
        }

        if (conn.getResponseCode() != HttpsURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code: " + conn.getResponseCode());
        }

        return conn.getInputStream();
    }

    protected void writeStream(HttpURLConnection conn) {
        try {
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(body); //json 형식의 메세지 전달
            wr.flush();
            wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
            String[] typeName = {"general", "paper", "plastic", "can", "glass", "vinyl"};

            tvRegion.setText(jsonOpened.getString("region"));

            for(int i = 0; i < typeName.length ; i++) {
                Log.d("in Iterator", typeName[i]);
                if(jsonOpened.getBoolean(typeName[i])){
                    openList.add(typeName[i]);
                }
            }

            Log.d("collection List", openList.toString());
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}