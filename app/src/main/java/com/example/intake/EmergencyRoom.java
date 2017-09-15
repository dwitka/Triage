package com.example.intake;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
*An emergency room. This class is serialized.
*/
public class EmergencyRoom implements Serializable {

	/**
	  * A serial number for this class.
	  */
	private static final long serialVersionUID = -4410857417316941454L;

	/** A map of health card number to Patient object. */ 
	private Map<String, Patient> patients;
	
	/** A list of all patients sorted by arrival time.*/
	private List<Patient> patientsByArrivalTime;

      /** 
      * Constructs an EmergencyRoom object. This object's map attribute is
      * populated with objects whose data is loaded from a file with name
      * filename and directory dir arguments from the constructor's call.
      * @param dir the directory that file which stores the saved data
      * of this EmergencyRoom
      * @param filename the name of the file which stores patient data.
      */
	    public EmergencyRoom(File dir, String filename) throws IOException {
	        patients = new HashMap<String, Patient>();
	        patientsByArrivalTime = new ArrayList<Patient>();
	
	        File file = new File(dir, filename);
	        if (file.exists()) {
	            readFromFile(file.getPath());
	        } else {
	            file.createNewFile();
	        }
	    }

		/**
	      * Adds a Patient to this EmergencyRoom's collection of patients.
	      * The patient parameter is the reference to the Patient object being
	      * added.
	      * @param patient the Patient being added
	      */
	    public void add(Patient patient) {
	        patients.put(patient.getHealthCardNumber(), patient);
	        patientsByArrivalTime.add(patient);
	    }
	    
		/**
	      * Retrieves a patient given a health card number.
	      * @param patientHCN the health card number used to look up the patient.
	      * @return the patient with the given health card number
	      */
	    public Patient lookUpPatient(String patientHCN){
			return patients.get(patientHCN);
	    }

		/**
	      * Returns this EmergencyRoom's map of 
	      * health card number to Patients.
	      * @return the map of health card number to Patient
	      */
	    public Map<String, Patient> getPatients() {
	        return patients;
	    }

	    /**
	     * Record and update the patient's Vitals
	     * @param patient the patient whose vitals are being recorded
	     * @param vitals the vitals to record
	     */
	    public void recordVitalSigns(Patient patient, VitalSigns vitals){
	    	
	    	patient.getAllVitalSigns().add(vitals);
		    	
	    	int urgency = 0;
	    	
	    	int age = patient.getAge();
	    	
	    	if (age < 2){	    		
	    		urgency += 1;
	    	}
	    	
	    	int systolic = vitals.getSystolic();
	    	int diastolic = vitals.getDiastolic();
	    	int heartRate = vitals.getHeartRate();
	    	int temperature = vitals.getTemperature();
	    	
	    	if (systolic >= 140 || diastolic >= 90){		
	    		urgency += 1;
	    	}
	    	if (temperature >= 39){
	    		urgency += 1;
	    	}
	    	if (heartRate >= 100 || heartRate <= 50){
	    		urgency += 1;
	    	}
	    	patient.setUrgency(urgency);
	    	
	    }
	    
	    /**
	     * Record a patient's prescription
	     * @param patient the patient whose prescription is being recorded
	     * @param prescription the prescription to record
	     */
	    public void recordPrescription(Patient patient, Medication prescription){
	    	patient.getAllMeds().add(prescription);
	    }
	    
	    /**
	     * Return a string that displays all patients in this Emergency Room 
	     * that haven't been seen by a doctor in categories of urgency.
	     * @return a string that displays all patients in this Emergency
	     * Room that haven't been seen by a doctor in categories of urgency.
	     */
	    public String displayByUrgency(){
	    	String displayString = "";
	    	if (patientsByArrivalTime.isEmpty()){
	    		displayString += 
	    				"THERE ARE NO PATIENTS WHO HAVE NOT SEEN A DOCTOR";
	    	}
	    	List<Patient> urgent = new ArrayList<Patient>();
	    	List<Patient> lessUrgent = new ArrayList<Patient>();
	    	List<Patient> nonUrgent = new ArrayList<Patient>();
	    	List<Patient> unknownUrgency = new ArrayList<Patient>();
	    	for (Patient patient : patientsByArrivalTime){
	    		if (!patient.getSeenByDoctor()){
	    			if (patient.getUrgency() > 2){
	    				urgent.add(patient);
	    			} else if (patient.getUrgency() > 1){
	    				lessUrgent.add(patient);
	    			} else if (patient.getUrgency() > 0){
	    				nonUrgent.add(patient);
	    			} else {
	    				unknownUrgency.add(patient);
	    			}
	    		}
	    	}
	    	if (!urgent.isEmpty()){
	    		displayString += "MOST URGENT PATIENTS:\n\n";
	    		for (Patient urgentPatient : urgent){
	    			displayString += "Name: " + urgentPatient.getName() + " " + 
	    					", bd: " + urgentPatient.getBirthDate() + " " +
	    					", hcn: " + urgentPatient.getHealthCardNumber() + "\n";
	    		}
	    		displayString += "\n";
	    	}
	    	if (!lessUrgent.isEmpty()){
	    		displayString += "LESS URGENT PATIENTS:\n\n";
	    		for (Patient lessUrgentPatient : lessUrgent){
	    			displayString += "Name: " + lessUrgentPatient.getName() + " " + 
	    					", bd: " + lessUrgentPatient.getBirthDate() + " " +
	    					", hcn: " + lessUrgentPatient.getHealthCardNumber() + "\n";
	    		}
	    		displayString += "\n";
	    	}
	    	if (!nonUrgent.isEmpty()){
	    		displayString += "NON URGENT PATIENTS:\n\n";
	    		for (Patient nonUrgentPatient : nonUrgent){
	    			displayString += "Name: " + nonUrgentPatient.getName() + " " + 
	    					", bd: " + nonUrgentPatient.getBirthDate() + " " +
	    					", hcn: " + nonUrgentPatient.getHealthCardNumber() + "\n";
	    		}
	    		displayString += "\n";
	    	}
	    	if (!unknownUrgency.isEmpty()){
	    		displayString += "URGENCY OF PATIENTS UNKNOWN:\n\n";
	    		for (Patient unknownUrgencyPatient : unknownUrgency){
	    			displayString += "Name: " + unknownUrgencyPatient.getName() + " " + 
	    					", bd: " + unknownUrgencyPatient.getBirthDate() + " " +
	    					", hcn: " + unknownUrgencyPatient.getHealthCardNumber() + "\n";
	    		}
	    		displayString += "\n";
	    	}
	    	return displayString;
	    }

