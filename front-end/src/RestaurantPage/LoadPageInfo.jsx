import React from 'react';
import RestaurantPage from './RestaurantPage';
import {handleDelete, handleUpdate, handleSumbit} from '../Interfaces/ReviewInterface';
import {useReviews} from '../Service/ReviewService';
import {useRestaurant} from '../Service/RestaurantService';
import {usePhotos} from '../Service/PhotoService';




function LoadPageInfo(props) {
    const restaurant = useRestaurant(props.restaurantId);

    const photos = usePhotos(props.restaurantId);
    
    const reviews = useReviews(props.restaurantId);
 
    // if(restaurant){
    //     console.log(restaurant);
    // }

    // if(photos[0]){
    //     console.log(photos[0]);
    // }

    // if(reviews){
    //     console.log(reviews);
    // }


        console.log(props.restaurantId);
        console.log(props.userId);

    return(
        <div>
            {/* {loadingPhotos && <p>loadingPhotos...</p>}
            {loadingReviews && <p>loadingReviews...</p>}
            {loadingRestaurant && <p>loadingRestaurant...</p>} */}
            {reviews && photos && restaurant && <RestaurantPage Photos={photos} Restaurant={restaurant} Reviews={reviews} userId={props.userId} restaurantId={props.restaurantId}/>}
    
        </div>
    )
}

export default LoadPageInfo;