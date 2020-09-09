

import React, {Component,  useState } from 'react';


import CarouselComp from './CarouselComp';
import LoadPageInfo from '../RestaurantPage/LoadPageInfo';


function RestaurantCard(props) {
    const [id,setId] = useState([]);
   
        return(
       
       <div className="card" id = {props.restaurantId}  onClick={(event)=>  localStorage.setItem('restId', props.restaurantId)}>
        
            <Header title = {props.name}/>
            <Body 
                restaurantId = {props.restaurantId}
                address = {props.address} 
                city= {props.city} 
                state = {props.state}
                zipcode = {props.zipcode} 
                description = {props.description}
                foodtype = {props.foodtype}/>
            <Footer/>

        </div>
      
        );
    }


function Header(props){
    return(
        <div className = "card-header">
            {props.title}

            </div>
    );
    
}


function Body(props){
    return(
        <div className = "card-body">
            <div className = "imgdiv">
            <CarouselComp id = {props.restaurantId} interval = {1000}>
                
                
            </CarouselComp>
            <div className = "container">
                        <p> Address: {props.address}</p>
                        <p> {props.city}, {props.state} {props.zipcode}</p>
                        <p> Description: {props.description}</p>
                        <p> Food Type: {props.foodtype}</p>



                
            </div>
            </div>
            </div>
    );
}







function Footer(props){
    return(
        <div className = "card-footer">
        

            </div>
    );
    
}



export default RestaurantCard;

