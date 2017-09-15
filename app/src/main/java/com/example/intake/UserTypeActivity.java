package com.example.intake;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


/**This is the launch activity that requires users to sign in before they can 
 * access features.
 */
public class UserTypeActivity extends Activity{
	
	/**The set of all user types that the app supports.*/
	public static final String[] userTypes = {"NURSE", "PHYSICIAN"};
	
	/**The user type of the app.*/
	private String userType;
	
	/**The file where all patient information is to be stored.*/
	public static final String PATIENT_FILE = "patient_information.txt";

    public static final String PASSWORD_FILE = "passwords.txt";
	
	/** The Emergency Room object which keeps track of all patients.*/
	private EmergencyRoom er;
	
	/** The password manager that keeps track of all users, their passwords and
	 * user types.
	 */
	private PasswordManager passManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);

        String fileString = "Michael,123,0\nClara,456,1\n";
        File file = new File(this.getFilesDir(), PASSWORD_FILE);
        file.delete();

        try {
            FileOutputStream fos = openFileOutput(PASSWORD_FILE, this.MODE_PRIVATE);
            fos.write(fileString.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            passManager =
            new PasswordManager(this.getApplicationContext().getFilesDir(),
                    PASSWORD_FILE);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Checks if the user signing in is a valid user, and if the password
     * is correct, takes the user access all features based on the user's
     * user type.
     * @param view the view of this activity
     */
    public void signIn(View view){
        EditText usernameText = (EditText) findViewById(R.id.user_name_field);
        EditText passwordText = (EditText) findViewById(R.id.password_field);


        String user_name =
                usernameText.getText().toString();

        String password = passwordText.getText().toString();

        if (passManager.getPasswordsKey().containsKey(user_name)){
            String correctPassword =
                    passManager.getPasswordsKey().get(user_name);
            String typeOfUser =
                    passManager.getUserTypes().get(user_name);
            if (correctPassword.equals(password)){
                try{
                    er =
             new EmergencyRoom(this.getApplicationContext().getFilesDir(),
                            PATIENT_FILE);
                } catch (IOException e){
                    e.printStackTrace();
                }
                if (typeOfUser.equals("0")){
                    Intent intent =
                            new Intent(this, HomeScreenActivity.class);
                    this.userType = userTypes[0];
                    intent.putExtra("er", er);
                    intent.putExtra("userType", userType);
                    startActivity(intent);
                }
                else if (typeOfUser.equals("1")){
                    Intent intent =
                            new Intent(this, FindPatientActivity.class);
                    this.userType = userTypes[1];
                    intent.putExtra("er", er);
                    intent.putExtra("userType", userType);
                    startActivity(intent);
                }
            }
            else{
                Intent intent = new Intent(this, UserTypeActivity.class);
                startActivity(intent);
            }
        }
        passwordText.setText("");
        usernameText.setText("");
    }
}
