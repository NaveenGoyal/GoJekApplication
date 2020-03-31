package com.demo.gojekapplication.view.adapter;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.demo.gojekapplication.data.model.Repository;
import com.demo.gojekapplication.viewmodel.TrendingListViewModel;

public class TrendingBindingAdapter {

    @BindingAdapter("userName")
    public static void setUserName(TextView textView, Repository repository) {

        textView.setText(repository.getAuthor());
    }

    @BindingAdapter("repoName")
    public static void setRepoName(TextView textView, Repository repository) {
        textView.setText(repository.getName());
    }

    @BindingAdapter("userAvatar")
    public static void setUserAvatar(ImageView imageView, Repository repository) {
        RequestOptions defaultOptions = new RequestOptions();
        defaultOptions.circleCrop();
        Glide.with(imageView.getContext())
                .load(repository.getAvatar())
                .apply(defaultOptions)
                .into(imageView);
    }

    @BindingAdapter("repoDescription")
    public static void setRepoDescription(TextView textView, Repository repository) {
        textView.setText(repository.getDescription());
        if(repository.isSelected()) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("language")
    public static void setlanguage(TextView textView, Repository repository) {
        textView.setText(repository.getLanguage());
        if(repository.isSelected()) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("stars")
    public static void setStars(TextView textView, Repository repository) {
        textView.setText(repository.getStars().toString());
        if(repository.isSelected()) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("forks")
    public static void setForks(TextView textView, Repository repository) {
        textView.setText(repository.getForks().toString());
        if(repository.isSelected()) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("container")
    public static void setContainer(ConstraintLayout layout, Repository repository) {

        layout.setOnClickListener(view -> {
            TrendingListViewModel trendingListViewModel = (TrendingListViewModel) layout.getTag();
            trendingListViewModel.itemClicked(repository);
        });
    }

    @BindingAdapter("specialTag")
    public static void setSpecialTag(ConstraintLayout view, TrendingListViewModel value) {
        view.setTag(value);
    }

    @BindingAdapter("seperator")
    public static void setSeperator(View view, Repository repository) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(repository.isSelected())
                view.setElevation(10);
            else
                view.setElevation(0);
        }
    }

}
