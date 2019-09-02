package com.fourtsoft.bvc.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.utils.Constant;

public class ErrorDialog extends DialogFragment {
    private static int typeDialog;

    public static ErrorDialog getInstance(int type) {
        typeDialog = type;
        return new ErrorDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (typeDialog == Constant.ALTER_DIALOG_FRAGMENT_ERROR) {
            view = inflater.inflate(R.layout.dialog_fragment_error, container, false);
        }
        return view;
    }

}
