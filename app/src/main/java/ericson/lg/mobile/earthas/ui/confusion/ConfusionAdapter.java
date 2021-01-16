package ericson.lg.mobile.earthas.ui.confusion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class ConfusionAdapter extends RecyclerView.Adapter<ConfusionAdapter.ItemViewHolder> {
    private ArrayList<Confusion> confusions = new ArrayList<>();
    private View root;
    String collectionName;
    private Context context;
    private ConfusionParse collectionParse;

    private AlertDialog.Builder builder;
    ConfusionAdapter(Context context, ConfusionParse listener){
        this.context = context;
        this.collectionParse = listener;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_search, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(confusions.get(position));
    }

    @Override
    public int getItemCount() {
        return confusions.size();
    }

    void addItem(Confusion confusion) {
        confusions.add(confusion);
    }

    void clearItem() {
        confusions.clear();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView recycleName;
        private TextView recycleType;

        ItemViewHolder(View itemView) {
            super(itemView);

            recycleName = itemView.findViewById(R.id.text_name);
            recycleType = itemView.findViewById(R.id.text_type);

            //해당하는 collection open
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //get item's type
                    collectionName = recycleType.getText().toString();

                    builder = new AlertDialog.Builder(context);
                    builder.setMessage(recycleType.getText().toString() + " open")
                            .setCancelable(false)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    collectionParse.parseOpen(collectionName);
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                    Log.d("adapter text", recycleName.getText() + " --- " + recycleType.getText());
                }
            });

        }

        void onBind(Confusion confusion) {
            recycleName.setText(confusion.getName());
            recycleType.setText(confusion.getType());
        }
    }

}
