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
		int m_socks_id = 5;
		ArrayList<SizeXML> m_socks_sizes = new ArrayList<SizeXML>();
				//String Base, String EU, String US, String RU, String DE, String UK, String IT, String FR)
		String m_socks[][] = 
			{	{null	, "37/38",	"8", "23", 	null, null, null, null}, 
				{null	, "39/40",	"9", "25", 	null, null, null, null}, 
				{null	, "41/42",	"10", "27", null, null, null, null}, 
				{null	, "43/44",	"11", "29", null, null, null, null}, 
				{null	, "45/46",	"12", "31", null, null, null, null}
			};
		for(int i =0; i < m_socks.length; i++){
			m_socks_sizes.add(new SizeXML(i, m_socks[i][0], m_socks[i][1], m_socks[i][2], m_socks[i][3]));
		}
		sizetypes.add(new SizeTypeXML(m_socks_id, m_socks_sizes));
		
		//shoes M 6
		int m_shoes_id = 6;
		ArrayList<SizeXML> m_shoes_sizes = new ArrayList<SizeXML>();
				//String Base, String EU, String US, String RU, String DE, String UK, String IT, String FR)
		String m_shoes[][] = 
			{	{"25"	, "40"	,	"7"		, "39"	, null, null, null, null}, 
				{"25.5"	, "40.5",	"7.5"	, "39.5", null, null, null, null}, 
				{"26"	, "41"	,	"8"		, "40"	, null, null, null, null}, 
				{"26.5"	, "41.5",	"8.5"	, "40.5", null, null, null, null}, 
				{"27"	, "42"	,	"9"		, "41"	, null, null, null, null},
				{"27.5"	, "42.5",	"9.5"	, "41.5", null, null, null, null},
				{"28"	, "43"	,	"10"	, "42"	, null, null, null, null},
				{"28.5"	, "43.5",	"10.5"	, "42.5", null, null, null, null},
				{"29"	, "44"	,	"11"	, "43"	, null, null, null, null},
				{"29.5"	, "44.5",	"11.5"	, "43.5", null, null, null, null},
				{"30"	, "45"	,	"12"	, "44"	, null, null, null, null},
				{"31"	, "46"	,	"13"	, "45"	, null, null, null, null},
				{"32"	, "47"	,	"14"	, "46"	, null, null, null, null},
				
			};
		for(int i =0; i < m_shoes.length; i++){
			m_shoes_sizes.add(new SizeXML(i, m_shoes[i][0], m_shoes[i][1], m_shoes[i][2], m_shoes[i][3]));
		}
		sizetypes.add(new SizeTypeXML(m_shoes_id, m_shoes_sizes));
		
		//ladieswear  W 7
		int ladieswear_id = 7;
		ArrayList<SizeXML> ladieswear_sizes = new ArrayList<SizeXML>();
				// Base,  EU,  US,  RU,  DE,  UK,  IT,  FR)
		String ladieswear[][] = 
			{	{"XS"	, "34-36"	,	"6-8"	, "40-42"	, null, null, null, null}, 
				{"S"	, "38"		,	"10"	, "44"		, null, null, null, null}, 
				{"M"	, "40-42"	,	"12-14"	, "46-48"	, null, null, null, null}, 
				{"L"	, "44"		,	"16"	, "50"		, null, null, null, null}, 
				{"XL"	, "46-48"	,	"18-20"	, "52-54"	, null, null, null, null}, 
				{"XXL"	, "50"		,	"22"	, "56"		, null, null, null, null}, 
				{"XXXL"	, "52"		,	"24"	, "58"		, null, null, null, null}
			};
		for(int i =0; i < ladieswear.length; i++){
			ladieswear_sizes.add(new SizeXML(i, ladieswear[i][0], ladieswear[i][1], ladieswear[i][2], ladieswear[i][3]));
		}
		sizetypes.add(new SizeTypeXML(ladieswear_id, ladieswear_sizes));		
		
		//bras W 8
		int bras_id = 8;
		ArrayList<SizeXML> bras_sizes = new ArrayList<SizeXML>();
				// Base,  EU,  US,  RU,  DE,  UK,  IT,  FR)
		String bras[][] = 
			{	{null	, "65"	,	"30"	, null	, null, "30", "1", "80"	}, 
				{null	, "70"	,	"32"	, null	, null, "32", "2", "85"	}, 
				{null	, "75"	,	"34"	, null	, null, "34", "3", "90"	}, 
				{null	, "80"	,	"36"	, null	, null, "36", "4", "95"	}, 
				{null	, "85"	,	"38"	, null	, null, "38", "5", "100"}, 
				{null	, "90"	,	"40"	, null	, null, "40", "6", "105"}, 
				{null	, "95"	,	"42"	, null	, null, "42", "7", "110"}
			};
		for(int i =0; i < bras.length; i++){
			bras_sizes.add(new SizeXML(i, bras[i][0], bras[i][1], bras[i][2], bras[i][3]));
		}
		sizetypes.add(new SizeTypeXML(bras_id, bras_sizes));
		
		//lingerie W 9
		int lingerie_id = 9;
		ArrayList<SizeXML> lingerie_sizes = new ArrayList<SizeXML>();
				// Base,  EU,  US,  RU,  DE,  UK,  IT,  FR)
		String lingerie[][] = 
			{   {"XXS"	, null	,	"8"	, "42"	, "36", null, null, "38"}, 
				{"XS"	, null	,	"10", "44"	, "38", null, null, "40"}, 
				{"S"	, null	,	"12", "46"	, "40", null, null, "42"}, 
				{"M"	, null	,	"14", "48"	, "42", null, null, "44"}, 
				{"L"	, null	,	"16", "50"	, "44", null, null, "46"}, 
				{"XL"	, null	,	"18", "50"	, "46", null, null, "48"}, 
				{"XXL"	, null	,	"20", "54"	, "48", null, null, "50"}, 
				{"XXXL"	, null	,	"22", "56"	, "50", null, null, "52"}
			};
		for(int i =0; i < lingerie.length; i++){
			lingerie_sizes.add(new SizeXML(i, lingerie[i][0], lingerie[i][1], lingerie[i][2], lingerie[i][3]));
		}
		sizetypes.add(new SizeTypeXML(lingerie_id, lingerie_sizes));
		
		//shoes 10
		int w_shoes_id = 6;
		ArrayList<SizeXML> w_shoes_sizes = new ArrayList<SizeXML>();
				//String Base, String EU, String US, String RU, String DE, String UK, String IT, String FR)
		String w_shoes[][] = 
			{	{"21.5"	, "35"	,	"5"		, "34"	, null, "2.5"	, null, null}, 
				{"22"	, "35.5",	"5.5"	, "34.5", null, "3"		, null, null}, 
				{"22.5"	, "36"	,	"6"		, "35"	, null, "3.5"	, null, null}, 
				{"23"	, "36.5",	"6.5"	, "35.5", null, "4"		, null, null}, 
				{"23.5"	, "37"	,	"7"		, "36"	, null, "4.5"	, null, null},
				{"24"	, "37.5",	"7.5"	, "36.5", null, "5"		, null, null},
				{"24.5"	, "38"	,	"8"		, "37"	, null, "5.5"	, null, null},
				{"25"	, "38.5",	"8.5"	, "37.5", null, "6"		, null, null},
				{"25.5"	, "39"	,	"9"		, "38"	, null, "6.5"	, null, null},
				{"26"	, "39.5",	"9.5"	, "38.5", null, "7"		, null, null},
				
			};
		for(int i =0; i < w_shoes.length; i++){
			w_shoes_sizes.add(new SizeXML(i, w_shoes[i][0], w_shoes[i][1], w_shoes[i][2], w_shoes[i][3]));
		}
		sizetypes.add(new SizeTypeXML(w_shoes_id, w_shoes_sizes));
		
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