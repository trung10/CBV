package com.fourtsoft.bvc.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.fourtsoft.bvc.dao.Repository;
import com.fourtsoft.bvc.dao.RepositoryHelper;
import com.fourtsoft.bvc.incomming.InCommingActivity;
import com.fourtsoft.bvc.model.History;
import com.fourtsoft.bvc.model.ThemeSelected;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class PhoneCallStateBroadcast extends BroadcastReceiver {

    private static final String TAG = PhoneCallStateBroadcast.class.getSimpleName();

    private RepositoryHelper repositoryHelper;
    private History history;

    private int i = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new CustomPhoneStateListener(context), PhoneStateListener.LISTEN_CALL_STATE);

        repositoryHelper = new RepositoryHelper(context);
        history = new History();
    }

    private class CustomPhoneStateListener extends PhoneStateListener {
        private Context context;

        CustomPhoneStateListener(Context context) {
            this.context = context;
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            i++;
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    history.setAvatarContact("");
                    history.setDateTimeContact("");
                    history.setNameContact("Thong-san 6");

                    history.setTypeCall(History.TYPE_CALL_IN);
                    history.setStatus(History.STATUS_MISS);

//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            repositoryHelper.insertHistory(history).
//                        }
//                    }).start();

//                    history = new History(History.TYPE_CALL_IN, "", "", "", History.STATUS_MISS);
                    //todo check save call

                    if (i == 1) {
                        Log.e("Thread : " + i, Thread.currentThread().toString());

                        repositoryHelper.insertHistory(history)
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .subscribe();
                    }

                    Intent intent1 = new Intent(context, InCommingActivity.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);

                    // todo insertTheme database to get history of call
                    break;

                case TelephonyManager.CALL_STATE_IDLE:
                    // phone state idle -> waiting to listen a incoming: miss call, off
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // user answer a incoming
                    // TODO history will be create at here. Insert a record history when user actual listen a call
                    break;

                default:

                    break;
            }
        }
    }
}
