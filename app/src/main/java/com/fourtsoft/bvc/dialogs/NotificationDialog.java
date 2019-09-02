package com.fourtsoft.bvc.dialogs;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fourtsoft.bvc.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NotificationDialog extends DialogFragment {

    public final static int DIALOG_ERROR = 0;
    public final static int DIALOG_SUCCESS = 1;
    public final static int DIALOG_DELETE = 2;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DIALOG_ERROR, DIALOG_SUCCESS, DIALOG_DELETE})
    private @interface Style {
    }

    @Style
    private int styleDialog;

    private String title;
    private View mDialogView;
    private String message;
    NotificationDialogListener listener;

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mDialogView = inflater.inflate(R.layout.dialog_notification, container, false);

        Button button = mDialogView.findViewById(R.id.btnOK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickOk();
                dismiss();
            }
        });

        LinearLayout linearDelete = mDialogView.findViewById(R.id.linearDelete);

        Button buttonCancel = mDialogView.findViewById(R.id.btnCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Button buttonDelete = mDialogView.findViewById(R.id.btnDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickDelete();
                dismiss();
            }
        });

        TextView txtTitle = mDialogView.findViewById(R.id.title);
        txtTitle.setText(title);

        TextView txtMessage = mDialogView.findViewById(R.id.message);
        txtMessage.setText(message);

        FloatingActionButton fab = mDialogView.findViewById(R.id.fab);

        if (styleDialog == DIALOG_SUCCESS) {
            fab.setBackgroundResource(R.drawable.ic_check_white_24dp);
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_dialog_success)));
            txtTitle.setText(getText(R.string.text_title_success));
            button.setBackgroundResource(R.color.color_dialog_success);

        } else if (styleDialog == DIALOG_ERROR) {
            txtTitle.setText(getText(R.string.text_title_error));
            fab.setImageResource(R.drawable.ic_clear_black_24dp);
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.primary_read)));
            button.setBackgroundResource(R.color.primary_read);

        } else if (styleDialog == DIALOG_DELETE) {
            txtTitle.setText(getText(R.string.text_title_delete));
            button.setVisibility(View.GONE);
            linearDelete.setVisibility(View.VISIBLE);
            fab.setImageResource(R.drawable.ic_delete_white_24dp);
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.primary_orange)));
        }

        return mDialogView;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStyleDialog(@Style int styleDialog) {
        this.styleDialog = styleDialog;
    }

    public void setListener(NotificationDialogListener listener) {
        this.listener = listener;
    }

    public void dismiss() {
        this.dismissAllowingStateLoss();
    }

    public interface NotificationDialogListener {
        void onClickOk();

        void onClickDelete();
    }
}
