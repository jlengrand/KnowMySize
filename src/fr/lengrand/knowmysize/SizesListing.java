/**
 * 
 */
package fr.lengrand.knowmysize;

import java.util.ArrayList;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

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
	private ArrayList<SizeTypeXML> sizetypes; // XXX: how to handle that?
	
	public SizesListing(){
		super();
	}

}


@Root (name="sizetype")
class SizeTypeXML{
	
	@Attribute(name="id")
	private int id;
	
	private ArrayList<SizeXML> sizes; // XXX: how to handle that?
	
}


@Root (name="size")
class SizeXML{
	
	@Attribute(name="id")
	private int id;

	public SizeXML(){
		super();
	}
}
