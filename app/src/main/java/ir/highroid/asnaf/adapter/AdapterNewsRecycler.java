package ir.highroid.asnaf.adapter;
/**
 * Created by mohammad on 5/3/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.activity.ActivityNews;
import ir.highroid.asnaf.bundle.BundleNews;

public class AdapterNewsRecycler extends RecyclerView.Adapter<AdapterNewsRecycler.ProductViewHolder> {

    private List<BundleNews> newsList;
    private Context contextMain;
    Typeface tfSans;

    public AdapterNewsRecycler(List<BundleNews> newsList, Context context) {
        this.newsList = newsList;
        this.contextMain = context;
        tfSans = Typeface.createFromAsset(contextMain.getAssets(), "IRAN Sans.ttf");
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }



    @Override
    public void onBindViewHolder(ProductViewHolder appViewHolder, final int position) {


        BundleNews newsInfo = newsList.get(position);
        appViewHolder.newsTitle.setText(newsInfo.title);
        appViewHolder.newsId = newsInfo.id;
        appViewHolder.newsTitle.setTypeface(tfSans);
        appViewHolder.txtDate.setTypeface(tfSans);
        appViewHolder.txtTime.setTypeface(tfSans);
        appViewHolder.newsId = newsInfo.id;
        appViewHolder.newsTitle.setText(newsInfo.title);
        appViewHolder.image = newsInfo.image;
        appViewHolder.newsImage.setImageResource(getImageId(contextMain,newsInfo.image));
        appViewHolder.txtDate.setText(newsInfo.date);
        appViewHolder.txtTime.setText(newsInfo.time);

//        Picasso
//                .with(contextMain)
//                .load(newsInfo.picture)
//                .placeholder(R.mipmap.ic_launcher)
//                .into(appViewHolder.newsImage);

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_news_item, viewGroup, false);
        ProductViewHolder news = new ProductViewHolder(itemView);
        return news;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        protected int newsId;
        protected String image;
        protected TextView newsTitle;
        protected ImageView newsImage;
        protected TextView txtDate;
        protected TextView txtTime;

        public ProductViewHolder(View v) {
            super(v);

            contextMain = v.getContext();
            newsTitle =  (TextView) v.findViewById(R.id.txtTitle);
            newsImage = (ImageView) v.findViewById(R.id.imgNews);
            txtTime  = (TextView) v.findViewById(R.id.txtTime);
            txtDate  = (TextView) v.findViewById(R.id.txtDate);

            v.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(contextMain, ActivityNews.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("newsId",newsId);
                    bundle.putString("image",image);
                    bundle.putString("title",newsTitle.getText().toString());
                    bundle.putString("date",txtDate.getText().toString());
                    bundle.putString("time",txtTime.getText().toString());
                    intent.putExtra("newsInfo",bundle);
                    contextMain.startActivity(intent);
                }
            });


        }

    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}