package com.paipeng.gcm_tester.gcm;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

/**
 * Created by paipeng on 21/06/15.
 */
public class GCMRegister {

    private GCMRegisterInterface gCMRegisterInterface;
    private GoogleCloudMessaging gcm;
    private String regId;

    public GCMRegister(GCMRegisterInterface gCMRegisterInterface) {
        this.gCMRegisterInterface = gCMRegisterInterface;
    }

    public void getRegId(final String projectNumber, final Context context){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String regid = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(projectNumber);


                } catch (IOException ex) {
                    regid = "Error :" + ex.getMessage();

                }
                return regid;
            }

            @Override
            protected void onPostExecute(String msg) {
                GCMRegister.this.regId = msg;
                gCMRegisterInterface.getRegisterId(msg);
            }
        }.execute(null, null, null);
    }

    public String getRegId() {
        return regId;
    }
}
