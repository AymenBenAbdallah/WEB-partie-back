package pack;

import javax.persistence.*;

@Entity
public class Avis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String commentaire;
	int note;
	
	@ManyToOne
	Produit produit;
	@ManyToOne
	User user;
	
	public int getid() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id =id;
	}
	
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}

}
