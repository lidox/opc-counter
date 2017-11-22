package com.futuzon.opccounter.view.opc;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.futuzon.opccounter.R;
import com.futuzon.opccounter.controller.calculation.OpcIntakeCalculator;
import com.futuzon.opccounter.controller.config.App;
import com.futuzon.opccounter.controller.opc.GlobalOpc;
import com.futuzon.opccounter.view.dialogs.NumberPickers;


public class OpcCounterFragment extends Fragment {

    private String className = OpcCounterFragment.class.getSimpleName();

    public OpcCounterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        // add click listeners to the buttons available
        setClickListenerById(R.id.card_view_body_weight, getOnClickListenerBodyWeight());
        setClickListenerById(R.id.card_view_opc_share, getOnClickListenerOpcShare());
        setClickListenerById(R.id.card_view_opc_per_body_weight, getOnClickListenerOpcAmountPerKg());

        // set UI element's values
        Context ctx = App.getAppContext();
        setUiValues(R.id.body_weight_txt, GlobalOpc.getOpcBodyWeight(ctx).intValue(), "kg");
        setUiValues(R.id.opc_share_txt, GlobalOpc.getOpcShareWithinGrapeSeedExtract(ctx).intValue(), "%");
        setUiValues(R.id.opc_per_body_weight_txt, GlobalOpc.getOpcAmountPerBodyWeight(ctx).intValue(), "g");
        setUiValues(R.id.grape_seed_extract_value, new OpcIntakeCalculator().getRecommendedGrapeSeedExtractDailyRation(ctx), "mg");
        setUiValues(R.id.opc_value, new OpcIntakeCalculator().getRecommendedOpcDailyRation(ctx), "mg");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_opc_counter, container, false);
    }

    /**
     * Set text of a TextView by its Id
     *
     * @param viewId the view'S Id
     * @param value  the text's value
     * @param unit   the text's unit
     */
    private void setUiValues(int viewId, int value, String unit) {
        if (getView() != null) {
            TextView textView = getView().findViewById(viewId);
            String text = value + " " + unit;
            textView.setText(text);
        }
    }

    /**
     * Set 'on click listener' to the specified card view using the ID
     *
     * @param cardViewId      the selected card view's ID
     * @param onClickListener the listener to be executed
     */
    private void setClickListenerById(int cardViewId, View.OnClickListener onClickListener) {
        if (getView() != null) {
            CardView cardView = getView().findViewById(cardViewId);
            if (cardView != null && onClickListener != null) {
                cardView.setOnClickListener(onClickListener);
            }
        }
    }

    /**
     * Get on click listener for the body weight card view
     *
     * @return 'on click' listener for the body weight card view
     */
    private View.OnClickListener getOnClickListenerBodyWeight() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(className, "'body weight' card view selected");
                new NumberPickers(getActivity()).openNumberPicker(R.string.c_opc_body_weight, App.getStringByRId(R.string.body_weight), 2, 150);
            }
        };
    }

    /**
     * Get on click listener for the OPC share card view
     *
     * @return 'on click' listener for the OPC share card view
     */
    private View.OnClickListener getOnClickListenerOpcShare() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(className, "'OPC share' card view selected");
                new NumberPickers(getActivity()).openNumberPicker(R.string.c_opc_share_within_grape_seed_extract, App.getStringByRId(R.string.opc_share), 1, 100);
            }
        };

    }

    /**
     * Get on click listener for the OPC amount per kg card view
     *
     * @return 'on click' listener for the OPC amount per kg card view
     */
    private View.OnClickListener getOnClickListenerOpcAmountPerKg() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(className, "'OPC amount per kg' card view selected");
                new NumberPickers(getActivity()).openNumberPicker(R.string.c_opc_amount_per_body_weight, App.getStringByRId(R.string.opc_per_body_weight), 1, 10);
            }
        };
    }

}
