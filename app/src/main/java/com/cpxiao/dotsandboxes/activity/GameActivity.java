package com.cpxiao.dotsandboxes.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.cpxiao.R;
import com.cpxiao.gamelib.activity.BaseActivity;
import com.cpxiao.dotsandboxes.mode.extra.Extra;
import com.cpxiao.dotsandboxes.view.GameView;


public class GameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();
        LinearLayout layout = (LinearLayout) findViewById(R.id.game_layout);
        GameView gameView = new GameView(GameActivity.this, bundle);
        layout.addView(gameView);

    }

    public static Bundle makeBundle(int blockCountX, int blockCountY, int playerCount) {
        Bundle bundle = new Bundle();
        bundle.putInt(Extra.Name.BLOCK_COUNT_X, blockCountX);
        bundle.putInt(Extra.Name.BLOCK_COUNT_Y, blockCountY);
        bundle.putInt(Extra.Name.PLAYER_COUNT, playerCount);
        return bundle;
    }

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GameActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return intent;
    }

}
