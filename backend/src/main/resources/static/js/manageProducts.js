let currentPage = 0;
let maxPages = 0;



function more() {
    if(currentPage < maxPages - 1) {
        $.ajax({
            url: "/apiadmin/manageProducts/" + (currentPage + 1),
            type: "get",
            dataType: "json"
        }).done(function (products) {
        	for(let i=0; i<products.length; i++) {
        		let product= products[i];
        		$("tbody").append("<tr id=\"product-"+product.id+"\">\r\n"
        				+ "                            <td scope=\"row\" class=\"product-id\">"+product.id+"</td>\r\n"
        				+ "                            <td class=\"product-name\">"+product.name+"</td>\r\n"
        				+ "                            <td class=\"product-description\">"+product.description+"</td>\r\n"
        				+ "                            <td class=\"product-price\">"+product.price+"</td>\r\n"
        				+ "                            <td class=\"product-stock\">"+product.stock+"</td>\r\n"
        				+ "                            <td class=\"product-size\">"+product.size+"</td>\r\n"
        				+ "                            <td class=\"product-imgs d-none\">\r\n"                                    
    				    + "                            </td>\r\n"
        				+ "                            <td><button class=\"btn btn-primary\" type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#modalAddEditProductData\"\r\n"
        				+ "                                data-id=\""+product.id+"\" onclick=\"edit_product_load($(this).data('id'));\">Edit</button></td>\r\n"
        				+ "                            <td><button data-id=\""+product.id+"\" onclick=\"delete_product($(this).data('id'));\" class=\"btn btn-primary\" type=\"button\">Delete</button></td>\r\n"
        				+ "                          </tr>")
        		for(let j=0; j<product.imgs.length; j++) {
        			let img = product.imgs[j]
        			$(" #product-"+$("tbody")[0].childElementCount +" >.product-imgs").append("<img src=\""+img+"\" class=\"d-none\"/>")
        		}
        	}
        	currentPage++;
            if(currentPage >= maxPages - 1) {
                $("#more-btn").hide();
            }
        });
    }
};

function edit_product_load(id){
    $("#mode").val("EDIT");

    trSelected = "#product-" + id;
    editName = trSelected + " .product-name";
    editDescription = trSelected + " .product-description";
    editPrice = trSelected + " .product-price";
    editStock = trSelected + " .product-stock";
    editSize = trSelected + " .product-size";

    $("#editId").val(id);
    $("#editName").val($(editName)[0].innerHTML);
    $("#editDescription").val($(editDescription)[0].innerHTML);
    $("#editPrice").val($(editPrice)[0].innerHTML);
    $("#editStock").val($(editStock)[0].innerHTML);
    $("#editSize").val($(editSize)[0].innerHTML);
}

function delete_product(id){
    $("#mode").val("DELETE");

    $("#editId").val(id);
    $('#formProducts').submit();
}

function delete_img_fields_form(){
    while($('.img-group').children().length > 1){
        $('.img-group').children()[1].remove();
    }
    $('#addImgIcon').data("lastimg", 1);
}

$("#button-add-product").click(function(){
    $("#mode").val("ADD");
    $("editPassword").attr("required", "true");

    $("#editId").val('');
    $("#editName").val('');
    $("#editDescription").val('');
    $("#editPrice").val('');
    $("#editStock").val('');
    $("#editSize").val('');
    $('#editImg1').val('');
    // for(i = $('.img-group').children().length; i > 0; i--){
    //     $('.img-group').children()[i].val('');
    // }
})

$('#addImgIcon').click(function(){
    lastImg = $(this).data("lastimg");
    nextImg = lastImg + 1;
    $(this).data("lastimg", nextImg);
    htmlInput = '<div class="form-group"><label for="editImg'+ nextImg +'">Image #'+ nextImg +
    '</label> <input type="file" accept="image/*" class="form-control editImg" id="editImg'+ nextImg +'" name="img'+ nextImg +'"></div>';
    $('.img-group').append(htmlInput);
})

function success_alert(){
    $('#manage-products-container').prepend('<div class="alert alert-success" role="alert" id="success-alert"> Operation succeded! </div>');
    setTimeout(function() {
        $('#success-alert').remove();
      }, 3000);
}

function error_alert(){
    $('#manage-products-container').prepend('<div class="alert alert-danger" role="alert" id="error-alert"> Operation failed! </div>');
    setTimeout(function() {
        $('#error-alert').remove();
      }, 3000);
}

$('#formProducts').submit(function(e){
    e.preventDefault();

    var ajaxUrl = '/apiadmin/manageProducts';
    var formElements = this.elements;
    var images = [];
    var imgNum = 1;

    for(i = 7; i < formElements.length; i++){ // i=7 number of elements before images in form
        edit = "#editImg" + imgNum;
        images.push($(edit).val());
        imgNum++;
    }
    // console.log(images);
    // alert(images);
    var idAux = formElements[1].value;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: {
            mode: formElements[0].value,
            id: idAux,
            name: formElements[2].value,
            description: formElements[3].value,
            price: formElements[4].value,
            stock: formElements[5].value,
            size: formElements[6].value,
            imgs: images,
        },
        success: function(data)
        {
            success_alert();
            $('#dismiss-modal-products').click();
            if(data != ""){ //If we added or edited a product
                let arrayIds = [] //Create an array of all ids currently in the page
                $('.product-id').each(function(){
                    arrayIds.push(Number($(this).html()));
                })
                if(arrayIds.indexOf(data.id) != -1){ //If the array is currently in the page, we have edited
                    trSelected = "#product-" + data.id;
                    editName = trSelected + " .product-name";
                    editDescription = trSelected + " .product-description";
                    editPrice = trSelected + " .product-price";
                    editStock = trSelected + " .product-stock";
                    editSize = trSelected + " .product-size";
                    //Images??

                    $(editName).html(data.name);
                    $(editDescription).html(data.description);
                    $(editPrice).html(data.price);
                    $(editStock).html(data.stock);
                    $(editSize).html(data.size);
                    //Images??
                
                }else{ // We have added
                    let addHTML = '<tr id="product-'+data.id+'"> <td scope="row" class="product-id">'+data.id+'</td> <td class="product-name">'+
                    data.name+'</td>' + '<td class="product-description">'+data.description+'</td> <td class="product-price">'+data.price+'</td>' +
                    '<td class="product-stock">'+data.stock+'</td> <td class="product-size">'+data.size+'</td>' +
                    '<td><button class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#modalAddEditProductData" '+
                    'data-id="'+data.id+'" onclick="edit_product_load('+data.id+');"><i class="fa fa-pencil" aria-hidden="true"></i></button></td>' +
                    '<td><button data-id="'+data.id+'" onclick="delete_product('+data.id+');" '+
                    'class="btn btn-primary" type="button"><i class="fa fa-trash" aria-hidden="true"></i></button></td> </tr>';
                    $('tbody').append(addHTML);
                }
            }else{ //Deleted
                trSelected = "#product-" + idAux;
                $(trSelected).remove();
            }
        },
        error: function (data) {
            error_alert();
        },
        always: function(){
            delete_img_fields_form();
        }
    })
})

$(document).ready(function () {
    maxPages = Number($("#max-pages").attr("content"));
});