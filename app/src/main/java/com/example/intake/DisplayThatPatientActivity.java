package com.example.intake;

import android.os.Bundle;

//testcomment
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


/** This activity displays all of a patient's saved data.*/
public class DisplayThatPatientActivity extends Activity {

/** The health card number for the patient to display.*/
private String patientHCN;

/** The emergency room where the patient to display is stored.*/
private EmergencyRoom er;

/** The user type of the app.*/
private String userType;

/** The patient to display.*/
private Patient patient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_that_patient);
		Intent intent = getIntent();
		this.er = (EmergencyRoom)intent.getSerializableExtra("er");
		this.patientHCN = (String)intent.getSerializableExtra("hcn");
		this.userType = (String)intent.getSerializableExtra("userType");
		TextView patientDisplayText = (TextView) 
				findViewById(R.id.patient_to_display);
		this.patient = er.lookUpPatient(patientHCN);
		patientDisplayText.setText(patient.displayString());
	}

	/** Inflates the menu; adds items to the 
	*action bar if it is present. */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.display_that_patient, menu);
		return true;
	}

	 /** Takes the user back to the previous activity.
	 * @param view the view of this activity
	 */
	public void goBack(View view){

		if (userType.equals(UserTypeActivity.userTypes[0])){
			Intent intent = new Intent(this, NurseOptionsActivity.class);
	    	intent.putExtra("er", er);
			intent.putExtra("hcn", patientHCN);
			intent.putExtra("userType", userType);
	    	startActivity(intent);
		}
		else if (userType.equals(UserTypeActivity.userTypes[1])){
			Intent intent = new Intent(this, PhysicianOptionsActivity.class);
	    	intent.putExtra("er", er);
			intent.putExtra("hcn", patientHCN);
			intent.putExtra("userType", userType);
	    	startActivity(intent);
		}
	}
	

}
