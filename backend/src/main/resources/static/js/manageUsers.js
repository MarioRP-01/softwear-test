function edit_user_load(id){
    $("#mode").val("EDIT");

    trSelected = "#user-" + id;
    editUsername = trSelected + " .user-username";
    editEmail = trSelected + " .user-email";
    editName = trSelected + " .user-name";
    editLastName = trSelected + " .user-lastName";
    editAddress = trSelected + " .user-address";
    editPhone = trSelected + " .user-phone";
    editBirthdate = trSelected + " .user-birthdate";
    editRole = trSelected + " .user-role";

    $("#editId").val(id);
    $("#editUserName").val($(editUsername)[0].innerHTML);
    $("#editEmail").val($(editEmail)[0].innerHTML);
    $("#editName").val($(editName)[0].innerHTML);
    $("#editLastName").val($(editLastName)[0].innerHTML);
    $("#editAddress").val($(editAddress)[0].innerHTML);
    $("#editPhone").val($(editPhone)[0].innerHTML);
    $('#editBirthDate').val($(editBirthdate)[0].innerHTML);
    $("#editRole").val($(editRole)[0].innerHTML);

    $("editPassword").attr("required", "false");
}

function delete_user(id){
    $("#mode").val("DELETE");

    $("#editId").val(id);
    $('#formUsers').submit();
}

$("#button-add-user").click(function(){
    $("#mode").val("ADD");
    $("editPassword").attr("required", "true");

    $("#editId").val('');
    $("#editPassword").val('');
    $("#editUserName").val('');
    $("#editEmail").val('');
    $("#editName").val('');
    $("#editLastName").val('');
    $("#editAddress").val('');
    $("#editPhone").val('');
    $('#editBirthDate').val('');
    $("#editRole").val('');
})

$('#formUsers').submit(function(e){
    e.preventDefault();

    var ajaxUrl = '/apiadmin/manageUsers'; //How do we make this relative to the root?
    var formElements = this.elements;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: {
            mode: formElements[0].value,
            id: formElements[1].value,
            username: formElements[2].value,
            password: formElements[3].value,
            email: formElements[4].value,
            name: formElements[5].value,
            lastName: formElements[6].value,
            address: formElements[7].value,
            mobileNumber: formElements[8].value,
            birthdate: formElements[9].value,
            role: formElements[10].value
        },
        success: function(data)
        {
            console.log('Worked!');
            if(data != null){
                
            }
        },
        error: function (data) {
            console.log('An error occurred.');
            console.log(data);
        }
    })
})