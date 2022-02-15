function renderCart(items) {
    
    console.log(items)
    console.log(cartLS.list())

    if(items.length = 0) return //Not null by force
    const $cart = $(".cart")
    const $total = $(".total")
    $cart.innerHTML = items.map((item) => `
        <tr>
          <td>#${item.id}</td>
          <td>${item.name}</td>
          <td>${item.quantity}</td>
          <td style="width: 60px;"> 
            <button type="button" class="btn btn-block btn-sm btn-outline-primary"
              onClick="cartLS.quantity(${item.id},1)">+</button>
          </td>
          <td style="width: 60px;"> 
            <button type="button" class="btn btn-block btn-sm btn-outline-primary"
              onClick="cartLS.quantity(${item.id},-1)">-</button>
          </td>
          <td class="text-right">$${item.price}</td>
          <td class="text-right"><Button class="btn btn-primary" onClick="cartLS.remove(${item.id})">Delete</Button></td>
        </tr>`).join("")
    $total.innerHTML = "$" + cartLS.total()
}

$("#add-cart-1").click(function() {
    
    cartLS.add({
        id: $("#add-cart-1").data("id"), 
        name: $("#add-cart-1").data("name"), 
        price: $("#add-cart-1").data("price")
    })
    console.log(cartLS.list())
  });

cartLS.onChange(renderCart)

$( document ).ready(function() {
    renderCart(cartLS.list())
});