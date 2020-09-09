import React, {Component} from 'react'
import Home from '../Home/Home'
import {getUsers} from '../Service/UserService'


class LoginModal extends Component{

   constructor(props) {
       super(props);
       this.state={
           Username:'',
           Password:'',
           Admin: false
          
       };


       this.handleChange = this.handleChange.bind(this);
       this.handleSubmit = this.handleSubmit.bind(this);

   }

   handleChange(event){
       let name=event.target.name;
       let value=event.target.value;

       this.setState({[name]: value});
       console.log(value);
      

   }
   
   handleSubmit(event){

     window.alert("Login Successful!");
             
    window.onload(Home);

      
       event.preventDefault();
   }

    render(){
        return(
            <div>
                 <div className="modal fade" id="myModal">
                     <div className="modal-dialog modal-md">
                         <div className="modal-content">
                             <div class="modal-header">
                                 <h4 className="modal-title">Login</h4>
                                 <button type="button" class="close" data-dismiss="modal">&times;</button>
                             </div>
                             <div class="modal-body">

                             
                                 <form onSubmit={this.handleSubmit}>
                       
                                     
                                 <label htmlFor="uname"><b>Username</b></label>
                                <input className="form-control" onChange={this.handleChange} type="text" placeholder="Enter Username" name="uname" id="uname"required/>

                                    <label htmlFor="psw"><b>Password</b></label>
                                <input className="form-control" onChange={this.handleChange} type="password" placeholder="Enter Password" name="psw" id="psw"required/>
                                       
                                    <label>
                                    <input type="checkbox" name="Admin" value={this.state.Admin}/> Are you an Admin?
                                    
                                    <button type="submit" id="login" class="btn btn-success btn-lg btn-block">Login</button>
                                     </label>
                                 </form>
                                
                             </div>

                         </div>
                     </div>
                 </div>
            </div>
        );
    }

}export default LoginModal;
