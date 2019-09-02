package com.fourtsoft.bvc.homescreen.contactscreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.model.Contact;
import com.fourtsoft.bvc.utils.GlideCBV;

import java.util.List;

public class ListContactAdapter extends RecyclerView.Adapter<ListContactAdapter.Holder> {

    private static final String TAG = ListContactAdapter.class.getSimpleName();
    private List<Contact> mListContact;
    private ContactFragmentListener mContactFragmentListener;

    public static ListContactAdapter getInstance(List<Contact> contactList,ContactFragmentListener listener) {
        return new ListContactAdapter(contactList,listener);
    }

    private ListContactAdapter(List<Contact> mListHistory,ContactFragmentListener listener) {
        this.mListContact = mListHistory;
        this.mContactFragmentListener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contact_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Contact contact = mListContact.get(position);
        String name = contact.getName();
        String mobileNumber = contact.getMobileNumber();

        holder.mTvNameContact.setText(String.format("%s\n%s", name, mobileNumber));
        GlideCBV.with(holder.itemView)
                .load(contact.getPhoto())
                .circleCrop()
                .error(R.drawable.ic_account)
                .into(holder.mImgAvatarContact);
    }

    @Override
    public int getItemCount() {
        return mListContact.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView mTvNameContact;

        private ImageButton mImgCall;

        private ImageView mImgAvatarContact;

        Holder(View itemView) {
            super(itemView);
            mTvNameContact = itemView.findViewById(R.id.tv_number_contact);
            mImgCall = itemView.findViewById(R.id.btn_call);
            mImgAvatarContact = itemView.findViewById(R.id.img_avatar_contact);

            mImgCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContactFragmentListener.callClick(mListContact.get(getAdapterPosition()));
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContactFragmentListener.detailContactClick(mListContact.get(getAdapterPosition()));
                }
            });
        }
    }
}
