package ericson.lg.mobile.earthas.ui.confusion;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ericson.lg.mobile.earthas.R;

public class ConfusionAdapter extends RecyclerView.Adapter<ConfusionAdapter.ItemViewHolder> {
    private ArrayList<Confusion> confusions = new ArrayList<>();

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

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), recycleName.getText() + " --- " + recycleType.getText(), Toast.LENGTH_SHORT).show();
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
