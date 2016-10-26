package hr.math.taboo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Final extends AppCompatActivity {

    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Intent mIntent = getIntent();
        int tocni = mIntent.getIntExtra("tocni", 0);
        int netocni = mIntent.getIntExtra("netocni", 0);
        time = mIntent.getStringExtra("time");
        TextView brT = (TextView) findViewById(R.id.br_tocnih);
        TextView brN = (TextView) findViewById(R.id.br_netocnih);
        brT.setText("Broj točno objašnjenih riječi: " + tocni);
        brN.setText("Broj preskočenih riječi: " + netocni);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {

            Intent i = new Intent(getApplicationContext(), Start.class);
            startActivity(i);
            return true;
        }
        return false;
    }

    public void novaIgra (View v) {
        Intent i = new Intent(getApplicationContext(), Start.class);
        i.putExtra("time", time);
        startActivity(i);
    }

    public void izlaz (View v) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_final, menu);
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
