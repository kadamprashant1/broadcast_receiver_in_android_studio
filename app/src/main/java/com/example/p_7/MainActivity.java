package com.example.p_7;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    ConnectivityReceiver receiver;
    IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new ConnectivityReceiver();
        intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver,intentFilter);
        Button btn=(Button) findViewById( R.id.button2 );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Toast toast =Toast.makeText( MainActivity.this, "Custom Intent Detected.", Toast.LENGTH_SHORT );
                toast.show();
                Handler handler= new Handler();
                handler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                } ,2000);
            }
        } );
    }

    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.p_7.broadcastreceiver.CUSTOM_MESSAGE ");
        sendBroadcast(intent);

    }
    @Override
    protected void onResume()
    {
        super.onResume();

    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}