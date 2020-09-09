import React, {useState, useEffect} from 'react';
import axios from 'axios';

// custom hook for performing get requests
export function useRestaurant(id){
    const[data, setData] = useState([]);

    // console.log("Restaurants: ", data[0]);

    const getRestaurant = async () => {
        await axios.get('http://localhost:8070/api/restaurants/' + id)
        .then(res => {
          const restaurant = res.data;
          if(res.status === 200){
          setData(restaurant)};
        })
    
    };

    useEffect(()=>{
        getRestaurant();} ,[]);
    return (
        
        data

    )
}

