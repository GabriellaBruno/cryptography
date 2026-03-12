/*
 * Name: Gabriella Bruno
 * Project 3
 * Title: CryptographyDriver.java
 * Description: encrypts/decrypts messages that the user enters, using file input and output
 * Date: 4/27/25
 * 
 */

import java.io.*;
import java.util.*;

public class CryptographyDriver {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner k = new Scanner(System.in);

		int choice = 0;
		System.out.println("Welcome to the Cryptography Program!");
		
		Scanner input = null;
		PrintWriter output = null;
		
		String userMessage = ""; 
		
		String outputFileName = "";
		String inputFileName = "";

		boolean outputFileExists = false;

		
		do {
			System.out.println("Enter your choice: \n1 -- Encode/Encrypt a message \n2 -- Decode/Decrypt a message \n3 -- Quit/Exit");
			choice = k.nextInt();
			
			if (choice == 1) { //encode
				k.nextLine();
				
				System.out.println("Please enter the name of the file for the encryption key:");
				inputFileName = k.nextLine();
				
				System.out.println("Please enter the name of the file where you want your message to be saved:");
				outputFileName = k.nextLine();
				
				try {
					input = new Scanner(new FileInputStream(inputFileName));
					if (outputFileExists == false) { //to see if there is already an output file, so the messages are not written over
						output = new PrintWriter(new FileOutputStream(outputFileName));
					}
				} catch (FileNotFoundException e) {
					System.err.println("Error. The file does not exist.");
					System.exit(0);
				} catch (Exception e) {
					System.err.println("Error accessing the file.");
					System.exit(0);
					
				}
				
				outputFileExists = true; //only reaches this if there are no errors in the try block
				
				if (input.hasNextLine() == false) {
					System.err.println("Error. The cipher file is empty.");
					System.exit(0);
				}
				
				String alphabet = input.nextLine();
				String cipher = input.nextLine();
				if (cipher.length() !=52 && alphabet.length() != 52) {
					System.err.println("The cipher does not have the correct amount of characters.");
					System.exit(1);
				}
				
				System.out.println("Please enter the message you want to be encrypted:");
				userMessage = k.nextLine();
				encrypt(userMessage, alphabet, cipher, output);
		
			} else if (choice == 2) { //decode
				
				k.nextLine();
		
				System.out.println("Please enter the name of the file for the encryption key:");
				inputFileName = k.nextLine();
				
				System.out.println("Please enter the name of the file where you want your message to be saved:");
				outputFileName = k.nextLine();
				
				try {
					input = new Scanner(new FileInputStream(inputFileName));
					if (outputFileExists == false) { //to see if there is already an output file, so the messages are not written over
						output = new PrintWriter(new FileOutputStream(outputFileName));
					}
				} catch (FileNotFoundException e) {
					System.err.println("Error. The file does not exist.");
					System.exit(0);
				} catch (Exception e) {
					System.err.println("Error accessing the file.");
					System.exit(0);
				}
				
				outputFileExists = true; //only reaches this if there are no errors in the try block
				
				if (input.hasNextLine() == false) {
					System.err.println("Error. The cipher file is empty.");
					System.exit(0);
				}
				
				String alphabet = input.nextLine();
				String cipher = input.nextLine();
				
				if (cipher.length() !=52 && alphabet.length() != 52) {
					System.err.println("The cipher does not have the correct amount of characters.");
					System.exit(1);
				}
				
				System.out.println("Please enter the message you want to be decrypted:");
				userMessage = k.nextLine();
				decrypt(userMessage, alphabet, cipher, output);

			} else if (choice == 3) { //exit
				System.out.println("Thank you for using the program. Goodbye!");
				output.close();
			} else { //invalid
				System.out.println("Please enter a valid option.");

			}
			
		} while (choice != 3);

	}//end main
	
	public static void encrypt(String userMessage, String alphabet, String cipher, PrintWriter output) {
			
		String eMessage = ""; //encrypt the message
		
		for (int i = 0; i < userMessage.length(); i++) { //iterate through entire message, letter by letter
			char c = userMessage.charAt(i); //current letter
			
			int index = alphabet.indexOf(c); //checking position
			
			if (index == -1) { //using that position, check the letter on the encrypted line
				eMessage = eMessage + " ";
			} else {
				eMessage = eMessage + cipher.charAt(index);
			}
		}
		System.out.println(eMessage);
		output.println(eMessage);	
	}//end encrypt static method
	
	public static void decrypt(String userMessage, String alphabet, String cipher, PrintWriter output) {
		
		String dMessage = ""; //decrypt the message
		
		for (int i = 0; i < userMessage.length(); i++) { //iterate through entire message, letter by letter
			char c = userMessage.charAt(i); //current letter
			
			int index = cipher.indexOf(c); //checking position
			
			if (index == -1) { //using that position, check the letter on the encrypted line
				dMessage = dMessage + " ";
			} else {
				dMessage = dMessage + alphabet.charAt(index);
			}	
		}
		System.out.println(dMessage);
		output.println(dMessage);
	}//end decrypt static method
	
}//end class

