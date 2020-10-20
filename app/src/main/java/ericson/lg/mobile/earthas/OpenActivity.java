package ericson.lg.mobile.earthas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;

public class OpenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        Handler handler = new Handler() {
            public  void handleMessage(Message message){
                super.handleMessage(message);

                Intent intent = new Intent(OpenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 3000);

    }
}
