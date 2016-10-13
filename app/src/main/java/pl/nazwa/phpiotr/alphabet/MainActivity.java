package pl.nazwa.phpiotr.alphabet;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    protected TextView letter = null;
    protected Button prev = null, next = null;
    protected int index = 0;
    protected int alphabet_length = 0;
    protected boolean is_upper = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alphabet_length = alphabet.length;
        letter = (TextView) findViewById(R.id.letter);
        letter.setText("" + alphabet[index]);

        prev = (Button) findViewById(R.id.prev);
        next = (Button) findViewById(R.id.next);
        prev.setOnClickListener(this);
        next.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.manage);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_upper = !is_upper;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    letter.setAllCaps(is_upper);
                } else {
                    if (is_upper) {
                        letter.setText(("" + alphabet[index]).toLowerCase());
                    } else {
                        letter.setText(("" + alphabet[index]).toUpperCase());
                    }
                }
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                index = index == alphabet_length - 1 ? 0 : index + 1;
                break;
            case R.id.prev:
                index = index == 0 ? (alphabet_length - 1) : index - 1;
                break;
            default:
                break;
        }
        Log.d("INDEX", "" + index);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (is_upper) {
                letter.setText(("" + alphabet[index]).toLowerCase());
            } else {
                letter.setText(("" + alphabet[index]).toUpperCase());
            }
        } else {
            letter.setText("" + alphabet[index]);
        }
    }
}
