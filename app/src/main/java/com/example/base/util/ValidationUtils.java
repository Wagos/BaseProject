package com.example.base.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.base.BuildConfig;
import com.example.base.R;

import java.util.ArrayList;

public class ValidationUtils {

    public static final int PERMISSION_REQUEST_CODE = 13;
    private static final int SETTINGS_REQUEST_CODE = 14;

    public static void requestPermissions(Activity parent, String... permissionList) {
        ActivityCompat.requestPermissions(parent, permissionList, PERMISSION_REQUEST_CODE);
    }

    public static void requestPermissions(Fragment parent, String... permissionList) {
        parent.requestPermissions(permissionList, PERMISSION_REQUEST_CODE);
    }

    public static void displaySettingsSnackbar(View root, @StringRes int text, Object parent) {
        Snackbar.make(root, text, Snackbar.LENGTH_LONG)
                .setAction(R.string.settings, v -> startSettingsActivity(parent))
                .show();
    }

    private static void startSettingsActivity(Object parent) {
        if (parent instanceof Activity) {
            Activity activity = (Activity) parent;
            Intent intent = getSettingsActivityIntent(activity);
            activity.startActivityForResult(intent, SETTINGS_REQUEST_CODE);
        } else if (parent instanceof Fragment) {
            Fragment fragment = (Fragment) parent;
            Intent intent = getSettingsActivityIntent(fragment.getContext());
            fragment.startActivityForResult(intent, SETTINGS_REQUEST_CODE);
        }
    }

    @NonNull
    private static Intent getSettingsActivityIntent(Context context) {
        Intent settingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        settingsIntent.setData(Uri.parse("package:" + BuildConfig.APPLICATION_ID));
        if (settingsIntent.resolveActivity(context.getPackageManager()) == null) {
            settingsIntent = new Intent(Settings.ACTION_SETTINGS);
        }
        return settingsIntent;
    }


    public static boolean checkPermissionsGranted(@NonNull int[] grantResults) {
        boolean value = true;
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                value = false;
                break;
            }
        }
        return value;
    }

    public static ArrayList<String> getNeverAskPermissions(Activity activity, String[] permissions, @NonNull int[] grantResults) {
        ArrayList<String> value = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            int result = grantResults[i];
            if (result == PackageManager.PERMISSION_DENIED && !ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[i])) {
                value.add(permissions[i]);
            }
        }
        return value;
    }
}
