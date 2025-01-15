package com.cts.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



//@NamedQuery(name="User.findByEmailId",query="select u from User u where u.email=:email")

public class UserDTO {

	@NotBlank(message = "Name cannot be blank")
		private String name;
		
		@Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
		private String contactNumber;
		
		@NotBlank(message = "Email cannot be blank")
		@Email(message = "Invalid email format")
		private String email;
		
		@Size(min = 4, message = "Password must be at least 4 characters")
		private String password;
		
		@NotBlank(message = "Role cannot be blank")
		private String role;
		
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContactNumber() {
			return contactNumber;
		}

		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
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

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	
}

