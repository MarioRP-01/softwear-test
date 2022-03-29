$(document).ready(function() {   
    
});

function signIn() {
	var dataObj = {
            username: $("#floatingUsername")[0].value,
            password: $("#floatingPassword")[0].value
	};
    $.ajax({
        url: "/api/auth/login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(dataObj),
    })
}



function logOut() {
	$.ajax({
        url: "/api/auth/logout",
        type: "POST",
        contentType: "application/json",
        data: {
        	
        },
        success: function(result) {
        }
    })
	
}
