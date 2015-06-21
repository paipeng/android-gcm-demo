package com.paipeng.gcm_tester;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.paipeng.gcm_tester.gcm.GCMRegister;
import com.paipeng.gcm_tester.gcm.GCMRegisterInterface;


public class MainActivity extends ActionBarActivity implements GCMRegisterInterface {

    private GCMRegister gcmRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText projectNumberEditText = (EditText) findViewById(R.id.project_number);
        if (projectNumberEditText != null)
            projectNumberEditText.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    // If the event is a key-down event on the "enter" button
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        // Perform action on key press
                        String projectNumber = projectNumberEditText.getText().toString();
                        registerGCM(projectNumber);
                        return true;
                    }
                    return false;
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

    private void registerGCM(String projectNumber) {
        gcmRegister = new GCMRegister(this);
        gcmRegister.getRegId(projectNumber, this);

    }

    @Override
    public void getRegisterId(String regId) {
        TextView gcmRegIdTextView = (TextView) findViewById(R.id.gcm_reg_id);
        if (gcmRegIdTextView != null) {
            gcmRegIdTextView.setText("GCM RegId" + regId);
        }
    }
}
