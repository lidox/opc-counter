package com.futuzon.opccounter.controller.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Looper;
import android.widget.Toast;

import com.futuzon.opccounter.controller.config.App;

public class ExceptionHandler {

    //TODO: not implemented yet
    public static void showException(final String str){
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                AlertDialog.Builder builder = new AlertDialog.Builder(App.getAppContext());
                builder.setMessage("Application was stopped...")
                        .setPositiveButton("Report to developer about this problem.", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Not worked!
                                dialog.dismiss();
                                System.exit(0);
                                android.os.Process.killProcess(android.os.Process.myPid());

                            }
                        });

                AlertDialog dialog = builder.create();


                Toast.makeText(App.getAppContext(), "OOPS! Application crashed", Toast.LENGTH_SHORT).show();
                if(!dialog.isShowing())
                    dialog.show();
                Looper.loop();

            }
        }.start();
    }

}
