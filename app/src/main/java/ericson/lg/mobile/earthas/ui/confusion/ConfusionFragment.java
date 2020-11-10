package ericson.lg.mobile.earthas.ui.confusion;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;
import java.util.List;

import ericson.lg.mobile.earthas.R;

public class ConfusionFragment extends Fragment {

    private DynamoDBMapper dynamoDBMapper;

    private Confusion confusion;

    private Button btnSearch;

    private ListView lvSearch;
    private List<Confusion> confusionList;

    private Cursor cursor;
    private SimpleCursorAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_confusion, container, false);

        confusionList = new ArrayList<>();
        lvSearch = root.findViewById(R.id.list_search);
        //cursor = ;
//        adapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_2, cursor,
  //                                          new String[]{"name", "type"}, new int[]{android.R.id.text1, android.R.id.text2});

        btnSearch = root.findViewById(R.id.button_search);
        btnSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // AWSMobileClient enables AWS user credentials to access your table
        AWSMobileClient.getInstance().initialize(root.getContext());


                new AWSStartupHandler() {
            @Override public void onComplete(AWSStartupResult awsStartupResult) {
                // Add code to instantiate a AmazonDynamoDBClient
                try {
                    AmazonDynamoDBClient dynamoDBClient
                            = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getAWSCredentials());
                    //    = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
                    dynamoDBMapper = DynamoDBMapper.builder()
                            .dynamoDBClient(dynamoDBClient)
                            .awsConfiguration( AWSMobileClient.getInstance().getConfiguration())
                            .build();

                    readData();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).execute();

//        AWSMobileClient.getInstance().initialize(root.getContext(), new AWSStartupHandler() {
//            @Override public void onComplete(AWSStartupResult awsStartupResult) {
//                // Add code to instantiate a AmazonDynamoDBClient
//                try {
//                    AmazonDynamoDBClient dynamoDBClient
//                            = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getAWSCredentials());
//                    //    = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
//                    dynamoDBMapper = DynamoDBMapper.builder()
//                            .dynamoDBClient(dynamoDBClient)
//                            .awsConfiguration( AWSMobileClient.getInstance().getConfiguration())
//                            .build();
//
//                    readData();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).execute();

        return root;
    }

    public void readData() {
        new Thread(new Runnable() {
            @Override public void run() {
//                for(int i=0; i < dynamoDBMapper.; i++){
//
//                }
                confusion = dynamoDBMapper.load(Confusion.class, "파뿌리", 1000000L);
                Log.i("[*]AWS", "name : " + confusion.getName());
                Log.i("[*]AWS", "type : " + confusion.getType());

                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {
//                        Confusion confusion = new Confusion();
//                        confusion.setName(item.getName());
//                        confusion.setType();

                        confusionList.add(confusion);
                    }
                });
            }
        }).start();
    }


}