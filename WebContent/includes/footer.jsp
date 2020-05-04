<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
	  <div id="footer" class="navbar navbar-expand-lg navbar-light bg-light fixed-bottom">
	    <h2 class="navbar-brand">Dildozone</h2>
	    <div class="collapse navbar-collapse">
		    <ul class="navbar-nav mr-auto">
			    <li class="nav-item" v-for="todo in links" >
			      <a class="nav-link">
				      {{ todo.text }}
			      </a>
			    </li>
			</ul>
		    <a class="btn btn-primary">Se connecter</a>
	  	</div>
	  </div>
	
	<script>
	var footer = new Vue({
	  el: "#footer",
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