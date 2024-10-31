package model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
//    @Id
//    @Column(name = "person_id")
//    private String person_id;
//    
//    @Column(name = "first_name")
//    private String first_name;
//    
//    @Column(name = "last_name")
//    private String last_name;
//    
//    @Enumerated(EnumType.STRING)
//    @Column(name = "GENDER")
//    private Gender gender;
//    
//    @Column(name = "phone_number")
//    private String phone_number;
	    @Id
	    @Column(name = "person_id")
	    private String person_id;
	    @Column(name = "first_name")
	    private String first_name;

	    @Column(name = "last_name")
	    private String last_name;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "GENDER")
	    private Gender gender;

	    @Column(name = "phone_number")
	    private String phone_number;
	    public Person() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Person(String person_id, String first_name, String last_name, Gender gender, String phone_number) {
	super();
	this.person_id = person_id;
	this.first_name = first_name;
	this.last_name = last_name;
	this.gender = gender;
	this.phone_number = phone_number;
}

		public String getPerson_id() {
			return person_id;
		}

		public void setPerson_id(String person_id) {
			this.person_id = person_id;
		}

		public String getFirst_name() {
			return first_name;
		}

		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}

		public String getLast_name() {
			return last_name;
		}

		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

		public Gender getGender() {
			return gender;
		}

		public void setGender(Gender gender) {
			this.gender = gender;
		}

		public String getPhone_number() {
			return phone_number;
		}

		public void setPhone_number(String phone_number) {
			this.phone_number = phone_number;
		}

		
}
