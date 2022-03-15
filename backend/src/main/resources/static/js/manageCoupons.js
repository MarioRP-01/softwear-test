$( document ).ready(function() {
    $('i').each(function(){
        if($(this).next().children().hasClass('product-li')){
            $(this).removeClass("d-none")
            $(this).next().css("display", "none")
        }
    })
});

$('.add-coupons-button').click(function(){
    $(this).next().toggle();;
})

function edit_coupon_load(id){
    $("#mode").val("EDIT");

    trSelected = "#coupon-" + id;
    editName = trSelected + " .coupon-name";
    editType = trSelected + " .coupon-type";
    editStartDate = trSelected + " .coupon-startDate";
    editDateOfExpiry = trSelected + " .coupon-dateOfExpiry";
    editMinimum = trSelected + " .coupon-minimum";
    editDiscount = trSelected + " .coupon-discount";
    editAffectedProducts = trSelected + " .coupon-affectedProducts li";

    $("#editId").val(id);
    $("#editName").val($(editName)[0].innerHTML);
    $("#editType").val($(editType)[0].innerHTML);
    $("#editStartDate").val($(editStartDate)[0].innerHTML);
    $("#editDateOfExpiry").val($(editDateOfExpiry)[0].innerHTML);
    $("#editMinimum").val($(editMinimum)[0].innerHTML.trim());
    $('#editDiscount').val($(editDiscount)[0].innerHTML.trim());
    affectedProducts = $(editAffectedProducts)[0].outerText.trim();
    if(affectedProducts === "All") //If it's "All", take it
        $("#editAffectedProducts").val("All");
    else{ //Else, take the IDs
        productsAux = ""
        $("#coupon-" + id + " .coupon-affectedProducts ul li span")
            .each(function(index){
                productsAux += $(this)[0].outerText;
                if($("#coupon-" + id + " .coupon-affectedProducts ul li span").length -1 !== index)
                    productsAux += ","
        })
        $("#editAffectedProducts").val(productsAux);
    }


}

function delete_coupon(id){
    $("#mode").val("DELETE");

    $("#editId").val(id);
    $('#formCoupons').submit();
}

$("#button-add-coupon").click(function(){
    $("#mode").val("ADD");

    $("#editId").val('');
    $("#editName").val('');
    $("#editType").val('');
    $("#editStartDate").val('');
    $("#editDateOfExpiry").val('');
    $("#editMinimum").val('');
    $("#editDiscount").val('');
    $("#editAffectedProducts").val('');
})

$('#formCoupons').submit(function(e){
    e.preventDefault();

    var ajaxUrl = '/apiadmin/manageCoupons';
    var formElements = this.elements;
    var idAux = formElements[1].value;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: {
            mode: formElements[0].value,
            id: idAux,
            name: formElements[2].value,
            type: formElements[3].value,
            startDate: formElements[4].value,
            minimum: formElements[5].value,
            discount: formElements[6].value,
            affectedProducts: formElements[7].value
        },
        success: function(data)
        {
            $('#dismiss-modal-coupons').click();
            if(data != ""){ //If we added or edited a coupon
                let arrayIds = [] //Create an array of all ids currently in the page
                $('.coupon-id').each(function(){
                    arrayIds.push(Number($(this).html()));
                })
                if(arrayIds.indexOf(data.id) != -1){ //If the array is currently in the page, we have edited
                    trSelected = "#coupon-" + data.id;
                    editName = trSelected + " .coupon-name";
                    editType = trSelected + " .coupon-type";
                    editStartDate = trSelected + " .coupon-startDate";
                    editDateOfExpiry = trSelected + " .coupon-dateOfExpiry";
                    editMinimum = trSelected + " .coupon-minimum";
                    editDiscount = trSelected + " .coupon-discount";
                    editAffectedProducts = trSelected + " .coupon-affectedProducts";

                    $(editName).html(data.name);
                    $(editType).html(data.type);
                    $(editStartDate).html(data.startDate);
                    $(editDateOfExpiry).html(data.dateOfExpiry);
                    $(editMinimum).html(data.minimum);
                    $(editDiscount).html(data.discount);
                    $(editAffectedProducts).html(data.affectedProducts); //probably not working
                
                }else{ // We have added
                    let addHTML = '<tr id="coupon-'+data.id+'"> <td scope="row" class="coupon-id">'+data.id+'</td> <td class="coupon-name">'+
                    data.name+'</td>' + '<td class="coupon-type">'+data.type+'</td> <td class="coupon-startDate">'+data.startDate+'</td>' +
                    '<td class="coupon-dateOfExpiry">'+data.dateOfExpiry+'</td> <td class="coupon-minimum">'+data.minimum+'</td>' +
                    '<td class="coupon-discount">'+data.discount+'</td> <td class="coupon-affectedProducts">'+data.affectedProducts+'</td>' +
                    '<td><button class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#modalAddEditCouponData" '+
                    'data-id="'+data.id+'" onclick="edit_coupon_load($(this).data(\'+id+\'));">Edit</button></td>' +
                    '<td><button data-id="'+data.id+'" onclick="delete_coupon($(this).data(\'+id+\'));" '+
                    'class="btn btn-primary" type="button">Delete</button></td> </tr>';
                    $('tbody').append(addHTML);
                }
            }else{ //Deleted
                trSelected = "#coupon-" + idAux;
                $(trSelected).remove();
            }
        },
        error: function (data) {
            console.log('An error occurred.');
        }
    })
})