package com.liany.csiclient.widget.image;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liany.csiclient.R;
import com.liany.csiclient.base.Constants;
import com.liany.csiclient.utils.ToastUtils;
import com.liany.csiclient.widget.image.model.PlottingRaw;
import com.liany.csiclient.widget.image.model.PlottingStruct;
import com.liany.csiclient.widget.image.util.BitmapUtils;
import com.liany.csiclient.widget.image.util.Kit;
import com.liany.csiclient.widget.image.widget.ComplexImageView;
import com.liany.csiclient.widget.image.widget.MagnifierView;
import com.liany.csiclient.widget.image.widget.OvonicSeekBar;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.TickMarkType;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * 在浏览模式下，用户仍可以对图形进行编辑和绘制
 * 但不进行保存
 */
public class MeasureFragment extends Fragment {

    private View mSelf;

    //<editor-fold desc="UI">
    private ImageButton mBackBtn;
    private ImageButton mEyeBtn;
    private ImageButton mToolbarBtn;


    private ViewGroup mToolbar;
    private ViewGroup mMainToolbar;
    private ViewGroup mSubToolbar;

    private ComplexImageView mComplexView;

    private RadioGroup mMainMenu;
    private RadioGroup mAdjustMenu;
    private LinearLayout mAdjustTools;
    private LinearLayout mHistogramToolbar;
    private LinearLayout mEffectMenu;
    private LinearLayout mOperateMenu;
    private RadioGroup mPlottingMenu;
    private LinearLayout mScaleRuleTools;
    private LinearLayout mScrollTools;

    private OvonicSeekBar mAdjustSeekbar;
    private IndicatorSeekBar mScaleRuleSeekbar;
    private IndicatorSeekBar mHistogramSeekbar;

    private RadioButton mBrightneesBtn;
    private RadioButton mContrastBtn;
    private RadioButton mHistogramBtn;

    //用于放大镜效果
    private PopupWindow mMagnifierWindow;
    private MagnifierView mMagnifier;
    private int mMagnifierMargin;
    int magniferWinSize;
    //</editor-fold>

    private OvonicSeekBar.OnSeekBarChangeListener mBrightnessChangeLinstener;
    private OvonicSeekBar.OnSeekBarChangeListener mConstrastChangeLinstener;
    private MeasureViewCallback mMeasureViewCallback;

    private final static int MENU_RELOAD = 00;      //重置按钮ID
    private final static int MENU_ADJUST = 10;      //图像调节按钮ID
    private final static int MENU_EFFECT = 20;      //图像效果按钮ID
    private final static int MENU_OPERATE = 30;     //图像操作按钮ID
    private final static int MENU_RULE = 40;        //图像测量按钮ID

    private final static int ADJUST_BRIGHTNESS = 11;    //亮度调节
    private final static int ADJUST_CONTRAST = 12;      //对比度调节
    private final static int ADJUST_HISTOGRAM = 13;      //直方调节

    private final static int EFFECT_SHARPENING = 21;    //锐化
    private final static int EFFECT_GRAY = 22;          //灰度
    private final static int EFFECT_TWO_VALUE = 23;     //二值化
    private final static int EFFECT_EDGE = 24;          //边缘检测

    private final static int OPERATE_ROTATE_CW = 31;    //顺时针旋转
    private final static int OPERATE_ROTATE_ACW = 32;   //逆时针旋转
    private final static int OPERATE_REVERSAL_H = 33;
    private final static int OPERATE_REVERSAL_V = 34;

    private final static int RULE_SCALE = 41;       //比例尺
    private final static int RULE_LINE_FREE = 42;   //自由线
    private final static int RULE_LINE_FOOT_LEN = 43;     //足长
    private final static int RULE_LINE_FRONT_WIDTH = 44;     //前掌
    private final static int RULE_LINE_MID_WIDTH = 45;     //中腰
    private final static int RULE_LINE_HEEL_WIDTH = 46;     //后跟
    private final static int RULE_HARD_EDGE_LEN = 47;     //实边长
    private final static int RULE_STRESS_LEN = 48;     //重压面长
    private final static int RULE_TRASH = 49;       //移除测量线
    private final static int RULE_Rectangle = 50;       //矩形框

