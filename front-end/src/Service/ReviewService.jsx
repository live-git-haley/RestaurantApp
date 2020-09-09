import React, {useState, useEffect} from 'react';
import axios from 'axios';

// custom hook for performing get requests
export function useReviews(props){
    const[data, setData] = useState([]);

    // console.log("Restaurants: ", data[0]);

    const getReviews = async () => {
        await axios.get('http://localhost:8070/api/reviews/' + props.restaurantId)
        .then(res => {
          const reviews = res.data;
     
          if(res.status === 200){
          setData(reviews)};
        })
    
    };

    useEffect(()=>{
        getReviews();} ,[]);
    return (
        
        data

    )
}

// export function useReviews(props){
//     const[data, setData] = useState([]);

//     // console.log("Restaurants: ", data[0]);

//     const getReviews = async () => {
//         await axios.get('http://localhost:8080/api/reviews')
//         .then(res => {
//           const reviews = res.data;
     
//           if(res.status === 200){
//           setData(reviews)};
//         })
    
//     };

//     useEffect(()=>{
//         getReviews();} ,[]);
//     return (
        
//         data

//     )
// }

export function submitReview(review){
    return axios.post("http://localhost:8080/api/add/review", review);

}


export function deleteReview(userId) {
    return axios.delete('http://localhost:8080/api/delete/review/' + userId);
  }

export function updateReview(review) {
    console.log('in the upate service');
    return axios.put('http://localhost:8080/api/update/review', review);
  }