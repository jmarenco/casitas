package com.mym.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(MainActivity.this);
        layout.setId(R.id.layout_id);
        LayoutParams layoutParams
                = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(LinearLayout.VERTICAL);

        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setId(R.id.image_id);
        imageView.setImageResource(R.drawable.ic_launcher);
        LayoutParams imageViewLayoutParams
                = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(imageViewLayoutParams);

        layout.addView(imageView);

        setContentView(layout);

        layout.setOnClickListener(viewOnClickListener);
        imageView.setOnClickListener(viewOnClickListener);

        layout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Toast.makeText(MainActivity.this, "Touch coordinates : " + String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()), Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }

    OnClickListener viewOnClickListener
            = new OnClickListener(){

        @Override
        public void onClick(View v) {

            int myId = v.getId();

            Toast.makeText(MainActivity.this,
                    "ID: " + String.valueOf(myId) + " clicked",
                    Toast.LENGTH_LONG).show();
        }};
}
