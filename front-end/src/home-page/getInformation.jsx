
import axios from 'axios';


async function getPhotos(id){
const url = `http://localhost:8070/api/photos/restaurant/` + id;
const photos = await axios.get(`http://localhost:8070/api/photos/restaurant/` + id);

const data = photos.data;

return(data);


}


async function getRestaurants(){


  const restaurants = await axios.get(`http://localhost:8070/api/restaurants`);
  const data = restaurants.data;
  
  return(data);
  
  
  }



export{getRestaurants, getPhotos};
