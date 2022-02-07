![Logo SoftWear](startbootstrap-shop-homepage-gh-pages/assets/full-logo.png)

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

- Home: landing page with products ordered by relevance. This filter can be changed to categories. A user can sign in or sign up from this screen. Clothes can be sent to the cart and wishing list from here.

- Login / register: where a user can create an account or log into it with a mail adress, a nickname and a password.

- Cart: list with products the user wants to buy. To end the transaction the user must be logged in.

- Wish list: list with products the user likes and can add to cart. They can be added from this list.

- Product view: where a single product is displayed and specific details are shown.

- Admin home: where admins can chose what to do (manage categories, manage users, manage products) and a sales graph is displayed.

- Manage categories: where an admin can create and delete categories.

- Manage users: where an admin can ban a user, make a user admin or revoke admin privilege from a user.

- Manage products: where an admin can create and delete products.

## Entities

- User: admin, registered and non registered.
- Product
- Coupon
- Transaction

## Belongings

- Product:
	* Use: all
	* Owner: admin

- Coupon
	* Use: all
	* Owner: admin

- Transaction
	* Use: registered
	* Owner: registered

- Wish list
	* Use: registered and non registered
	* Owner: registered and non registered

## Algorithms

Our app tracks the least favorite products and calculates a discount for them based on their popularity ranking and a minimum price every product has.

## Graphs

Our graph is a bar plot located at the admin panel and allows admins to track the volume of sales and their respective earnings.

## Extra technologies

We've implemented a mail function that sends the user a mail when they make a purchase with the product summary, price and transaction number for posible incidence.
