//recycler 뷰 위한 어댑터

package ericson.lg.mobile.earthas.ui.opened;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
import ericson.lg.mobile.earthas.ui.confusion.Confusion;

public class OpenedAdapter extends RecyclerView.Adapter<OpenedAdapter.ItemViewHolder> {

    private ArrayList<Opened> openeds = new ArrayList<>();

    private AlertDialog.Builder builder;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_opened, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(openeds.get(position));
    }

    @Override
    public int getItemCount() {
        return openeds.size();
    }

    public void addItem(Opened opened) {
        // 외부에서 item을 추가시킬 함수입니다.
        openeds.add(opened);
    }

    public void removeAll(){
        openeds.clear();
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvType;

        ItemViewHolder(View itemView) {
            super(itemView);

            tvType = itemView.findViewById(R.id.text_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //int pos = getAdapterPosition();

                    builder = new AlertDialog.Builder(view.getContext());

                    builder.setMessage(tvType.getText().toString() + " 닫기")
                            .setCancelable(false)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }
            });
        }

        void onBind(Opened opened) {
            tvType.setText(opened.getType());
        }
    }
}
