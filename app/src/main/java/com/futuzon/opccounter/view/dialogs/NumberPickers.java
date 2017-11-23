package com.futuzon.opccounter.view.dialogs;


import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.futuzon.opccounter.R;
import com.futuzon.opccounter.controller.calculation.OpcIntakeCalculator;
import com.futuzon.opccounter.controller.utils.Global;

public class NumberPickers {

    private static String className = NumberPickers.class.getSimpleName();
    private Activity activity = null;

    public NumberPickers(Activity activity) {
        this.activity = activity;
    }

    private void updateUiValue(int newValue, int globalValueId) {

        // get view id by value id
        int viewId = R.id.body_weight_txt;
        if (globalValueId == R.string.c_opc_amount_per_body_weight)
            viewId = R.id.opc_per_body_weight_txt;
        else if (globalValueId == R.string.c_opc_share_within_grape_seed_extract)
            viewId = R.id.opc_share_txt;
        else if (globalValueId == R.string.c_opc_body_weight)
            viewId = R.id.body_weight_txt;

        if (activity != null) {
            TextView textView = activity.findViewById(viewId);
            String oldValue = textView.getText().toString();
            Log.d(className, "string: " + oldValue);
            String[] valueUnit = oldValue.split(" ");
            String text = newValue + " " + valueUnit[1];
            textView.setText(text);
        }
    }

    /**
     * Sets a new value in the UI using UiId and showing count animation
     *
     * @param oldVal the old value, which is needed to start animation
     * @param newVal the new value to display in the UI
     * @param viewId the text view Id where to display the new value
     */
    private void updateUiByCountAnimation(int oldVal, int newVal, final int viewId) {
        ValueAnimator animator = ValueAnimator.ofInt(oldVal, newVal);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                TextView textView = activity.findViewById(viewId);
                String text = animation.getAnimatedValue().toString() + " mg";
                textView.setText(text);
            }
        });
        animator.start();
    }

    /**
     * Open up new number picker Dialog
     *
     * @param globalValueKey the global key for used value
     * @param dialogTitle    the dialog's title to show
     * @param minValue       the number picker's min value
     * @param maxValue       the number picker's min value
     */
    public void showNumberPickerDialog(final int globalValueKey, String dialogTitle, int minValue, int maxValue) {

        // create dialog
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.number_picker_dialog);

        // set dialogTitle
        TextView titleView = dialog.findViewById(R.id.dialog_title);
        if (titleView != null)
            titleView.setText(dialogTitle);

        // get number picker instance
        final NumberPicker numberPicker = dialog.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(maxValue);
        numberPicker.setMinValue(minValue);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setValue((int) Global.getDoubleByKey(globalValueKey, 2));


        // get submit button instance
        ImageView submitButton = dialog.findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newValue = numberPicker.getValue();
                updateDbAndUi(newValue, globalValueKey);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    /**
     * Updates new value in database and UI
     *
     * @param newValue      the new value to set
     * @param globalValueId the global value key for the new value
     */
    private void updateDbAndUi(int newValue, int globalValueId) {
        Log.d(className, "new Value selected: " + newValue);

        // get old values before making an update
        int oldOpcValue = new OpcIntakeCalculator().getRecommendedOpcDailyRation(activity);
        int oldGrapeSeedExtract = new OpcIntakeCalculator().getRecommendedGrapeSeedExtractDailyRation(activity);

        // update database value
        Global.putDouble(globalValueId, newValue);

        // update OPC value in UI using animation
        updateUiByCountAnimation(Math.round(oldOpcValue), new OpcIntakeCalculator().getRecommendedOpcDailyRation(activity), R.id.opc_value);

        // update 'Grape Seed Extract' value in UI using animation
        updateUiByCountAnimation(Math.round(oldGrapeSeedExtract), new OpcIntakeCalculator().getRecommendedGrapeSeedExtractDailyRation(activity), R.id.grape_seed_extract_value);

        // update changed value in UI
        updateUiValue(newValue, globalValueId);
    }

}
