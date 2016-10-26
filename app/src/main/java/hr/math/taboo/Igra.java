package hr.math.taboo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Igra extends AppCompatActivity {

    Set used = new HashSet();
    int tocni,netocni;
    public int seconds=60;
    public int minutes=0;
    String time = "1 min";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igra);

        Intent mIntent = getIntent();
        time = mIntent.getStringExtra("time");

        if (time.equals("30 sec")) {
            minutes = 0;
            seconds = 30;
        }

        if (time.equals("1 min")) {
            minutes = 0;
            seconds = 60;
        }

        if (time.equals("1.5 min")) {
            minutes = 1;
            seconds = 30;
        }

        if (time.equals("2 min")) {
            minutes = 2;
            seconds = 0;
        }

        String[] array = getResources().getStringArray(R.array.pojmovi);
        int r = new Random().nextInt(array.length);
        used.add(r);
        String randomStr = array[r];
        String[] Separated = randomStr.split(",");
        TextView tv0 = (TextView) findViewById(R.id.TV0);
        tv0.setText(Separated[0]);
        TextView tv1 = (TextView) findViewById(R.id.TV1);
        tv1.setText(Separated[1]);
        TextView tv2 = (TextView) findViewById(R.id.TV2);
        tv2.setText(Separated[2]);
        TextView tv3 = (TextView) findViewById(R.id.TV3);
        tv3.setText(Separated[3]);
        TextView tv4 = (TextView) findViewById(R.id.TV4);
        tv4.setText(Separated[4]);

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            public void run () {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        seconds-=1;
                        TextView tv_timer = (TextView) findViewById(R.id.timer);
                        if (seconds==0) {
                            if (minutes == 0) {
                                tv_timer.setText("Time's up!");
                                gotovo(null);
                            }
                            else {
                                minutes -= 1;
                                seconds = 60;
                            }
                        }
                        tv_timer.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
                        if (seconds>0 && seconds<10)
                            tv_timer.setText(String.valueOf(minutes)+":0"+String.valueOf(seconds));

                    }
                });
            }
        }, 0, 1000);
    }
    public void tocno (View v) {
        tocni++;
        dalje(v);
    }
    public void netocno (View v) {
        netocni++;
        dalje(v);
    }
    public void dalje (View v) {
        String[] array = getResources().getStringArray(R.array.pojmovi);
        int r = new Random().nextInt(array.length);
        while (used.contains(r) == true)
            r = new Random().nextInt(array.length);
        used.add(r);
        String randomStr = array[r];
        String[] Separated = randomStr.split(",");
        TextView tv0 = (TextView) findViewById(R.id.TV0);
        tv0.setText(Separated[0]);
        TextView tv1 = (TextView) findViewById(R.id.TV1);
        tv1.setText(Separated[1]);
        TextView tv2 = (TextView) findViewById(R.id.TV2);
        tv2.setText(Separated[2]);
        TextView tv3 = (TextView) findViewById(R.id.TV3);
        tv3.setText(Separated[3]);
        TextView tv4 = (TextView) findViewById(R.id.TV4);
        tv4.setText(Separated[4]);
    }
    public void gotovo (View v) {
        Intent i = new Intent(getApplicationContext(), Final.class);
        i.putExtra("tocni", tocni);
        i.putExtra("netocni", netocni);
        i.putExtra("time", time);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_igra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
