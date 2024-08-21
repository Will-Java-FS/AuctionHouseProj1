import { Image } from "@nextui-org/react";
import { Button } from "@nextui-org/react";
import { UserIcon, TrashIcon, CameraIcon,  } from "@heroicons/react/24/outline";
import { Input } from "@nextui-org/react";
import { useRef, useState } from "react";
import AdminPanel from "./admin-panel";
import axios from "axios";
import { useNavigate } from "react-router-dom";

import { jwtDecode } from 'jwt-decode'

function Account() {
  
  const navigate = useNavigate();
  const FALL_BACK_IMAGE = process.env.REACT_APP_FALL_BACK_IMAGE;
  const FALL_BACK_USER = process.env.REACT_APP_FALL_BACK_USER;
  const FALL_BACK_PASSWORD = process.env.REACT_APP_FALL_BACK_PASSWORD;
  
  const fileInputRef = useRef(null);
  var user = {
    username: FALL_BACK_USER,
    password: FALL_BACK_PASSWORD,
    userimage: FALL_BACK_IMAGE
  }
  const storedToken = localStorage.getItem('token');
         const tokenObject = JSON.parse(storedToken);
        const decodedToken = jwtDecode(storedToken);
         const userId = decodedToken.user_Id;
    axios.get(`http://localhost:8080/user/${userId}`,  {
      headers: {
      Authorization: `Bearer ${tokenObject.accessToken}`
        
      }
    })
    .then(response => {
      if(response.data != null)
      {
        user = response.data
      }

    })


  const changeImgButton = function () {
    document.getElementById("dropzone-file").setAttribute("hidden", `${false}`)
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
      userimage: user.userimage
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

  const handleFileChange = (event) => {
    
         const file = event.target.files[0];
         if (file) {
          const objectUrl = URL.createObjectURL(file);
             
             const storedToken = localStorage.getItem('token');
             const tokenObject = JSON.parse(storedToken);
            const decodedToken = jwtDecode(storedToken);
             const userId = decodedToken.user_Id;
             alert(objectUrl);
                  axios.patch(`http://localhost:8080/user/${userId}`, {
                    username: user.username,
                    password: user.password,
                    userimage: objectUrl
                  }, 
                  {
                    headers: {
                    Authorization: `Bearer ${tokenObject.accessToken}`
                      
                    }
                  })
                  .then(response => {
                    
                    localStorage.setItem('token', JSON.stringify(response.data));
                    navigate('/');
                  })
             };
      
      
  }

  return (
    <div className="App flex flex-col items-center">
      <div className="one-thousand-px flex flex-row items-start justify-center m-10 space-x-10">
        <div className="flex flex-col items-center">
          <Image
            isBlurred
            src={user.userimage}
            alt="NextUI Album Cover"
            className="mb-4 account-image"
            width={300}
            height={300}
          />
          <label htmlFor="dropzone-file" className="flex flex-col items-center justify-center w-full h-32 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-gray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
                                <div className="flex flex-col items-center justify-center pt-5 pb-6">
                                    <svg className="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                                        <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2" />
                                    </svg>
                                    <p className="mb-2 text-sm text-gray-500 dark:text-gray-400"><span className="font-semibold">Click to upload</span> or drag and drop</p>
                                    <p className="text-xs text-gray-500 dark:text-gray-400">JPG or PNG (MAX. 1200x1200px)</p>
                                </div>
                                <input
                                    id="dropzone-file"
                                    type="file"
                                    className="hidden"
                                    accept="image/jpeg, image/png"
                                    ref={fileInputRef}
                                    onChange={handleFileChange}
                                />
                            </label>
          <Button color="success" className="mb-2 w-full max-w-xs" onClick={changeImgButton}>
            Change Image
            <CameraIcon className="w-5 h-5 ml-2" />
          </Button>
        </div>

        <div className="flex flex-col items-center w-full max-w-md">
          <h2 className="text-4xl font-semibold mb-10">
            Hello, {user.username}
          </h2>
          <Input id="un"size="lg" type="text" label="Username" placeholder="Enter your username" className="mb-5 w-full" />
          <Input id="pw"size="lg" type="password" label="Password" placeholder="Enter your password" className="mb-5 w-full" />
          <Button color="success" className="w-full" onClick={changeUserButton}>
            Update Profile
            <UserIcon className="w-5 h-5 ml-2" />
          </Button>
        </div>
      </div>

      {/* Render dynamically if the user is admin */}
      <AdminPanel />
    </div>
  );
}

export default Account;
