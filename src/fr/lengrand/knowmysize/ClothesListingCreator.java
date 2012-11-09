/**
 * 
 */
package fr.lengrand.knowmysize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.content.Context;
import android.util.Log;

/**
 * Defines the list of clothe types we have, and creates the xml file if it not already in the system. 
 * @author jll
 *
 */
public class ClothesListingCreator {

	private static final String TAG = "ClothesListingCreator";
	private static final String XML_FILE = "l_clothes.xml";

	private Context context;

	public ClothesListingCreator(Context context){
		this.context = context;
	}

	public void create(){
		/*
		 * <clothes>
		 * 	<clothe id=1> // each piece of clothe is uniquely defined by its id. 
		 * 		<name>top</name> // general name of this type of clothes.
		 * 		<sizetype>1</sizetype> //refers to an id in sizes listing. Defines all possible sizes for this precise type of clothe
		 * 	</clothe>
		 * 	<clothe id=2>
		 * 		<name>bras</name>
		 * 		<sizetype>2</sizetype>
		 * 	</clothe>
		 * </clothes>		
		 */
		//Defines all possibilities 
		ArrayList<ClotheXML> clothes = new ArrayList<ClotheXML>();
		clothes.add(new ClotheXML(1, "hats", Friend.man, 1));
		clothes.add(new ClotheXML(2, "suits", Friend.man, 2));
		clothes.add(new ClotheXML(3, "shirts", Friend.man, 3));
		clothes.add(new ClotheXML(4, "underwear", Friend.man, 4));
		clothes.add(new ClotheXML(5, "socks", Friend.unknown, 5));
		clothes.add(new ClotheXML(6, "shoes", Friend.woman, 6));
		clothes.add(new ClotheXML(7, "ladieswear", Friend.woman, 7));
		clothes.add(new ClotheXML(8, "bras", Friend.woman, 8));
		clothes.add(new ClotheXML(9, "lingerie", Friend.woman, 9));
		clothes.add(new ClotheXML(10, "shoes", Friend.woman, 10));


		// Tests if file already exists here 

		// Should only be performed in case of update or reset.
		//Boolean res =  context.getApplicationContext().deleteFile(XML_FILE);
		//Log.v(TAG, "clothes removed : " + res);

		//saves file here
		Serializer serializer = new Persister();
		ClothesListing cListing = new ClothesListing(clothes);
		FileOutputStream listing;
		try {
			listing = context.getApplicationContext().openFileOutput(XML_FILE, Context.MODE_PRIVATE);

			try{			
				serializer.write(cListing, listing);
			}
			catch(Exception e){
				//Log.e(TAG, "Impossible to write serializer");
				System.out.println(e);
			}


		} catch (FileNotFoundException e1) {
			Log.e(TAG, "Problem with file creation");
		}

		try{
			//reading back
			FileInputStream reader = context.getApplicationContext().openFileInput(XML_FILE);
			int content;
			while ((content = reader.read()) != -1) {
				// convert to char and display it
				Log.v(TAG, Character.toString((char) content));
			}
			reader.close();

		}
		catch(Exception e){
			Log.e(TAG, "Impossible to read file");
		}
		
		Log.v(TAG, "Finished!");
	}

}