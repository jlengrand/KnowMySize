package fr.lengrand.knowmysize;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.provider.OpenableColumns;
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
	private String xml_filename(String name){
		return marker + format(name) + extension;
	}
	
	
	/** Adds a new friend to the list in Main Activity.
	 *  Should be triggered by FriendAdder activity
	 *  
	 *  Takes care of spaces, but not special characters
	 * @param name the name of the friend to add
	 * @throws IOException
	 */
	public void addFriend(String name) throws IOException{
		String filename = xml_filename(name);
		//TODO: Check if file already exist here
		FileOutputStream fos =  context.getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
		fos.close(); // should have created the file
	}
	
	
	/**
	 * Returns a list of Strings corresponding to all the friends
	 * created by the user.
	 * Returns an empty list if user has no friend
	 */
	public String[] getFriends(){
		//Log.v(TAG, "HERE IS YOUR FILELIST");
		//Log.v(TAG, context.getApplicationContext().fileList().toString());
		return context.getApplicationContext().fileList();
	}
	
}
