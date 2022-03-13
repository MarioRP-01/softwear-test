function updateWishlistLabels() {
    $(".product").each(function () {
        let id = Number($(
            this).find(".product-id").attr("content"));
        updateWishlistLabel(id);
    })
}

function updateWishlistLabel(productId) {
    $.ajax({
        url: "/wishlist/" + productId,
        type: "get",
        dataType: "json"
    }).done(function (product) {
        let badge = "#product-"+productId+"-fav-badge";
        let icon = "#product-"+productId+"-fav-icon";
        if(product.id == null || product.id === 0) {
            if($(badge).hasClass("bg-dark")) {
                $(badge).removeClass("bg-dark");
                $(badge).removeClass("text-white");
            }
            if(!$(badge).hasClass("bg-white")) {
                $(badge).addClass("bg-white");
                $(badge).addClass("text-black");
            }
            $(icon).hide();
        } else {
            if($(badge).hasClass("bg-white")) {
                $(badge).removeClass("bg-white");
                $(badge).removeClass("text-black");
            }
            if(!$(badge).hasClass("bg-dark")) {
                $(badge).addClass("bg-dark");
                $(badge).addClass("text-white");
            }
            $(icon).show();
        }
    });
}

function toggleWishlistLabel(productName) {
    let ids = [];
    $(".product").each(function () {
        if($(this).find(".product-name").html() === productName) {
            ids.push(Number($(this).find(".product-id").attr("content")));
        }
    });
    for(let index=0; index<ids.length; index++) {
        let productId = ids[index];
        let badge = "#product-" + productId + "-fav-badge";
        let icon = "#product-" + productId + "-fav-icon";
        if ($(badge).hasClass("bg-dark")) {
            $(badge).removeClass("bg-dark");
            $(badge).removeClass("text-white");
            $(badge).addClass("bg-white");
            $(badge).addClass("text-black");
            $(icon).hide();
        } else {
            $(badge).removeClass("bg-white");
            $(badge).removeClass("text-black");
            $(badge).addClass("bg-dark");
            $(badge).addClass("text-white");
            $(icon).show();
        }
    }
}

function wishlist(productId, productName) {
    $.ajax({
        url: "/wishlist/" + productId,
        type: "get",
        dataType: "json"
    }).done(function (product) {
            if(product.id != null && product.id !== 0) {
                removeFromWishlist(productId, productName);
            } else {
                addToWishlist(productId, productName);
            }
            updateWishlistLabel(productId);
        });
}

function addToWishlist(productId, productName) {
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
            toggleWishlistLabel(productName);
        }
    });
}

function removeFromWishlist(productId, productName) {
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
            toggleWishlistLabel(productName);
        }
    });

}

$(document).ready(function () {
    updateWishlistLabels();
});