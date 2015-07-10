package ir.highroid.asnaf.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.activity.ActivityGallery;
import ir.highroid.asnaf.bundle.BundlePhotographerTab;
import ir.highroid.asnaf.view.CustomAlertDialog;

/**
 * Created by mohammad on 6/20/2016.
 */
public class AdapterPhotographerGalleryTabRecycler extends  RecyclerView.Adapter<AdapterPhotographerGalleryTabRecycler.PhotographerTabViewHolder>{

    private List<BundlePhotographerTab> photographerTabList;
    private Context contextMain;
    Typeface tfSans;

    public AdapterPhotographerGalleryTabRecycler(List<BundlePhotographerTab> photographerTabList, Context context) {
        this.photographerTabList = photographerTabList;
        this.contextMain = context;
        tfSans = Typeface.createFromAsset(contextMain.getAssets(), "IRAN Sans.ttf");
    }

    @Override
    public int getItemCount() {
        return photographerTabList.size();
    }

    @Override
    public void onBindViewHolder(PhotographerTabViewHolder photographerTabViewHolder, final int position) {

        BundlePhotographerTab photographerTabInfo = photographerTabList.get(position);
        photographerTabViewHolder.photographerTabTitle.setText(photographerTabInfo.title);
        photographerTabViewHolder.photographerTabTitle.setTypeface(tfSans);

    }

    @Override
    public PhotographerTabViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_photographer_gallery_tab_item, viewGroup, false);
        int colorId = ContextCompat.getColor(contextMain,R.color.colorCustomize);
        itemView.setBackgroundColor(colorId);
        PhotographerTabViewHolder photographerTab = new PhotographerTabViewHolder(itemView);

        return photographerTab;
    }


    public class PhotographerTabViewHolder extends RecyclerView.ViewHolder {

        protected TextView photographerTabTitle;

        public PhotographerTabViewHolder(View v) {
            super(v);

            contextMain = v.getContext();
            photographerTabTitle =  (TextView) v.findViewById(R.id.txtTabName);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(contextMain, ActivityGallery.class);
                    if(photographerTabTitle.getText().equals("گالری عروسی")){
                        CustomAlertDialog customAlertDialog = new CustomAlertDialog(contextMain);

                        AlertDialog dialog = new AlertDialog.Builder(contextMain)
                                .setMessage("برای مشاهده ی آلبوم این قسمت حضوری مراجعه فرمایید")
                                .setPositiveButton("خب", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .setCustomTitle(customAlertDialog.getTitleText("پیغام"))
                                .show();

                        customAlertDialog.setDialogStyle(dialog);
                    }
                    else{
                        contextMain.startActivity(intent);
                    }
                }
            });

        }

    }
}
