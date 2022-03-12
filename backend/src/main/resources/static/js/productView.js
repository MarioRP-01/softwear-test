let inWishlist;
let size;

//Load items from server
function loadProduct(callback) {
  $.ajax({
      url: 'https://localhost:8443/products/'
  }).done(function (products) {
      console.log('products loaded: ' + JSON.stringify(products));
      callback(products);
  })
}

function showProduct(product) {

	$("#product-featured")[0].src= product.img_routes[0];
	$("img.product-thumbnail")[0].src= product.img_routes[0]; //FOR RECORRER LAS RUTAS
	$("h1.softFont")[0].innerHTML = product.name;
	$("h3")[0].innerHTML = "$"+ product.price;
	$("p.product-description")[0].innerHTML = product.description;
	
}

function getProduct(name, size, callback) {
	$.ajax({
		url: "/product",
		type: "get",
		data: {
			name: $(".product-name").html(),
			size: size
		},
		dataType: "json"
	}).done(function (product){
		callback(product)
	});
}

function updateCartItemsNumber(cart) {
	let number = 0;
	if(cart != null) {
		for(const entry of cart.transactionEntries) {
			number += entry.quantity;
		}
	}
	$(".cart-items-number").html(number);
}

function updateStock() {
	getProduct($(".product-name").html(),size, function (product){
		let product_stock = $(".product-stock")
		let row = product_stock.parent()
		product_stock.remove()
		if(product.stock > 0) {
			if(product.stock > 20) {
				row.append("<div class=\"product-stock col-6 rounded p-1 text-center text-white fs-4 bg-success softFont\">In Stock</div>")
			} else {
				row.append("<div class=\"product-stock col-6 rounded p-1 text-center fs-5 bg-warning softFont\">Low Stock: Only " + product.stock + " units available</div>")
			}
			if($("#cart-btn").hasClass("disabled")) {
				$("#cart-btn").removeClass("disabled");
			}
		} else {
			row.append("<div class=\"product-stock col-6 rounded p-1 text-center text-white fs-4 bg-danger softFont\">Out of Stock</div>")
			if(!$("#cart-btn").hasClass("disabled")) {
				$("#cart-btn").addClass("disabled");
			}
		}
	})
}

function addToCart(id, quantity) {
	$.ajax({
		url: "/cart",
		type: "post",
		data:{
			action: "add",
			productId: id,
			quantity: quantity
		},
		dataType: "json"
	}).done(function (cart) {
		if(cart.id !== 0){
			updateCartItemsNumber(cart);
		}
	});
}

function wishlist(productId, productName) {
	if(inWishlist) {
		removeFromWishlist(productName);
	} else {
		addToWishlist(productId);
	}
}

function addToWishlist(productId) {
	$.ajax({
		url: "/wishlist",
		type: "post",
		data: {
			action: "add",
			productId: productId
		},
		dataType: "json"
	}).done(function (wishlist) {
		if(wishlist.id !== 0) {
			inWishlist = true
			let fav_btn = $("#fav-btn");
			fav_btn.addClass("bg-dark");
			fav_btn.addClass("text-white");
		}
	});

}

function removeFromWishlist(productName) {
	$.ajax({
		url: "/wishlist",
		type: "post",
		data: {
			action: "delete",
			productName: productName
		},
		dataType: "json"
	}).done(function (wishlist) {
		if(wishlist.id !== 0) {
			inWishlist = false;
			let fav_btn = $("#fav-btn");
			fav_btn.removeClass("bg-dark");
			fav_btn.removeClass("text-white");

		}
	});

}

function changeSize(new_size) {
	$(".product-size").each(function () {
		if($(this).hasClass("bg-dark")) {
			$(this).removeClass("bg-dark");
		}
		if($(this).hasClass("text-white")) {
			$(this).removeClass("text-white");
		}
	})
	$("#btn-size-"+new_size).addClass("bg-dark");
	$("#btn-size-"+new_size).addClass("text-white");
	size = new_size;
	updateStock();
}

$(document).ready(function () {

	loadProduct(function (products) {
		//When items are loaded from server
		for (var i = 0; i < products.length; i++) {
			showProduct(products[i]);
		}
	});

	let thumbnails = document.getElementsByClassName('product-thumbnail')

	let activeImages = document.getElementsByClassName('product-active')

	for (var i=0; i < thumbnails.length; i++){

		thumbnails[i].addEventListener('mouseover', function(){
			console.log(activeImages)

			if (activeImages.length > 0){
				activeImages[0].classList.remove('product-active')
			}


			this.classList.add('product-active')
			document.getElementById('product-featured').src = this.src
		})
	}

	let buttonRight = document.getElementById('product-slideRight');
	let buttonLeft = document.getElementById('product-slideLeft');

	buttonLeft.addEventListener('click', function(){
		document.getElementById('product-slider').scrollLeft -= 180
	})

	buttonRight.addEventListener('click', function(){
		document.getElementById('product-slider').scrollLeft += 180
	})

	inWishlist = $("#fav-btn").hasClass("bg-dark")
	$("#cart-form").submit(function (event) {
		event.preventDefault();
		getProduct($(".product-name").html(), size, function (product){
			let values = {}
			$.each($('#cart-form').serializeArray(), function (i, field) {
				values[field.name] = Number(field.value);
			});
			addToCart(product.id, values["quantity"]);
		})
	})
	size = $(".product-size").html();
	changeSize(size);
})