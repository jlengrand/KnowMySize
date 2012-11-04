package fr.lengrand.knowmysize;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * View displayed when user press Add in Main activity.
 * Used to create a new friend in the base.
 * Returns name of the friend to main activity, that will create the actual friend in data layer
 * @author jll
 *
 */
public class FriendAdder extends Activity {
    private static final String TAG = "FriendAdder";
    
    public static final String RES_FRIEND = "FRIEND";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_adder);
        
        // Adds button interaction to accept friend name
        Button ok = (Button) findViewById(R.id.add_ok_button);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                String new_friend = ((EditText) findViewById(R.id.friend_name)).getText().toString();          
                intent.putExtra(RES_FRIEND, new_friend);
                setResult(RESULT_OK, intent);
                finish();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_friend_adder, menu);
        return true;
    }
}
