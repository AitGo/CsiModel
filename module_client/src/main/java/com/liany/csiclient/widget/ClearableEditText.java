package com.liany.csiclient.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.liany.csiclient.R;


/**
 * Created by user on 2016/9/26.
 */
public class ClearableEditText extends RelativeLayout
{
    private LayoutInflater inflater = null;
    private EditText edit_text;
    private Button btn_clear;
    private String edit_hint;

    public ClearableEditText(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initParams(context,attrs);
        initViews();
    }

    public ClearableEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initParams(context,attrs);
        initViews();

    }

    public ClearableEditText(Context context)
    {
        super(context);
        initViews();
    }

    public void setMaxLines(int lines){
        if(lines==1) {
            edit_text.setSingleLine(true);
        }else {
            edit_text.setSingleLine(false);
            edit_text.setMaxLines(lines);
        }
    }

    public void setHint(String hint){
        edit_text.setHint(hint);
    }

    void initViews()
    {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_clearable_edit_text, this, true);
        edit_text = (EditText) findViewById(R.id.clearable_edit);
        edit_text.setSingleLine(true);//默认单行
        edit_text.setHint(edit_hint);
        btn_clear = (Button) findViewById(R.id.clearable_button_clear);
        btn_clear.setVisibility(RelativeLayout.INVISIBLE);
        clearText();
        showHideClearButton();
    }

    private void initParams(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClearableEditText);
        if (typedArray != null) {
            edit_hint = typedArray.getString(R.styleable.ClearableEditText_hint);
            typedArray.recycle();
        }
    }

    void clearText()
    {
        btn_clear.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                edit_text.setText("");
            }
        });
    }

    void showHideClearButton()
    {
        edit_text.addTextChangedListener(new TextWatcher()
        {

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (s.length() > 0)
                    btn_clear.setVisibility(RelativeLayout.VISIBLE);
                else
                    btn_clear.setVisibility(RelativeLayout.INVISIBLE);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            public void afterTextChanged(Editable s)
            {

            }
        });
    }

    public String getText() {
        Editable text = edit_text.getText();
        return text.toString();
    }

    public void setText(String text) {
        edit_text.setText(text);
    }

    public void setKeyListener(KeyListener input) {
        edit_text.setKeyListener(input);
    }

    public void addTextChangedListener(TextWatcher input){edit_text.addTextChangedListener(input);}
}
