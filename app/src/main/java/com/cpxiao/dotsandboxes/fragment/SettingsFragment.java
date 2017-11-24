package com.cpxiao.dotsandboxes.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.dotsandboxes.mode.extra.Extra;
import com.cpxiao.dotsandboxes.mode.extra.GridSize;
import com.cpxiao.gamelib.fragment.BaseZAdsFragment;

/**
 * @author cpxiao on 2017/11/24.
 */

public class SettingsFragment extends BaseZAdsFragment implements View.OnClickListener {
    private TextView mGridSize;
    private TextView mSound;
    private TextView mMusic;

    public static SettingsFragment newInstance(Bundle bundle) {
        SettingsFragment fragment = new SettingsFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        Context context = getHoldingActivity();

        view.findViewById(R.id.btn_grid_size).setOnClickListener(this);
        view.findViewById(R.id.btn_sound).setOnClickListener(this);
        view.findViewById(R.id.btn_music).setOnClickListener(this);


        mGridSize = (TextView) view.findViewById(R.id.tv_grid_size);
        String gridSize = PreferencesUtils.getString(context, GridSize.SIZE_KEY, GridSize.SIZE_DEFAULT);
        mGridSize.setText(gridSize);

        mSound = (TextView) view.findViewById(R.id.tv_sound);
        mMusic = (TextView) view.findViewById(R.id.tv_music);

        boolean isSoundOn = PreferencesUtils.getBoolean(context, Extra.Key.SETTING_SOUND, Extra.Key.SETTING_SOUND_DEFAULT);
        if (isSoundOn) {
            mSound.setText(R.string.settings_on);
        } else {
            mSound.setText(R.string.settings_off);
        }


        boolean isMusicOn = PreferencesUtils.getBoolean(context, Extra.Key.SETTING_MUSIC, Extra.Key.SETTING_MUSIC_DEFAULT);
        if (isMusicOn) {
            mMusic.setText(R.string.settings_on);
        } else {
            mMusic.setText(R.string.settings_off);
        }


    }

    @Override
    public void onClick(View v) {
        Context context = getHoldingActivity();
        int id = v.getId();
        if (id == R.id.btn_grid_size) {
            String gridSize = PreferencesUtils.getString(context, GridSize.SIZE_KEY, GridSize.SIZE_DEFAULT);
            String nextGridSize = GridSize.getNextGridSize(gridSize);
            PreferencesUtils.putString(context, GridSize.SIZE_KEY, nextGridSize);
            mGridSize.setText(nextGridSize);
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
        }
    }
}
