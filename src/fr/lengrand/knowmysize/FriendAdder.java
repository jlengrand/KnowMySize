package fr.lengrand.knowmysize;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FriendAdder extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_adder);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_friend_adder, menu);
        return true;
    }
}
