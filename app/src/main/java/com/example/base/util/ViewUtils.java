package com.example.base.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Wagos.
 */
public class ViewUtils {

    public static void hideKeyboard(Context context, View view) {
        if (context == null) {
            return;
        }
        if (view == null) {
            view = new View(context);
        }
        InputMethodManager inputManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }

    public static void hideKeyboard(@Nullable Activity activity) {
        if (activity == null) {
            return;
        }
        hideKeyboard(activity, activity.getCurrentFocus());
    }

    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
