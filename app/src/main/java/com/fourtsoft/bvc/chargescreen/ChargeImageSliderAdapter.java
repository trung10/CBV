package com.fourtsoft.bvc.chargescreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.fourtsoft.bvc.model.ImageSource;
import com.fourtsoft.bvc.utils.GlideCBV;

import java.util.ArrayList;
import java.util.List;

public class ChargeImageSliderAdapter extends PagerAdapter {

    List<ImageSource> imageSources;

    public ChargeImageSliderAdapter(List<ImageSource> sources) {
        if (sources == null) {
            imageSources = new ArrayList<>();
        } else {
            imageSources = new ArrayList<>(sources);
        }
    }

    @Override
    public int getCount() {
        return imageSources.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        ImageSource image = imageSources.get(position);
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        bind(context, imageView, image);
        container.addView(imageView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    private void bind(Context context, ImageView imageView, ImageSource source) {
        RequestManager glide = GlideCBV.with(context);
        if (source.isPath()) {
            glide.load(source.getPath()).into(imageView);
        } else if (source.isFile()) {
            glide.load(source.getFile()).into(imageView);
        } else if (source.isUri()) {
            glide.load(source.getUri()).into(imageView);
        } else if (source.isLocalResourceId()) {
            glide.load(source.getResourceId()).into(imageView);
        }
    }
}
