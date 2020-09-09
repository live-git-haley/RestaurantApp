import React from 'react';
import { Row, Col } from 'react-bootstrap';


 function RenderRestaurant(props){
     console.log(props);
     
        return(
            <div class="container-fluid">
                <div class="container" id="Restaurant">
                <h1 class="display-4" id="Head"> {props.Restaurant.name} </h1>
                <h3 class="display-4" id="Body"> {props.Restaurant.foodType} </h3>
                
                <h3 class="display-4" id="Body"> {props.Restaurant.address} </h3>
        
                <h3 class="display-4" id="Body"> {props.Restaurant.description} </h3>
                </div>
            </div>
        )
}

export default RenderRestaurant;