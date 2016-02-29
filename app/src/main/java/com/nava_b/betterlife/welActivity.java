package com.nava_b.betterlife;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class welActivity extends AppCompatActivity
{
    public BetterLifeApplication mBetterLifeApp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(getApplication(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }).start();


    }
}
