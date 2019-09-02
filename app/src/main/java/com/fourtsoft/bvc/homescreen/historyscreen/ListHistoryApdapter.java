package com.fourtsoft.bvc.homescreen.historyscreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.model.History;
import com.fourtsoft.bvc.utils.GlideCBV;

import java.util.List;

public class ListHistoryApdapter extends RecyclerView.Adapter<ListHistoryApdapter.Holder> {

    private List<History> mListHistory;

    public ListHistoryApdapter(List<History> mListHistory) {
        this.mListHistory = mListHistory;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        History history = mListHistory.get(position);
        holder.mTvNameContact.setText(history.getNameContact());
        holder.mTvDateContact.setText(history.getDateTimeContact());
        GlideCBV.with(holder.itemView)
                .load(mListHistory.get(position).getAvatarContact())
                .centerCrop()
                .error(R.drawable.ic_account)
                .into(holder.mImgAvatarContact);
    }

    @Override
    public int getItemCount() {
        return mListHistory.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView mTvNameContact;

        private TextView mTvDateContact;

        private ImageView mImgAvatarContact;

        Holder(View itemView) {
            super(itemView);
            mTvNameContact = itemView.findViewById(R.id.tv_name);
            mTvDateContact = itemView.findViewById(R.id.tv_date_time);
            mImgAvatarContact = itemView.findViewById(R.id.img_avatar);
        }
    }
}
