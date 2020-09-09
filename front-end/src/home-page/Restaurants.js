import React, {Component, useState} from 'react';
import RestaurantCard from './RestaurantCard';
import { getRestaurants } from './getInformation';
import SearchBar from './SearchBar';


function Restaurants (props){

const [cards,setCards] = useState([]);

   

        const restaurantList = [];
        if(cards.length === 0){

    
        getRestaurants().then((list) =>
        {
            list.map((item) =>
                restaurantList.push(item)

            
            );
            const cards = restaurantList.map((one) =>
        
            <RestaurantCard 
            restaurantId = {one.restaurantId}
            name = {one.name} 
            address= {one.address}
            city = {one.city}
            state = {one.state}
            zipcode = {one.zipCode}
            description = {one.description}
            foodtype = {one.foodType}
            
            />
        
        );
        
        setCards(cards);
        }

        
        );
    }
       
    return <div> 
        <SearchBar></SearchBar>
        {cards} </div>;

  
    }


export default Restaurants;
