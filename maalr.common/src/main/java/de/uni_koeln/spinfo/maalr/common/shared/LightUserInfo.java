/*******************************************************************************
 * Copyright 2013 Sprachliche Informationsverarbeitung, University of Cologne
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.uni_koeln.spinfo.maalr.common.shared;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LightUserInfo implements Serializable {
	
	private static final long serialVersionUID = -8175500012750444290L;
	
	public static final String SORT_MODIFIED = Constants.Users.LAST_MODIFICATION, 
			SORT_CREATED = Constants.Users.CREATION_DATE,
			SORT_LOGIN = Constants.Users.LOGIN, SORT_FIRST_NAME = Constants.Users.FIRSTNAME, SORT_LAST_NAME = Constants.Users.LASTNAME,
			SORT_EMAIL = Constants.Users.EMAIL, SORT_ROLE=Constants.Users.ROLE;

	private String login, firstName, lastName, email;
	
	private long creationDate, lastModificationDate;
	
	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationTime) {
		this.creationDate = creationTime;
	}

	public long getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(long lastChangeTime) {
		this.lastModificationDate = lastChangeTime;
	}

	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LightUserInfo getCopy() {
		LightUserInfo copy = new LightUserInfo();
		copy.setEmail(getEmail());
		copy.setLogin(getLogin());
		copy.setFirstName(getFirstName());
		copy.setLastName(getLastName());
		copy.setRole(getRole());
		copy.setCreationDate(getCreationDate());
		copy.setLastModificationDate(getLastModificationDate());
		return copy;
	}

	@Override
	public String toString() {
		return "LightUserInfo [login=" + login + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", role="
				+ role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LightUserInfo other = (LightUserInfo) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (role != other.role)
			return false;
		return true;
	}


}
