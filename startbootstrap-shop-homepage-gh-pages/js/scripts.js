$(document).ready(function() {   
    $("#owl-demo").owlCarousel({
    
        navigation : false, // Show next and prev buttons
        slideSpeed : 300,
        paginationSpeed : 400,
        singleItem:true
    
        // "singleItem:true" is a shortcut for:
        // items : 1, 
        // itemsDesktop : false,
        // itemsDesktopSmall : false,
        // itemsTablet: false,
        // itemsMobile : false
    
    });
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
