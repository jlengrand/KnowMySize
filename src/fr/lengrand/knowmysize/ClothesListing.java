/**
 * 
 */
package fr.lengrand.knowmysize;

import java.util.ArrayList;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Defines the way data about clothes are saved. 
 * The clothe listing will be used to saved all pieces of clothes that friends can wear.
 * Clothes listing shall be automatically generated on first startup, and not touched directly by user interaction.
 * 
 * @author jll
 * 
 * Structure :
 * 
 * <clothes>
 * 	<clothe id=1> // each piece of clothe is uniquely defined by its id. 
 * 		<name>top</name> // general name of this type of clothes.
 * 		<sizetype>1</sizetype> //refers to an id in sizes listing. Defines all possible sizes for this precise type of clothe
 * 		<sex>M</sex>
 * 	</clothe>
 * 	<clothe id=2>
 * 		<name>bras</name>
 * 		<sizetype>2</sizetype>
 * 		<sex>F</sex>
 * 	</clothe>
 * </clothes>
 *
 */
@Root (name="clothes")
public class ClothesListing {
	
	@ElementList
	private ArrayList<ClotheXML> clothes; // XXX: how to handle that?

	public ClothesListing(){
		super();
	}

	public ClothesListing(ArrayList<ClotheXML> clothes){
		this.clothes = clothes;
	}		
	
	public ArrayList<ClotheXML> getClothes() {
		return clothes;
	}

	public void setClothes(ArrayList<ClotheXML> clothes) {
		this.clothes = clothes;
	}
	
}

@Root (name="clothe")
class ClotheXML{
	
	@Attribute(name="id")
	private int id;
	
	@Element(name="name")
	private String name;
	
	@Element(name="sizetype")
	private int sizetype;

	@Element(name="sexe")
	private String sexe;	
	
	public ClotheXML(){
		super();
	}
	
	public ClotheXML(int id, String name, String sexe, int sizetype){
		this.id = id;
		this.sizetype = sizetype;
		this.name = name;
		this.sexe = sexe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSizetype() {
		return sizetype;
	}

	public void setSizetype(int sizetype) {
		this.sizetype = sizetype;
	}
	
	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	
}