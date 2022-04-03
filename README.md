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
- Index: landing page with products ordered by relevance. This filter can be changed to categories. A user can sign in or sign up from this screen. Clothes can be sent to the cart and wishing list from here.
\
![login](readmeAssets/login.jpg)
- Login / register: where a user can create an account or log into it with a mail adress, a nickname and a password.

\
![cart](readmeAssets/cart.jpg)
- Cart: list with products the user wants to buy. To end the transaction the user must be logged in.

\
![wishlist](readmeAssets/wishlist.jpg)
- Wish list: list with products the user likes and can add to cart. They can be added from this list.

\
![productView](readmeAssets/productView.jpg)
- Product view: where a single product is displayed and specific details are shown. 

\
![adminHome](readmeAssets/adminHome.jpg)
- Admin home: where admins can choose what to do (manage categories, manage users, manage products) and a sales graph is displayed.

\
![manageUsers](readmeAssets/manageUsers.jpg)
- Manage users: where an admin can ban a user, make a user admin or revoke admin privilege from a user.

\
![manageProducts](readmeAssets/manageProducts.jpg)
- Manage products: where an admin can create, modify and delete products. 

\
![manageCoupons](readmeAssets/manageCoupons.jpg)
- Manage coupons: where an admin can create, modify and delete products. 

\
![about](readmeAssets/about.jpg)
- About: some information about us: the brand, our values and the webpage.

\
![purchaseHistory](readmeAssets/purchaseHistory.jpg)
- Purchase history: different purchases with the products information. 

\
![userProfile](readmeAssets/userProfile.jpg)
- User profile: account details of each user.

\
![error](readmeAssets/error.jpg)
- Error: shown when there is an error with loading page.

\
![errorPayment](readmeAssets/errorPayment.jpg)
- Error Payment: shown when there is some error with the product payment.

\
![errorPass](readmeAssets/errorPayment.jpg)
- Error Pass: shown when user wants to change the password and the new ones don´t match.

\
![outOfStock](readmeAssets/outOfStock.jpg)
- Out of stock: reports there is no stock of a product that the user is trying to buy.

\
![successfulPayment](readmeAssets/successfulPayment.jpg)
- Successful Payment: landing message when the purchase is correctly done.





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

## Diagrams
![navitagion_diagram.jpeg](readmeAssets/navitagion_diagram.jpeg)
![entity_diagram](readmeAssets/entity_diagram.jpeg)
![class_diagram](readmeAssets/class_diagram.jpeg)

## Algorithms

Our app tracks the least favorite products and calculates a discount for them based on their popularity ranking and a minimum price every product has.

## Graphs

Our graph is a bar plot located at the admin panel and allows admins to track the volume of sales and their respective earnings.

## Extra technologies

We've implemented a mail function that sends the user a mail when they make a purchase with the product summary, price and transaction number for posible incidence.

## Tools

Link to our Trello: https://trello.com/b/7PcNfIHM/daw

## Execution instructions

