package ir.highroid.asnaf.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.bundle.BundlePhotographerImageGallery;

/**
 * Created by mohammad on 6/21/2016.
 */
public class AdapterPhotographerImageGalleryRecycler extends RecyclerView.Adapter<AdapterPhotographerImageGalleryRecycler.PhotographerImageGalleryViewHolder>{

    private List<BundlePhotographerImageGallery> photographerImageGalleryList;
    private Context contextMain;
    Typeface tfSans;

    public AdapterPhotographerImageGalleryRecycler(List<BundlePhotographerImageGallery> photographerImageGalleryList, Context context) {
        this.photographerImageGalleryList = photographerImageGalleryList;
        this.contextMain = context;
        tfSans = Typeface.createFromAsset(contextMain.getAssets(), "IRAN Sans.ttf");
    }

    @Override
    public int getItemCount() {
        return photographerImageGalleryList.size();
    }

    @Override
    public void onBindViewHolder(PhotographerImageGalleryViewHolder photographerImageGalleryViewHolder, final int position) {

        BundlePhotographerImageGallery photographerImageGalleryInfo = photographerImageGalleryList.get(position);
        int id = contextMain.getResources().getIdentifier(photographerImageGalleryInfo.image, "drawable", contextMain.getPackageName());
        photographerImageGalleryViewHolder.photographerImageGalleryImage.setImageResource(id);

    }



    @Override
    public PhotographerImageGalleryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_photographer_image_gallery_item, viewGroup, false);
        PhotographerImageGalleryViewHolder photographerImageGallery = new PhotographerImageGalleryViewHolder(itemView);
        return photographerImageGallery;
    }


    public class PhotographerImageGalleryViewHolder extends RecyclerView.ViewHolder {

        protected ImageView photographerImageGalleryImage;

        public PhotographerImageGalleryViewHolder(View v) {
            super(v);

            contextMain = v.getContext();
            photographerImageGalleryImage = (ImageView)  v.findViewById(R.id.imgGalleryImage);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

    }
}
