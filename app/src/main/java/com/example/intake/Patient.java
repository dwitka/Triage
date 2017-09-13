package com.example.intake;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class Patient implements Serializable{

	/**
	 * An emergency room patient. This class is serialized.
	 */
	private static final long serialVersionUID = -7850661425025071207L;

	 /** This patient's health card number. */
	private String healthCardNumber;

	 /** This patient's name. */
	private String name;

	/** This patient's birthday. */
	private Calendar birthday;

	 /** The arrival time of this patient to the emergency room. */
	private Timestamp arrivalTime;

	/** A list of all this patient's recorded vital signs. */
	private List<VitalSigns> allVitalSigns;
	
	/** A list of all this patient's recorded medications.*/
	private List<Medication> allMeds;
	
	/** True if this patient has been seen by a doctor*/
	private boolean seenByDoctor;
	
	/**If the patient has been seen by a doctor, the time seen.*/
	private Timestamp timeSeenByDoctor;
	
	/**This patient's urgency according to hospital policy.*/
	private Integer urgency;

	 /**
	  * Constructs a new Patient and sets this Patient's attributes to
	  * the name and healthCardNumber arguments of the constructor's call.
	  * @param name the name of the patient
	  * @param healthCardNumber the health card number of the patient
	  */
	public Patient(String name, String healthCardNumber, String birthDate,
			String birthMonth, String birthYear){
		this.healthCardNumber = healthCardNumber;
		this.name = name;
		Calendar birthday = new GregorianCalendar(Integer.parseInt(birthYear),
				Integer.parseInt(birthMonth), Integer.parseInt(birthDate));
		this.birthday = birthday;
		Calendar right_now = Calendar.getInstance();
		this.arrivalTime = new Timestamp(right_now.getTimeInMillis());
		this.allVitalSigns = new ArrayList<VitalSigns>();
		this.allMeds = new ArrayList<Medication>();
		this.seenByDoctor = false;
		this.urgency = 0;
	}

	 /**
	  * Returns this patient's name.
	  * @return this patient's name
	  */
	public String getName(){
		return name;
	}

	/**
	 * Sets this patient's arrival time to a new time.
	 * @param the time of this patient's arrival.
	 */
	public void setArrivalTime(String time){
		Long timeAsLong = Long.valueOf(time);
		this.arrivalTime = new Timestamp(timeAsLong);
		
	}
	 /**
	  * Returns this patient's health card number.
	  * @return this patient's health card number
	  */
	public String getHealthCardNumber(){
		return healthCardNumber;
	}
	
	 /**
	  * Returns this patient's urgency.
	  * @return this patient's urgency
	  */
	public int getUrgency(){
		return urgency;
	}
	
	/**
	 * Sets this patient's urgency level to a new urgency level.
	 * @param urgency the urgency level for this patient.
	 */
	public void setUrgency(int urgency){
		this.urgency = urgency;
	}

	 /**
	  * Returns all this patient's recorded vital signs.
	  * @return this patient's recorded vital signs.
	  */
	public List<VitalSigns> getAllVitalSigns() {
		return allVitalSigns;
	}

	/**
	 * Sets this patient's list of all vital signs to a new
	 * list of VitalSigns.
	 * @param the list of VitalSigns to be designated to the patient.
	 */
	public void setAllVitalSigns(List<VitalSigns> allVitals){
		this.allVitalSigns = allVitals;
	}
	
	 /**
	  * Returns all this patient's recorded medications.
	  * @return this patient's recorded medications.
	  */
	public List<Medication> getAllMeds() {
		return allMeds;
	}
	
	/**
	 * Sets this patient's list of all medications to a new
	 * list of Medications.
	 * @param the list of Medications to be designated to the patient.
	 */
	public void setAllMeds(List<Medication> allMeds){
		this.allMeds = allMeds;
	}
	
	/**
	 * Sets that this patient has been seen by a doctor and 
	 * sets the time this patient was seen by a doctor as
	 * the current time. */
	public void setAsSeen(){
		this.seenByDoctor = true;
		Calendar right_now = Calendar.getInstance();
		this.timeSeenByDoctor = new Timestamp(right_now.getTimeInMillis());
	}
	
	/**
	 * Sets the time this patient was seen by a doctor to a new time.
	 * @param the time this patient was seen by a doctor
	 */
	public void setTimeSeenByDoctor(String time){
		Long timeAsLong = Long.valueOf(time);
		this.timeSeenByDoctor = new Timestamp(timeAsLong);
	}
	
	/**
	 * Returns true if this patient has been seen by a doctor,
	 * false otherwise.
	 * @return true if this patient has been seen by a doctor,
	 * false otherwise.
	 */
	public boolean getSeenByDoctor() {
		return seenByDoctor;
	}

	/**
	 * Returns a string representation of this patient's birthday.
	 * @return a string representation of this patient's birthday.
	 */
	public String getBirthDate() {
		return String.valueOf(birthday.get(Calendar.DAY_OF_MONTH)) + "-" +
				String.valueOf(birthday.get(Calendar.MONTH)) + "-" + 
				String.valueOf(birthday.get(Calendar.YEAR));
	}
	
	 /**
	  * Returns a String representation of this Patient to be displayed
	  * on screen.
	  * @return a String representation of this Patient to be displayed
	  * on screen.
	  */
	public String displayString(){
		
		String prescriptionString = "";
		if (!allMeds.isEmpty()){
			prescriptionString += "Prescriptions:\n\n";
			for (Medication prescription : allMeds){
				prescriptionString += prescription.displayString() + "\n";
			}
		}
		String vitalsString = "";
		if (!allVitalSigns.isEmpty()){
			vitalsString += "Vital Signs:\n\n";
			for (VitalSigns vitals : allVitalSigns){
				vitalsString += vitals.displayString() + "\n\n";
			}
		}
		String seen = "";
		if (seenByDoctor){
			seen += name + " was seen by a doctor at " + timeSeenByDoctor.toString() + "\n\n";
		}
		return "Name: " + name + "\n" + "Health Card Number: " + 
				healthCardNumber + "\n" + "Date of Birth: " + 
				String.valueOf(birthday.get(Calendar.DAY_OF_MONTH)) + "/"
				+ String.valueOf(birthday.get(Calendar.MONTH)) + "/"
				+ String.valueOf(birthday.get(Calendar.YEAR)) + "\n" +
				"Arrival Time: " + arrivalTime.toString() + "\n\n"
				+ seen + prescriptionString + vitalsString;
	}

	 /**
	  * Returns a String representation of this Patient to be written to file.
	  * @return a String representation of this Patient to be written to file.
	  */
	public String fileString(){
		
		String prescriptionString = "";
		for (Medication prescription : allMeds){
			prescriptionString += prescription.fileString() + ",";
		}
		String vitalsString = "";
		for (VitalSigns vitals : allVitalSigns){
			vitalsString += vitals.fileString() + ",";
		}
		
		String seen = "";
		if (seenByDoctor){
			seen += timeSeenByDoctor.getTime();
		}

		return name + ";" + healthCardNumber + ";" +
				String.valueOf(birthday.get(Calendar.DAY_OF_MONTH)) + ";" +
				String.valueOf(birthday.get(Calendar.MONTH)) + ";" + 
				String.valueOf(birthday.get(Calendar.YEAR)) + ";"
				+ arrivalTime.getTime() + ";" + urgency.toString() + ";"
				+ vitalsString + prescriptionString + seen;
	}

    /**
     * Returns this patient's age in years.
     * @return this patient's age in years.
     */
    public int getAge(){
    	
    	int age = 0;
    	
    	TimeZone timeZone = TimeZone.getTimeZone("Canada/Toronto");
    	Calendar currentDate = new GregorianCalendar(timeZone);
    	
    	int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
    	int currentMonth = currentDate.get(Calendar.MONTH);
    	int currentYear = currentDate.get(Calendar.YEAR);
    	
    	int birthDay = birthday.get(Calendar.DAY_OF_MONTH);
    	int birthMonth = birthday.get(Calendar.MONTH);
    	int birthYear = birthday.get(Calendar.YEAR);
    	
    	if (birthMonth < currentMonth){
    		age = currentYear - birthYear;
    	}
    	
    	else if(birthMonth == currentMonth){
    		
    		if(birthDay > currentDay){  				
    			age = currentYear - birthYear - 1;
    		}		
    		else{
    			age = currentYear - birthYear;
    		}
    		
    	}
    	
    	else{
    		age = currentYear - birthYear - 1;
    	}
    	
    	return age;
    	
    }
    




}