Installation: 
	-Install PostgreSQL
	-Install pgAdmin4 (https://www.pgadmin.org/) and introduce a master password.
	
Versions: \
	-Java: 17 \
	-Maven: 4 \
	-SpringBoot: 2.6.3
	
Project compilation:

	-In our github, download the project as a zip.
	-Create a database named softwear.
	-In the IDE, execute
		>cd backend
		>mvn spring-boot:run
	then go to the browser and access https://localhost:8443/
	-To stop the proccess, press Ctr+C, enter and "S".
	
## Heroku

Url: 

User credentials: 

Steps to display with Heroku:

## Docker

Execution instructions: 

Documentation to build Docker image:

## Api REST documentation

	
## Participation

### Adrián

I've been focused on transaction's block and coupon's block but I've been making changes and functions for nearly all classes.

#### Important Commits

Among others, the more outstanding commits are:
* [Wishlist transaction](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/c933892be32c817ce1a583a67637f4fe3f2d3256): Where TransactionView was created.
* [Added purchase history](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/2f0e982e571a3e7c8899af9075fa8f4c2e423b07)
* [AJAX in Cart](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/4a582b5ce05bd9357f166c170a68b5e943e1059c)
* [AJAX in Wishlist](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/1fc2f798529c074b1b42f8a1039fec2e63fff5cb)
* [CSRF enabled (NOT STABLE)](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/e3570a268d569428e396abba310dd30f84f3b3b5)

Those commits are develop versions of the files and they may have errors that have been fixed in other commits. For further information about it, you should check the version history of the file.

#### Important Files

Among others, the more outstanding files are:
* [Transaction.java (com.softwear.webapp5.model.Transaction)](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/model/Transaction.java)
* [Coupon.java (com.softwear.webapp5.model.Coupon)](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/model/Coupon.java)
* [CouponService.java (com.softwear.webapp5.service.CouponService)](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/service/CouponService.java)
* [TransactionController.java (com.softwear.webapp5.controller.TransactionController)](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/controller/TransactionController.java)
* [TransactionRESTController.java (com.softwear.webapp5.controller.TransactionRESTController)](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/controller/TransactionRESTController.java)

Apart from those files, i've been working on a lot of the project files. For further information about them, you should check commits filtering by my user.

### Mario and Ana

Mario and Ana have been working together at the same files. We have created Admin Home graphic with sellings data of database, created access to available sizes of each product, product entity creation and all its main classes to control, creation of example data of data base in databaseinitializer and readme documentation.

#### Important Commits

Among others, the more outstanding commits are:
* [Moustache and data base examples added](https://github.com/CodeURJC-DAW-2021-22/webapp5/tree/0585033f4588811d333048bb0b78fc6fea65040e).
* [Product controller refactorization and GetMapping addition](https://github.com/CodeURJC-DAW-2021-22/webapp5/tree/2a73df1efe6de911879ca7f8973a983fb0119612).
* [Product FindBy ](https://github.com/CodeURJC-DAW-2021-22/webapp5/tree/b015f27360e9f27c9c202fa345a8acf2dade9e79).
* [New transactions on data base and products modification](https://github.com/CodeURJC-DAW-2021-22/webapp5/tree/a3201beb78a9ce4d5700cdd7ee6528afa776d1be).
* [Readme documentation](https://github.com/CodeURJC-DAW-2021-22/webapp5/tree/9a1a97a8776a511b9e1391e8e6f2c6f2554a0d6a).


#### Important Files

Among others, the more outstanding files are:
* Chart.js
* TransactionController.java
* Product.java
* ProductRepository.java
* Databaseinitializer.java

Also other files of the project. For further information about them, you should check commits filtering by our users.

### Jorge

I developed almost all things relationated with users, also i worked on doing the web pageable and on the 
security package but I helped on the develop of other classes and functionalities.

### Important Commits

* [User and BBDD](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/d6e2112438bfaafeef317de0f80da209bef0e202)
* [Logger and Pass encoder](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/1feebc1a7d01ae4f384130e1490a7fcf4298255b)
* [Index pageable](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/a3d66451529d5a6d7ff2f2b5d78045200ea01d7d)
* [Added user entity](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/b6a190f6ace0430ee48492adab1cd7a2f0cbbf9d)
* [Added Sign up](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/a92d4a79f9a1f65f399783ff965692a7fcb1a5b4)

Maybe this commits dont show the final version of the files.

### Important Files

* [ShopUser.java](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/model/ShopUser.java)
* [UserController.java](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/controller/UserController.java)
* [UserService.java](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/service/UserService.java)
* [AdminController.java](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/controller/AdminController.java)
* [WebSecurityConfig.java](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/security/WebSecurityConfig.java)

I also worked on other files but the most time i spent working are on this files.

### Pablo

I developed the managers for the entities in the admin side: manage users, manage products and manage coupons. They all had an HTML, js and AJAX in the frontside
and backend related in AdminController and RestAdminController, as well as in the services and repositories they used.
I also developed the about and error page and divided the HTMLs in header (or adminHeader), body and footer in separate files.
I helped develop the Coupon entity.
I coded the general algorithm to suggest coupons that looks at the least frequently bought products and suggest a coupon for them.

### Important Commits

* [frontend manageCoupons done](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/f93bdb08cbaca30ced7c1362210f17ff4674b91d)
* [restController_done](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/487ff8721a78b0642494e9f976a9b27957a8e800)
* [manageUsers_final](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/cde08a090d23dcb464b20efc4a08abf16f8fddd2)
* [adminController](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/e16b76e17cfbb999d527ee325325e7233d252a4a)
* [divided_views](https://github.com/CodeURJC-DAW-2021-22/webapp5/commit/69ff003e314642a2f7b1e1bca003f4317ae31f74)

### Important Files

* [AdminController.java](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/controller/AdminController.java)
* [RestAdminController.java](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/controller/RestAdminController.java)
* [manageUsers.js](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/java/com/softwear/webapp5/model/ShopUser.java)
* [manageProducts.js](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/resources/static/js/manageProducts.js)
* [manageCoupons.js](https://github.com/CodeURJC-DAW-2021-22/webapp5/blob/main/backend/src/main/resources/static/js/manageCoupons.js)
