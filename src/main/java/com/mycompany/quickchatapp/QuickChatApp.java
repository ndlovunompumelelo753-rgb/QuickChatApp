package com.mycompany.quickchatapp;

import java.util.Scanner;

public class QuickChatApp {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);

// 1. Simple Login
System.out.println("--- Login ---");
System.out.print("Enter Username: ");
String username = scanner.nextLine();
System.out.print("Enter Password: ");
String password = scanner.nextLine();

if (username.isEmpty() || password.isEmpty()) {
System.out.println("Login failed. Username and password cannot be empty. Exiting.");
return;
}

System.out.println("Welcome to QuickChat.");

boolean running = true;

// 3. Menu Loop
while (running) {
System.out.println("\n--- Menu ---");
System.out.println("1) Send Messages");
System.out.println("2) Show recently sent messages");
System.out.println("3) Quit");
System.out.print("Choose an option: ");

// Read the choice
String choiceInput = scanner.nextLine();
int menuChoice;

try {
menuChoice = Integer.parseInt(choiceInput);
} catch (NumberFormatException e) {
System.out.println("Please enter a number.");
continue;
}

switch (menuChoice) {
case 1:
handleSendingMessages(scanner);
break;
case 2:
System.out.println("Coming Soon.");
break;
case 3:
running = false;
System.out.println("Exiting application...");
break;
default:
System.out.println("Invalid choice. Try again.");
}
}
scanner.close();
}

private static void handleSendingMessages(Scanner scanner) {
// 5. Define how many messages
System.out.print("How many messages do you wish to enter? ");
int numMessages = 0;
try {
numMessages = Integer.parseInt(scanner.nextLine());
} catch (NumberFormatException e) {
System.out.println("Invalid number.");
return;
}

for (int i = 1; i <= numMessages; i++) {
System.out.println("\n--- Message " + i + " ---");
System.out.print("Enter Recipient Cell Number: ");
String recipient = scanner.nextLine();

System.out.print("Enter Message (max 250 chars): ");
String messageText = scanner.nextLine();

// Check message length
if (messageText.length() > 250) {
int excess = messageText.length() - 250;
System.out.println("Message exceeds 250 characters by " + excess + "; please reduce the size.");
i--; // Decrement i so they have to retry the same message number
continue;
} else {
System.out.println("Message ready to send.");
}

// Create the message object
Message msg = new Message(recipient, messageText, i);

// Validate recipient
System.out.println(msg.checkRecipientCell());

// 6. Send/Store/Disregard options
System.out.println("Choose action:");
System.out.println("1) Send Message");
System.out.println("2) Disregard Message");
System.out.println("3) Store Message to send later");
System.out.print("Choice: ");

int action = 0;
try {
action = Integer.parseInt(scanner.nextLine());
} catch (NumberFormatException e) {
System.out.println("Invalid input, discarding message.");
action = 2;
}

// Process the action
System.out.println(msg.SentMessage(action));

// 7. Print details if sent successfully
if (action == 1) {
System.out.println("\n--- Message Details ---");
System.out.println("Message ID: " + msg.getMessageID());
System.out.println("Message Hash: " + msg.getMessageHash());
System.out.println("Recipient: " + msg.getRecipient());
System.out.println("Message: " + msg.getMessage());
}
}

// 6. Total messages sent
Message totalTracker = new Message("", "", 0);
System.out.println("\nTotal messages sent: " + totalTracker.returnTotalMessagess());
}
}
