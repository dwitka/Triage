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


/** This activity allows the user to admit a new patient*/
public class PatientAdmittanceActivity extends Activity {

	/** The emergency room where the patient is to be admitted.*/
	private EmergencyRoom er;

	/** The user type of the app.*/
	private String userType;

	/** The patient to admit.*/
	private Patient patient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_admittance);
		Intent intent = getIntent();
		this.er = (EmergencyRoom)intent.getSerializableExtra("er");
		this.userType = (String)intent.getSerializableExtra("userType");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		/** Inflate the menu; this adds items to the 
		*action bar if it is present. */
		getMenuInflater().inflate(R.menu.patient_admittance, menu);
		return true;
	}

	
	/** Admit this patient to the Emergency Room */
	public void admitThisPatient(View view) throws IOException{
		
		Intent intent = new Intent(this, HomeScreenActivity.class);

    	EditText nameText = (EditText) findViewById(R.id.name_field);
    	String name = nameText.getText().toString();

    	EditText healthCardNumberText = (EditText) 
    			findViewById(R.id.hcn_field);
    	String healthCardNumber = healthCardNumberText.getText().toString();

    	EditText birthDateText = (EditText) findViewById(R.id.birthDay_field);
    	String birthDate = birthDateText.getText().toString();

    	EditText birthMonthText = (EditText) 
    			findViewById(R.id.birthMonth_field);
    	String birthMonth = birthMonthText.getText().toString();

    	EditText birthYearText = (EditText) 
    			findViewById(R.id.birthYear_field);
    	String birthYear = birthYearText.getText().toString();

    	this.patient = new Patient(name, healthCardNumber,
    			birthDate, birthMonth, birthYear);
    	er.add(patient);

    	FileOutputStream outputStream;
    	try{
    		outputStream = 
    				openFileOutput(UserTypeActivity.PATIENT_FILE, MODE_PRIVATE);
    		er.saveToFile(outputStream);
    	} catch (FileNotFoundException e){
    		e.printStackTrace();
    	}

		intent.putExtra("er", er);
		intent.putExtra("userType", userType);
    	startActivity(intent);

	}
}
