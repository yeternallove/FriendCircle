package com.sorcererxw.demo.friendcircle.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sorcererxw.demo.friendcircle.R;
import com.sorcererxw.demo.friendcircle.models.GeneralBean;
import com.sorcererxw.demo.friendcircle.models.HeadBean;
import com.sorcererxw.demo.friendcircle.util.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/10/20
 */

public class FriendCircleAdapter
        extends RecyclerView.Adapter<FriendCircleAdapter.FriendCircleViewHolder> {

    private static final int TYPE_HEAD = 0x0;
    private static final int TYPE_GENERAL = 0x1;

    private HeadBean mHeadBean;
    private List<GeneralBean> mGeneralBeanList;

    private Context mContext;

    public FriendCircleAdapter(Context context,
                               HeadBean headBean,
                               List<GeneralBean> generalBeanList) {
        mHeadBean = headBean;
        mGeneralBeanList = generalBeanList;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEAD : TYPE_GENERAL;
    }

    @Override
    public FriendCircleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            return new HeadViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_head, parent, false));
        }
        return new GeneralViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_general, parent, false));
    }

    @Override
    public void onBindViewHolder(FriendCircleViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEAD) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            Glide.with(mContext)
                    .load(mHeadBean.getHeadAvatar())
                    .placeholder(R.color.colorImagePlaceHolder)
                    .into(headViewHolder.avatar);
            Glide.with(mContext)
                    .load(mHeadBean.getHeadBackground())
                    .centerCrop()
                    .placeholder(R.color.colorImagePlaceHolder)
                    .into(headViewHolder.background);
            headViewHolder.name.setText(mHeadBean.getHeadName());
        } else {
            GeneralViewHolder generalViewHolder = (GeneralViewHolder) holder;
            GeneralBean generalBean = mGeneralBeanList.get(position - 1);
            Glide.with(mContext)
                    .load(Uri.parse(generalBean.getAvatar()))
                    .placeholder(R.color.colorImagePlaceHolder)
                    .into(generalViewHolder.avatar);
            generalViewHolder.content.setText(generalBean.getContent());
            generalViewHolder.name.setText(generalBean.getName());
            if (generalBean.getImageList() != null && generalBean.getImageList().size() > 0) {
                generalViewHolder.gridLayout.setVisibility(View.VISIBLE);

                List<String> imageList = generalBean.getImageList();

                int size = imageList.size();
                int rowCount = (int) Math.sqrt(size);
                int columnCount = size / rowCount;

                generalViewHolder.gridLayout.setRowCount(rowCount);
                generalViewHolder.gridLayout.setColumnCount(columnCount);

                int k = 0;
                for (int i = 0; i < rowCount; i++) {
                    for (int j = 0; j < columnCount; j++) {
                        if (k >= size) {
                            break;
                        }
                        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                        layoutParams.setGravity(Gravity.CENTER);
                        layoutParams.rowSpec = GridLayout.spec(i);
                        layoutParams.columnSpec = GridLayout.spec(j);

                        if (size == 1) {
                            layoutParams.width = WRAP_CONTENT;
                            layoutParams.height = WRAP_CONTENT;
                        } else {
                            layoutParams.width = (int) mContext.getResources()
                                    .getDimension(R.dimen.item_general_image_grid_image_size);
                            layoutParams.height = (int) mContext.getResources()
                                    .getDimension(R.dimen.item_general_image_grid_image_size);
                        }

                        ImageView imageView = new ImageView(mContext);
                        imageView.setLayoutParams(layoutParams);
                        generalViewHolder.gridLayout.addView(imageView);


                        Glide.with(mContext)
                                .load(imageList.get(k))
                                .placeholder(R.color.colorImagePlaceHolder)
                                .into(imageView);
                    }
                }
            } else {
                generalViewHolder.gridLayout.setVisibility(View.GONE);
            }
            generalViewHolder.date
                    .setText(DateUtil.getDateString(mContext, generalBean.getPublishDate()));

        }
    }

    @Override
    public int getItemCount() {
        return mGeneralBeanList.size() + 1;
    }

    static class FriendCircleViewHolder extends RecyclerView.ViewHolder {

         FriendCircleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class HeadViewHolder extends FriendCircleViewHolder {

        @BindView(R.id.textView_item_head_name)
        TextView name;

        @BindView(R.id.imageView_item_head_background)
        ImageView background;

        @BindView(R.id.imageView_item_head_avatar)
        ImageView avatar;

        HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class GeneralViewHolder extends FriendCircleViewHolder {

        @BindView(R.id.textView_item_general_name)
        TextView name;

        @BindView(R.id.textView_item_general_content)
        TextView content;

        @BindView(R.id.imageView_item_general_avatar)
        ImageView avatar;

        @BindView(R.id.gridLayout_item_general)
        GridLayout gridLayout;

        @BindView(R.id.textView_item_general_date)
        TextView date;

        GeneralViewHolder(View itemView) {
            super(itemView);
        }
    }
}
