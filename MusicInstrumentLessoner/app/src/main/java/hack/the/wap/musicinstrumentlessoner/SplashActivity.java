package hack.the.wap.musicinstrumentlessoner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {
    /*
        debug field
     */
    private Button btnDeb;

    /*
        static field
     */
    private static SplashActivity instance;

    /*
        init block
     */
    {
        instance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(SplashActivity.getInstance(), LoginActivity.class);
            intent.putExtra("state", "launch");
            startActivity(intent);
            finish();
        }).start();
    }

    public static SplashActivity getInstance(){
        return instance;
    }
}
