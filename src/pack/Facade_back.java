
package pack;

import java.util.Collection;

import java.lang.Math;

import javax.ejb.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Facade_back
 */
@Singleton
@Path("/")
public class Facade_back extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int taille_token = 22;

	
	@PersistenceContext
    EntityManager em;
		
	static String generateToken() {
		String token = "";
		for (int i = 0; i < taille_token; i++) {
			token += (char) ((char) (Math.random() * ( 126 - 32 )) + 32);
		}
		
		return token;
	}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Facade_back() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	 @POST
	    @Path("/addUser")
	    @Consumes({ "application/json" })
	    public void createUser(User u, Adresse a) {
		 try {
			 Object result1 = em.createQuery("SELECT * FROM TABLE adresses where (Rue.value = '" + a.getRue() +"') AND (Ville.value = '"+a.getVille()+"') AND (Num.value = '"+a.getNum()+"')")
                 .getSingleResult();
		 }catch(NoResultException noresult1) {
			 em.persist(a);
			 try {
				 Object result2 = em.createQuery("SELECT email FROM TABLE users where email.value = '" + u.getEmail()+"'")
	                 .getSingleResult();
			 }catch(NoResultException noresult2) {
				 em.persist(u);
			 }
	    }
		 
		}
	 
	 @POST
	 @Path("/login")
	 @Consumes({"application/json"})
	 @Produces({"application/json"})
	 public String loginUser(User u) {
		try {
			resultMail = em.createQuery("SELECT email FROM TABLE users where email.value = '" + u.getEmail()+"'");
		} catch (NoResultException noResultMail) {
			throw new Exception("Mail not found");
		}			 
		try {
			Object resultPass = em.createQuery("SELECT password FROM TABLE users where password.value = '"
					 + u.getPassword() + "'");			 
		} catch (NoResultException noResultPass) {
			throw new Exception("Wrong password");
		}
		return generateToken();
	 }
}

