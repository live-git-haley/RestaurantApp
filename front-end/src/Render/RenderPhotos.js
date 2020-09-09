import React from 'react';
import Carousel from 'react-bootstrap/Carousel';

    function RenderPhotos(props){
    console.log(props.Photos);

    const photos = props.Photos.map(
        (Photo) => 
          <Carousel.Item>
            <img
              className="image"
              src={Photo.url}
              alt={Photo.url}
           />
          </Carousel.Item>)


        return(
          <div className="col c-container">
              <div className="vertical">
            <Carousel>
                {photos}
            </Carousel>
            </div>
          </div>
             
        )
}
export default RenderPhotos;