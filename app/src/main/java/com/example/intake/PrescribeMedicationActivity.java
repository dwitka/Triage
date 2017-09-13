package com.example.intake;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


/** This activity allows the user to record a patient's prescription.*/
public class PrescribeMedicationActivity extends Activity {
	
	/** The health card number for the patient whose prescription is
	 * to be recorded.*/
	private String patientHCN;

	/** The emergency room where the patient whose prescription is to
	 * be recorded is stored.*/
	private EmergencyRoom er;

	/** The user type of the app.*/
	private String userType;

	/** The patient whose prescription is to be recorded.*/
	private Patient patient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescribe_medication);
		
		Intent intent = getIntent();
		this.er = (EmergencyRoom)intent.getSerializableExtra("er");
		this.patientHCN = (String)intent.getSerializableExtra("hcn");
		this.userType = (String)intent.getSerializableExtra("userType");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prescribe_medication, menu);
		return true;
	}
	
	/**
	 * Records information about a patient's medication based on user input.
	 * @param view the view of this activity
	 * @throws IOException
	 */
	public void recordPrescriptionInfo(View view) throws IOException{

    	EditText nameText = (EditText) findViewById(R.id.prescription_name_field);
    	String name = nameText.getText().toString();

    	EditText instructionsText = (EditText) findViewById(R.id.instructions_field);
    	String instructions = instructionsText.getText().toString();

    	this.patient = er.lookUpPatient(patientHCN);
    	Medication med = new Medication(name, instructions);
    	er.recordPrescription(patient, med);
    	System.out.println(patient.displayString());

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
		intent.putExtra("userType", userType);
		intent.putExtra("hcn", patientHCN);
    	startActivity(intent);

	}
}


