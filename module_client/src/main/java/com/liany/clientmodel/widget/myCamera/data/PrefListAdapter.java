/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liany.clientmodel.widget.myCamera.data;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liany.clientmodel.R;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2019/12/30
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class PrefListAdapter extends RecyclerView.Adapter<PrefListAdapter.MyViewHolder>{
    private PreferenceGroup mGroup;
    private Context mContext;
    private PrefClickListener mListener;
    private int mHighlightIndex = -1;
    private int mTextColor;
    private int mHighlightColor;

    public interface PrefClickListener {
        void onClick(View view, int position, CamListPreference preference);
    }

    public PrefListAdapter(Context context, PreferenceGroup group) {
        mContext = context;
        mGroup = group;
        mTextColor = context.getResources().getColor(R.color.menu_text_color);
        mHighlightColor = context.getResources().getColor(R.color.menu_highlight_color);
    }

    public PreferenceGroup getPrefGroup() {
        return mGroup;
    }

    public void updateHighlightIndex(int index, boolean notify) {
        int preIndex = mHighlightIndex;
        mHighlightIndex = index;
        if (notify) {
            notifyItemChanged(preIndex);
            notifyItemChanged(mHighlightIndex);
        }
    }

    public void setClickListener(PrefClickListener listener) {
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.menu_item_layout, parent,
                false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        CamListPreference preference = mGroup.get(position);
        int color = position == mHighlightIndex ? mHighlightColor : mTextColor;
        holder.itemText.setTextColor(color);
        if (TextUtils.isEmpty(preference.getTitle())) {
            holder.itemText.setVisibility(View.GONE);
        } else {
            holder.itemText.setVisibility(View.VISIBLE);
            holder.itemText.setText(preference.getTitle());
        }
        if (CamListPreference.RES_NULL == preference.getIcon()) {
            holder.itemIcon.setVisibility(View.GONE);
        } else {
            holder.itemIcon.setVisibility(View.VISIBLE);
            holder.itemIcon.setImageResource(preference.getIcon());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(v, position, mGroup.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGroup.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemIcon;
        TextView itemText;
        MyViewHolder(View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.item_icon);
            itemText = itemView.findViewById(R.id.item_text);
        }
    }
}