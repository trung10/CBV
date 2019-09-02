package com.fourtsoft.bvc.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.IntDef;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.dialogs.ErrorDialog;
import com.fourtsoft.bvc.dialogs.NotificationDialog;
import com.fourtsoft.bvc.dialogs.ThemeSelectionDialog;
import com.fourtsoft.bvc.model.ThemeSelected;
import com.fourtsoft.bvc.model.VideoItem;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ViewUtils {

    public final static int DIALOG_ERROR = 0;
    public final static int DIALOG_SUCCESS = 1;
    public final static int DIALOG_DELETE = 2;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DIALOG_ERROR, DIALOG_SUCCESS, DIALOG_DELETE})
    private @interface Style {
    }

    NotificationDialog dialog = new NotificationDialog();

    public static void showDialogError(AppCompatActivity context, int type) {
        ErrorDialog errorDialog = ErrorDialog.getInstance(type);
        errorDialog.setCancelable(false);
        errorDialog.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppDialogTheme);
        errorDialog.show(context.getSupportFragmentManager(), String.valueOf(Constant.ALTER_DIALOG_FRAGMENT_ERROR));
    }

    public static void showDialogMessage(AppCompatActivity context, String message, @Style int style, NotificationDialog.NotificationDialogListener listener) {
        NotificationDialog dialog = new NotificationDialog();
        dialog.setMessage(message);
        dialog.setStyleDialog(style);
        dialog.setCancelable(false);
        dialog.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppDialogTheme);
        dialog.setListener(listener);
        dialog.show(context.getSupportFragmentManager(), String.valueOf(Constant.ALTER_DIALOG_FRAGMENT_ERROR));
    }


    public static ProgressDialog showProgressDialog(Context context, String title, String message) {
        ProgressDialog loading = new ProgressDialog(context, R.style.AppCompatAlertDialogStyle);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);
        loading.setTitle(title);
        loading.setMessage(message);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();

        return loading;
    }

    public static void showDialogThemeSlection(AppCompatActivity context, VideoItem themeSelect, ThemeSelectionDialog.ThemeSelectListener listener) {
        ThemeSelectionDialog dialog = new ThemeSelectionDialog();
        dialog.setCancelable(false);
        dialog.setmVideoItem(themeSelect);
        dialog.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppDialogTheme);
        dialog.setmListener(listener);
        dialog.show(context.getSupportFragmentManager(), String.valueOf(Constant.ALTER_DIALOG_FRAGMENT_ERROR));
    }

    /**
     * @param viewRoot
     * @param message
     * @param duration Snackbar basic
     */
    public static void showSnackbar(View viewRoot, String message, int duration) {
        Snackbar.make(viewRoot, message, duration).show();
    }

    public static void showSnackbar(View viewRoot, String message, String action, int duration,
                                    final View.OnClickListener listener) {
        Snackbar snackbar = Snackbar
                .make(viewRoot, message, duration)
                .setAction(action, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onClick(view);
                    }
                });
        snackbar.show();
    }

    /**
     * @param viewRoot
     * @param message
     * @param messageColor
     * @param action
     * @param actionColor
     * @param duration     Snackbar.short, Snackbar.long
     * @param listener     Listen on click action
     *                     snackbar with action
     */
    public static void showSnackbar(View viewRoot, String message, int messageColor,
                                    String action, int actionColor, int duration,
                                    final View.OnClickListener listener) {
        Snackbar snackbar = Snackbar
                .make(viewRoot, message, duration)
                .setAction(action, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onClick(view);
                    }
                });

        // Changing message text color
        snackbar.setActionTextColor(actionColor);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(messageColor);
        snackbar.show();
    }
}
