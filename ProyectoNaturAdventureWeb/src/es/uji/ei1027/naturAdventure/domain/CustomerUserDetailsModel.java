package es.uji.ei1027.naturAdventure.domain;

public class CustomerUserDetailsModel {
	
	private Customer customer;
	private UserDetails userDetails;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
}
