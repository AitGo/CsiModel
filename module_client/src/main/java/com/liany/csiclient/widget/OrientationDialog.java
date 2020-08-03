package com.liany.csiclient.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.liany.csiclient.R;

/**
 * @创建者 ly
 * @创建时间 2019/8/19
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class OrientationDialog extends Dialog {

    public OrientationDialog(Context context) {
        super(context);
    }

    public OrientationDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder implements View.OnClickListener {
        private Context context;
        private String positiveButtonText;
        private String negativeButtonText;
        private ConfirmListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private Button btnConfirm;
        private Button btnCancel;

        private RadioButton[] rbs;
        private RadioButton sn;
        private RadioButton ns;
        private RadioButton ew;
        private RadioButton we;
        private RadioButton eswn;
        private RadioButton wnes;
        private RadioButton wsen;
        private RadioButton enws;

        private EditText etDes;

        private String orientationValue = "";

        public Builder(Context context) {
            this.context = context;
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

        public OrientationDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final OrientationDialog dialog = new OrientationDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.item_pop_orientation, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            btnConfirm = layout.findViewById(R.id.btn_confirm);
//            btnConfirm.setOnClickListener(this);
            if (positiveButtonClickListener != null) {
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                String message = "";
                                if(orientationValue.equals("")) {
                                    message = etDes.getText().toString();
                                }else {
//                                    message = orientationValue + "/" + etDes.getText().toString();
                                    message =  etDes.getText().toString() + "/"  + "(" +orientationValue + ")";
                                }
                                positiveButtonClickListener.onClick(message);
                            }
                        });
            }

            sn = layout.findViewById(R.id.rb_sn);
            ns = layout.findViewById(R.id.rb_ns);
            ew = layout.findViewById(R.id.rb_ew);
            we = layout.findViewById(R.id.rb_we);
            eswn = layout.findViewById(R.id.rb_eswn);
            wnes = layout.findViewById(R.id.rb_wnes);
            wsen = layout.findViewById(R.id.rb_wsen);
            enws = layout.findViewById(R.id.rb_enws);

            sn.setOnClickListener(this);
            ns.setOnClickListener(this);
            ew.setOnClickListener(this);
            we.setOnClickListener(this);
            eswn.setOnClickListener(this);
            wnes.setOnClickListener(this);
            wsen.setOnClickListener(this);
            enws.setOnClickListener(this);

            etDes = layout.findViewById(R.id.et_des);

            rbs = new RadioButton[]{sn,ns,ew,we,eswn,wnes,wsen,enws};

            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(true);
            dialog.setContentView(layout);
            return dialog;
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.rb_sn) {
                changeClick(sn);
                orientationValue = sn.getText().toString();
            } else if (id == R.id.rb_ns) {
                changeClick(ns);
                orientationValue = ns.getText().toString();
            } else if (id == R.id.rb_ew) {
                changeClick(ew);
                orientationValue = ew.getText().toString();
            } else if (id == R.id.rb_we) {
                changeClick(we);
                orientationValue = we.getText().toString();
            } else if (id == R.id.rb_eswn) {
                changeClick(eswn);
                orientationValue = eswn.getText().toString();
            } else if (id == R.id.rb_wnes) {
                changeClick(wnes);
                orientationValue = wnes.getText().toString();
            } else if (id == R.id.rb_wsen) {
                changeClick(wsen);
                orientationValue = wsen.getText().toString();
            } else if (id == R.id.rb_enws) {
                changeClick(enws);
                orientationValue = enws.getText().toString();
            }
        }

        private void changeClick(RadioButton rb) {
            for(int i = 0; i < rbs.length; i++) {
                if(rb.getId() == rbs[i].getId()) {
                    rbs[i].setChecked(true);
                }else {
                    rbs[i].setChecked(false);
                }
            }
        }
    }

    public interface ConfirmListener {
        void onClick(String input);
    }
}
