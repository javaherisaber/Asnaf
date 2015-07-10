package ir.highroid.asnaf.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import ir.highroid.asnaf.R;
import ir.highroid.asnaf.bundle.BundleAsnafMember;

/**
 * Created by mohammad on 6/20/2016.
 */
public class AdapterAsnafMemberAdapter extends  RecyclerView.Adapter<AdapterAsnafMemberAdapter.AsnafMemberViewHolder>{

    private List<BundleAsnafMember> asnafMemberList;
    private Context contextMain;
    Typeface tfSans;

    public AdapterAsnafMemberAdapter(List<BundleAsnafMember> asnafMemberList, Context context) {
        this.asnafMemberList = asnafMemberList;
        this.contextMain = context;
        tfSans = Typeface.createFromAsset(contextMain.getAssets(), "IRAN Sans.ttf");
    }

    @Override
    public int getItemCount() {
        return asnafMemberList.size();
    }


    @Override
    public void onBindViewHolder(AsnafMemberViewHolder asnafMemberViewHolder, final int position) {

        BundleAsnafMember asnafMemberInfo = asnafMemberList.get(position);
//        asnafMemberViewHolder.asnafMemberTitle.setText(asnafMemberInfo.title);
//        asnafMemberViewHolder.asnafMemberTitle.setTypeface(tfSans);
        asnafMemberViewHolder.asnafMemberTitle = asnafMemberInfo.title;

        int id = contextMain.getResources().getIdentifier(asnafMemberInfo.image, "drawable", contextMain.getPackageName());
        asnafMemberViewHolder.asnafMemberImage.setImageResource(id);
    }



    @Override
    public AsnafMemberViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_asnaf_member_item, viewGroup, false);
        AsnafMemberViewHolder asnafMember = new AsnafMemberViewHolder(itemView);
        return asnafMember;
    }


    public class AsnafMemberViewHolder extends RecyclerView.ViewHolder {

//        protected TextView asnafMemberTitle;
        protected ImageView asnafMemberImage;
        protected String asnafMemberTitle;

        public AsnafMemberViewHolder(View v) {
            super(v);

            contextMain = v.getContext();
//            asnafMemberTitle =  (TextView) v.findViewById(R.id.txtTitle);
            asnafMemberImage = (ImageView)  v.findViewById(R.id.imgMemberProfile);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(contextMain," آقای " + asnafMemberTitle,Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
