import React, { useState } from 'react';
import Carousel from 'react-bootstrap/Carousel'
import { getPhotos } from './getInformation';



function CarouselComp (props){
    const id = props.id;
    
    const [photos,setPhotos] = useState([]);



        const photoList = [];
        if(photos.length === 0){

    
        getPhotos(id).then((list) =>
        {
            list.map((item) =>
                photoList.push(item)

            
            );
           const photos = photoList.map((one) =>

                <Carousel.Item > 
                <img className = "d-block w-100"
                  src = {one.url}
                    alt = {one.alt}
                /> 

                 </Carousel.Item>
            
        
        );
          
        
        setPhotos(photos);
        }

        
        );
    }

       
        return (
            <div className = "car1"> 
                <Carousel interval = {20000}>
                    {photos}
                </Carousel>
            </div>
        );
    }


    


export default CarouselComp;