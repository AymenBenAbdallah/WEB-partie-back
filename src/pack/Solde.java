package pack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Solde {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)  
	int soldeid;

	
	@OneToOne
	User user;

	public int getId() {
		return soldeid;
	}

	public void setId(int id) {
		this.soldeid = id;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User u) {
		this.user = u;
	}

	
}