    //Variables
    private int mButtonSize;
    private float brightnessValue = 0;  //亮度
    private float constrastValue = 0;   //对比度
    private float histogramValue = 0;   //直方

    private final static int PADDING_HEIGHT_SIZE = 5;
    private final static int PADDING_WIDTH_SIZE = 10;

    public interface MeasureViewCallback {
        void onCreateView();

        void onDestoryView();
    }

    public MeasureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
//        if (context instanceof IMultiFragmentSwitch) {
//            super.onAttach(context);
//        } else {
//            throw new ClassCastException("使用 MeasureFragment 的 Activity 必须实现接口 IFootprintAnalyze !");
//        }
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        if (mMeasureViewCallback != null) {
            mMeasureViewCallback.onDestoryView();
        }

        super.onDestroyView();
    }

    @Override
    public void onPause() {
//        this.saveDrawingState();
        super.onPause();
    }

    @Override
    public void onResume() {
        if (mMeasureViewCallback != null) {
            mMeasureViewCallback.onCreateView();
        }

        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mSelf == null) {
            // Inflate the layout for this fragment
            mSelf = inflater.inflate(R.layout.fragment_measure, container, false);
            init(mSelf);
            initListeners();
            initToolbars();
            initComplexView();
        } else {
            ViewGroup parent = (ViewGroup) mSelf.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            return mSelf;
        }

        return mSelf;
    }

    /**
     * 基本初始化内容，需第一个调用
     */
    private void init(View view) {
        mComplexView = view.findViewById(R.id.view_complex_img);
        mComplexView.setPlottingListener(new PlottingShapeListenerImpl());

        /*后退按钮*/
        mBackBtn = view.findViewById(R.id.btn_back);
        /*测量线隐藏按钮*/
        mEyeBtn = view.findViewById(R.id.btn_eye);
        /*工具栏隐藏/开启按钮*/
        mToolbarBtn = view.findViewById(R.id.btn_toolbar);

        /* 底部工具栏 */
        mToolbar = view.findViewById(R.id.measure_toolbar);
        mMainToolbar = view.findViewById(R.id.measure_toolbar_main);
        mSubToolbar = view.findViewById(R.id.measure_toolbar_sub);

        /*尺寸值*/
        mButtonSize = Kit.getPixelsFromDp(getActivity(), 36);

        /*初始化放大镜的弹出窗口*/
        mMagnifierMargin = Kit.getPixelsFromDp(getContext(), 15);
        magniferWinSize = Kit.getPixelsFromDp(getActivity(), 120);


        RelativeLayout layout = new RelativeLayout(getContext());
        layout.setPadding(8, 8, 8, 8);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mMagnifier = new MagnifierView(getContext());
        mMagnifier.setLayoutParams(lp);
        layout.addView(mMagnifier);
        mMagnifierWindow = new PopupWindow(layout, magniferWinSize, magniferWinSize, false);
        mMagnifierWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.magnifier_border));
    }

    Bitmap bitmap;
    /**
     * 初始化预览图形
     */
    private void initComplexView() {
        //获取图像
        IFootprintAnalyze activity = (IFootprintAnalyze) getActivity();
        String img = activity.getFilePath();

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        bitmap = BitmapFactory.decodeFile(img, opts);
        Glide.with(getActivity())
                .load(bitmap)
                .into(mComplexView);
    }

    /**
     * 初始化顶部按钮事件
     */
    private void initListeners() {

        /*后退返回足迹信息编辑页面*/
        mBackBtn.setOnClickListener(v -> {
            //saveDrawingState();
            getActivity().finish();
        });

        /*测量线隐藏/开启*/
        mEyeBtn.setOnClickListener(v -> {
            // 测量线的显示/隐藏
            switchMeasureLineVisible();
        });

        /*工具栏显示/隐藏*/
        mToolbarBtn.setOnClickListener(v -> {
            switchToolbarVisible();
        });

    }

    /**
     * 工具栏初始化
     */
    private void initToolbars() {

        /*移除工具栏中的所有控件*/
        mMainToolbar.removeAllViews();
        mSubToolbar.removeAllViews();

        /*隐藏子工具栏*/
        mSubToolbar.setVisibility(View.GONE);

        Context ctx = getContext();

        /*构造主按钮组*/
//        makeMainMenu(ctx);
        /*构造操作子菜单按钮组*/
//        makeOperateMenu(ctx);
        /*非预览模式下，可构造测量子菜单按钮组*/
//        IFootprintAnalyze activity = (IFootprintAnalyze) getActivity();
//        if (!activity.isMode(IFootprintAnalyze.MODE_VIEW)) {
//            makePlottingMenu(ctx);
//        }
        makePlottingMenu(ctx);

        /*默认在主工具栏加载主按钮*/
        mainToolbarSwitch(mPlottingMenu);
    }

    /**
     * 主操作菜单
     */
    private void makeMainMenu(Context ctx) {

        if (mMainMenu == null) {
            mMainMenu = new RadioGroup(ctx);
            mMainMenu.setClickable(true);
            mMainMenu.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams mainLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mMainMenu.setLayoutParams(mainLp);
        }

        int margin = Kit.getPixelsFromDp(getActivity(), 8);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(mButtonSize, mButtonSize);
        lp.setMarginStart(margin);
        lp.setMarginEnd(margin);

        RadioButton operateBtn = new RadioButton(ctx);
        operateBtn.setId(MENU_OPERATE);
        operateBtn.setBackground(ctx.getDrawable(R.drawable.radio_btn_operate));
        operateBtn.setButtonDrawable(null);

        RadioButton ruletBtn = new RadioButton(ctx);
        ruletBtn.setId(MENU_RULE);
        ruletBtn.setBackground(ctx.getDrawable(R.drawable.radio_btn_rule));
        ruletBtn.setButtonDrawable(null);

        mMainMenu.addView(operateBtn, lp);

        //非预览模式下，可以绘制测量线
        IFootprintAnalyze activity = (IFootprintAnalyze) getActivity();
        if (!activity.isMode(IFootprintAnalyze.MODE_VIEW)) {
            mMainMenu.addView(ruletBtn, lp);
        }

        mMainMenu.setOnCheckedChangeListener((group, id) -> {
            switch (id) {
                case MENU_ADJUST:
                    subToolbarSwitch(mAdjustMenu);
                    break;
                case MENU_EFFECT:
                    subToolbarSwitch(mEffectMenu);
                    break;
                case MENU_OPERATE:
                    subToolbarSwitch(mOperateMenu);
                    break;
                case MENU_RULE:
                    subToolbarSwitch(mPlottingMenu);
                    break;
            }
        });
    }

    /**
     * 图像操作菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void makeOperateMenu(Context ctx) {
        if (mOperateMenu == null) {
            mOperateMenu = new LinearLayout(ctx);
            mOperateMenu.setClickable(true);
            mOperateMenu.setOrientation(LinearLayout.HORIZONTAL);
            mOperateMenu.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mOperateMenu.setPadding(120,0,0,0);
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mButtonSize);

        //ImageButton rotateCW = new ImageButton(ctx);
        TextView rotateCW = new TextView(ctx);
        rotateCW.setGravity(Gravity.CENTER);
        rotateCW.setPadding(Kit.getPixelsFromDp(getActivity(), PADDING_WIDTH_SIZE), Kit.getPixelsFromDp(getActivity(), PADDING_HEIGHT_SIZE), Kit.getPixelsFromDp(getActivity(), PADDING_WIDTH_SIZE), Kit.getPixelsFromDp(getActivity(), PADDING_HEIGHT_SIZE));
//        rotateCW.setPadding(Kit.getPixelsFromDp(getActivity(), 10),Kit.getPixelsFromDp(getActivity(), 5),Kit.getPixelsFromDp(getActivity(), 10),Kit.getPixelsFromDp(getActivity(), 5));
        rotateCW.setId(OPERATE_ROTATE_CW);
        rotateCW.setBackground(ctx.getDrawable(R.drawable.btn_sub));
        rotateCW.setText(R.string.btn_text_rotate);
        rotateCW.setTextAppearance(R.style.MeasureTextButton);
        //rotateCW.setImageDrawable(ctx.getDrawable(R.drawable.ic_rotate));
        rotateCW.setOnClickListener(v -> {
            mComplexView.rotateCW();
        });

        //ImageButton reversalH = new ImageButton(ctx);
        TextView reversalH = new TextView(ctx);
        reversalH.setGravity(Gravity.CENTER);
        reversalH.setPadding(Kit.getPixelsFromDp(getActivity(), PADDING_WIDTH_SIZE), Kit.getPixelsFromDp(getActivity(), PADDING_HEIGHT_SIZE), Kit.getPixelsFromDp(getActivity(), PADDING_WIDTH_SIZE), Kit.getPixelsFromDp(getActivity(), PADDING_HEIGHT_SIZE));
        reversalH.setId(OPERATE_REVERSAL_H);
        reversalH.setBackground(ctx.getDrawable(R.drawable.btn_sub));
        reversalH.setText(R.string.btn_text_mirror_h);
        reversalH.setTextAppearance(R.style.MeasureTextButton);
        //reversalH.setImageDrawable(ctx.getDrawable(R.drawable.ic_reversal_h));
        reversalH.setOnClickListener(v -> {
            mComplexView.reversalHorizontal();
        });

        //ImageButton reversalV = new ImageButton(ctx);
        TextView reversalV = new TextView(ctx);
        reversalV.setGravity(Gravity.CENTER);
        reversalV.setPadding(Kit.getPixelsFromDp(getActivity(), PADDING_WIDTH_SIZE), Kit.getPixelsFromDp(getActivity(), PADDING_HEIGHT_SIZE), Kit.getPixelsFromDp(getActivity(), PADDING_WIDTH_SIZE), Kit.getPixelsFromDp(getActivity(), PADDING_HEIGHT_SIZE));
        reversalV.setId(OPERATE_REVERSAL_V);
        reversalV.setBackground(ctx.getDrawable(R.drawable.btn_sub));
        reversalV.setText(R.string.btn_text_mirror_v);
        reversalV.setTextAppearance(R.style.MeasureTextButton);
        //reversalV.setImageDrawable(ctx.getDrawable(R.drawable.ic_reversal_v));
        reversalV.setOnClickListener(v -> {
            mComplexView.reverslVertical();
        });

        mOperateMenu.addView(rotateCW, layoutParams);
        mOperateMenu.addView(reversalH, layoutParams);
//        mOperateMenu.addView(reversalV, layoutParams);
    }

    /**
     * 图像测量菜单
     */
    private void makePlottingMenu(Context ctx) {

        /*构造比例尺子菜单按钮组*/
        makeScaleRuleToolbar(ctx);

        if (mPlottingMenu == null) {
            mPlottingMenu = new RadioGroup(ctx);
            mPlottingMenu.setClickable(true);
            mPlottingMenu.setOrientation(LinearLayout.HORIZONTAL);
            mPlottingMenu.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mButtonSize, mButtonSize);
        layoutParams.setMargins(120,0,0,0);

        RadioButton scale = new RadioButton(ctx);
        scale.setId(RULE_SCALE);
        scale.setBackground(ctx.getDrawable(R.drawable.radio_btn_sub));
        scale.setButtonDrawable(ctx.getDrawable(R.drawable.ic_plotting_scale_rule));

        RadioButton free = new RadioButton(ctx);
//        free.setId(RULE_LINE_FREE);
        free.setId(RULE_Rectangle);
        free.setBackground(ctx.getDrawable(R.drawable.radio_btn_sub));
        free.setButtonDrawable(ctx.getDrawable(R.drawable.ic_plotting_free_line));

        ImageButton reloadBtn = new ImageButton(ctx);
        reloadBtn.setId(MENU_RELOAD);
        reloadBtn.setBackground(ctx.getDrawable(R.drawable.btn_sub));
        reloadBtn.setImageDrawable(ctx.getDrawable(R.mipmap.ic_reload));
        reloadBtn.setOnClickListener(v -> {
            saveDrawingState();
            mComplexView.resetCfg();
            initComplexView();
        });

        mPlottingMenu.addView(scale, layoutParams);
        mPlottingMenu.addView(free, layoutParams);
        mPlottingMenu.addView(reloadBtn, layoutParams);

        //2018/7/16 监听按钮变化
        mPlottingMenu.setOnCheckedChangeListener((group, id) -> {

            switch (id) {
                case RULE_SCALE:
                    if (((RadioButton) group.findViewById(id)).isChecked()) {
                        mComplexView.drawPlottingScale();
                    }
                    break;
                case RULE_Rectangle:
                    mComplexView.drawRectangle();
                    break;
            }
        });

        mScaleRuleTools.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                //Log.d(TAG, "onViewAttachedToWindow: 加载控件");
                scale.setEnabled(false);
                free.setEnabled(false);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                //Log.d(TAG, "onViewDetachedFromWindow: 移除控件");
                scale.setEnabled(true);
                free.setEnabled(true);
            }
        });
    }

    private void makeScaleRuleToolbar(Context ctx) {
        int margin = Kit.getPixelsFromDp(getActivity(), 15);
        LinearLayout.LayoutParams toolbarLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toolbarLP.setMarginStart(margin);
        toolbarLP.setMarginEnd(margin);

        mScaleRuleTools = new LinearLayout(getActivity());
        mScaleRuleTools.setOrientation(LinearLayout.HORIZONTAL);
        mScaleRuleTools.setLayoutParams(toolbarLP);

        int width = Kit.getPixelsFromDp(getActivity(), 160);
        LinearLayout.LayoutParams btnLp = new LinearLayout.LayoutParams(width, mButtonSize);
        btnLp.weight = 2;
        LinearLayout.LayoutParams seekLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mButtonSize);
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
            //2018/8/7 确认设置的比例尺
            mComplexView.setPlottingScaleUnitLength(mScaleRuleSeekbar.getProgress());
            mainToolbarSwitch(mPlottingMenu);
        });

        ImageButton cancel = new ImageButton(ctx);
        cancel.setClickable(true);
        cancel.setBackground(ta.getDrawable(0));
        cancel.setImageDrawable(ctx.getDrawable(R.mipmap.ic_cancel));
        cancel.setLayoutParams(btnLp);
        cancel.setOnClickListener(v -> {
            //2018/8/7  取消设置的比例尺
            mComplexView.setPlottingScaleUnitLength(mComplexView.getPlottingScaleUnit());   // important !! 需要通过此方法触发--重置视图操作模式
            mainToolbarSwitch(mPlottingMenu);
        });

        mScaleRuleSeekbar = IndicatorSeekBar
                .with(ctx)
                .max(5)
                .min(1)
                .tickCount(5)
                .tickTextsArray(new String[]{"1cm", "2cm", "3cm", "4cm", "5cm"})
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
        mScaleRuleSeekbar.setIndicatorTextFormat("${PROGRESS} cm"); // or "${TICK_TEXT} cm"
        mScaleRuleSeekbar.setLayoutParams(seekLp);

        mScaleRuleTools.addView(cancel);
        mScaleRuleTools.addView(mScaleRuleSeekbar);
        mScaleRuleTools.addView(ok);

        ta.recycle();
    }

    /**
     * 主工具栏内容切换
     */
    private void mainToolbarSwitch(View view) {
        if (mMainToolbar.getChildAt(0) == view) {
            return;
        }

        mMainToolbar.removeAllViews();
        if (view == mMainMenu) {
            mMainToolbar.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            mMainToolbar.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        mMainToolbar.addView(view);
    }

    /**
     * 自工具栏内容切换
     */
    private void subToolbarSwitch(ViewGroup view) {
        mSubToolbar.removeAllViews();
        mSubToolbar.addView(view);
        if (mSubToolbar.getVisibility() == View.GONE) {
            mSubToolbar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 工具栏可见性
     */
    private void switchToolbarVisible() {
        if (mToolbar.getVisibility() == View.VISIBLE) {
            mToolbar.setVisibility(View.GONE);
        } else if (mToolbar.getVisibility() == View.GONE) {
            mToolbar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 测量线的显示/隐藏
     */
    private boolean lineVisible = true;

    private void switchMeasureLineVisible() {
        /*眼睛按钮的样式*/
        /*控制测量线的显示*/
        if (lineVisible) {
            mEyeBtn.setImageDrawable(getActivity().getDrawable(R.mipmap.ic_eye_disable));
            lineVisible = false;
            mComplexView.hideMeasureLine();
        } else {
            mEyeBtn.setImageDrawable(getActivity().getDrawable(R.mipmap.ic_eye));
            lineVisible = true;
            mComplexView.showMeasureLine();
        }
    }

    /**
     * 截取矩形框图像
     */
    private void saveDrawingState() {
        //截取矩形框图像
//        Bitmap bitmap = mComplexView.getBitmap();
        List<PlottingRaw> plottingDatas = mComplexView.getPlottingStruct().getPlottingDatas();
        float x2 = 0f,y2 = 0f;
        int x1 = 0;
        int y1 = 0;
        for(PlottingRaw raw : plottingDatas) {
            if(raw.getType() == 0) {
                if(raw.getX1() == 0 && raw.getY1() == 0) {
                    x2 = raw.getX2();
                    y2 = raw.getY2();
                }else {
                    x2 = raw.getX1();
                    y2 = raw.getY1();
                }
                if(x2 < 0) {
                    x2 = x2 * -1;
                }
                if(y2 < 0) {
                    y2 = y2 * -1;
                }
            }else if(raw.getType() == 17) {
                x1 = Math.round(raw.getPx());
                y1 = Math.round(raw.getPy());
            }
        }
        float length = 0f;
        if(x2 == 0.0f) {
            length = y2;
        }else {
            length = x2;
        }
        int scale = mComplexView.getPlottingScaleUnit();
        double value = 0f;
        if(scale != 0f) {
            value = length/scale * 2.6;
        }
        BigDecimal decimal = new BigDecimal(value);
        int value1 = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
        if(value1 <= 0) {
            ToastUtils.showShort("宽度不能为0，请重新标注");
            return;
        }
        if((x1 - (value1/2)) < 0 || ((x1 - (value1/2)) + value1)> bitmap.getWidth() ) {
            ToastUtils.showShort("请截取图像后重试");
            return;
        }
        if((y1 - (value1/2)) < 0 || ((y1 - (value1/2)) + value1) > bitmap.getHeight() ) {
            ToastUtils.showShort("请截取图像后重试");
            return;
        }
        bitmap = BitmapUtils.imageCrop2(bitmap, x1 - (value1/2), y1 - (value1/2), value1);
                    bitmap = BitmapUtils.imageScale(bitmap, 512, 512);
//                bitmap = BitmapUtils.save640Bitmap(bitmap);
                    bitmap = BitmapUtils.convertGray(bitmap);
                    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
                    BitmapUtils.save8BitBmp(bitmap, Constants.path_photoDir + File.separator + timeStamp + ".bmp");
                    BitmapUtils.saveBitmapAsPng(bitmap, new File(Constants.path_photoDir + File.separator + timeStamp + ".jpg"));
                    getActivity().getIntent().putExtra("bitmapPath", Constants.path_photoDir + File.separator + timeStamp + ".bmp");
                    getActivity().setResult(Activity.RESULT_OK, getActivity().getIntent());
                    getActivity().finish();
//        //跳转到旋转界面
//        ((ImageProcessActivity)getActivity()).setBitmap(bitmap);
//        ((ImageProcessActivity)getActivity()).hideFragment();
//        ((ImageProcessActivity)getActivity()).showFragment(((ImageProcessActivity)getActivity()).mRotateFragment);
    }

    public void setMeasureViewCallback(MeasureViewCallback measureViewCallback) {
        this.mMeasureViewCallback = measureViewCallback;
    }

    private Handler mHandler = new Handler();

    class PlottingShapeListenerImpl implements ComplexImageView.PlottingShapeListener {

        @Override
        public void onAfterFitImageSize() {
            IFootprintAnalyze activity = (IFootprintAnalyze) getActivity();
//            AppBasic appBasic = activity.getAppBasic();
//            if (!Kit.isEmpty(appBasic.getDrawings())) {
//                Gson gson = new Gson();
//                PlottingStruct struct = gson.fromJson(appBasic.getDrawings(), PlottingStruct.class);
//                mComplexView.setPlottingStruct(struct);
//            }
        }

        @Override
        public void onClickPlottingHandler(Bitmap img) {
            //Log.d(TAG, "onClickPlottingHandler: 重新初始化放大区域背景");
            mMagnifier.init(img);
        }

        @Override
        public void onLongPressPlottingHandler(float x, float y, Matrix matrix, PlottingStruct struct) {
            //Log.d(TAG, "onLongPressPlottingHandler: 打开放大镜，通知放大区域");
            if (!mMagnifierWindow.isShowing()) {
                mMagnifierWindow.showAtLocation(mSelf, Gravity.TOP | Gravity.LEFT, mMagnifierMargin, mMagnifierMargin * 2);
                mMagnifier.update(x, y, matrix, struct);
            }
        }

        @Override
        public void onControlPlottingHandlerMove(float x, float y, Matrix matrix, PlottingStruct struct) {
            //Log.d(TAG, "onControlPlottingHandlerMove: 通知放大区域改变");
            if (!mMagnifierWindow.isShowing()) {
                mMagnifierWindow.showAtLocation(mSelf, Gravity.TOP | Gravity.LEFT, mMagnifierMargin, mMagnifierMargin * 2);
            }
            mMagnifier.update(x, y, matrix, struct);
        }

        @Override
        public void onLeaveHandler() {
            //延迟500ms关闭放大镜效果
            mHandler.postDelayed(() -> {
                mMagnifierWindow.dismiss();
                //mHandler.removeCallbacksAndMessages(null);
            }, 300);
        }

        @Override
        public void afterPlotting() {
            mPlottingMenu.clearCheck();
        }

        @Override
        public void afterDeletePlottingScale() {
            if (mScaleRuleTools == null) {
                mScaleRuleTools = new LinearLayout(getActivity());
            }

            if (mScaleRuleTools.getVisibility() == View.VISIBLE) {
//                mainToolbarSwitch(mMainMenu);
//                mainToolbarSwitch(mScaleRuleTools);
            }
        }

        @Override
        public void afterPlottingScale() {
            mPlottingMenu.clearCheck();
            // 进行比例尺调节操作
            mainToolbarSwitch(mScaleRuleTools);
            mScaleRuleSeekbar.setProgress(mComplexView.getPlottingScaleUnit());
        }

        @Override
        public void onDoubleClickPlottingScale() {
            if (mScaleRuleTools == null) {
                mScaleRuleTools = new LinearLayout(getActivity());
            }
            if (mScaleRuleTools.getVisibility() == View.VISIBLE) {
                mainToolbarSwitch(mScaleRuleTools);
                mScaleRuleSeekbar.setProgress(mComplexView.getPlottingScaleUnit());
            }
        }
    }
}
