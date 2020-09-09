import React, { Component } from 'react';
import {submitReview} from '../Service/ReviewService';

class ReviewForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            reviewId: 0,
            restaurantId: props.restaurantId,
            userId: 1,
            comment: null,
            noStars: null,
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({ [nam]: val });
    }

    handleSubmit = (event) => {
        console.log('submit');
        submitReview(this.state);
    }

    render() {
        console.log(this.state);
        return (
            <div>
                <div className="form-style">
                    {this.state.id && <h3>{'Changes for ' + this.state.firstName + ' ' + this.state.lastName}</h3>}
                    <form onSubmit={this.handleSubmit}>
                        <div className='row d-inline-flex'>
                            <div className="form-group col">
                                <label className="ReviewLabel" >Leave a Review</label>
                                <input type="text" name="comment" className="form-control" id="comment" value={this.state.comment} onChange={this.handleChange} required />
                            </div>

                            <div className="form-group col">
                                <label className="ReviewLabel" >Leave a Rating</label>
                                <input type="text" name="noStars" className="form-control" id="noStars" value={this.state.noStars} onChange={this.handleChange} required />
                            </div>
                            <input type="submit" value="Submit" className="btn btn-primary" />
                        </div>

                        
                    </form>

                </div>
            </div>
        );
    }
}
export default ReviewForm;