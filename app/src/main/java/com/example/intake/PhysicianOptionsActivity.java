package com.example.intake;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;



/** After loading a patient, this activity gives a physician 2 options:
 * display all data for this patient, or record a prescription
 * for this patient.*/
public class PhysicianOptionsActivity extends Activity {
	
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
		setContentView(R.layout.activity_physician_options);
		Intent intent = getIntent();
		this.er = (EmergencyRoom)intent.getSerializableExtra("er");
		this.patientHCN = (String)intent.getSerializableExtra("hcn");
		this.userType = (String)intent.getSerializableExtra("userType");
		TextView patientBasicText = (TextView) 
				findViewById(R.id.basicInfo);
		this.patient = er.lookUpPatient(patientHCN);
		patientBasicText.setText(patient.getName() + " " + patient.getHealthCardNumber());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.physician_options, menu);
		return true;
	}


	/**
	 * Takes the user to DisplayThatPatientActivity.
	 * @param view the view of this activity
	 */
	public void displayPatient(View view){
		Intent intent = new Intent(this, DisplayThatPatientActivity.class);
    	intent.putExtra("er", er);
		intent.putExtra("hcn", patientHCN);
		intent.putExtra("userType", userType);
    	startActivity(intent);	
	}
	
	/**
	 * Takes the user to PrescribeMedicationActivity.
	 * @param view the view of this activity
	 */
	public void recordPrescriptionInfo(View view){
		Intent intent = new Intent(this, PrescribeMedicationActivity.class);
    	intent.putExtra("er", er);
		intent.putExtra("hcn", patientHCN);
		intent.putExtra("userType", userType);
    	startActivity(intent);	
	}
	
	/**
	 * Takes the user back to FindPatientActivity.
	 * @param view the view of this activity
	 */
	public void goHome(View view){
		Intent intent = new Intent(this, FindPatientActivity.class);
    	intent.putExtra("er", er);
		intent.putExtra("userType", userType);
    	startActivity(intent);
		
	}
}
