package com.example.intake;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


/**This activity allows the user to view all patients in the ER not yet
 * seen by a doctor categorized by urgency.*/
public class UrgencyDisplayActivity extends Activity {

	/** The emergency room where the patients to display are stored.*/
	private EmergencyRoom er;

	/** The user type of the app.*/
	private String userType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_urgency_display);
		Intent intent = getIntent();
		this.er = (EmergencyRoom)intent.getSerializableExtra("er");
		this.userType = (String)intent.getSerializableExtra("userType");
		TextView urgencyDisplayText = (TextView) 
				findViewById(R.id.urgency_display);
		urgencyDisplayText.setText(er.displayByUrgency());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.urgency_display, menu);
		return true;
	}
	
	/**Send the user to the previous activity.*/
	public void goBack(View view){
		Intent intent = new Intent(this, HomeScreenActivity.class);
    	intent.putExtra("er", er);
		intent.putExtra("userType", userType);
    	startActivity(intent);
	
	}

}
