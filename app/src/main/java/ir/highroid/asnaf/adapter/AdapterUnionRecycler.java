package ir.highroid.asnaf.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.activity.ActivityAtelier;
import ir.highroid.asnaf.activity.ActivityHome;
import ir.highroid.asnaf.bundle.BundleUnion;

/**
 * Created by mohammad on 6/29/2016.
 */
public class AdapterUnionRecycler extends RecyclerView.Adapter<AdapterUnionRecycler.UnionViewHolder>  {

    private List<BundleUnion> unionList;
    private Context contextMain;
    Typeface tfSans;
    boolean isHome;

    public AdapterUnionRecycler(List<BundleUnion> unionList, Context context) {
        this.unionList = unionList;
        this.contextMain = context;
        tfSans = Typeface.createFromAsset(contextMain.getAssets(), "IRAN Sans.ttf");
    }

    @Override
    public int getItemCount() {
        return unionList.size();
    }

    @Override
    public void onBindViewHolder(UnionViewHolder unionViewHolder, final int position) {

        BundleUnion unionInfo = unionList.get(position);


        unionViewHolder.unionTitle.setText(unionInfo.title);
        unionViewHolder.unionTitle.setTypeface(tfSans);
        int id= contextMain.getResources().getIdentifier(unionInfo.image, "drawable", contextMain.getPackageName());
        unionViewHolder.unionImage.setImageResource(id);
        int color = Color.parseColor("#AE6118"); //The color u want
        unionViewHolder.color = unionInfo.color;
        unionViewHolder.unionImage.setColorFilter(color);
    }


    @Override
    public UnionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_union_item, viewGroup, false);
        UnionViewHolder union = new UnionViewHolder(itemView);
        return union;
    }

    public class UnionViewHolder extends RecyclerView.ViewHolder {

        protected TextView unionTitle;
        protected ImageView unionImage;
        protected String color;

        public UnionViewHolder(View v) {
            super(v);

            contextMain = v.getContext();
            unionTitle =  (TextView) v.findViewById(R.id.txtTitle);
            unionImage = (ImageView)  v.findViewById(R.id.imgUnionIcon);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(contextMain, ActivityHome.class);
                    intent.putExtra("isHome", false);
                    intent.putExtra("color",color);
                    contextMain.startActivity(intent);

                }
            });

        }

    }
}
