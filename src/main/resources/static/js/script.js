console.log("this is script file");
function showPassword() {
	  var x = document.getElementById("password");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	}




var input = $("#show_hide_password input");
var icon = $("#show_hide_password i");

icon.on('click', function(event) {
	event.preventDefault();

	if (input.attr("type") === "text") {
		input.attr('type', 'password');
		icon.addClass("fa-eye-slash");
		icon.removeClass("fa-eye");

	} else if (input.attr("type") === "password") {
		input.attr('type', 'text');
		icon.removeClass("fa-eye-slash");
		icon.addClass("fa-eye");
	}
});
const toggleSidebar = () =>{
	if($('.sidebar').is(":visible")){
		$(".sidebar").css("display","none");
		$(".content").css("margin-left","0%")
	}
	else{
		
		$(".sidebar").css("display","block");
		$(".content").css("margin-left","20% ")
		
	}
}

function deleteContact(cId){
	swal({
		  title: "Are you sure?",
		  text: "You want to delete this contact!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			  deleteContactRequest(cId);
		
		    /*swal("Your contact  been deleted!", {
		      icon: "success",
		    });*/
		  } else {
		    swal("Your Contact is safe!");
		  }
		});
}
function deleteContactRequest(cId) {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      console.log("Deleted")
	      window.location.reload();
	    }
	  };
	  xhttp.open("POST", "/user/contact/delete/"+cId, true);
	  xhttp.send();
	}


function deleteUser(){
	swal({
		  title: "Are you sure?",
		  text: "You want to delete this Account!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			  deleteUserRequest();
		
		  } else {
		    swal("Your Account  is safe!");
		  }
		});
}
function deleteUserRequest() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      console.log("Deleted")
	      window.location.reload();
	    }
	  };
	  xhttp.open("POST", "/user/delete", true);
	  xhttp.send();
	}

