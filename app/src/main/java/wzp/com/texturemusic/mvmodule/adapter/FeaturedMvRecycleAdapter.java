package wzp.com.texturemusic.mvmodule.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;

import wzp.com.texturemusic.R;
import wzp.com.texturemusic.bean.ItemBean;
import wzp.com.texturemusic.core.adapter.BaseAdapterForVlayout;
import wzp.com.texturemusic.core.config.AppConstant;
import wzp.com.texturemusic.mvmodule.bean.MvContentBean;
import wzp.com.texturemusic.util.BaseUtil;
import wzp.com.texturemusic.util.FormatData;
import wzp.com.texturemusic.util.ImageUtil;

/**
 * Created by Wang on 2017/6/9.
 * 精选MVFragment中的recycleview适配器
 */

public class FeaturedMvRecycleAdapter extends BaseAdapterForVlayout<MvContentBean,FeaturedMvRecycleAdapter.MvFeatureContentViewHolder> {

    public FeaturedMvRecycleAdapter(Context c) {
        super(c);
    }

    @Override
    public FeaturedMvRecycleAdapter.MvFeatureContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_person_mv, parent, false);
        return new MvFeatureContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeaturedMvRecycleAdapter.MvFeatureContentViewHolder holder, int position) {
        holder.itemView.setTag(position);
        //数据和视图绑定
        MvContentBean bean = dataList.get(position);
        ImageUtil.loadImage(mContext,
                bean.getCoverImgUrl() + AppConstant.WY_IMG_500_300,
                holder.imgView,
                R.drawable.ic_large_album);
        holder.playCountTv.setText(FormatData.longValueToString(bean.getPlayCount()));
        holder.descTv.setText(bean.getDescription());
        holder.mvNameTv.setText(bean.getMvName());
        holder.mvArtistNameTv.setText(bean.getArtistName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper helper = new GridLayoutHelper(2);
        helper.setGap(BaseUtil.dp2px(4));
        helper.setHGap(BaseUtil.dp2px(2));
        return helper;
    }

    class MvFeatureContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView playCountTv, descTv, mvNameTv, mvArtistNameTv;
        private ImageView imgView;
        private LinearLayout itemLinear;

        public MvFeatureContentViewHolder(View itemView) {
            super(itemView);
            playCountTv = itemView.findViewById(R.id.person_mv_playcount);
            descTv = itemView.findViewById(R.id.person_mv_desc);
            mvNameTv = itemView.findViewById(R.id.person_mv_name);
            mvArtistNameTv = itemView.findViewById(R.id.person_mv_artistname);
            imgView = itemView.findViewById(R.id.person_mv_img);
            itemLinear = itemView.findViewById(R.id.person_mv_linear);
            itemLinear.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickSubject != null) {
                itemClickSubject.onNext(new ItemBean(v, (int) itemView.getTag()));
            }
        }
    }


}