/*
 * console output:
 * 
Welcome to the Cryptography Program!
Enter your choice: 
1 -- Encode/Encrypt a message 
2 -- Decode/Decrypt a message 
3 -- Quit/Exit
0
Please enter a valid option.
Enter your choice: 
1 -- Encode/Encrypt a message 
2 -- Decode/Decrypt a message 
3 -- Quit/Exit
1
Please enter the name of the file for the encryption key:
cipher.txt																	//this file has the cipher, no spaces between letters
Please enter the name of the file where you want your message to be saved:
saveMessage.txt
Please enter the message you want to be encrypted:
My name is Gabriella
Ct bgcr sf Qgulsriig
Enter your choice: 
1 -- Encode/Encrypt a message 
2 -- Decode/Decrypt a message 
3 -- Quit/Exit
2
Please enter the name of the file for the encryption key:
cipher.txt
Please enter the name of the file where you want your message to be saved:
saveMessage.txt
Please enter the message you want to be decrypted:
Agkg sf mdb
Java is fun
Enter your choice: 
1 -- Encode/Encrypt a message 
2 -- Decode/Decrypt a message 
3 -- Quit/Exit
2
Please enter the name of the file for the encryption key:
cipher.txt
Please enter the name of the file where you want your message to be saved:
saveMessage.txt
Please enter the message you want to be decrypted:
Hprlr glr bn frwlrhf hn fdwwrff
There are no secrets to success
Enter your choice: 
1 -- Encode/Encrypt a message 
2 -- Decode/Decrypt a message 
3 -- Quit/Exit
1
Please enter the name of the file for the encryption key:
cipher.txt
Please enter the name of the file where you want your message to be saved:
saveMessage.txt
Please enter the message you want to be encrypted:
Hello World
Priin Jnliy
Enter your choice: 
1 -- Encode/Encrypt a message 
2 -- Decode/Decrypt a message 
3 -- Quit/Exit
3
Thank you for using the program. Goodbye!
 *  
 * 
 * 
 * 
 * 
 * error messages:
 * 
Welcome to the Cryptography Program!
Enter your choice: 
1 -- Encode/Encrypt a message 
2 -- Decode/Decrypt a message 
3 -- Quit/Exit
1
Please enter the name of the file for the encryption key:
cipher2.txt																	//this cipher has spaces between the letters, so it is more than 52 characters
Please enter the name of the file where you want your message to be saved:
saveMessage.txt
The cipher does not have the correct amount of characters.
 * 
 * 
Welcome to the Cryptography Program!
Enter your choice: 
1 -- Encode/Encrypt a message 
2 -- Decode/Decrypt a message 
3 -- Quit/Exit
2
Please enter the name of the file for the encryption key:
empty.txt																	//this is an empty file that I used to test
Please enter the name of the file where you want your message to be saved:
saveMessage.txt
Error. The cipher file is empty.
 * 
 * 
Welcome to the Cryptography Program!
Enter your choice: 
1 -- Encode/Encrypt a message 
2 -- Decode/Decrypt a message 
3 -- Quit/Exit
2
Please enter the name of the file for the encryption key:
cipher3.txt																	//this name of a file does not exist that I used to test
Please enter the name of the file where you want your message to be saved:
saveMessage.txt
Error. The file does not exist.
 * 
 */
