import React, {useState, useEffect} from 'react';
import axios from 'axios';

// custom hook for performing get requests
export function usePhotos(id){
    const[data, setData] = useState([]);

    // console.log("Restaurants: ", data[0]);

    const getPhotos = async () => {
        await axios.get('http://localhost:8070/api/photos/restaurant/' + id )
        .then(res => {
          const photos = res.data;
          console.log(photos);

          if(res.status === 200){
          setData(photos)};
        })
    
    };

    useEffect(()=>{
        getPhotos();} ,[]);
    return (
        
        data

    )

}



