$( document ).ready(function() {
	maxPages = Number($("#max-pages").attr("content"));
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

let currentPage = 0;
let maxPages = 0;



function more() {
    if(currentPage < maxPages - 1) {
        $.ajax({
            url: "/apiadmin/manageCoupons/" + (currentPage + 1),
            type: "get",
            dataType: "json"
        }).done(function (coupons) {
        	for(let i=0; i<coupons.length; i++) {
        		let coupon= coupons[i];
        		$("tbody").append("<tr id=\"coupon-"+coupon.id+"\">\r\n"
        				+ "                                <td scope=\"row\" class=\"coupon-id\">"+coupon.id+"</td>\r\n"
        				+ "                                <td class=\"coupon-name\">"+coupon.code+"</td>\r\n"
        				+ "                                <td class=\"coupon-type\">"+coupon.type+"</td>\r\n"
        				+ "                                <td class=\"coupon-startDate\">"+coupon.startDate+"</td>\r\n"
        				+ "                                <td class=\"coupon-dateOfExpiry\">"+coupon.dateOfExpiry+"</td>\r\n"
        				+ "                                <td class=\"coupon-minimum\">"+coupon.minimum+"</td>\r\n"
        				+ "                                <td class=\"coupon-discount\">"+coupon.discount+"</td>\r\n"
        				+ "                                <td class=\"coupon-affectedProducts\">\r\n"
        				+ "                                </td>\r\n"
        				+ "                                \r\n"
        				+ "                                <td><button class=\"btn btn-primary\" type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#modalAddEditCouponData\"\r\n"
        				+ "                                    data-id=\""+coupon.id+"\" onclick=\"edit_coupon_load($(this).data('id'));\"><i class=\"fa fa-pencil\" aria-hidden=\"true\"></i></button></td>\r\n"
        				+ "                                <td><button data-id=\""+coupon.id+"\" onclick=\"delete_coupon($(this).data('id'));\" class=\"btn btn-primary\" type=\"button\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></button></td>\r\n"
        				+ "                            </tr>")
        		if(coupon.affectedProducts.length==0) {
        			$("#coupon-"+$("tbody")[0].childElementCount +"> .coupon-affectedProducts").append("<i class=\"fa fa-plus add-coupons-button d-none\" aria-hidden=\"true\"></i>\r\n"
        					+ "                                    <ul>\r\n"
        					+"  								   </ul>")
        			$("  #coupon-"+$("tbody")[0].childElementCount +" ul").append("<li style=\"list-style: none;\">All</li>")
        		}else {
        			$("#coupon-"+$("tbody")[0].childElementCount +"> .coupon-affectedProducts").append("<i class=\"fa fa-plus add-coupons-button\" data-id="+coupon.id+" aria-hidden=\"true\" onclick=\"hide($(this).data('id'));\" ></i>\r\n"
        					+ "                                    <ul style=\"display:none\">\r\n"
        					+ "									   </ul>")
        			$("  #coupon-"+$("tbody")[0].childElementCount + " i.fa.fa-plus.add-coupons-button.d-none").removeClass("d-none")
        			for(let j=0 ; j<coupon.affectedProducts.length; j++) {
        				let product= coupon.affectedProducts[j];
        				$("  #coupon-"+$("tbody")[0].childElementCount +" ul").append("<li style=\"list-style: none;\" class=\"product-li\">#<span>"+product.id+"</span> - "+product.name+"</li>")
        			}
        			
        		}
        		
        	}
        	currentPage++;
            if(currentPage >= maxPages - 1) {
                $("#more-btn").hide();
            }
        });
    }
};

function hide(id){
	if($("#coupon-"+id+" .coupon-affectedProducts ul").css("display")== "none") {
		$("#coupon-"+id+" .coupon-affectedProducts ul").css("display","")
	}else {
		$("#coupon-"+id+" .coupon-affectedProducts ul").css("display","none")
	}
	
}