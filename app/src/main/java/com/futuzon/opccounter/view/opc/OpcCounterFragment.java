package com.futuzon.opccounter.view.opc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futuzon.opccounter.R;
import com.futuzon.opccounter.controller.config.App;

import biz.kasual.materialnumberpicker.MaterialNumberPicker;


public class OpcCounterFragment extends Fragment {

    private String className = OpcCounterFragment.class.getSimpleName();
    private Fragment self = this;

    public OpcCounterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        setClickListenerById(R.id.card_view_body_weight, getOnClickListenerBodyWeight());
        setClickListenerById(R.id.card_view_opc_share, getOnClickListenerOpcShare());
        setClickListenerById(R.id.card_view_opc_per_body_weight, getOnClickListenerOpcAmountPerKg());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_opc_counter, container, false);
    }

    /**
     * Set 'on click listener' to the specified card view using the ID
     *
     * @param cardViewId      the selected card view's ID
     * @param onClickListener the listener to be executed
     */
    private void setClickListenerById(int cardViewId, View.OnClickListener onClickListener) {
        CardView cardView = getView().findViewById(cardViewId);
        if (cardView != null && onClickListener != null) {
            cardView.setOnClickListener(onClickListener);
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
                final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(App.getAppContext())
                        .minValue(1)
                        .maxValue(10)
                        .defaultValue(1)
                        .backgroundColor(Color.WHITE)
                        .separatorColor(Color.TRANSPARENT)
                        .textColor(Color.BLACK)
                        .textSize(20)
                        .enableFocusability(false)
                        .wrapSelectorWheel(true)
                        .build();

                new AlertDialog.Builder(self.getActivity())
                        .setTitle("Title")
                        .setView(numberPicker)
                        .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d(className, "You picked : " + numberPicker.getValue());;
                            }
                        })
                        .show();
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
            }
        };
    }
}
