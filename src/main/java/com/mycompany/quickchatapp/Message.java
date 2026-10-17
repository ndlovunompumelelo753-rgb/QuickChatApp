package com.mycompany.quickchatapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.json.JSONObject;

public class Message {
private String messageID;
private int numMessagesSent;
private String recipient;
private String message;
private String messageHash;

public static ArrayList<Message> allSentMessages = new ArrayList<>();
public static int totalMessagesSentGlobal = 0;

public Message(String recipient, String message, int numMessagesSent) {
this.messageID = generateMessageID();
this.recipient = recipient;
this.message = message;
this.numMessagesSent = numMessagesSent;
this.messageHash = createMessageHash();
}

private String generateMessageID() {
Random rand = new Random();
long id = (long) (rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
return String.valueOf(id);
}

public boolean checkMessageID() {
return this.messageID.length() <= 10;
}

public String checkRecipientCell() {
if (recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 12) {
return "Cell phone number successfully captured.";
} else {
return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
}
}

public String createMessageHash() {
String firstTwoID = messageID.substring(0, 2);
String[] words = message.split(" ");
String firstWord = words[0];
String lastWord = words[words.length - 1];
return (firstTwoID + ":" + numMessagesSent + ":" + firstWord + lastWord).toUpperCase();
}

public String SentMessage(int choice) {
switch (choice) {
case 1:
allSentMessages.add(this);
totalMessagesSentGlobal++;
return "Message successfully sent.";
case 2:
return "Press 0 to delete the message.";
case 3:
storeMessage();
return "Message successfully stored.";
default:
return "Invalid option.";
}
}

public String printMessages() {
StringBuilder sb = new StringBuilder();
for (Message msg : allSentMessages) {
sb.append("Message ID: ").append(msg.messageID)
.append("\nMessage Hash: ").append(msg.messageHash)
.append("\nRecipient: ").append(msg.recipient)
.append("\nMessage: ").append(msg.message)
.append("\n--------------------\n");
}
return sb.toString();
}

public int returnTotalMessagess() {
return totalMessagesSentGlobal;
}

public void storeMessage() {
JSONObject json = new JSONObject();
json.put("MessageID", this.messageID);
json.put("MessageHash", this.messageHash);
json.put("Recipient", this.recipient);
json.put("Message", this.message);

try (FileWriter file = new FileWriter("messages.json", true)) {
file.write(json.toString() + "\n");
} catch (IOException e) {
System.out.println("An error occurred while saving to JSON.");
}
}

public String getMessageID() { return messageID; }
public String getRecipient() { return recipient; }
public String getMessage() { return message; }
public String getMessageHash() { return messageHash; }
}
