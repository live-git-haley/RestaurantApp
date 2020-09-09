
import React, {Component} from 'react';

import './App.css';
import LoginModal from './Login/LoginModal.jsx'
import SearchBar from './home-page/SearchBar';

import LoadPageInfo from './RestaurantPage/LoadPageInfo.jsx';
import Restaurants from './home-page/Restaurants';





class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      page: 'home',
      id: localStorage.getItem("restId")
    }

    this.handleNavClick = this.handleNavClick.bind(this);
    this.handleChange = this.handleChange.bind(this);
   
  }

  changePage(page, id) {
    
    switch(page) {
      case 'home':
       
      return <Restaurants/>
     
      case 'restaurants':
        console.log("This is the state" + this.state.id)
       return <LoadPageInfo restaurantId = {this.state.id} userId = {1}/>; 
         
      case 'login':
      return<LoginModal/>
       
      case 'logout':
        return(alert("You are logged out"),<Restaurants/>)

      default:
       return 1;
    }
  }

  handleNavClick(event, newPage) {
    this.setState({
      page: newPage,
      id: localStorage.getItem("restId")

    });  



   
    // prevent reload of page caused by clicking links
    event.preventDefault();
   
  }

  handleChange(newPage,id) {
    this.setState({
      page: newPage,
      id: localStorage.getItem("restId")

    });  

   
   console.log("From Local Storage>>>> " + localStorage.getItem("restId"))

}

  render() {

   return (
    
      <div>
      <nav className="navbar navbar-expand-lg navbar-light bg-light fixed">
        <div class= "container-fluid">
         
            <a class="navbar-brand" href="#" onClick={(event)=>this.handleNavClick(event,'home')}>YelpProject</a>
            
       
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
          </button>
         
          <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div className="navbar-nav mr-auto">
            <a href="#"  className="nav-link" onClick={(event) => this.handleNavClick(event, 'home')}>Home</a>
            <a href="#" className="nav-link" onClick={(event) => this.handleNavClick(event, 'restaurants')}>Restaurants</a>
            </div>

            <div className="nav navbar-nav navbar-right">
             <a href="#"  className="nav-link"  data-toggle="modal" data-target="#myModal" onClick={(event) => this.handleNavClick(event, 'login')}>Login </a>
              
              
              < a href="#"  className="nav-link" onClick={(event) => this.handleNavClick(event, 'logout')}>Logout </a>
          

            
            </div>
            
          

          </div>
      
       </div>
       
       </nav>

       <body>


       

        

       </body>
       <footer > 
       {/* <p> Copyright 2020 &#169; All Rights Reserved.</p> */}
       </footer>


      {this.changePage(this.state.page, this.state.id)}
      

      </div>
      
    );
  }
}

export default App;
