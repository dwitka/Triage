package com.example.intake;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;


/**
 * A vital signs record.
 */
public class VitalSigns implements Serializable{

		private static final long serialVersionUID = -8785769072648837966L;

		/** The time that the vital signs were recorded. */
		private Timestamp timeRecorded;

		/** The temperature recorded. */
		private Integer temperature;

		/** The systolic blood pressure recorded. */
		private Integer systolic;

		/** The diastolic blood pressure recorded. */
		private Integer diastolic;

		 /** The heart rate recorded. */
		private Integer heartRate;

		 /**
		  * Constructs a VitalSigns object and sets 
		  * its attributes to the arguments of
		  * temp, systolic, diastolic and heartRate.
		  * @param temp the temperature being recorded
		  * @param systolic the systolic blood pressure being recorded
		  * @param diastolic the diastolic blood pressure being recorded
		  * @param heartRate the heart rate being recorded
		  */
		public VitalSigns(Integer temp, Integer systolic, Integer diastolic,
				Integer heartRate){
			this.temperature = temp;
			this.systolic = systolic;
			this.diastolic = diastolic;
			this.heartRate = heartRate;
			Calendar right_now = Calendar.getInstance();
			this.timeRecorded = new Timestamp(right_now.getTimeInMillis());
		}
		
		public void setTimeRecorded(String time){
			Long timeAsLong = Long.valueOf(time);
			this.timeRecorded = new Timestamp(timeAsLong);
		}
		
	
	/** Returns the temperature associated with this VitalSigns object.
	 * @return the temperature associated with this VitalSigns object.
	 */
	 public Integer getTemperature() {
		return temperature;
	}

	/** Returns the systolic blood pressure associated with this
	 *  VitalSigns object.
	 * @return the systolic blood pressure associated with this
	 *  VitalSigns object.
	 */
	public Integer getSystolic() {
		return systolic;
	}

	/** Returns the diastolic blood pressure associated with this
	 *  VitalSigns object.
	 * @return the diastolic blood pressure associated with this
	 *  VitalSigns object.
	 */
	public Integer getDiastolic() {
		return diastolic;
	}

	/** Returns the heart rate associated with this VitalSigns object.
	 * @return the heart rate associated with this VitalSigns object.
	 */
	public Integer getHeartRate() {
		return heartRate;
	}
	
	/**
	  * Returns a String representation of this VitalSigns object to be
	  * displayed on screen.
	  * @return the String representation of this VitalSigns object to be
	  * displayed on screen.
	  */	
		public String displayString(){
			return "Time Recorded: " + timeRecorded.toString() + "\n" + 
					"Temperature: " + temperature.toString() + "\n" +
					"Blood Pressure: " + systolic.toString() + "/" + 
					diastolic.toString() + "\n" + "Heart Rate: " +
					heartRate.toString();
					
		}
		
	 /**
	  * Returns a String representation of this VitalSigns object to be
	  * written to file.
	  * @return a String representation of this VitalSigns object to be
	  * written to file.
	  */
		public String fileString(){		
			return temperature.toString() + "=" + systolic.toString() + "=" +
					diastolic + "=" + heartRate.toString() + "=" +
					timeRecorded.getTime();
		}
	}



