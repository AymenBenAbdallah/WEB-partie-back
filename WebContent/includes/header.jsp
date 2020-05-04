<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
	  <div id="navbar" class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
	    <h2 class="navbar-brand">Dildozone</h2>
	    <div class="collapse navbar-collapse">
		    <ul class="navbar-nav mr-auto">
			    <li class="nav-item" v-for="todo in links" >
			      <a class="nav-link">
				      {{ todo.text }}
			      </a>
			    </li>
			</ul>
		    <a class="btn btn-primary" data-toggle="modal" data-target="#login" v-if="!logged" >Se connecter</a>
		    <a class="btn btn-primary" v-else>{{LoggedUser.nom}} {{LoggedUser.prenom}}</a>
	  	</div>
	  </div>
	  
	  <div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="login" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle">Login</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        ...
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary">Save changes</button>
		      </div>
		    </div>
		  </div>
		</div>
	
	<script>
	var navbar = new Vue({
	  el: "#navbar",
	  data: {
	      links: [
	        {
	          id: 0,
	          text: 'Hello World',
	          page:'/HelloWorld'
	        },
	        {
	          id: 1,
	          text: 'Home',
	          page:'/Home'
	        },
	        {
	          id: 2,
	          text: 'About',
	          page:'/About'
	        },
	        {
	          id: 3,
	          text: 'Contact',
	          page:'/Contact'
	        }
	      ]
	  }
	})
	</script>

</div>