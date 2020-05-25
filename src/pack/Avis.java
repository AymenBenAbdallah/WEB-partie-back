package pack;

import javax.persistence.*;

@Entity
public class Avis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int avis;
	String commentaire;
	int note;
	
	@ManyToOne
	Produit produit;
	
	public int getAvis() {
		return avis;
	}
	public void setAvis(int avis) {
		this.avis = avis;
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
