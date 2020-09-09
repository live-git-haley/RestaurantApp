import React, {useState, useEffect} from 'react';
import axios from 'axios';

export function getUsers(user){

    return axios.get('http://localhost:8080/api/add/users',user);
  
 }
