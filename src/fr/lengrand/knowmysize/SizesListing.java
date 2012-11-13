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
 * Defines the way data about sizes are saved. 
 * The size listing will be used to saved all different sizes clothes can have.
 * size listing shall be automatically generated on first startup, and not touched directly by user interaction.
 * 
 * @author jll
 * 
 * Structure :
 * 
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
 *
 */

@Root (name="sizeListing")
public class SizesListing {

	@ElementList
	private ArrayList<SizeTypeXML> sizetypes; // XXX: how to handle that?
	
	public SizesListing(){
		super();
	}

	public SizesListing(ArrayList<SizeTypeXML> sizetypes){
		this.sizetypes = sizetypes;
	}	
	
	public ArrayList<SizeTypeXML> getSizetypes() {
		return sizetypes;
	}

	public void setSizetypes(ArrayList<SizeTypeXML> sizetypes) {
		this.sizetypes = sizetypes;
	}
}

@Root (name="sizetype")
class SizeTypeXML{
	
	@Attribute(name="id")
	private int id;
	
	@ElementList
	private ArrayList<SizeXML> sizes; // XXX: how to handle that?

	public SizeTypeXML(){
		super();
	}

	public SizeTypeXML(int id, ArrayList<SizeXML> sizes){
		this.id = id;
		this.sizes = sizes;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<SizeXML> getSizes() {
		return sizes;
	}

	public void setSizes(ArrayList<SizeXML> sizes) {
		this.sizes = sizes;
	}
	
}

@Root (name="size")
class SizeXML{
	
	@Attribute(name="id")
	private int id;

	@Element(required=false)
	private String BASE;

	@Element(required=false)
	private String EU;
	
	@Element(required=false)
	private String RU;
	
	@Element(required=false)
	private String US;

	@Element(required=false)
	private String DE;
	
	@Element(required=false)
	private String UK;
	
	@Element(required=false)
	private String IT;

	@Element(required=false)
	private String FR;
	
	public SizeXML(){
		super();
	}
	
	public SizeXML(int id, String Base, String EU, String US, String RU, String DE, String UK, String IT, String FR){
		this.id = id;
		this.EU = EU;
		this.US = US;
		this.RU = RU;
		this.UK = UK;
		this.DE = DE;
		this.IT = IT;
		this.FR = FR;
		this.BASE = Base;
	}

	public SizeXML(int id, String Base, String EU, String US, String RU){
		this.id = id;
		this.EU = EU;
		this.US = US;
		this.RU = RU;
		this.UK = null;
		this.DE = null;
		this.IT = null;
		this.BASE = Base;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFR() {
		return FR;
	}

	public void setFR(String fR) {
		FR = fR;
	}

	public String getDE() {
		return DE;
	}

	public void setDE(String dE) {
		DE = dE;
	}

	public String getUK() {
		return UK;
	}

	public void setUK(String uK) {
		UK = uK;
	}

	public String getIT() {
		return IT;
	}

	public void setIT(String iT) {
		IT = iT;
	}

	public String getBASE() {
		return BASE;
	}


	public void setBASE(String bASE) {
		BASE = bASE;
	}


	public String getEU() {
		return EU;
	}


	public void setEU(String eU) {
		EU = eU;
	}


	public String getRU() {
		return RU;
	}


	public void setRU(String rU) {
		RU = rU;
	}


	public String getUS() {
		return US;
	}


	public void setUS(String uS) {
		US = uS;
	}
}
