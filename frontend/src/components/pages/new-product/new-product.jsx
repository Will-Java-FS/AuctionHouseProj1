import { Input, Textarea, Button } from "@nextui-org/react";
import { useRef, useState } from "react";
import { FireIcon } from "@heroicons/react/24/outline";
import axios from "axios";

import { jwtDecode } from 'jwt-decode'

function NewProduct() {
    const [imgLink, setImgLink] = useState('');
    //const [width, setWidth] = useState(0);
    //const [height, setHeight] = useState(0);
    const [item, setItem] = useState(null);
    const fileInputRef = useRef(null);

    /*const handleFileChange = (event) => {
        const file = event.target.files[0];
        if (file) {
            const img = new Image();
            const objectUrl = URL.createObjectURL(file);

            img.onload = function () {
                // Check dimensions
                if (img.width > 1200 || img.height > 1200) {
                    alert('Image dimensions should not exceed 1200px x 1200px.');
                    fileInputRef.current.value = ''; // Clear the input
                }
                else
                {
                    setImgLink(objectUrl);
                    //setHeight(img.height); setWidth(img.width);
                }
                URL.revokeObjectURL(objectUrl); // Clean up the object URL
            };

            img.src = objectUrl;

            // Check file type
            if (!['image/jpeg', 'image/png'].includes(file.type)) {
                alert('Please upload a JPG or PNG file.');
                fileInputRef.current.value = ''; // Clear the input
            }
        }
    };*/

    const upload = () => {
        const itemnam = document.getElementById("name").value;
        const itemdsc = document.getElementById("descr").value;
        const itemImg = document.getElementById("imageURL").value;
        const storedToken = localStorage.getItem('token');
         const tokenObject = JSON.parse(storedToken);
        const decodedToken = jwtDecode(storedToken);
         const userId = decodedToken.user_Id;

        axios.post("http://localhost:8080/item", {
            owner: {
                user_id: userId 
            },
            itemName: itemnam,
            itemDescription: itemdsc,
            itemImage: itemImg
          }, {
            headers: {
            Authorization: `Bearer ${tokenObject.accessToken}`
              
            }
          })
          .then(response => {
            setItem(response.data);
          })
    }

    return (
        <div className="w-full flex justify-center">
            <div className="w-full backdrop:flex flex-col items-center m-10">
                <h2 className="text-3xl font-semibold mb-5">Add an Item</h2>
                <div className="w-full flex flex-col gap-4">
                    <div className="flex w-full flex-col md:flex-nowrap mb-6 md:mb-0 gap-4">
                        <Input id="name" type="text" label="Item Name" placeholder="Enter item name" />
                        <Textarea
                            id = "descr"
                            label="Description"
                            placeholder="Enter your description"
                            style={{ height: "250px" }} // Inline style to set height
                        />
                        <div className="flex items-center justify-center w-full">
                            <Input id="imageURL" type="text" label="Item Name" placeholder="Enter item name" />
                        </div>
                        <Button size="lg" color="success" className="w-full" onClick={upload}>
                            Upload item
                            <FireIcon className="w-5 h-5 ml-2" />
                        </Button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default NewProduct;