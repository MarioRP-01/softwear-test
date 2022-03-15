let token = "";

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

    var ajaxUrl = '/apiadmin/manageUsers';
    var formElements = this.elements;
    var idAux = formElements[1].value;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: {
            mode: formElements[0].value,
            id: idAux,
            username: formElements[2].value,
            password: formElements[3].value,
            email: formElements[4].value,
            name: formElements[5].value,
            lastName: formElements[6].value,
            address: formElements[7].value,
            mobileNumber: formElements[8].value,
            birthdate: formElements[9].value,
            role: formElements[10].value,
            _csrf: token
        },
        success: function(data)
        {
            $('#dismiss-modal-users').click();
            if(data != ""){ //If we added or edited a user
                let arrayIds = [] //Create an array of all ids currently in the page
                $('.user-id').each(function(){
                    arrayIds.push(Number($(this).html()));
                })
                if(arrayIds.indexOf(data.id) != -1){ //If the array is currently in the page, we have edited
                    trSelected = "#user-" + data.id;
                    editUsername = trSelected + " .user-username";
                    editEmail = trSelected + " .user-email";
                    editName = trSelected + " .user-name";
                    editLastName = trSelected + " .user-lastName";
                    editAddress = trSelected + " .user-address";
                    editPhone = trSelected + " .user-phone";
                    editBirthdate = trSelected + " .user-birthdate";
                    editRole = trSelected + " .user-role";

                    $(editUsername).html(data.username);
                    $(editEmail).html(data.email);
                    $(editName).html(data.name);
                    $(editLastName).html(data.lastName);
                    $(editAddress).html(data.address);
                    $(editPhone).html(data.mobileNumber);
                    $(editBirthdate).html(data.birthdate);
                    $(editRole).html(data.role);

                }else{ // We have added
                    let addHTML = '<tr id="user-'+data.id+'"> <td scope="row" class="user-id">'+data.id+'</td> <td class="user-username">'+
                    data.username+'</td>' + '<td class="user-email">'+data.email+'</td> <td class="user-name">'+data.name+'</td>' +
                    '<td class="user-lastName">'+data.lastName+'</td> <td class="user-address">'+data.address+'</td>' +
                    '<td class="user-phone">'+data.mobileNumber+'</td> <td class="user-birthdate">'+data.birthdate+'</td>' +
                    '<td class="user-role">'+data.role+'</td>'+
                    '<td><button class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#modalAddEditUserData" '+
                    'data-id="'+data.id+'" onclick="edit_user_load($(this).data(\'+id+\'));">Edit</button></td>' +
                    '<td><button data-id="'+data.id+'" onclick="delete_user($(this).data(\'+id+\'));" '+
                    'class="btn btn-primary" type="button">Delete</button></td> </tr>';
                    $('tbody').append(addHTML);
                }
            }else{ //Deleted
                trSelected = "#user-" + idAux;
                $(trSelected).remove();
            }
        },
        error: function (data) {
            console.log('An error occurred.');
        }
    })
})

$(document).ready(function () {
    token = $("#csrf-token").attr("content");
})