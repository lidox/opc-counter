package com.futuzon.opccounter.view.dialogs;


import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.futuzon.opccounter.LauncherActivity;
import com.futuzon.opccounter.R;
import com.futuzon.opccounter.controller.calculation.OpcIntakeCalculator;
import com.futuzon.opccounter.controller.config.App;
import com.futuzon.opccounter.controller.opc.GlobalOpc;
import com.futuzon.opccounter.controller.utils.Global;

import java.lang.reflect.Field;

public class NumberPickers {

    private static Activity ctx = null;

    public NumberPickers(Activity context) {
        this.ctx = context;
    }

    public void openNumberPicker(final int globalValueId, String dialogTitle, int minValue, int maxValue) {
        RelativeLayout linearLayout = new RelativeLayout(App.getAppContext());
        final NumberPicker aNumberPicker = new NumberPicker(App.getAppContext());

        aNumberPicker.setMaxValue(maxValue);
        aNumberPicker.setMinValue(minValue);
        aNumberPicker.setValue((int) new Global(App.getAppContext()).getDoubleByKey(globalValueId, 2));
        setNumberPickerTextColor(aNumberPicker, Color.WHITE);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
        int wrapContent = RelativeLayout.LayoutParams.WRAP_CONTENT;
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(wrapContent, wrapContent);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        linearLayout.setLayoutParams(params);
        linearLayout.addView(aNumberPicker, numPicerParams);

        // dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder.setView(linearLayout);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(App.getStringByRId(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                Log.d(NumberPickers.class.getSimpleName(), "Number picker new value selected: " + aNumberPicker.getValue());

                                /*
                                int oldRecommendedGrapeSeedExtractDailyRation = new OpcIntakeCalculator().getRecommendedGrapeSeedExtractDailyRation(ctx);
                                int oldRecommendedOpcDailyRation = new OpcIntakeCalculator().getRecommendedOpcDailyRation(ctx);
                                double oldValue = Global.getDoubleByKey(globalValueId, 2);

                                new Global(App.getAppContext()).putDouble(globalValueId, aNumberPicker.getValue());
                                //startCountAnimation((int) Math.round(oldValue), aNumberPicker.getValue(),  R.id.grape_seed_extract_value);
                                startCountAnimation((int) Math.round(oldRecommendedGrapeSeedExtractDailyRation), new OpcIntakeCalculator().getRecommendedGrapeSeedExtractDailyRation(ctx),  R.id.grape_seed_extract_value);
                                startCountAnimation((int) Math.round(oldRecommendedOpcDailyRation), new OpcIntakeCalculator().getRecommendedOpcDailyRation(ctx),  R.id.opc_value);
                                */

                                new Global(App.getAppContext()).putDouble(globalValueId, aNumberPicker.getValue());
                                ctx.finish();
                                ctx.startActivity(new Intent(ctx, LauncherActivity.class));

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

    private void startCountAnimation(int oldVal, int newVal, final int viewId) {
        ValueAnimator animator = ValueAnimator.ofInt(oldVal, newVal);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                TextView textView = ctx.findViewById(viewId);
                textView.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }

}
