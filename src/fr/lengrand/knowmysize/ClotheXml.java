package fr.lengrand.knowmysize;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * DescriBes a piece of clothe in the data model.
 * Each piece of clothe belongs to a category, and can be intended to a sex.
 * Friends possess list of clothes
 * @author jll
 * Size will be saved by default in French notation, and converted if needed.
 * 
 * Example:
 * 
 * 	<clothe name="shirt">
 *   	<category>1</category>
 *   	<size>42</size>
 *	</clothe>
 *
 * FIXME: I have to give more thoughts into that 
 */

@Root (name="clothe")
public class ClotheXml {

	@Attribute(name="name")
	private String name;

	@Element(name="size")
	private int size;

	@Element(name="category")
	private int category; // top could for example be 1

	public ClotheXml() {
		super();
	}  

	public ClotheXml(String name, int size, int category) {
		this.name = name;
		this.size = size;
		this.category = category;
	}

	public String getName(){
		return this.name;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public int getCategory(){
		return this.category;
	}
}