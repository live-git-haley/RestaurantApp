import React, { useState } from 'react';
import { Row, Col, Container } from 'react-bootstrap';
import '../StyleSheets/ReviewCells.css';
import ReviewForm from '../Forms/ReviewForm'


function RenderReviews(props){
    console.log(props.restaurantId);
    console.log(props.userId);
    const reviews = props.Reviews.map(
        (Review) => 
                <tr id={'review' + props.userId}>                        
                    <td class="col-3 ReviewCells">{Review.userId}</td>
                    <td class="col-3 ReviewCells">{Review.reviewId}</td>
                    <td class="col-3 ReviewCells">{Review.noStars}</td>
                    <td class="col-3 ReviewCells">{Review.comment}</td>
                </tr>)
    
    
            return(
                  <div class="container" id="ReviewContainer">
                  <h2 id="ReviewsTitle">Reviews</h2>
                  <div class="table-responsive">
                        <table class="table table-fixed">
                            <thead>
                                <tr>
                                    <th class="col-3 ReviewsHeadCells">UserId</th>
                                    <th class="col-3 ReviewsHeadCells">reviewId</th>
                                    <th class="col-3 ReviewsHeadCells">Rating</th>
                                    <th class="col-3 ReviewsHeadCells">Review</th>
                                </tr>
                            </thead>
                            <tbody>
                            {reviews}
                            </tbody>
                          
                            <div id="form">
                                <ReviewForm restaurantId={props.restaurantId} userId={props.userId}/>
                            </div>
                
                        </table>
                    </div>
                  </div>
            )


}



export default RenderReviews;