		/**
	      * Writes the data from this instance's map to file outputStream.
	      * @param outputStream the output stream to write the records to
	      */
	    public void saveToFile(FileOutputStream outputStream) 
	    		throws IOException {
	        try {
	        	System.out.println(patients.values());

	            for (Patient patient : patients.values()) {
	            	System.out.println(patient);
	            	String outputString = patient.fileString() + "\n";
	                outputStream.write(outputString.getBytes());
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

		/**
	      * Populates the patients map and patientsByArrivalTime list
	      * of this instance from the file at path
	      * filePath. Throws a FileNotFoundException if the file in the 
	      * file path does not exist. 
	      * @param filePath the file path of the data file
	      * @throws FileNotFoundException
	      */
	    public void readFromFile(String filePath) 
	    		throws FileNotFoundException {

	        Scanner scanner = new Scanner(new FileInputStream(filePath));

	        while(scanner.hasNextLine()) {
	        		String[] record;
	                record = scanner.nextLine().split(";"); 
	                String name = record[0];
	                String healthCardNumber = record[1];
	                String birthDay = record[2];
	                String birthMonth = record[3];
	                String birthYear = record[4];
	                Patient patient = new Patient(name, healthCardNumber,
	                		birthDay, birthMonth, birthYear);
	                patient.setArrivalTime(record[5]);
	                patient.setUrgency(Integer.valueOf(record[6]));	                
	                if (record.length == 8){
	                	String[] medsAndVitals = record[7].split(",");
	                	List<VitalSigns> allVitalSigns = 
	                			new ArrayList<VitalSigns>();
	                	List<Medication> allPrescriptions = 
	                			new ArrayList<Medication>();
	                	for (String info: medsAndVitals){
	                		String[] MedsOrVitalsOrSeen = info.split("=");
	                		if (MedsOrVitalsOrSeen.length == 5){
		                    	Integer temp = 
		                    			Integer.valueOf(MedsOrVitalsOrSeen[0]);
		                    	Integer systolic = 
		                    			Integer.valueOf(MedsOrVitalsOrSeen[1]);
		                    	Integer diastolic = 
		                    			Integer.valueOf(MedsOrVitalsOrSeen[2]);
		                    	Integer heartRate = 
		                    			Integer.valueOf(MedsOrVitalsOrSeen[3]);
		                    	VitalSigns vitals = 
		                    			new VitalSigns(temp, systolic, 
		                    					diastolic, heartRate);
		                    	vitals.setTimeRecorded(MedsOrVitalsOrSeen[4]);
		                    	allVitalSigns.add(vitals);
	                		}
	                    	else if (MedsOrVitalsOrSeen.length == 2){
	                    		String prescriptionName = 
	                    				MedsOrVitalsOrSeen[0];
	                    		String instructions = MedsOrVitalsOrSeen[1];
	                    		Medication prescription = 
	                    				new Medication(prescriptionName, 
	                    						instructions);
		                    	allPrescriptions.add(prescription);
	                    	}
	                    	else {
	                    		patient.setAsSeen();
	                    		String timeSeen = MedsOrVitalsOrSeen[0];
	                    		patient.setTimeSeenByDoctor(timeSeen);
	                    	}
	                	}
	                	patient.setAllVitalSigns(allVitalSigns);  
	                	patient.setAllMeds(allPrescriptions);
	                }
	                this.add(patient);
	        }
	        scanner.close();
	    }
	    
	}




