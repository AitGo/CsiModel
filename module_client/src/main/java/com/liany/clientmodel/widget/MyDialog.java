package com.liany.clientmodel.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.liany.clientmodel.R;


public class MyDialog extends Dialog {


    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String msg;
        private String positiveButtonText;
        private String negativeButtonText;
        private String editButtonText;
        private String compareButtonText;
        private View contentView;
        private ConfirmListener positiveButtonClickListener;
        private ConfirmListener compareButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private OnClickListener editButtonClickListener;
        private TextView tv_title,tv_msg;
        private Button btn_confirm, btn_cancel,btn_edit,btn_compare;
        private boolean isShowPositiveButton = true;
        private boolean isShowEditButton = false;
        private boolean isShowCompareButton = false;

        public Builder(Context context) {
            this.context = context;
        }

        //        public Builder setMessage(String message) {
        //            this.message = message;
        //            return this;
        //        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setPositiveButtonShow(boolean isShowPositiveButton) {
            this.isShowPositiveButton = isShowPositiveButton;
            return this;
        }

        public Builder setEditButtonShow(boolean isShowEditButton) {
            this.isShowEditButton = isShowEditButton;
            return this;
        }

        public Builder setCompareButtonShow(boolean isShowButton) {
            this.isShowCompareButton = isShowButton;
            return this;
        }
        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         ConfirmListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         ConfirmListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }


        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setEditButton(String editButtonText, OnClickListener listener) {
            this.editButtonText = editButtonText;
            this.editButtonClickListener = listener;
            return this;
        }

        public Builder setCompareButton(String compareButtonText, ConfirmListener listener) {
            this.compareButtonText = compareButtonText;
            this.compareButtonClickListener = listener;
            return this;
        }

        @SuppressWarnings("deprecation")
        public MyDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final MyDialog dialog = new MyDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.item_text_dialog, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            // set the dialog title
            tv_msg = (TextView) layout.findViewById(R.id.dialog_msg);
            tv_title = (TextView) layout.findViewById(R.id.dialog_title);
            btn_confirm = ((Button) layout.findViewById(R.id.dialog_confirm));
            btn_cancel = (Button) layout.findViewById(R.id.dialog_cancel);
            btn_edit = (Button) layout.findViewById(R.id.dialog_edit);
            btn_compare = layout.findViewById(R.id.dialog_compare);
            tv_title.setText(title);
            tv_msg.setText(msg);
            btn_confirm.setText(positiveButtonText);
            btn_cancel.setText(negativeButtonText);
            btn_edit.setText(editButtonText);
            btn_compare.setText(compareButtonText);
            if(isShowPositiveButton) {
                btn_confirm.setVisibility(View.VISIBLE);
            }else {
                btn_confirm.setVisibility(View.INVISIBLE);
            }
            if(isShowEditButton) {
                btn_edit.setVisibility(View.VISIBLE);
            }else {
                btn_edit.setVisibility(View.GONE);
            }
            if(isShowCompareButton) {
                btn_compare.setVisibility(View.VISIBLE);
            }else {
                btn_compare.setVisibility(View.GONE);
            }

            if (positiveButtonClickListener != null) {
                btn_confirm
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                positiveButtonClickListener.onClick();
                            }
                        });
            }
            if (negativeButtonClickListener != null) {
                btn_cancel
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                negativeButtonClickListener.onClick(dialog,
                                        DialogInterface.BUTTON_NEGATIVE);
                            }
                        });
            }
            if(editButtonClickListener != null) {
                btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                    }
                });
            }
            if(compareButtonClickListener != null) {
                btn_compare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        compareButtonClickListener.onClick();
                    }
                });
            }
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.setContentView(layout);
            return dialog;
        }

    }

    public interface ConfirmListener {
        void onClick();
    }

}
