package com.fourtsoft.bvc.homescreen.themescreen;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.dialogs.ThemeSelectionDialog;
import com.fourtsoft.bvc.model.ThemeSelected;
import com.fourtsoft.bvc.model.VideoItem;
import com.fourtsoft.bvc.utils.GlideCBV;
import com.fourtsoft.bvc.utils.ViewUtils;

import java.util.List;

public class ListThemeAdapter extends RecyclerView.Adapter<ListThemeAdapter.Holder> {

    private List<VideoItem> mThemeList;
    private ThemeFragmentListener mThemeFragmentListener;

    public static ListThemeAdapter getInstance(List<VideoItem> list, ThemeFragmentListener themeFragmentListener) {
        return new ListThemeAdapter(list, themeFragmentListener);
    }

    private ListThemeAdapter(List<VideoItem> mThemeList, ThemeFragmentListener listener) {
        this.mThemeList = mThemeList;
        this.mThemeFragmentListener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_theme_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        VideoItem videoItem = mThemeList.get(position);
        String pathTheme = videoItem.getImagePath();
        GlideCBV.with(holder.itemView)
                .load(pathTheme)
                .optionalFitCenter()
                .into(holder.mCallbackBackground);
    }

    @Override
    public int getItemCount() {
        return mThemeList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private ImageView mCallbackBackground;

        Holder(View itemView) {
            super(itemView);
            mCallbackBackground = itemView.findViewById(R.id.img_theme);

            mCallbackBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mThemeFragmentListener.clickThemeLoad(mThemeList.get(getAdapterPosition()));
                }
            });
        }
    }
}