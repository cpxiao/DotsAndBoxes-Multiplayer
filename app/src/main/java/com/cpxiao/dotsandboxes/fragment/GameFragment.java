package com.cpxiao.dotsandboxes.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.cpxiao.R;
import com.cpxiao.dotsandboxes.mode.extra.Extra;
import com.cpxiao.dotsandboxes.view.GameView;
import com.cpxiao.gamelib.fragment.BaseZAdsFragment;

/**
 * @author cpxiao on 2017/11/24.
 */

public class GameFragment extends BaseZAdsFragment {

    public static GameFragment newInstance(Bundle bundle) {
        GameFragment fragment = new GameFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_game;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        Context context = getHoldingActivity();
        Bundle bundle = getArguments();
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.game_layout);
        GameView gameView = new GameView(context, bundle);
        layout.addView(gameView);

    }

    public static Bundle makeBundle(int blockCountX, int blockCountY, int playerCount) {
        Bundle bundle = new Bundle();
        bundle.putInt(Extra.Name.BLOCK_COUNT_X, blockCountX);
        bundle.putInt(Extra.Name.BLOCK_COUNT_Y, blockCountY);
        bundle.putInt(Extra.Name.PLAYER_COUNT, playerCount);
        return bundle;
    }

}
