# FindComputerWeb

*A simple Web Application for simple eCommerce, where user can buy and sell computer parts.*

For Live Demo can be seen here https://findcomputer-joshua.herokuapp.com/

For Video Demo can be seen here https://github.com/JoshEvan/FindComputerWeb/blob/master/findcomputer-demo.mp4?raw=true

made using:
1. Java Spring Boot
2. PostgreSQL Database
3. ReactJS Typescript Frontend

User Requirements :
* User can register, login, and logout.
* User can update their profile information.
* User can add their items to be sold.
  * Item sold by an user must have Item name, Item description, Item
category, Item Price, and Item owner.
* User can see their own items in the shop.
* User can remove their own items from the shop.
* User can update their own items from the shop.
* User can see other User items.
* User can search for specific other User items in the shop.
* User can search other User items by category in the shop.
  * Categories: RAM, Processor, VGA, Motherboard, Storage.
* User can buy other User items (price doesn’t count, so when you click buy
the item will be automatically bought).
* User can’t buy their own products.
* After an item is bought, that item doesn’t exist anymore

To run this project locally:
1. clone the repo
2. for frontend codes:
  * change directory to `fincomputer-frontend`
  * run `npm install`
  * run `npm install --only=dev`
  * run `npm run be-prod`
3. for backend codes:
  * run through favorite IDE or
    * change directory to `findcomputer-backend/findcomputer/target`
    * run command `java -jar findcomputer-0.0.1-SNAPSHOT.jar`
