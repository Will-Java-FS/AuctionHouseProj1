import { Image } from "@nextui-org/react";
import { Button } from "@nextui-org/react";
import { UserIcon, TrashIcon, CameraIcon,  } from "@heroicons/react/24/outline";
import { Input } from "@nextui-org/react";
import { useEffect, useRef, useState } from "react";
import AdminPanel from "./admin-panel";
import axios from "axios";
import { useNavigate } from "react-router-dom";

import { jwtDecode } from 'jwt-decode'

function Account() {
  const FALL_BACK_IMAGE = process.env.REACT_APP_FALL_BACK_IMAGE;
  const FALL_BACK_USER = process.env.REACT_APP_FALL_BACK_USER;
  const FALL_BACK_PASSWORD = process.env.REACT_APP_FALL_BACK_PASSWORD;
  var user = {
    username: FALL_BACK_USER,
    password: FALL_BACK_PASSWORD,
    userimage: FALL_BACK_IMAGE,
    admin: false
  }
  const [currUser, setCurrUser] = useState(user);
  const navigate = useNavigate();
  
  
  useEffect(() =>
  {const storedToken = localStorage.getItem('token');
         const tokenObject = JSON.parse(storedToken);
        const decodedToken = jwtDecode(storedToken);
         const userId = decodedToken.user_Id;
      console.log(userId);
    axios.get(`http://localhost:8080/user/${userId}`,  {
      headers: {
      Authorization: `Bearer ${tokenObject.accessToken}`
        
      }
    })
    .then(response => {
      if(response.data != null)
      {
         setCurrUser(response.data);
         console.log(response.data);
         console.log(currUser);
      }

    })}, []
  )

  const changeImgButton = function () {
    const imgURL = document.getElementById("image").value;
    const storedToken = localStorage.getItem('token');
             const tokenObject = JSON.parse(storedToken);
            const decodedToken = jwtDecode(storedToken);
             const userId = decodedToken.user_Id;
    axios.patch(`http://localhost:8080/user/${userId}`, {
      username: currUser.username,
      password: currUser.password,
      userImage: imgURL,
      admin: currUser.admin
    }, 
    {
      headers: {
      Authorization: `Bearer ${tokenObject.accessToken}`
        
      }
    }).then(response => {
      localStorage.setItem('token', JSON.stringify(response.data));
      navigate('/');
    })
  }

  const changeUserButton = function () {
    const un = document.getElementById("un").value;
    const pw = document.getElementById("pw").value;
    const storedToken = localStorage.getItem('token');
             const tokenObject = JSON.parse(storedToken);
            const decodedToken = jwtDecode(storedToken);
             const userId = decodedToken.user_Id;
    axios.patch(`http://localhost:8080/user/${userId}`, {
      username: un,
      password: pw,
      userImage: currUser.userimage,
      admin: currUser.admin
    }, 
    {
      headers: {
      Authorization: `Bearer ${tokenObject.accessToken}`
        
      }
    }).then(response => {
      localStorage.setItem('token', JSON.stringify(response.data));
      navigate('/');
    })
  }



  return (
    <div className="App flex flex-col items-center">
      <div className="one-thousand-px flex flex-row items-start justify-center m-10 space-x-10">
        <div className="flex flex-col items-center">
          <Image
            isBlurred
            src={currUser.userImage}
            alt="NextUI Album Cover"
            className="mb-4 account-image"
            width={300}
            height={300}
          />
          <Input id="image"size="lg" type="text" label="Image URL" placeholder="Enter your image URL" className="mb-5 w-full" />
          <Button color="success" className="mb-2 w-full max-w-xs" onClick={changeImgButton}>
            Change Image
            <CameraIcon className="w-5 h-5 ml-2" />
          </Button>
        </div>

        <div className="flex flex-col items-center w-full max-w-md">
          <h2 className="text-4xl font-semibold mb-10">
            Hello, {currUser.username}
          </h2>
          <Input id="un"size="lg" type="text" label="Username" placeholder="Enter your username" className="mb-5 w-full" />
          <Input id="pw"size="lg" type="password" label="Password" placeholder="Enter your password" className="mb-5 w-full" />
          <Button color="success" className="w-full" onClick={changeUserButton}>
            Update Profile
            <UserIcon className="w-5 h-5 ml-2" />
          </Button>
        </div>
      </div>
      <div hidden={!currUser.admin} id="panel">
      {/* Render dynamically if the user is admin */}
        <AdminPanel />
      </div>
    </div>
  );
}

export default Account;
