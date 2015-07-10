package ir.highroid.asnaf.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.highroid.asnaf.activity.ActivityRule;
import ir.highroid.asnaf.R;
import ir.highroid.asnaf.bundle.BundleRule;

/**
 * Created by mohammad on 7/7/2016.
 */
public class AdapterRuleRecycler extends RecyclerView.Adapter<AdapterRuleRecycler.RuleViewHolder>  {

    private List<BundleRule> ruleList;
    private Context contextMain;
    Typeface tfSans;
    boolean isHome;

    public AdapterRuleRecycler(List<BundleRule> ruleList, Context context) {
        this.ruleList = ruleList;
        this.contextMain = context;
        tfSans = Typeface.createFromAsset(contextMain.getAssets(), "IRAN Sans.ttf");
    }

    @Override
    public int getItemCount() {
        return ruleList.size();
    }

    @Override
    public void onBindViewHolder(RuleViewHolder ruleViewHolder, final int position) {

        BundleRule ruleInfo = ruleList.get(position);


        ruleViewHolder.ruleTitle.setText(ruleInfo.title);
        ruleViewHolder.ruleTitle.setTypeface(tfSans);
        ruleViewHolder.ruleId = ruleInfo.id;
//        int id= contextMain.getResources().getIdentifier(ruleInfo.image, "drawable", contextMain.getPackageName());
//        ruleViewHolder.ruleImage.setImageResource(id);
        int color = Color.parseColor("#000000"); //The color u want
        ruleViewHolder.ruleImage.setColorFilter(color);
    }


    @Override
    public RuleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_rule_list_item, viewGroup, false);
        RuleViewHolder rule = new RuleViewHolder(itemView);
        return rule;
    }

    public class RuleViewHolder extends RecyclerView.ViewHolder {

        protected TextView ruleTitle;
        protected ImageView ruleImage;
        protected int ruleId;

        public RuleViewHolder(View v) {
            super(v);

            contextMain = v.getContext();
            ruleTitle =  (TextView) v.findViewById(R.id.txtTitle);
            ruleImage = (ImageView)  v.findViewById(R.id.imgRule);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(contextMain, ActivityRule.class);
                    intent.putExtra("ruleTitle",ruleTitle.getText().toString());
                    intent.putExtra("ruleId",ruleId);
                    contextMain.startActivity(intent);
                }
            });

        }

    }
}
