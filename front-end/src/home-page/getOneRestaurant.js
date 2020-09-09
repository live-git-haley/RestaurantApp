
import React, {Component, useState} from 'react';
import RestaurantCard from './RestaurantCard';
import { getRestaurants } from './getInformation';


function getOneRestaurant (props){

    const [card,setCards] = useState([]);
    
       
    
            const restaurantList = [];
            if(card.length === 0){
    
        
            getRestaurants().then((list) =>
            {
                list.map((item) =>
                    restaurantList.push(item)
    
                
                );
                
        

                const card = restaurantList.filter(found => found.name === "Raising Cane").map(one => (

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
                


                ));

                    
                }
                
            
            );
            
            setCards(card);
            }
    
            
            );
        
           
        return <div> {card} </div>;
    
      
        
    
    
    export default getOneRestaurant;