package ericson.lg.mobile.earthas;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity  {

    private Button btnOn;
    private Button btnOff;
    private Button btnConnect;

    private TextView tvStatus;

    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;
    private List<String> listPairedDevices;

    private Handler bluetoothHandler;
   // private ConnectedBluetoothThread threadConnectedBluetooth;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;

    private final static int BT_REQUEST_ENABLE = 1;
    private final static int BT_MESSAGE_READ = 2;
    private final static int BT_CONNECTING_STATUS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        btnOn = findViewById(R.id.btn_bluetooth_on);
        btnOff = findViewById(R.id.btn_bluetooth_off);
        btnConnect = findViewById(R.id.btn_bluetooth_connect);

        tvStatus = findViewById(R.id.text_bluetooth_status);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnOn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothOn();
            }
        });

        btnOff.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothOff();
            }
        });

        btnConnect.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPairedDevices();
            }
        });
    }

    public void bluetoothOn() {
        if(bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "블루투스를 지원하지 않는 기기입니다.", Toast.LENGTH_LONG).show();
        }
        else {
            if (bluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "블루투스가 이미 활성화 되어 있습니다.", Toast.LENGTH_LONG).show();
                tvStatus.setText("블루투스 ON");
            }
            else {
                Toast.makeText(getApplicationContext(), "블루투스가 활성화 되어 있지 않습니다.", Toast.LENGTH_LONG).show();
                Intent intentBluetoothEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intentBluetoothEnable, BT_REQUEST_ENABLE);
            }
        }
    }

    public void bluetoothOff() {
        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되었습니다.", Toast.LENGTH_SHORT).show();
            tvStatus.setText("블루투스 OFF");
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 이미 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case BT_REQUEST_ENABLE:
                if (resultCode == RESULT_OK) { // 블루투스 활성화를 확인을 클릭하였다면
                    Toast.makeText(getApplicationContext(), "블루투스 활성화", Toast.LENGTH_LONG).show();
                    tvStatus.setText("블루투스 ON");
                } else if (resultCode == RESULT_CANCELED) { // 블루투스 활성화를 취소를 클릭하였다면
                    Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
                    tvStatus.setText("블루투스 OFF");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void listPairedDevices() {
        if (bluetoothAdapter.isEnabled()) {
            pairedDevices = bluetoothAdapter.getBondedDevices();

            if (pairedDevices.size() > 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("장치 선택");

                listPairedDevices = new ArrayList<String>();
                for (BluetoothDevice device : pairedDevices) {
                    listPairedDevices.add(device.getName());
                    //mListPairedDevices.add(device.getName() + "\n" + device.getAddress());
                }
                final CharSequence[] items = listPairedDevices.toArray(new CharSequence[listPairedDevices.size()]);
                listPairedDevices.toArray(new CharSequence[listPairedDevices.size()]);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
             //           connectSelectedDevice(items[item].toString());
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                Toast.makeText(getApplicationContext(), "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }

//    void connectSelectedDevice(String selectedDeviceName) {
//        for(BluetoothDevice tempDevice : pairedDevices) {
//            if (selectedDeviceName.equals(tempDevice.getName())) {
//                bluetoothDevice = tempDevice;
//                break;
//            }
//        }
//        try {
//            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(BT_UUID);
//            bluetoothSocket.connect();
//            threadConnectedBluetooth = new ConnectedBluetoothThread(bluetoothSocket);
//            threadConnectedBluetooth.start();
//            bluetoothHandler.obtainMessage(BT_CONNECTING_STATUS, 1, -1).sendToTarget();
//        } catch (IOException e) {
//            Toast.makeText(getApplicationContext(), "블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
//        }
//    }

}