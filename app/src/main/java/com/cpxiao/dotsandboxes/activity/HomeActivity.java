package com.cpxiao.dotsandboxes.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.dotsandboxes.mode.extra.GridSize;
import com.cpxiao.gamelib.activity.BaseActivity;


public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.vs_computer).setOnClickListener(this);
        findViewById(R.id.two_players).setOnClickListener(this);
        findViewById(R.id.three_players).setOnClickListener(this);
        findViewById(R.id.four_players).setOnClickListener(this);
        findViewById(R.id.how_to_play).setOnClickListener(this);
        findViewById(R.id.settings).setOnClickListener(this);
        findViewById(R.id.quit).setOnClickListener(this);

        findViewById(R.id.vs_computer).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String size = PreferencesUtils.getString(getApplicationContext(), GridSize.SIZE_KEY, GridSize.SIZE_DEFAULT);
        int sizeX = GridSize.getGridCountX(size);
        int sizeY = GridSize.getGridCountY(size);

        if (id == R.id.vs_computer) {
            Bundle bundle = GameActivity.makeBundle(sizeX, sizeY, 1);
            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
            startActivity(intent);
        } else if (id == R.id.two_players) {
            Bundle bundle = GameActivity.makeBundle(sizeX, sizeY, 2);
            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
            startActivity(intent);
        } else if (id == R.id.three_players) {
            Bundle bundle = GameActivity.makeBundle(sizeX, sizeY, 3);
            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
            startActivity(intent);
        } else if (id == R.id.four_players) {
            Bundle bundle = GameActivity.makeBundle(sizeX, sizeY, 4);
            Intent intent = GameActivity.makeIntent(HomeActivity.this, bundle);
            startActivity(intent);
        } else if (id == R.id.how_to_play) {
            Dialog dialog = new AlertDialog.Builder(HomeActivity.this)
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
        } else if (id == R.id.settings) {
            Bundle bundle = SettingsActivity.makeBundle();
            Intent intent = SettingsActivity.makeIntent(HomeActivity.this, bundle);
            startActivity(intent);
        } else if (id == R.id.quit) {
            showQuitConfirmDialog();
        }
    }

    @Override
    public void onBackPressed() {
        //        super.onBackPressed();
        showQuitConfirmDialog();
    }

    private void showQuitConfirmDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                //                .setTitle(R.string.quit_msg)
                .setMessage(R.string.quit_msg)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
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
