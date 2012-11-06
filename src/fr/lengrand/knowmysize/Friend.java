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

	private static int unknown = 0;
	private static int man = 1;
	private static int woman = 2;
	
	
	private String name; 
	private ArrayList<Clothe> clothes;
	private int sexe = 0;
	private int age;
}
