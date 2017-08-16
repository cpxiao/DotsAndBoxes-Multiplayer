package com.cpxiao.dotsandboxes.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.dotsandboxes.mode.extra.Extra;
import com.cpxiao.dotsandboxes.mode.extra.GridSize;
import com.cpxiao.gamelib.activity.BaseActivity;

/**
 * @author cpxiao on 2017/02/08.
 */
public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    private TextView mGridSize;
    private TextView mSound;
    private TextView mMusic;
    private TextView mBorders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initWidget();

    }

    private void initWidget() {
        findViewById(R.id.btn_grid_size).setOnClickListener(this);
        findViewById(R.id.btn_color_scheme).setOnClickListener(this);
        findViewById(R.id.btn_color_transparency).setOnClickListener(this);
        findViewById(R.id.btn_sound).setOnClickListener(this);
        findViewById(R.id.btn_music).setOnClickListener(this);
        findViewById(R.id.btn_borders).setOnClickListener(this);

        findViewById(R.id.btn_color_scheme).setVisibility(View.GONE);
        findViewById(R.id.btn_color_transparency).setVisibility(View.GONE);
        findViewById(R.id.btn_borders).setVisibility(View.GONE);

        mGridSize = (TextView) findViewById(R.id.tv_grid_size);
        String gridSize = PreferencesUtils.getString(getApplicationContext(), GridSize.SIZE_KEY, GridSize.SIZE_DEFAULT);
        mGridSize.setText(gridSize);

        mSound = (TextView) findViewById(R.id.tv_sound);
        mMusic = (TextView) findViewById(R.id.tv_music);
        mBorders = (TextView) findViewById(R.id.tv_borders);

        boolean isSoundOn = PreferencesUtils.getBoolean(getApplicationContext(), Extra.Key.SETTING_SOUND, Extra.Key.SETTING_SOUND_DEFAULT);
        if (isSoundOn) {
            mSound.setText(R.string.settings_on);
        } else {
            mSound.setText(R.string.settings_off);
        }


        boolean isMusicOn = PreferencesUtils.getBoolean(getApplicationContext(), Extra.Key.SETTING_MUSIC, Extra.Key.SETTING_MUSIC_DEFAULT);
        if (isMusicOn) {
            mMusic.setText(R.string.settings_on);
        } else {
            mMusic.setText(R.string.settings_off);
        }

        boolean isBordersOn = PreferencesUtils.getBoolean(getApplicationContext(), Extra.Key.SETTING_HAS_BORDERS, Extra.Key.SETTING_HAS_BORDERS_DEFAULT);
        if (isBordersOn) {
            mBorders.setText(R.string.settings_on);
        } else {
            mBorders.setText(R.string.settings_off);
        }


    }

    @Override
    public void onClick(View v) {
        Context context = getApplicationContext();
        int id = v.getId();
        if (id == R.id.btn_grid_size) {
            String gridSize = PreferencesUtils.getString(context, GridSize.SIZE_KEY, GridSize.SIZE_DEFAULT);
            String nextGridSize = GridSize.getNextGridSize(gridSize);
            PreferencesUtils.putString(context, GridSize.SIZE_KEY, nextGridSize);
            mGridSize.setText(nextGridSize);
        } else if (id == R.id.btn_color_scheme) {

        } else if (id == R.id.btn_color_transparency) {

        } else if (id == R.id.btn_sound) {
            boolean isSoundOn = PreferencesUtils.getBoolean(context, Extra.Key.SETTING_SOUND, Extra.Key.SETTING_SOUND_DEFAULT);
            if (isSoundOn) {
                PreferencesUtils.putBoolean(context, Extra.Key.SETTING_SOUND, false);
                mSound.setText(R.string.settings_off);
            } else {
                PreferencesUtils.putBoolean(context, Extra.Key.SETTING_SOUND, true);
                mSound.setText(R.string.settings_on);
            }
        } else if (id == R.id.btn_music) {
            boolean isMusicOn = PreferencesUtils.getBoolean(context, Extra.Key.SETTING_MUSIC, Extra.Key.SETTING_MUSIC_DEFAULT);
            if (isMusicOn) {
                PreferencesUtils.putBoolean(context, Extra.Key.SETTING_MUSIC, false);
                mMusic.setText(R.string.settings_off);
            } else {
                PreferencesUtils.putBoolean(context, Extra.Key.SETTING_MUSIC, true);
                mMusic.setText(R.string.settings_on);
            }
        } else if (id == R.id.btn_borders) {
            boolean isBordersOn = PreferencesUtils.getBoolean(context, Extra.Key.SETTING_HAS_BORDERS, Extra.Key.SETTING_HAS_BORDERS_DEFAULT);
            if (isBordersOn) {
                PreferencesUtils.putBoolean(context, Extra.Key.SETTING_HAS_BORDERS, false);
                mBorders.setText(R.string.settings_off);
            } else {
                PreferencesUtils.putBoolean(context, Extra.Key.SETTING_HAS_BORDERS, true);
                mBorders.setText(R.string.settings_on);
            }
        }
    }

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SettingsActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }


    public static Bundle makeBundle() {
        return null;
    }


}
