



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


//Load items from server
function loadProduct(callback) {
  $.ajax({
      url: 'http://localhost:8080/products/'
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

$(document).ready(function () {

  loadProduct(function (products) {
      //When items are loaded from server
      for (var i = 0; i < products.length; i++) {
          showProduct(products[i]);
      }
  });
})