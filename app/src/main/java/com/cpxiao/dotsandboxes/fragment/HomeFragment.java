package com.cpxiao.dotsandboxes.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.androidutils.library.utils.RateAppUtils;
import com.cpxiao.androidutils.library.utils.ShareAppUtils;
import com.cpxiao.dotsandboxes.mode.extra.GridSize;
import com.cpxiao.gamelib.fragment.BaseFragment;

/**
 * @author cpxiao on 2017/09/01.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.vs_computer).setOnClickListener(this);
        view.findViewById(R.id.two_players).setOnClickListener(this);
        view.findViewById(R.id.three_players).setOnClickListener(this);
        view.findViewById(R.id.four_players).setOnClickListener(this);
        view.findViewById(R.id.rate_app).setOnClickListener(this);
        view.findViewById(R.id.share).setOnClickListener(this);
        view.findViewById(R.id.how_to_play).setOnClickListener(this);
        view.findViewById(R.id.settings).setOnClickListener(this);
        view.findViewById(R.id.vs_computer).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Context context = getHoldingActivity();
        String size = PreferencesUtils.getString(context, GridSize.SIZE_KEY, GridSize.SIZE_DEFAULT);
        int sizeX = GridSize.getGridCountX(size);
        int sizeY = GridSize.getGridCountY(size);

        if (id == R.id.vs_computer) {
            Bundle bundle = GameFragment.makeBundle(sizeX, sizeY, 1);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.two_players) {
            Bundle bundle = GameFragment.makeBundle(sizeX, sizeY, 2);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.three_players) {
            Bundle bundle = GameFragment.makeBundle(sizeX, sizeY, 3);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.rate_app) {
            RateAppUtils.rate(context);
        } else if (id == R.id.share) {
            String msg = getString(R.string.share_msg) + "\n" +
                    getString(R.string.app_name) + "\n" +
                    "https://play.google.com/store/apps/details?id=" + context.getPackageName();
            ShareAppUtils.share(context, getString(R.string.share), msg);
        } else if (id == R.id.four_players) {
            Bundle bundle = GameFragment.makeBundle(sizeX, sizeY, 4);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.how_to_play) {
            showHowToPlayDialog(context);
        } else if (id == R.id.settings) {
            addFragment(SettingsFragment.newInstance(null));
        }
    }

    private void showHowToPlayDialog(Context context) {
        Dialog dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.how_to_play)
                .setMessage(R.string.how_to_play_msg)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    private void showQuitConfirmDialog(Context context) {
        Dialog dialog = new AlertDialog.Builder(context)
                //                .setTitle(R.string.quit_msg)
                .setMessage(R.string.quit_msg)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        removeFragment();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        //            dialog.setCancelable(true);
        //            dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
