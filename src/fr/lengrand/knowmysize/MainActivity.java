package fr.lengrand.knowmysize;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	// This is the Adapter being used to display the list's data
	SimpleCursorAdapter mAdapter;

	// These are the Contacts rows that we will retrieve
	static String[] friends = new String[] {"Paul", "Bob", "Jacques"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayAdapter<String> adapter =
				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends);
		ListView list = (ListView) findViewById(R.id.friendList);
		list.setAdapter(adapter);

		registerForContextMenu(list);
	}

	@Override
	public void onResume(){
		super.onResume();
		//mAdapter.notifyDataSetChanged();
		ListView list = (ListView) findViewById(R.id.friendList);
		((BaseAdapter) list.getAdapter()).notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void SayHelloAgain(View view){
		System.out.println("Hello again !");
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId()==R.id.friendList) { // make sure the event comes from the list

			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			menu.setHeaderTitle(friends[info.position]);

			String[] menuItems = getResources().getStringArray(R.array.home_list_menu); // get items
			for (int i = 0; i<menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]); // set items in List
			}
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) { // long press on item of list

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

		int menuItemIndex = item.getItemId(); // get selected item

		String[] menuItems = getResources().getStringArray(R.array.home_list_menu); // loads menu elements
		String menuItemName = menuItems[menuItemIndex];
		String listItemName = friends[info.position];

		if(menuItemName == menuItems[0]){ // delete
			friends = removeItem(listItemName);
		}
		else{
			System.out.println("Unexpected behaviour ! What do ? ?"); //FIXME
		}

		//TextView text = (TextView)findViewById(R.id.footer);
		//text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));

		//Button myButton = (Button)findViewById(R.id.add_button);
		//myButton.setText(menuItemName + " " + listItemName);

		ListView list = (ListView) findViewById(R.id.friendList);
		((BaseAdapter) list.getAdapter()).notifyDataSetChanged();

		return true;
	}

	private String[] removeItem(String friend) {
		//FIXME : Checks here
		ArrayList<String> temp = null;
		for (int i = 0; i < friends.length; i++) {
			if(friends[i] != friend){
				temp.add(friends[i]);
			}
		}
		return temp.toArray(new String[temp.size()]);
	}
}
