package com.futuzon.opccounter.view.dialogs;


import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.futuzon.opccounter.R;
import com.futuzon.opccounter.controller.calculation.OpcIntakeCalculator;
import com.futuzon.opccounter.controller.config.App;
import com.futuzon.opccounter.controller.utils.Global;

import java.lang.reflect.Field;

public class NumberPickers {

    private Activity activity = null;

    public NumberPickers(Activity activity) {
        this.activity = activity;
    }

    public void openNumberPicker(final int globalValueId, String dialogTitle, int minValue, int maxValue) {
        RelativeLayout linearLayout = new RelativeLayout(App.getAppContext());

        final NumberPicker numberPicker = new NumberPicker(App.getAppContext());
        numberPicker.setMaxValue(maxValue);
        numberPicker.setMinValue(minValue);
        numberPicker.setValue((int) new Global(App.getAppContext()).getDoubleByKey(globalValueId, 2));
        setNumberPickerTextColor(numberPicker, Color.WHITE);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
        int wrapContent = RelativeLayout.LayoutParams.WRAP_CONTENT;
        RelativeLayout.LayoutParams numPickerParams = new RelativeLayout.LayoutParams(wrapContent, wrapContent);
        numPickerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        linearLayout.setLayoutParams(params);
        linearLayout.addView(numberPicker, numPickerParams);

        // dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder.setView(linearLayout);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(App.getStringByRId(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                Log.d(NumberPickers.class.getSimpleName(), "Number picker new value selected: " + numberPicker.getValue());

                                // get old values before making an update
                                int oldOpcValue = new OpcIntakeCalculator().getRecommendedOpcDailyRation(activity);
                                int oldGrapeSeedExtract = new OpcIntakeCalculator().getRecommendedGrapeSeedExtractDailyRation(activity);

                                // update database value
                                new Global(App.getAppContext()).putDouble(globalValueId, numberPicker.getValue());

                                // update OPC value in UI using animation
                                updateUiByCountAnimation((int) Math.round(oldOpcValue), new OpcIntakeCalculator().getRecommendedOpcDailyRation(activity), R.id.opc_value);

                                // update 'Grape Seed Extract' value in UI using animation
                                updateUiByCountAnimation((int) Math.round(oldGrapeSeedExtract), new OpcIntakeCalculator().getRecommendedGrapeSeedExtractDailyRation(activity), R.id.grape_seed_extract_value);

                                // update changed value in UI
                                updateUiValue(numberPicker.getValue(), globalValueId);
                            }
                        })
                .setNegativeButton(App.getStringByRId(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.gradient_list);
        alertDialog.show();
    }

    private void updateUiValue(int newValue, int globalValueId) {

        // get view id by value id
        int viewId = R.id.body_weight_txt;
        if(globalValueId == R.string.c_opc_amount_per_body_weight)
            viewId = R.id.opc_per_body_weight_txt;
        else if(globalValueId == R.string.c_opc_share_within_grape_seed_extract)
            viewId = R.id.opc_share_txt;
        else if(globalValueId == R.string.c_opc_body_weight)
            viewId = R.id.body_weight_txt;

        if (activity != null) {
            TextView textView = activity.findViewById(viewId);
            String oldValue = textView.getText().toString();
            Log.d("", "string: " + oldValue);
            String[] valueUnit = oldValue.split(" ");
            String text = newValue + " " + valueUnit[1];
            textView.setText(text);
        }
    }

    @SuppressLint("LongLogTag")
    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText) child).setTextColor(color);
                    numberPicker.invalidate();
                    return true;
                } catch (NoSuchFieldException e) {
                    Log.w("setNumberPickerTextColor", e);
                } catch (IllegalAccessException e) {
                    Log.w("setNumberPickerTextColor", e);
                } catch (IllegalArgumentException e) {
                    Log.w("setNumberPickerTextColor", e);
                }
            }
        }
        return false;
    }

    private void updateUiByCountAnimation(int oldVal, int newVal, final int viewId) {
        ValueAnimator animator = ValueAnimator.ofInt(oldVal, newVal);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                TextView textView = activity.findViewById(viewId);
                textView.setText(animation.getAnimatedValue().toString() + " mg");
            }
        });
        animator.start();
    }

}
