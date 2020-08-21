package com.liany.clientmodel.widget.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liany.clientmodel.R;
import com.liany.clientmodel.base.BaseFragment;
import com.liany.clientmodel.utils.BitmapUtils;
import com.liany.clientmodel.widget.image.util.Kit;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import com.warkiz.widget.TickMarkType;

import uk.co.senab.photoview.PhotoView;

/**
 * @创建者 ly
 * @创建时间 2020/6/10
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class RotateFragment extends BaseFragment implements View.OnClickListener {
    RelativeLayout ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleConfirm;
    PhotoView ivPhoto;
    LinearLayout llRotate;
    LinearLayout llImage;
    FrameLayout flBottom;
    LinearLayout rotateToolbarSub;

    private Bitmap firstBitmap;
    private LinearLayout mScrollTools;
    private IndicatorSeekBar mScrollBar;
    private LinearLayout mScaleRuleTools;
    private IndicatorSeekBar mScaleRuleSeekbar;
    private int progress = 0;
    private Context ctx;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rotate;
    }

    @Override
    protected void initView(View inflate) {
        ivTitleBack = inflate.findViewById(R.id.iv_title_back);
        tvTitle = inflate.findViewById(R.id.tv_title);
        ivTitleConfirm = inflate.findViewById(R.id.iv_title_confirm);
        ivPhoto = inflate.findViewById(R.id.iv_photo);
        llRotate = inflate.findViewById(R.id.ll_rotate);
        llImage = inflate.findViewById(R.id.ll_image);
        flBottom = inflate.findViewById(R.id.fl_bottom);
        rotateToolbarSub = inflate.findViewById(R.id.rotate_toolbar_sub);

        ivTitleBack.setOnClickListener(this);
        ivTitleConfirm.setOnClickListener(this);
        llRotate.setOnClickListener(this);
        llImage.setOnClickListener(this);

        ivTitleConfirm.setVisibility(View.VISIBLE);
        tvTitle.setText("调整图片");

        initRotateMenu();
        //获取图像
        IFootprintAnalyze activity = (IFootprintAnalyze) getActivity();
        String img = activity.getFilePath();

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        firstBitmap = BitmapFactory.decodeFile(img, opts);
        if (firstBitmap != null) {
            Glide.with(getActivity())
                    .load(firstBitmap)
                    .into(ivPhoto);
        }
        flBottom.setVisibility(View.GONE);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            firstBitmap = ((ImageProcessActivity) getActivity()).getBitmap();
//            if (firstBitmap != null) {
//                ivPhoto.setImageBitmap(firstBitmap);
//            }
//        }
    }

    @Override
    protected void initData() {
        ctx = getActivity();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_title_back) {
            ((ImageProcessActivity) getActivity()).hideFragment();
            ((ImageProcessActivity) getActivity()).showFragment(((ImageProcessActivity) getActivity()).mMeasureFragment);
        } else if (id == R.id.iv_title_confirm) {//保存处理过的截图图片，删除之前拍照的大图，处理为8bit灰度图
            if (firstBitmap != null) {
                firstBitmap = BitmapUtils.rotateBitmap(firstBitmap, progress);
//                    Bitmap bitmap = BitmapUtils.imageScale(firstBitmap, 512, 512);
////                bitmap = BitmapUtils.save640Bitmap(bitmap);
//                    bitmap = BitmapUtils.convertGray(bitmap);
//                    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
//                    BitmapUtils.save8BitBmp(bitmap, Constants.path_photoDir + File.separator + timeStamp + ".bmp");
//                    BitmapUtils.saveBitmapAsPng(bitmap, new File(Constants.path_photoDir + File.separator + timeStamp + ".jpg"));
//                    getActivity().getIntent().putExtra("bitmapPath", Constants.path_photoDir + File.separator + timeStamp + ".bmp");
//                    getActivity().setResult(Activity.RESULT_OK, getActivity().getIntent());
//                    getActivity().finish();

                //保存图片
                //跳转到旋转界面
                ((ImageProcessActivity) getActivity()).setBitmap(firstBitmap);
                ((ImageProcessActivity) getActivity()).hideFragment();
                ((ImageProcessActivity) getActivity()).showFragment(((ImageProcessActivity) getActivity()).mMeasureFragment);
            }
        } else if (id == R.id.ll_rotate) {
            if (firstBitmap != null) {
//                    firstBitmap = BitmapUtils.rotateBitmap(firstBitmap, 90);
//                    ivPhoto.setImageBitmap(firstBitmap);
//                    if(flBottom.isShown()) {
//                        flBottom.setVisibility(View.GONE);
//                    }else {
//                        flBottom.setVisibility(View.VISIBLE);
//                    }
                mainToolbarSwitch(mScaleRuleTools);
            }
        } else if (id == R.id.ll_image) {
            if (firstBitmap != null) {
                firstBitmap = BitmapUtils.convertBitmap(firstBitmap);
                Glide.with(getActivity())
                        .load(firstBitmap)
                        .into(ivPhoto);
//                    ivPhoto.setImageBitmap(firstBitmap);
            }
        }
    }

    private void initRotateMenu() {
        int margin = Kit.getPixelsFromDp(getActivity(), 15);
        LinearLayout.LayoutParams toolbarLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toolbarLP.setMarginStart(margin);
        toolbarLP.setMarginEnd(margin);

        mScaleRuleTools = new LinearLayout(getActivity());
        mScaleRuleTools.setOrientation(LinearLayout.HORIZONTAL);
        mScaleRuleTools.setLayoutParams(toolbarLP);

        int width = Kit.getPixelsFromDp(getActivity(), 160);
        LinearLayout.LayoutParams btnLp = new LinearLayout.LayoutParams(width, Kit.getPixelsFromDp(getActivity(), 36));
        btnLp.weight = 2;
        LinearLayout.LayoutParams seekLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Kit.getPixelsFromDp(getActivity(), 36));
        seekLp.gravity = Gravity.CENTER_VERTICAL;
        seekLp.weight = 1;

        TypedValue typedValue = new TypedValue();
        ctx.getTheme().resolveAttribute(R.attr.selectableItemBackgroundBorderless, typedValue, true);
        int[] attribute = new int[]{android.R.attr.selectableItemBackgroundBorderless};
        TypedArray ta = ctx.getTheme().obtainStyledAttributes(typedValue.resourceId, attribute);

        ImageButton ok = new ImageButton(ctx);
        ok.setClickable(true);
        ok.setBackground(ta.getDrawable(0));
        ok.setImageDrawable(ctx.getDrawable(R.mipmap.ic_ok));
        ok.setLayoutParams(btnLp);
        ok.setOnClickListener(v -> {
            rotateToolbarSub.removeAllViews();
//            Bitmap bitmap = BitmapUtils.rotateBitmap(firstBitmap, progress);
//            BitmapUtils.saveBitmapAsJpg(bitmap, new File("/sdcard/test2.jpg"));
        });

        ImageButton cancel = new ImageButton(ctx);
        cancel.setClickable(true);
        cancel.setBackground(ta.getDrawable(0));
        cancel.setImageDrawable(ctx.getDrawable(R.mipmap.ic_cancel));
        cancel.setLayoutParams(btnLp);
        cancel.setOnClickListener(v -> {
            rotateToolbarSub.removeAllViews();
        });

        mScaleRuleSeekbar = IndicatorSeekBar
                .with(ctx)
                .max(360)
                .min(0)
//                .tickCount(360)
//                .tickTextsArray(new String[]{"1cm", "2cm", "3cm", "4cm", "5cm"})
                .tickTextsColor(getResources().getColor(R.color.color_white))
                .indicatorColor(getResources().getColor(R.color.color_blue))
                .tickMarksColor(getResources().getColor(R.color.color_blue))
                .thumbColor(getResources().getColor(R.color.color_blue))
                .trackBackgroundColor(getResources().getColor(R.color.color_white))
                .trackProgressColor(getResources().getColor(R.color.color_blue))
                .showTickMarksType(TickMarkType.OVAL)
                .tickMarksSize(5)
                .showThumbText(true)
                .showTickTexts(true)
                .build();
        mScaleRuleSeekbar.setIndicatorTextFormat("${PROGRESS} °"); // or "${TICK_TEXT} cm"
        mScaleRuleSeekbar.setLayoutParams(seekLp);

        Display defaultDisplay = ((ImageProcessActivity) ctx).getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int x = point.x;
        int y = point.y;

        mScaleRuleSeekbar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                progress = seekParams.progress;
                ivPhoto.setPivotX(x / 2);
                ivPhoto.setPivotY(y / 2);//支点在图片中心
                ivPhoto.setRotation(seekParams.progress);
                Log.e("onSeeking progress ", seekParams.progress + "");
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

        mScaleRuleTools.addView(cancel);
        mScaleRuleTools.addView(mScaleRuleSeekbar);
        mScaleRuleTools.addView(ok);
        ta.recycle();
    }

    /**
     * 主工具栏内容切换
     */
    private void mainToolbarSwitch(View view) {
        if (rotateToolbarSub.getChildAt(0) == view) {
            return;
        }
        rotateToolbarSub.removeAllViews();
        rotateToolbarSub.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        rotateToolbarSub.addView(view);
    }

}
