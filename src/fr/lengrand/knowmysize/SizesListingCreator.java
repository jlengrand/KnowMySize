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
public class SizesListingCreator {

	private static final String TAG = "SizesListingCreator";
	private static final String XML_FILE = "l_sizes.xml";

	private Context context;

	public SizesListingCreator(Context context){
		this.context = context;
	}

	public void create(){
		/*
		 * <sizes>
		 * 	<sizetype id=1> // each type of size is uniquely defined by its id. Eacg sizetype corresponds to a precise type of clothe
		 * 		<size id=1>
		 * 			<BASE>S<BASE>
		 * 			<EU>37-38</EU>
		 * 			<RU>37-38</RU>
		 * 			<US>14.5-15</US>
		 * 		</size> //refers each possible size for this size type.
		 * 		<size id=2>
		 * 			<BASE>S<BASE>
		 * 			<EU>39-40</EU>
		 * 			<RU>39-40</RU>
		 * 			<US>15.5</US>
		 * 		</size> //refers each possible size for this size type.
		 * 	</sizetype>
		 * 	<sizetype id=2>
		 * 		<size id=1>
		 * 			<BASE>XS<BASE>
		 * 			<EU>34</EU>
		 * 			<RU>40</RU>
		 *	 		<US>6</US>
		 *      </size>
		 * 	</sizetype>
		 * </sizes>
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
		//Log.v(TAG, "sizes removed : " + res);

		// Looks if file already defined
		String[] files = context.getApplicationContext().fileList();
		Boolean isDef = false;
		for(String aFile : files ){
			if (  aFile.equalsIgnoreCase(XML_FILE)){
				isDef = true;
				Log.v(TAG, XML_FILE + " already defined");
			}
		}

		if (!isDef){ // listing does not exist yet
			Log.v(TAG, "Creating " + XML_FILE);
			//saves file here
			Serializer serializer = new Persister();
			SizesListing sListing = new SizesListing(sizes);
			FileOutputStream listing;
			try {
				listing = context.getApplicationContext().openFileOutput(XML_FILE, Context.MODE_PRIVATE);

				try{			
					serializer.write(sListing, listing);
				}
				catch(Exception e){
					Log.e(TAG, "Impossible to write " + XML_FILE + " " + e);
				}


			} catch (FileNotFoundException e1) {
				Log.e(TAG, "Impossible to write " + XML_FILE + " " + e1);
			}
		}
		//		try{
		//			//reading back
		//			FileInputStream reader = context.getApplicationContext().openFileInput(XML_FILE);
		//			int content;
		//			while ((content = reader.read()) != -1) {
		//				// convert to char and display it
		//				Log.v(TAG, Character.toString((char) content));
		//			}
		//			reader.close();
		//
		//		}
		//		catch(Exception e){
		//			Log.e(TAG, "Impossible to read file");
		//		}
		//
		//		Log.v(TAG, "Finished!");
	}

}