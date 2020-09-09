  
import React from 'react'
import RenderRestaurant from '../Render/RenderRestaurant';
import RenderReviews from '../Render/RenderReviews';
import RenderPhotos from '../Render/RenderPhotos';
import '../StyleSheets/ReviewCells.css';
import '../StyleSheets/RestaurantCells.css';
import {handleDelete, handleUpdate, handleSumbit} from '../Interfaces/ReviewInterface';


function RestaurantPage(props){
  console.log(props.userId);
  console.log(props.restaurantId);
    
    return(
      <div className="padding">
        <div className="container-fluid">
          <div className="row no-gutters">
                <RenderPhotos Photos={props.Photos}/>
              
 
            <div className="col" id="RestaurantInfoCol">
              <RenderRestaurant Restaurant={props.Restaurant}/>
            </div>

          </div>
          <div className="row no-gutters">
          <div className="col" id="ReviewsInfoCol">
          <RenderReviews Reviews={props.Reviews} userId={props.userId} restaurantId={props.restaurantId}
          handleDelete={handleDelete} handleUpdate={handleUpdate} handleSumbit={handleSumbit} 
          />
   

            </div>
          </div>
        </div>
      </div>
    )
}


export default RestaurantPage;