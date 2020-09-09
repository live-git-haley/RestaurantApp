import React from "react";
import {updateReview, deleteReview, submitReview} from '../Service/ReviewService';

 export const handleDelete = (Id) => {
    let ok = window.confirm('Are you sure you wish to delete your review?\nPress "Ok" to confirm');
    if (ok === true) {
      var review = document.getElementById('review' + Id);
      review.parentNode.removeChild(review);
      deleteReview(Id);
      window.alert('Review has been deleted');
    }
  }

 export const handleUpdate = (review) => {
    updateReview(review);
  }


  export const handleSumbit = (review) => {
    submitReview(review);
  }

