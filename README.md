 Now this API will always be publicly available and will never require any extensive configuration process to consume.
 I used two api calls, one to fetch 20 pokemons and another to fetch the data of each pokemon, I listed the pokemons with 
RecyclerView and added the SnapHelper class to help snapping. 
 I used the Hilt lib to perform dependency injection and to perform requests to the libs: Okhttp, Moshi and Retrofit. 
 Finally I used the navigation component to go to the detail screen and pass the selected pokemon as an argument, 
on the detail screen I showed the data and the image using the Glide lib.
