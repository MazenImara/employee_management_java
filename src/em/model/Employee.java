package em.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


// MOHAMAD Code  

@Entity

@Table(name="employee")

public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="id")
	public int id;
	
 
    @Column(name="name")
	public String name;

    
    @Column(name="email")
	public String email;


    @Column(name="password")
	public String password;

    
    
    @Column(name="address")
	public String address;

   
    @Column(name="phone")
	public long phone;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	@OneToMany(mappedBy = "employee")
	public List<Task> tasks;
	
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
    public void validate(Object target, Errors errors) {
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "field.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email", "field.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address", "field.required");
    
       
       /*
         int  MINIMUM_PASSWORD_LENGTH=6;
       Employee employee = (Employee) target;
       if (employee.getPassword() != null  && employee.getPassword().trim().length() < MINIMUM_PASSWORD_LENGTH) {
          errors.rejectValue("password", "field.min.length",
          new Object[]{Integer.valueOf(MINIMUM_PASSWORD_LENGTH)},
          "The password must be at least [" + MINIMUM_PASSWORD_LENGTH + "] characters in length.");
       }
       */
    }

}

