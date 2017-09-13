package com.example.intake;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


/** After loading a patient, this activity gives a nurse 3 options:
 * display all data for this patient, record vital signs for this 
 * patient or record that this patient has been seen by a doctor.*/
public class NurseOptionsActivity extends Activity {
	
	/** The health card number for the patient.*/
	private String patientHCN;

	/** The emergency room where the patient is stored.*/
	private EmergencyRoom er;

	/** The user type of the app.*/
	private String userType;

	/** The patient that the options are regarding.*/
	private Patient patient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurse_options);
		Intent intent = getIntent();
		this.er = (EmergencyRoom)intent.getSerializableExtra("er");
		this.patientHCN = (String)intent.getSerializableExtra("hcn");
		this.userType = (String)intent.getSerializableExtra("userType");
		TextView patientBasicText = (TextView) 
				findViewById(R.id.basicInfo);
		this.patient = er.lookUpPatient(patientHCN);
		patientBasicText.setText(patient.getName() + " " + 
				patient.getHealthCardNumber());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar 
		// if it is present.
		getMenuInflater().inflate(R.menu.nurse_options, menu);
		return true;
	}
	
	 /** Takes the user to the DisplayThatPatientActivity.
	 * @param view the view of this activity.
	 */
	public void displayPatient(View view){
		Intent intent = new Intent(this, DisplayThatPatientActivity.class);
    	intent.putExtra("er", er);
		intent.putExtra("hcn", patientHCN);
		intent.putExtra("userType", userType);
    	startActivity(intent);
	}
	
	 /** Takes the user to the RecordVitalsActivity.
	 * @param view the view of this activity.
	 */
	public void recordVitals(View view){
		Intent intent = new Intent(this, RecordVitalsActivity.class);
    	intent.putExtra("er", er);
		intent.putExtra("hcn", patientHCN);
		intent.putExtra("userType", userType);
    	startActivity(intent);
	}
	
	/** Records a patient as having been seen by a doctor then takes
	 * the user to DisplayThatPatientActivity. /*
	 * @param view
	 * @throws IOException
	 */
	public void setAsSeen(View view) throws IOException{
		patient.setAsSeen();
		
    	FileOutputStream outputStream;
    	try{
    		outputStream = 
    			openFileOutput(UserTypeActivity.PATIENT_FILE, MODE_PRIVATE);
    		er.saveToFile(outputStream);
    	} catch (FileNotFoundException e){
    		e.printStackTrace();
    	}
    	
		Intent intent = new Intent(this, DisplayThatPatientActivity.class);
    	intent.putExtra("er", er);
		intent.putExtra("hcn", patientHCN);
		intent.putExtra("userType", userType);
    	startActivity(intent);
	}
	
	 /** Takes the user to the HomeScreenActivity.
	 * @param view the view of this activity.
	 */
	public void goHome(View view){
		Intent intent = new Intent(this, HomeScreenActivity.class);
    	intent.putExtra("er", er);
		intent.putExtra("userType", userType);
    	startActivity(intent);
		
	}
	

}
