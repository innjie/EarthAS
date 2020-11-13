package ericson.lg.mobile.earthas.ui.collection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import ericson.lg.mobile.earthas.ui.opened.Opened;
import ericson.lg.mobile.earthas.ui.opened.OpenedFragment;

public class CollectionFragment extends Fragment {
    private Button btnOpen;
    private Button btnGeneral;
    private Button btnPaper;
    private Button btnGlass;
    private Button btnCan;
    private Button btnPlastic;
    private Button btnVinyl;

    private TextView tvCaution;
    private TextView tvCollection;

    private String selectType;
    private String body;

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_collection, container, false);

        btnOpen = root.findViewById(R.id.button_open);
        btnGeneral = root.findViewById(R.id.button_general);
        btnPaper = root.findViewById(R.id.button_paper);
        btnGlass = root.findViewById(R.id.button_glass);
        btnCan = root.findViewById(R.id.button_can);
        btnPlastic = root.findViewById(R.id.button_plastic);
        btnVinyl = root.findViewById(R.id.button_vinyl);

        tvCaution = root.findViewById(R.id.text_caution);
        tvCollection = root.findViewById(R.id.text_collectionList);

        btnOpen.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                parsing();
            }
        });

        btnGeneral.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChange(selectType);
                btnGeneral.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "general";

                tvCaution.setText(
                        "≡ 종량제 봉투에 담아 버려주세요\n" +
                        "≡ 재활용이 불가능한 품목들은 일반쓰레기로 배출");
                tvCollection.setText("");
            }
        });

        btnPaper.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChange(selectType);
                btnPaper.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "paper";

                tvCaution.setText(
                        "≡ 물에 젖지 않아야 하고 끈으로 묶거나 박스류에 담아서 배출\n" +
                        "≡ 종이팩은 내용물을 비운 뒤 가급적 물로 헹군 후 압착하여 배출\n" +
                        "≡ 상자에 붙어 있는 테이프나 철판 제거\n" +
                        "≡ 비닐 코팅되거나 스프링 제본된 책자는 가급적 제거\n\n" +
                        "<재활용 불가>\n" +
                        "≡ 기름묻은 종이, 묶지 않은 낱장종이, 낱장신문지, 화장지 등\n" +
                        "≡ 광고전단지 중 비닐 코팅이 된 것");
                tvCollection.setText("신문지, 책자, 노트, 전단지, 쇼핑백, 달력, 포장지, 상자류, 종이컵 등");
            }
        });

        btnPlastic.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChange(selectType);
                btnPlastic.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "plastic";

                tvCaution.setText(
                        "≡ 뚜껑을 제거한 후, 내용물을 비우고 물로 한번 헹구어 배출\n" +
                        "≡ 가능한 압착하여 부피를 축소\n" +
                        "≡ 알루미늄 뚜껑은 고철로 분리 배출\n" +
                        "≡ 용기의 표면 또는 바닥부분에 표기된 분리배출표시 (LDPE, PP, PS)를 확인하여 배출\n\n" +
                        "<재활용 불가>\n" +
                        "≡ 열에 잘 녹지 않는 플라스틱용기 : 전화기, 소켓, 다리미, 냄비의 손잡이, 전기전열기, 단추, 화장품 용기, 식기류, 재떨이, 쟁반 등\n" +
                        "≡ PVC류 용기 : 파이프, 빗물홈통, 장판, 전선 등\n" +
                        "≡ 복합재질 용기 : 과자, 라면봉지, 식품포장 용기 등\n" +
                        "≡ 기타 재활용 경제성이 없는 용기 : 가전제품 케이스, 함지박, 정화조, 비닐봉지, 볼펜 등 필기구, 1회용품 등\n" +
                        "≡ 플라스틱과 철사가 합성되어 있는 제품류(옷걸이 등)\n" +
                        "≡ 용기표면 재질분류표시 3,7번으로 표시된 플라스틱 제품");
                tvCollection.setText("페트병, 플라스틱 용기류, 스티로폼 등");
            }
        });

        btnGlass.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChange(selectType);
                btnGlass.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "glass";

                tvCaution.setText(
                        "≡ 플라스틱, 알루미늄 뚜껑 제거 후 내용물 비운 후 배출\n" +
                        "≡ 담배꽁초등 이물질을 넣지 말것\n" +
                        "≡ 일반병(소주, 맥주, 음료)은 소매정 등에서 보증금을 환불받을 수 있음\n" +
                        "≡ 농약병은 다른 병류와 섞이지 않게 따로 모아 한국환경자원공사에 회수의뢰\n\n" +
                        "<재활용 불가>\n" +
                        "≡ 거울, 깨진 유리, 도자기류, 내열식기류, 형광등, 전구 등\n" +
                        "≡ 유백색의 유리병");
                tvCollection.setText("소주병, 맥주병, 청량음료 병 등");
            }
        });

        btnCan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChange(selectType);
                btnCan.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "can";

                tvCaution.setText(
                        "<캔>\n" +
                        "≡ 내용물은 비우고 물로 씻은 후 가능하면 압착\n" +
                        "≡ 겉 또는 속에 플라스틱 뚜껑이 있는 것은플라스틱 제거\n" +
                        "≡ 부탄가스통, 살충제통 등은 구멍을 뚫어 내용물을 비운 후 배출\n\n" +
                        "<고철>\n" +
                        "≡ 부착된 플라스틱, 고무류 등은 제거\n" +
                        "≡ 이물질이 섞이지 않도록 한 후 투명 봉투에 넣거나 묶어서 배출\n\n" +
                        "<재활용 불가>\n" +
                        "≡ 페인트, 오일통 등 유해물질 포장통과 폐유 용기");
                tvCollection.setText(
                        "<캔>\n" +
                        "맥주캔, 음료수캔, 통조림캔, 분유통, 과자통, 면도통 등\n" +
                        "부탄가스통, 살충제통 등\n\n" +
                        "<고철>\n" +
                        "공기구, 철사, 못, 철판 등 (고철)\n" +
                        "양은, 스텐류, 구리, 전선, 알루미늄 샤시 등 (비철금속)");
            }
        });

        btnVinyl.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChange(selectType);
                btnVinyl.setBackgroundResource(R.drawable.button_radius_sub2);
                selectType = "vinyl";

                tvCaution.setText(
                        "≡ 흰색봉투와 그 외 유색봉투로 분리\n" +
                        "≡ 일정량을 모아 묶어서 배출\n" +
                        "≡ 이물질이 묻어있지 않아야 함\n" +
                        "≡ 무상제공금지규정에 따라 유상구매한 1회용\n" +
                        "≡ 비닐봉투는 소매점에 반환시 환불가능\n\n" +
                        "<재활용 불가>\n" +
                        "≡ 이물질 및 부착 상표 등의 제거가 어려운 경우");
                tvCollection.setText(
                        "라면 비닐, 과자봉지, 커피믹스 봉지, 일회용 봉지, 검은 봉지, 포장용 뽁뽁이 등 \n" +
                        "재활용분리 배출마크가 있는 비닐 포장재 등");
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

    void parsing() {
        try {
            String url = root.getResources().getString(R.string.url) + root.getResources().getString(R.string.url_box_open) + "seoul";
            new RestAPITask().execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class RestAPITask extends AsyncTask<String, Void, String> {

        //수행 전
        @Override
        protected void onPreExecute() {
            try {
                JSONObject json = new JSONObject();
                json.put("type", selectType);
                body = json.toString();
            } catch (JSONException e) {
                e.printStackTrace();
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
            Toast.makeText(root.getContext(), selectType + " box open success", Toast.LENGTH_SHORT).show();
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
        conn.setRequestMethod("PUT");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("content-type", "application/json");

        writeStream(conn);

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
}