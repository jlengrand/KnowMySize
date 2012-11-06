package fr.lengrand.knowmysize;

import java.util.ArrayList;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * DescriBes an entity the user created. 
 * A friend represent a unique element of the list 
 * generated in MainActivity
 * 
 * Root component of our data model
 * @author jll
 * 
 * Example:
 * 
 * 	<friend name="shirt">
 *   	<sex>1</sex>
 *   	<age>42</age>
 *		<clothe name="shirt">
 *   		<category>1</category>
 *   		<size>42</size>
 *		</clothe>
 *		<clothe name="skirt">
 *   		<category>2</category>
 *   		<size>38</size>
 *		</clothe>
 *	</friend>
 *
 */
@Root (name="friend")
public class FriendXml {
	
	@Attribute(name="name")
	private String name; 
		
	@Element(name="sex")
	private int sex = 0;
	
	@Element(name="age")
	private int age;

	private ArrayList<Clothe> clothes; // XXX: how to handle that?
	
	public FriendXml() {
		super();
	}  

	public FriendXml(String name, int sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getSex(){
		return this.sex;
	}
	
	public int getAge(){
		return this.age;
	}
	
}
