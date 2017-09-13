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


/** This activity allows the user to record a patient's vital signs.*/
public class RecordVitalsActivity extends Activity {

	/** The health card number for the patient whose vital signs are
	 * to be recorded.*/
	private String patientHCN;

	/** The emergency room where the patient whose vital signs are to
	 * be recorded is stored.*/
	private EmergencyRoom er;

	/** The user type of the app.*/
	private String userType;

	/** The patient whose vital signs are to be recorded.*/
	private Patient patient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_vitals);

		Intent intent = getIntent();
		this.er = (EmergencyRoom)intent.getSerializableExtra("er");
		this.patientHCN = (String)intent.getSerializableExtra("hcn");
		this.userType = (String)intent.getSerializableExtra("userType");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		/** Inflate the menu; this adds items to the 
		* action bar if it is present. */
		getMenuInflater().inflate(R.menu.record_vitals, menu);
		return true;
	}

	/**
	 * Records information about a patient's vital signs based on user input.
	 * @param view the view of this activity
	 * @throws IOException
	 */
	public void recordSomeVitals(View view) throws IOException{

    	EditText tempText = (EditText) findViewById(R.id.temperature_field);
    	Integer temperature = Integer.valueOf(tempText.getText().toString());

    	EditText sysText = (EditText) findViewById(R.id.systolic_field);
    	Integer systolic = Integer.valueOf(sysText.getText().toString());

    	EditText diaText = (EditText) findViewById(R.id.diastolic_field);
    	Integer diastolic = Integer.valueOf(diaText.getText().toString());

    	EditText hrText = (EditText) findViewById(R.id.hr_field);
    	Integer heartrate = Integer.valueOf(hrText.getText().toString());

    	VitalSigns vitals = new VitalSigns(temperature,
    			systolic, diastolic, heartrate);
    	
    	this.patient = er.lookUpPatient(patientHCN);
    	er.recordVitalSigns(patient, vitals);
    	

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
