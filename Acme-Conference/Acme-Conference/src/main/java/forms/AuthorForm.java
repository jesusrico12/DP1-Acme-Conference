package forms;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class AuthorForm {

		// Actor
		private String name;
		private String surname;
		private String middleName;
		private String photo;
		private String phoneNumber;
		private String address;

		private int id;
		// Author
		private String email;

		// UserAccount
		private String username;
		private String oldPassword;
		private String newPassword;
		private String confirmPassword;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@SafeHtml(whitelistType = WhiteListType.NONE)
		@Size(min = 5, max = 32)
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getOldPassword() {
			return oldPassword;
		}

		public void setOldPassword(String oldPassword) {
			this.oldPassword = oldPassword;
		}

		public String getNewPassword() {
			return newPassword;
		}

		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}

		@SafeHtml(whitelistType = WhiteListType.NONE)
		@NotBlank
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@SafeHtml(whitelistType = WhiteListType.NONE)
		@NotBlank
		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		@SafeHtml(whitelistType = WhiteListType.NONE)
		@URL
		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

	
		@SafeHtml(whitelistType = WhiteListType.NONE)
		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		@NotBlank
		@Pattern(regexp = "^([A-z0-9 ]+[ ]<[A-z0-9]+@([A-z0-9]+\\.{0,1})+[A-z0-9]+>|[A-z0-9]+@([A-z0-9]+\\.{0,1})+[A-z0-9]+)$")
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	
	
}
