package es.uji.ei1027.naturAdventure.domain;

public class Suggestion {
	
	private int suggestion_code;
	private String name;
	private String email;
	private MessageType messageType;
	private String message;
	
	public int getSuggestion_code() {
		return suggestion_code;
	}
	
	public void setSuggestion_code(int suggestion_code) {
		this.suggestion_code = suggestion_code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public MessageType getMessageType() {
		return messageType;
	}
	
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
