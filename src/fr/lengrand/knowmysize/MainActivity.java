package fr.lengrand.knowmysize;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	SimpleCursorAdapter mAdapter; 	// This is the Adapter being used to display the list's data

	static String[] friends = new String[0];// These are the Contacts rows that we will retrieve

	FriendsProvider fp; // used to access the data layer

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button add = (Button) findViewById(R.id.add_button);
		add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), FriendAdder.class);
				startActivityForResult(myIntent, 0);
			}

		});

		fp = new FriendsProvider(this.getApplicationContext());
		friends = fp.getFriends();		// Loads already known friends

		ArrayAdapter<String> adapter =
				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends);
		ListView list = (ListView) findViewById(R.id.friendList);
		list.setAdapter(adapter);

		registerForContextMenu(list);
		
		//ugly test

		// gotta remove file first
		ClothesListingCreator clc = new ClothesListingCreator(this.getApplicationContext());
		clc.create();
		
	}

	@Override
	public void onResume(){
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		if (v.getId()==R.id.friendList) { // make sure the event comes from the list

			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			menu.setHeaderTitle(friends[info.position]);

			String[] menuItems = getResources().getStringArray(R.array.home_list_menu); // get items
			for (int i = 0; i<menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]); // set items in List
			}
		}
		else if(v.getId() == R.id.add_button){

		}
		else{
			Log.e(TAG, " Unknown View requested in onCreateContextMenu!!!");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) { // long press on item of list

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

		int menuItemIndex = item.getItemId(); // get selected item

		String[] menuItems = getResources().getStringArray(R.array.home_list_menu); // loads menu elements for long press
		String menuItemName = menuItems[menuItemIndex];
		String listItemName = friends[info.position];

		if(menuItemName == menuItems[0]){ // delete
			fp.deleteFriend(listItemName);		// delete corresponding friend
		}
		else{
			Log.e(TAG, "Unexpected behaviour ! What do ? ?");
		}

		ListView list = (ListView) findViewById(R.id.friendList);
		friends = fp.getFriends();		// reloads already known friends
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends); 
		list.setAdapter(adapter); // updates list of friends

		return true;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent pData) 
	{
		if (resultCode == Activity.RESULT_OK ) {
			String new_friend = pData.getExtras().getString( FriendAdder.RES_FRIEND );
			
			ListView list = (ListView) findViewById(R.id.friendList);
			try {
				fp.addFriend(new_friend);
			} catch (IOException e) {
				Log.e(TAG, "Impossible to create new friend : " + new_friend + "!");
			}
			friends = fp.getFriends();		// Reloads already known friends
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends); 
			list.setAdapter(adapter); // updates list of friends on display

		}
		else{
			Log.v( TAG, "Problem : Unexpected requestCode");
		}
	}
}
