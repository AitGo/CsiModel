package com.liany.clientmodel.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liany.clientmodel.R;
import com.liany.clientmodel.adapter.SelectSceneAdapter;
import com.liany.clientmodel.diagnose.CrimeItem;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 ly
 * @创建时间 2019/8/19
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class SelectCrimeDialog extends Dialog {

    public SelectCrimeDialog(Context context) {
        super(context);
    }

    public SelectCrimeDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder implements View.OnClickListener {
        private Context context;
        private RelativeLayout rlBack;
        private TextView tvTitle;
        private RecyclerView rvScene;
        private Context mContext;
        private List<CrimeItem> crimeItems = new ArrayList<>();
        private SelectSceneAdapter adapter;
        private ItemListener itemListener;

        public SelectCrimeDialog.Builder itemClick(SelectCrimeDialog.ItemListener listener) {
            this.itemListener = listener;
            return this;
        }

        public Builder(Context context,List<CrimeItem> crimeItems) {
            this.crimeItems = crimeItems;
            this.context = context;
        }

        public SelectCrimeDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final SelectCrimeDialog dialog = new SelectCrimeDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.item_pop_selectcrime, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            rvScene = layout.findViewById(R.id.rv_scenelist);
            adapter = new SelectSceneAdapter(R.layout.item_adapter_selectscene,crimeItems);
            rvScene.setLayoutManager(new LinearLayoutManager(mContext));
            rvScene.setAdapter(adapter);
            if(itemListener != null) {
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        itemListener.onClick(crimeItems.get(position));
                    }
                });
            }
            rlBack = layout.findViewById(R.id.iv_title_back);
            rlBack.setOnClickListener(this);
            tvTitle = layout.findViewById(R.id.tv_title);
            tvTitle.setText("选择现场");
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(true);
            dialog.setContentView(layout);
            return dialog;
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.iv_title_back) {
                if (itemListener != null) {
                    itemListener.finish();
                }
            }
        }
    }

    public interface ItemListener {
        void onClick(CrimeItem crimeItem);
        void finish();
    }
}
