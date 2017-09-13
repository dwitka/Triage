package com.example.intake;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


/** This activity allows a user to search for a patient by health
 *  card number*/
public class FindPatientActivity extends Activity {

/** The emergency room where the patient to find is stored.*/
private EmergencyRoom er;

/** The health card number that the user will use to find the patient.*/
private String userType;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_patient);
		Intent intent = getIntent();
		this.er = (EmergencyRoom)intent.getSerializableExtra("er");
		this.userType = (String)intent.getSerializableExtra("userType");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		/** Inflate the menu; this adds items to the 
		*action bar if it is present. */
		getMenuInflater().inflate(R.menu.find_patient, menu);
		return true;
	}

	/**
	 * Get the patient that is being passed into the next Activity. If the
	 * user is a nurse, go to the activity with a nurse's options regarding
	 * this patient. If the user is a physician, go to the activity with
	 * a physician's options regarding this patient.
	 * @param view the view of the activity
	 */
	public void getThatPatient(View view){

    	EditText healthCardNumberText = (EditText) 
    			findViewById(R.id.load_from_hcn_field);
    	String healthCardNumber = healthCardNumberText.getText().toString();
    	
		if (!er.getPatients().containsKey(healthCardNumber)){
			Intent intent = new Intent(this, FindPatientActivity.class);
			intent.putExtra("er", er);
			startActivity(intent);
		}
		else{
			if (userType.equals(UserTypeActivity.userTypes[0])){
				Intent intent = new Intent(this, PhysicianOptionsActivity.class);
		    	intent.putExtra("er", er);
				intent.putExtra("hcn", healthCardNumber);
				intent.putExtra("userType", userType);
		    	startActivity(intent);
			}
			else if (userType.equals(UserTypeActivity.userTypes[1])){
				Intent intent = new Intent(this, 
						PhysicianOptionsActivity.class);
		    	intent.putExtra("er", er);
				intent.putExtra("hcn", healthCardNumber);
				intent.putExtra("userType", userType);
		    	startActivity(intent);
			}
		}
	}

}
