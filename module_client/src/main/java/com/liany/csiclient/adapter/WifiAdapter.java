package com.liany.csiclient.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liany.csiclient.R;
import com.liany.csiclient.diagnose.SceneWifiInfo;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @创建者 ly
 * @创建时间 2019/3/25
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class WifiAdapter extends BaseQuickAdapter<SceneWifiInfo, BaseViewHolder> {
    public WifiAdapter(int layoutResId, @Nullable List<SceneWifiInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SceneWifiInfo item) {
        helper.setText(R.id.tv_ssid,item.getSSID());
        helper.setText(R.id.tv_bssid,item.getBSSID());
    }
}
