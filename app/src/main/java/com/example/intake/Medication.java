package com.example.intake;

import java.io.Serializable;

/**
 * A record of medication.
 */
public class Medication implements Serializable {
	
	/** A serial number for this class.*/
	private static final long serialVersionUID = 4969056814124542138L;

	/** The name of this medication.*/
	private String name;
	
	/**The instructions for taking this medication.*/
	private String instructions;

	 /**
	  * Constructs a Medication object and sets its attributes to the arguments 
	  * of name and instructions.
	  * @param name the name of the medication being recorded
	  * @param instructions the instructions for taking the medication
	  * being recorded
	  */
	public Medication(String name, String instructions){
		this.name = name;
		this.instructions = instructions;
	}
	
	 /**
	  * Returns a String representation of this Medication to be written
	  * to file.
	  * @return a String representation of this Medication to be written
	  * to file.
	  */
	public String fileString(){		
		return name + "=" + instructions;
	}
	
	 /**
	  * Returns a String representation of this Medication to be displayed
	  * on screen.
	  * @return a String representation of this Medication to be displayed
	  * on screen.
	  */
	public String displayString(){
		return "Medication: " + name + "\n" + 
				"Instructions: " + instructions + "\n";
				
	}

}
