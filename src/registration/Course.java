package registration;

import java.sql.*;

public class Course {
	private String name;
	private int credits;
	

	public Course(String name, int credits) {
		this.name = name;
		this.credits = credits;
	}
	
	public int getCredits() {
		return credits;
	}

	public String getName() {
		return name;
	}
}