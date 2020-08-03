package com.liany.csiclient.view.subView;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseFragmentActivity;
import com.liany.csiclient.utils.ClickUtils;
import com.liany.csiclient.view.subView.fragment.SceneList_InuploadFragment;
import com.liany.csiclient.view.subView.fragment.SceneList_UploadFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @创建者 ly
 * @创建时间 2020/3/5
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SceneListFragmentActivity extends BaseFragmentActivity implements View.OnClickListener {

    RelativeLayout ivTitleBack;
    TextView tvTitle;
    TextView tvTitleAll;
    ImageView ivTitleEdit;
    TextView ivTitleDone;
    ImageView ivTitleUpload;
    RelativeLayout rlTitle;
    RelativeLayout taskUnreadTab;
    RelativeLayout taskReadTab;
    FrameLayout taskFl;
    TextView taskUnreadTitle;
    TextView taskReadTitle;
    View taskUnreadLine;
    View taskReadLine;


    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private SceneList_UploadFragment mUploadFragment;
    private SceneList_InuploadFragment mInuploadFragment;
    private boolean isShowInpuload = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sub_scenelist_fragment;
    }

    @Override
    protected void initView() {
        ivTitleBack = findViewById(R.id.iv_title_back);
        tvTitle = findViewById(R.id.tv_title);
        tvTitleAll = findViewById(R.id.tv_title_all);
        ivTitleEdit = findViewById(R.id.iv_title_edit);
        ivTitleDone = findViewById(R.id.iv_title_done);
        ivTitleUpload = findViewById(R.id.iv_title_upload);
        rlTitle = findViewById(R.id.rl_title);
        taskUnreadTab = findViewById(R.id.task_unread_tab);
        taskReadTab = findViewById(R.id.task_read_tab);
        taskFl = findViewById(R.id.task_fl);
        taskUnreadTitle = findViewById(R.id.task_unread_title);
        taskReadTitle = findViewById(R.id.task_read_title);
        taskUnreadLine = findViewById(R.id.task_unread_line);
        taskReadLine = findViewById(R.id.task_read_line);

        ivTitleBack.setOnClickListener(this);
        taskUnreadTab.setOnClickListener(this);
        taskReadTab.setOnClickListener(this);
        tvTitleAll.setOnClickListener(this);
        ivTitleEdit.setOnClickListener(this);
        ivTitleDone.setOnClickListener(this);
        ivTitleUpload.setOnClickListener(this);

        tvTitle.setText("现场列表");
        addFragment();
        hideFragment();
        chooseUnReadTab();
        showFragment(mInuploadFragment);
        isShowInpuload = true;
    }

    @Override
    protected void initData() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            finish();
        } else if (id == R.id.task_unread_tab) {
            hideFragment();
            chooseUnReadTab();
            showFragment(mInuploadFragment);
            isShowInpuload = true;
            setTitleBtnVisit(true);
        } else if (id == R.id.task_read_tab) {
            hideFragment();
            chooseReadTab();
            showFragment(mUploadFragment);
            isShowInpuload = false;
            mInuploadFragment.done(false);
            setTitleBtnVisit(false);
        } else if (id == R.id.tv_title_all) {
            if (isShowInpuload) {
                mInuploadFragment.selectAll();
            } else {
//                    mUploadFragment.selectAll();
            }
        } else if (id == R.id.iv_title_edit) {
            if (isShowInpuload) {
                mInuploadFragment.edit();
            } else {
//                    mUploadFragment.edit();
            }
        } else if (id == R.id.iv_title_upload) {
            if (isShowInpuload) {
                mInuploadFragment.upload();
            } else {
//                    mUploadFragment.upload();
            }
        } else if (id == R.id.iv_title_done) {
            if (isShowInpuload) {
                mInuploadFragment.done(false);
            } else {
//                    mUploadFragment.done(false);
            }
        }
    }

    public void showFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    public void addFragment() {
        transaction = fragmentManager.beginTransaction();
        if (mInuploadFragment == null) {
            mInuploadFragment = new SceneList_InuploadFragment();
            transaction.add(R.id.task_fl, mInuploadFragment);
        }
        if (mUploadFragment == null) {
            mUploadFragment = new SceneList_UploadFragment();
            transaction.add(R.id.task_fl, mUploadFragment);
        }
        transaction.commit();
    }

    public void hideFragment() {
        transaction = fragmentManager.beginTransaction();
        if (mInuploadFragment != null) {
            transaction.hide(mInuploadFragment);
        }
        if (mUploadFragment != null) {
            transaction.hide(mUploadFragment);
        }
        transaction.commit();
    }

    public void chooseUnReadTab() {
        taskUnreadTitle.setTextColor(getResources().getColor(R.color.colorPrimary));
        taskUnreadLine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        taskReadTitle.setTextColor(getResources().getColor(R.color.black));
        taskReadLine.setBackgroundColor(getResources().getColor(R.color.gray_bg));
    }

    public void chooseReadTab() {
        taskReadTitle.setTextColor(getResources().getColor(R.color.colorPrimary));
        taskReadLine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        taskUnreadTitle.setTextColor(getResources().getColor(R.color.black));
        taskUnreadLine.setBackgroundColor(getResources().getColor(R.color.gray_bg));
    }

    public void edit() {
        ivTitleEdit.setVisibility(View.GONE);
        ivTitleUpload.setVisibility(View.GONE);
        ivTitleDone.setVisibility(View.VISIBLE);
        tvTitleAll.setVisibility(View.VISIBLE);
    }

    public void done() {
        ivTitleEdit.setVisibility(View.VISIBLE);
        ivTitleUpload.setVisibility(View.VISIBLE);
        ivTitleDone.setVisibility(View.GONE);
        tvTitleAll.setVisibility(View.GONE);
    }

    public void setTitleBtnVisit(boolean isShow) {
        ivTitleEdit.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
        ivTitleUpload.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
        ivTitleDone.setVisibility(View.GONE);
        tvTitleAll.setVisibility(View.GONE);
    }
}
