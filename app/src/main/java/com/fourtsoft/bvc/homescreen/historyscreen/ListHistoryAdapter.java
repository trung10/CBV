package com.fourtsoft.bvc.homescreen.historyscreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.dialogs.NotificationDialog;
import com.fourtsoft.bvc.model.History;
import com.fourtsoft.bvc.utils.GlideCBV;
import com.fourtsoft.bvc.utils.ViewUtils;

import java.util.List;

public class ListHistoryAdapter extends RecyclerView.Adapter<ListHistoryAdapter.Holder> {

    private List<History> mListHistory;
    private HistoryListener mListener;
    private Context mContext;

    public static ListHistoryAdapter getInstance(Context context, List<History> list, HistoryListener listener) {
        return new ListHistoryAdapter(context, list, listener);
    }

    public ListHistoryAdapter(Context mContext, List<History> mListHistory, HistoryListener listener) {
        this.mListHistory = mListHistory;
        this.mListener = listener;
        this.mContext = mContext;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        History history = mListHistory.get(position);
        holder.mTvNameContact.setText(history.getNameContact());
        holder.mTvDateContact.setText(history.getDateTimeContact());
        GlideCBV.with(holder.itemView)
                .load(mListHistory.get(position).getAvatarContact())
                .centerCrop()
                .error(R.drawable.ic_account)
                .into(holder.mImgAvatarContact);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickHistory();
            }
        });

        holder.setClickListener(new SetClickListener() {
            @Override
            public void onLongClick() {
                ViewUtils.showDialogMessage((AppCompatActivity) mContext, "Do you delete this?", ViewUtils.DIALOG_DELETE, new NotificationDialog.NotificationDialogListener() {
                    @Override
                    public void onClickOk() {
                        //not using
                    }

                    @Override
                    public void onClickDelete() {
                        mListener.onLongClickHistory(position);
                    }
                });
            }

            @Override
            public void onClick() {
                mListener.onClickHistory();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListHistory.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView mTvNameContact;

        private TextView mTvDateContact;

        private ImageView mImgAvatarContact;

        private SetClickListener mClickListener;

        Holder(View itemView) {
            super(itemView);
            mTvNameContact = itemView.findViewById(R.id.tv_name);
            mTvDateContact = itemView.findViewById(R.id.tv_date_time);
            mImgAvatarContact = itemView.findViewById(R.id.img_avatar);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mClickListener.onLongClick();
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onClick();
                }
            });
        }

        public void setClickListener(SetClickListener mListener) {
            this.mClickListener = mListener;
        }


    }

    interface SetClickListener {
        void onLongClick();

        void onClick();
    }
}
