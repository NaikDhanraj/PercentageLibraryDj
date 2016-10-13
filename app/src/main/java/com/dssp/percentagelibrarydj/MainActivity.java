package com.dssp.percentagelibrarydj;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartButton;
    private Button mCancelButton;
    private Button mEndButton;
    private ObjectAnimator mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentage_seekbar);


        mStartButton = (Button) findViewById(R.id.start_button);
        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mEndButton = (Button) findViewById(R.id.end_button);

        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        final TextView textView = (TextView) findViewById(R.id.text);

        mAnimation = ObjectAnimator.ofInt(progressBar, "progress" , 0, 100);
        mAnimation.setDuration(2000); // 2 seconds
        mAnimation.setInterpolator(new DecelerateInterpolator());
        mAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //do something when the countdown is complete
                textView.setText("Animation Ended!!!!!");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });

        mStartButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);
        mEndButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == mStartButton.getId()) {
            mAnimation.start();
        } else if (viewId == mCancelButton.getId()) {
            // this will pause the animation (the progress will remain on the reached position)
            mAnimation.cancel();
        } else if (viewId == mEndButton.getId()) {
            // this will end the animation (the progress will be set to its max value)
            mAnimation.end();
        }
    }

    @Override
    protected void onDestroy() {
        if (mAnimation.isRunning()){
            mAnimation.end();
        }
        super.onDestroy();
    }
}
