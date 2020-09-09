
import React, { Component } from 'react';
import { getOneRestaurant } from '../Service/RestaurantService';

class SearchBar extends Component {


    constructor(props){
        super(props);
        this.state = {
            searched: ''
        };
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({
            searched: event.target.value
        });
    }



    render() {
        return (
            <div className = "search-bar"  >
            <div className="input-group md-form form-sm form-1 pl-0">
                <div className="input-group-prepend">
                <span className="input-group-text purple lighten-3" id="basic-text1"><i className="fas fa-search text-white"
                 aria-hidden="true"></i></span>
                </div>
            <input className="form-control my-0 py-1" value = {this.state.searched} onChange={this.handleChange} type="text" placeholder="Search" aria-label="Search">
                </input>
                
            
                </div>

            <br></br>
         
           
            </div>
        );
    }
}

export default SearchBar;