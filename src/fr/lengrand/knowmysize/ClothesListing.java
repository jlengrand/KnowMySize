/**
 * 
 */
package fr.lengrand.knowmysize;

import java.util.ArrayList;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
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
 * 		<size>1</size> //refers to an id in sizes listing. Defines all possible sizes for this precise type of clothe
 * 	</clothe>
 * 	<clothe id=2>
 * 		<name>bras</name>
 * 		<size>2</size>
 * 	</clothe>
 * </clothes>
 *
 */
@Root (name="clothes")
public class ClothesListing {
	private ArrayList<ClotheXML> clothes; // XXX: how to handle that?
	
	public ClothesListing(){
		super();
	}

}

@Root (name="clothe")
class ClotheXML{
	
	@Attribute(name="id")
	private int id;
	
	@Element(name="name")
	private String name;
	
	@Element(name="size")
	private int size;
	
	public ClotheXML(){
		super();
	}
	
	public ClotheXML(int id, String name, int size){
		this.id = id;
		this.size = size;
		this.name = name;
	}
}
