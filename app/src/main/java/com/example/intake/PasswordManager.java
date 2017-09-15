package com.example.intake;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**This class allows users of the app to log in and depending on
 * if the user is a nurse or a doctor, allows the user to access
 * different features.
 */
public class PasswordManager {

	    /** The passwords being managed by this PasswordManager. */
	    private Map<String, String> passwordsKey;
	    
	    /** The user types of all users*/
	    private Map<String, String> userTypes;

	    /**
	     * Constructs a new PasswordManager that manages a collection of passwords
	     * stored in directory dir in file named fileName.
	     * @param dir the directory in which the data file is stored
	     * @param fileName the data file containing Patient information
	     * @param value An instance of the type being 
	     * managed by this PasswordManager.
	     * @throws IOException
	     */
	    public PasswordManager(File dir, String fileName) throws IOException {



            Log.d("Directory: ", dir.toString());
            Log.d("Filename: ", fileName);
            passwordsKey = new HashMap<>();
	        userTypes = new HashMap<>();
			passwordsKey.put("Michael", "123");
			userTypes.put("Michael", "0");

            passwordsKey.put("Clara", "456");
            userTypes.put("Clara", "1");

	        // Populates the password list using stored data, if it exists.
            File file = new File(dir, fileName);
            Log.d("Filename: ", file.toString());
            Log.d("Absolute path: ", file.getAbsolutePath());

            Log.d("File path: ", file.getPath());

			if (file.exists()) {
	            readFromFile(file.getPath());
	        } else {
	            file.createNewFile();
	        }
	    }

	    
	    /**
	     * Returns the passwords managed by this PasswordManager.
	     * @return a map of username to password object
	     */
	    public Map<String, String> getPasswordsKey() {
	        return passwordsKey;
	    }
	    
	    /**
	     * Returns the userTypes of all users stored in this PasswordManager.
	     * @return a map of username to user type.
	     */
	    public Map<String, String> getUserTypes() {
	        return userTypes;
	    }


	    /**
	     * Populates the passwords map and user type map from the file 
	     * at path filePath
	     * @param filePath the filepath of the data file
	     * @throws FileNotFoundException
	     */
	    public void readFromFile(String filePath) 
	    		throws FileNotFoundException {

	        Scanner scanner = new Scanner(new FileInputStream(filePath));
	        String [] record;

	        while(scanner.hasNextLine()) {
	                record = scanner.nextLine().split(",");
	                String username = record[0];
	                String password = record[1];
	                String userType = record[2];
	            passwordsKey.put(username, password);
	            userTypes.put(username, userType);
	        }
	        scanner.close();
	    }

	}


