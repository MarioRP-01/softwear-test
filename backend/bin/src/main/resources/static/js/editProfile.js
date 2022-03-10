//Load items from server
function loadUser(callback) {
    $.ajax({
        url: 'http://localhost:8080/users/'
    }).done(function (users) {
        console.log('users loaded: ' + JSON.stringify(users));
        callback(users);
    })
}

function showUser(user) {

    $("form")[1][1].value = user.name;
    $("form")[1][2].value = user.lastName;
    $("form")[1][3].value = user.address;
    $("form")[1][4].value = user.email;
    $("form")[1][5].value = user.mobileNumber;
    $("form")[1][6].value = user.birthday;
}

$(document).ready(function () {

    loadUser(function (users) {
        //When items are loaded from server
        for (var i = 0; i < users.length; i++) {
            showUser(users[i]);
        }
    });
})

function openFormChangePassword() {
    document.getElementById("change-password-form").style.display = "block";
}
    
function closeFormChangePassword() {
    document.getElementById("change-password-form").style.display = "none";
}
		