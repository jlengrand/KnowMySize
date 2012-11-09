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
		 * EXAMPLE: 
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

		//-----------
		//Defines all possibilities 
		ArrayList<SizeTypeXML> sizetypes = new ArrayList<SizeTypeXML>();		
		//hats 1
		int hats_id = 1;
		ArrayList<SizeXML> hats_sizes = new ArrayList<SizeXML>();
		String hats[][] = 
			{	{"XXS"	, null,	 "6.5/8", "53"}, 
				{"XS"	, null,	"6.3/4"	, "54"}, 
				{"S"	, null,	"6.7/8"	, "55"}, 
				{"SM"	, null,	"7"		, "56"}, 
				{"M"	, null,	"7.1/8"	, "57"}, 
				{"ML"	, null,	"7.1/4"	, "58"}, 
				{"L"	, null,	"7.3/8"	, "59"}, 
				{"L-XL"	, null,	"7.1/2"	, "60"}, 
				{"XL"	, null,	"7.5/8"	, "61"}, 
				{"XXL"	, null,	"7.3/4"	, "62"}, 
				{"XXXL"	, null,	"7.7/8"	, "63"}, 
				{"4XL"	, null,	"8"		, "64"}, 
				{"5XL"	, null,	"8.1/8"	, "65"}				
			};
		for(int i =0; i < hats.length; i++){
			hats_sizes.add(new SizeXML(i, hats[i][0], hats[i][1], hats[i][2], hats[i][3]));
		}
		sizetypes.add(new SizeTypeXML(hats_id, hats_sizes));
		
		//suits 2
		int suits_id = 2;
		ArrayList<SizeXML> suits_sizes = new ArrayList<SizeXML>();
		String suits[][] = 
			{	{"S"	, "46-48",	"36-38"	, "46-48"}, 
				{"M"	, "48-50",	"38-40"	, "48-50"},  
				{"L"	, "50-52",	"40-42"	, "50-52"}, 
				{"XL"	, "52-54",	"42-44"	, "52-54"}, 
				{"XXL"	, "54-56",	"44-46"	, "54-56"}, 
				{"XXXL"	, "56-58",	"46-48"	, "56-58"}		
			};
		for(int i =0; i < suits.length; i++){
			suits_sizes.add(new SizeXML(i, suits[i][0], suits[i][1], suits[i][2], suits[i][3]));
		}
		sizetypes.add(new SizeTypeXML(suits_id, suits_sizes));
		
		//shirts M 3
		int shirts_id = 3;
		ArrayList<SizeXML> shirts_sizes = new ArrayList<SizeXML>();
		String shirts[][] = 
			{	{"S"	, "37-38"	,	"14.5-15"	, "37-38"}, 
				{"M"	, "39-40"	,	"15.5"		, "39-40"}, 
				{"L"	, "41-43"	,	"16-17"		, "41-43"}, 
				{"XL"	, "44"		,	"17.5"		, "44"}, 
				{"XXL"	, "45"		,	"18"		, "45"}	
			};
		for(int i =0; i < shirts.length; i++){
			shirts_sizes.add(new SizeXML(i, shirts[i][0], shirts[i][1], shirts[i][2], shirts[i][3]));
		}
		sizetypes.add(new SizeTypeXML(shirts_id, shirts_sizes));
		
		//underwear M 4
		int underwear_id = 4;
		ArrayList<SizeXML> underwear_sizes = new ArrayList<SizeXML>();
				//String Base, String EU, String US, String RU, String DE, String UK, String IT, String FR)
		String underwear[][] = 
			{	{"XS"	, null,	null, "44", "3", "32", null, "2"}, 
				{"S"	, null,	null, "46", "4", "34", null, "3"}, 
				{"M"	, null,	null, "48", "5", "36", null, "4"}, 
				{"L"	, null,	null, "50", "6", "38", null, "5"}, 
				{"XL"	, null,	null, "52", "7", "40", null, "6"}, 
				{"XXL"	, null,	null, "54", "8", "42", null, "7"}, 
				{"XXXL"	, null,	null, "56", "9", "44", null, "8"}
			};
		for(int i =0; i < underwear.length; i++){
			underwear_sizes.add(new SizeXML(i, underwear[i][0], underwear[i][1], underwear[i][2], underwear[i][3]));
		}
		sizetypes.add(new SizeTypeXML(underwear_id, underwear_sizes));
		
		
		//socks M 5
		//shoes M 6
		//ladieswear  W 7
		//bras W 8
		//lingerie W 9
		//shoes 10
		
		//-----------

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
			SizesListing sListing = new SizesListing(sizetypes);
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