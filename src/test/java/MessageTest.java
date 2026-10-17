package com.mycompany.quickchatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

@Test
public void testMessageLengthSuccess() {
Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
assertTrue(msg.getMessage().length() <= 250, "Message ready to send.");
}

@Test
public void testMessageLengthFailure() {
String longMessage = "A".repeat(260); // 260 characters
assertTrue(longMessage.length() > 250);
int excess = longMessage.length() - 250;
assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
"Message exceeds 250 characters by " + excess + "; please reduce the size.");
}

@Test
public void testRecipientFormatSuccess() {
Message msg = new Message("+27718693002", "Test", 1);
assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
}

@Test
public void testRecipientFormatFailure() {
Message msg = new Message("08575975889", "Test", 1); // No international code
assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
msg.checkRecipientCell());
}

@Test
public void testMessageHashGeneration() {
Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
assertTrue(msg.getMessageHash().contains(":0:HITONIGHT"));
}
}
