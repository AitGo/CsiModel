package com.liany.csiclient.widget.image;

import android.graphics.Bitmap;
import android.view.KeyEvent;

import com.liany.csiclient.R;
import com.liany.csiclient.base.BaseFragmentActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @创建者 ly
 * @创建时间 2020/5/8
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class ImageProcessActivity extends BaseFragmentActivity implements IFootprintAnalyze {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    public MeasureFragment mMeasureFragment;
    public RotateFragment mRotateFragment;
    private String filePath;
    private Bitmap bitmap;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    protected void initView() {
        addFragment();
        hideFragment();
        showFragment(mMeasureFragment);
    }

    @Override
    protected void initData() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        filePath = (String) getIntent().getStringExtra("image_filePath");
    }

    public void addFragment() {
        transaction = fragmentManager.beginTransaction();
        if (mMeasureFragment == null) {
            mMeasureFragment = new MeasureFragment();
            transaction.add(R.id.fragment_container, mMeasureFragment);
        }
        if(mRotateFragment == null) {
            mRotateFragment = new RotateFragment();
            transaction.add(R.id.fragment_container, mRotateFragment);
        }
        transaction.commit();
    }

    public void showFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    public void hideFragment() {
        transaction = fragmentManager.beginTransaction();
        if (mMeasureFragment != null) {
            transaction.hide(mMeasureFragment);
        }
        if (mRotateFragment != null) {
            transaction.hide(mRotateFragment);
        }
        transaction.commit();
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public boolean isMode(int mode) {
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(mRotateFragment.isVisible()) {
                hideFragment();
                showFragment(mMeasureFragment);
            }else {
                finish();
            }
        }
        return false;
    }
}
