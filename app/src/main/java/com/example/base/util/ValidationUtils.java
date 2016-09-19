package com.example.base.util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.base.BuildConfig;
import com.example.base.R;

import java.util.ArrayList;

public class ValidationUtils {

    public static final int PERMISSION_REQUEST_CODE = 13;
    private static final int SETTINGS_REQUEST_CODE = 14;

    public static boolean checkPermissions(final Activity activity, View root, String... permissionList) {
        boolean result = true;
        ArrayList<String> deniedPermissions = new ArrayList<>();
        ArrayList<String> neverAskPermissions = new ArrayList<>();
        getPermissionGroup(activity, deniedPermissions, neverAskPermissions, permissionList);
        if (!neverAskPermissions.isEmpty()) {
            displaySnackbar(root, v -> {
                Intent settingsIntent = getSettingsActivityIntent(activity);
                activity.startActivityForResult(settingsIntent, SETTINGS_REQUEST_CODE);
            });
            result = false;
        } else if (!deniedPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(activity, deniedPermissions.toArray(new String[deniedPermissions.size()]), PERMISSION_REQUEST_CODE);
            result = false;
        }
        return result;
    }

    public static boolean checkPermissions(final Fragment fragment, View root, String... permissionList) {
        boolean result = true;
        ArrayList<String> deniedPermissions = new ArrayList<>();
        ArrayList<String> neverAskPermissions = new ArrayList<>();
        final FragmentActivity activity = fragment.getActivity();
        getPermissionGroup(activity, deniedPermissions, neverAskPermissions, permissionList);
        if (!neverAskPermissions.isEmpty()) {
            displaySnackbar(root, v -> {
                Intent settingsIntent = getSettingsActivityIntent(activity);
                fragment.startActivityForResult(settingsIntent, SETTINGS_REQUEST_CODE);
            });
            result = false;
        } else if (!deniedPermissions.isEmpty()) {
            fragment.requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), PERMISSION_REQUEST_CODE);
            result = false;
        }
        return result;
    }

    private static void getPermissionGroup(Activity activity, ArrayList<String> deniedPermissions,
                                           ArrayList<String> neverAskPermissions,
                                           String[] permissionList) {
        for (String permission : permissionList) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    neverAskPermissions.add(permission);
                } else {
                    deniedPermissions.add(permission);
                }
            }
        }
    }

    private static void displaySnackbar(View root, View.OnClickListener listener) {
        if (root != null) {
            Snackbar.make(root, R.string.action_required_additional_permission, Snackbar.LENGTH_LONG)
                    .setAction(R.string.settings, listener)
                    .show();
        }
    }

    @NonNull
    private static Intent getSettingsActivityIntent(Activity activity) {
        Intent settingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        settingsIntent.setData(Uri.parse("package:" + BuildConfig.APPLICATION_ID));
        if (settingsIntent.resolveActivity(activity.getPackageManager()) == null) {
            settingsIntent = new Intent(Settings.ACTION_SETTINGS);
        }
        return settingsIntent;
    }


    public static boolean checkPermissionsGranted(@NonNull int[] grantResults) {
        boolean value = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                value = false;
                break;
            }
        }
        return value;
    }
}
