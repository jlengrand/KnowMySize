package fr.lengrand.knowmysize;

import java.util.ArrayList;

/**
 * DescriBes an entity the user created. 
 * A friend represent a unique element of the list 
 * generated in MainActivity
 * 
 * Root component of our data model
 * @author jll
 *
 */

public class Friend {
	public static String unknown = "U";
	public static String man = "M";
	public static String woman = "F";
	
	
	private String name; 
	private ArrayList<Clothe> clothes;
	private String sexe = unknown;
	private int age;
}
