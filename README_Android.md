# Your Task:
Create an Android App "Products" implementing the Design from: https://xd.adobe.com/view/4bb249e7-fd0d-48cb-a571-9b0af10ffbc8-1ee1/
(You can download the required assets from the XD link as well)

# The "Products" App
The App should dynamically fetch products from http://shopmap.wirecube.at/resources/73402840-975a-4f7c-9964-f865d4356d0c/products_simple.json, and display them as cards in a scrolling view.
While the products are being loaded, an indeterminate progress bar and text shows the loading state.
Tapping on one of the products opens a detail screen of the product. Tapping the upper right close button or pressing the system back button navigates back to the product overview.

## Architecture
- minSdkVersion: 21
- MVVM architecture
- LiveData
- View Binding
- kotlin

The rest of the setup/tooling is up to you.
Don't be afraid to use libraries, but use them wisely.

# Data
The data returned from the backend is a json containing product infos. A product object looks like this: 
{
    "id": "abcd1234",
    "title": "Product title",
    "imageURL": "Url to image",
    "price": 1.59,
    "strikePrice": 2.0,
    "description": "Product description"
}

Take care that the strikePrice can be `null`.

# Design
Your implementation doesn't have to be pixel perfect, but try to incorporate the margins, font sizes and colours.
The grid layout should have two columns of products, regardless of screen size.

If you feel that something is not specified in enough detail, you can decide how you want to implement it – just be ready to explain your implementation choices.

# Bonus (optional)
- Fancy transitions/animations where you feel it would improve the UX
- Use ripple effects where applicable
- Use a different endpoint where products are contained within categories. The category titles should be displayed as headers (like the "Obst und Gemüse" title in the design). The endpoint for the products with categories is: http://shopmap.wirecube.at/resources/73402840-975a-4f7c-9964-f865d4356d0c/products_categories.json
- Handle and show errors (No internet connection etc.)
- Unit tests and/or Instrumentation tests for the ViewModels and LiveData

These are some suggestions about optional improvements to the base app. 
If you have another idea, feel free to get creative and surprise us!


**Please note:**
The bonus features really *are* optional, you don't have to do any of them.
Choose the ones you can complete in a reasonable amount of time!

