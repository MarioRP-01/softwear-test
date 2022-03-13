![Logo SoftWear](startbootstrap-shop-homepage-gh-pages/assets/full-logo-white-bg.jpeg)

# Softwear

Online clothing store web app. We sell our own products, made with the highest quality fabrics and cloths, as well as the best brands on the market. We promote reducing clothing waste, so everything on sale is guaranteed to last more than two years, thus fighting climate change.

Based on minimalistic design bringing user experience upfront.

We provide a catalog that can be filtered through different categories, allowing users to access all products intuitively with ease. Furthermore, users can save a wish list for future transactions. Once the shopping cart is done, a checkout may be fullfilled.


## Members

|            **Name**           |             **Mail**             |  **Github User** |
|:-----------------------------:|:--------------------------------:|:----------------:|
| Pablo Pinillos Trigueros      | p.pinillos.2019@alumnos.urjc.es  | PabloPinillos    |
| Adrián Sánchez Guirado        | a.sanchezgu.2019@alumnos.urjc.es | a-sanchezgu-2019 |
| Jorge Esteban Pérez           | j.esteban.2019@alumnos.urjc.es   | JEstebanPerez    |
| Ana Cristina Acosta Hernández | ac.acosta.2017@alumnos.urjc.es   | AnaAcostaH       |
| Mario Rojas Padrón            | m.rojas.2019@alumnos.urjc.es     | MarioRP-01       |


## Screens

![index](readmeAssets/index1.jpg)
![index](readmeAssets/index2.jpg)
- Index: landing page with products ordered by relevance. This filter can be changed to categories. A user can sign in or sign up from this screen. Clothes can be sent to the cart and wishing list from here. Allows to navigate to About, Cart and Login.  

\
![login](readmeAssets/login.jpg)
- Login / register: where a user can create an account or log into it with a mail adress, a nickname and a password. Allows to navigate to Home, About and Cart.

\
![cart](readmeAssets/cart.jpg)
- Cart: list with products the user wants to buy. To end the transaction the user must be logged in. Allows to navigate to Home, About and Login.

\
![wishlist](readmeAssets/wishlist.jpg)
- Wish list: list with products the user likes and can add to cart. They can be added from this list. Allows to navigate to Home, About, Cart and Login.

\
![productView](readmeAssets/productView.jpg)
- Product view: where a single product is displayed and specific details are shown. Allows to navigate to Home, About, Shop and Login.

\
![adminHome](readmeAssets/adminHome.jpg)
- Admin home: where admins can choose what to do (manage categories, manage users, manage products) and a sales graph is displayed. Allows to navigate to Home, Manage Products and Manage Users.

\
![manageUsers](readmeAssets/manageUsers.jpg)
- Manage users: where an admin can ban a user, make a user admin or revoke admin privilege from a user. Allows to navigate to  Home, Admin Home and Manage Products.

\
![manageProducts](readmeAssets/manageProducts.jpg)
- Manage products: where an admin can create and delete products. Allows to navigate to Home, Admin Home and Manage Users.

\
![about](readmeAssets/about.jpg)
- About: some information about us: the brand, our values and the webpage. Allows to navigate to Home, Cart and Login.

\
![purchaseHistory](readmeAssets/purchaseHistory.jpg)
- Purchase history: different purchases with the products information. Allows to navigate to Home, About, Cart and Login.

\
![userProfile](readmeAssets/userProfile.jpg)
- User profile: account details of each user. Allows to navigate to Home, About and Cart.

\
![error](readmeAssets/error.jpg)
- Error: shown when there is an error with loading page. Allows to navigate to Home, About, Cart and Login.

\
![errorPayment](readmeAssets/errorPayment.jpg)
- Error Payment: shown when there is some error with the product payment. Allows to navigate to Home, About, Cart and Login.

\
![outOfStock](readmeAssets/outOfStock.jpg)
- Out of stock: reports there is no stock of a product that the user is trying to buy. Allows to navigate to Home, About, Cart and Login.

\
![successfulPayment](readmeAssets/successfulPayment.jpg)
- Successful Payment: landing message when the purchase is correctly done. Allows to navigate to Home, About, Cart and Login.





## Entities

- User: admin, registered and non registered.
- Product
- Coupon
- Transaction

Property list of each entity:

### User
- rol: USER, ADMIN
- name
- lastName
- eMail
- password
- address
- phoneNumber
- birthDay
- transactionHistory
- userid
### Product
- price
- name
- description
- size
- stock
- brand
- supplier
- placeMade
- manufacturingDate
- productId

### Coupon
- code
- type
- startDate
- dateOfExpiry
- minimumForAplication
- discount
- affectedProducts
- couponId
### Transaction
- type: WISHLIST, TRANSACTION, CART
- userID
- usedCoupons
- Date
- ProductList
- transactionId
## Belongings

* Product:
	* Use: all
	* Owner: admin

* Coupon
	* Use: registered
	* Owner: admin

* Transaction
	* Use: registered
	* Owner: registered

## Algorithms

Our app tracks the least favorite products and calculates a discount for them based on their popularity ranking and a minimum price every product has.

## Graphs

Our graph is a bar plot located at the admin panel and allows admins to track the volume of sales and their respective earnings.

## Extra technologies

We've implemented a mail function that sends the user a mail when they make a purchase with the product summary, price and transaction number for posible incidence.

## Tools

Link to our Trello: https://trello.com/b/7PcNfIHM/daw
