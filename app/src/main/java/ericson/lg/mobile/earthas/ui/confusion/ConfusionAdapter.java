//recycler 뷰 위한 어댑터

package ericson.lg.mobile.earthas.ui.confusion;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        // 외부에서 item을 추가시킬 함수입니다.
        confusions.add(confusion);
    }

    void clearItem(){
        confusions.clear();
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvType;

        ItemViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.text_name);
            tvType = itemView.findViewById(R.id.text_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //int pos = getAdapterPosition();
                    Toast.makeText(view.getContext(), tvName.getText()+" is "+tvType.getText(), Toast.LENGTH_SHORT).show();
                    Log.d("Ddddddd",tvName.getText()+" is "+tvType.getText());

                }
            });
        }

        void onBind(Confusion confusion) {
            tvName.setText(confusion.getName());
            tvType.setText(confusion.getType());
        }
    }
}
