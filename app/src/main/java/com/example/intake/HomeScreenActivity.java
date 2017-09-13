package com.example.intake;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;


/**This activity allows a nurse to choose from adding a patient, loading
 * a patient or displaying all patients by urgency. */
public class HomeScreenActivity extends Activity {

/** The emergency room where all patients are stored.*/
private EmergencyRoom er;

/** The user type of the app.*/
private String userType;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		Intent intent = getIntent();
		this.userType = (String)intent.getSerializableExtra("userType");
		this.er = (EmergencyRoom)intent.getSerializableExtra("er");

	}

	/** Inflate the menu; this adds items to the 
	*action bar if it is present. */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}


	/** Takes the user to the PatientAdmittanceActivity.
	 * @param view the view of this activity
	 */
	public void newPatient(View view){
		Intent intent = new Intent(this, PatientAdmittanceActivity.class);
		intent.putExtra("er", er);
		intent.putExtra("userType", userType);
    	startActivity(intent);
	}
	
	/** Takes the user to the FindPatientActivity.
	 * @param view the view of this activity
	 */
	public void findPatient(View view){
		Intent intent = new Intent(this, FindPatientActivity.class);
		intent.putExtra("er", er);
		intent.putExtra("userType", userType);
    	startActivity(intent);
	}
	
	/** Takes the user to the UrgencyDisplayActivity.
	 * @param view the view of this activity
	 */
	public void displayByUrgency(View view){
		Intent intent = new Intent(this, UrgencyDisplayActivity.class);
		intent.putExtra("er", er);
		intent.putExtra("userType", userType);
		startActivity(intent);
	}
}
