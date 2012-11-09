package fr.lengrand.knowmysize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

/**
 * @author jll
 * Contains all storing related code. 
 * Those methods shall be used to save and load profiles or modify them.
 *
 */
public class FriendsProvider {
	private static final String TAG = "MainActivity";
	private Context context;
	
	public static String extension = ".xml";
	public static String marker = "f_";
	
	public FriendsProvider(Context context){
		this.context = context;
	}
	
	/** Takes care of special characters the user could enter. 
	 * FIXME: Only takes care of spaces for now.
	 * @param name
	 * @return
	 */
	private String format(String name){
		return name.replace(" ", "_");
	}
	
	/** Creates a filename given the name of the new friend to add. 
	 * FIXME: MArker is not a good way to go : What if a user adds a friend name f_paul? 
	 * @param name
	 * @return
	 */
	private String to_xml_filename(String name){
		return marker + format(name) + extension;
	}
	
	private String to_friend_name(String name){
		String out = name.replace(marker, "");
		return out.replace(extension, "");
	}
	
	/** Adds a new friend to the list in Main Activity.
	 *  Should be triggered by FriendAdder activity
	 *  
	 *  Takes care of spaces, but not special characters
	 * @param name the name of the friend to add
	 * @throws IOException
	 */
	public void addFriend(String name) throws IOException{
		String filename = to_xml_filename(name);
		//TODO: Check if file already exist here
		FileOutputStream fos =  context.getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
		fos.close(); // should have created the file
	}
	
	/** Removes a new friend of the list in Main Activity.
	 *  Should be triggered by FriendAdder activity
	 *  
	 *  WARNING : The actual xml file will be removed! (ans data consequently be lost)
	 *  Takes care of spaces, but not special characters
	 * @param name the name of the friend to remove
	 * @throws IOException
	 */
	public void deleteFriend(String name){
		String filename = to_xml_filename(name);
		Boolean res =  context.getApplicationContext().deleteFile(filename);
		
		Log.d(TAG, "removing friend :" + filename + "," + res + " !");
		if (!res){
			Log.e(TAG, "unable to remove friend :" + filename + "!");
		}
	}
	
	/**
	 * Returns a list of Strings corresponding to all the friends
	 * created by the user.
	 * Returns an empty list if user has no friend
	 */
	public String[] getFriends(){
		String[] friends = context.getApplicationContext().fileList();
		
		 Boolean filter_friends = false;
		
		// xml filename to friendly name. Parses XML later on
		 ArrayList<String> new_friends = new ArrayList<String>();
		for(int i = 0; i < friends.length; i++){
			// TODO: think about reducing that later on
			if (filter_friends){
				if (friends[i].startsWith(marker)){ // if file is actually a friend
					new_friends.add(to_friend_name(friends[i]));
				}			
			}
			else{ // otherwise add everything 
				new_friends.add(to_friend_name(friends[i]));
			}
		}
		return new_friends.toArray(new String[new_friends.size()]);
	}
	
}
