import { Input, Textarea, Button } from "@nextui-org/react";
import { useNavigate, useParams } from "react-router-dom"; // Import useParams
import { useState, useEffect } from "react";
import { FireIcon } from "@heroicons/react/24/outline";
import { jwtDecode } from 'jwt-decode';
import axios from "axios";

function UpdateProduct() {
  const { id } = useParams(); // Extract the item ID from the URL
  const navigate = useNavigate();
  const [item, setItem] = useState(null); // Initialize state for the item

  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    const tokenObject = JSON.parse(storedToken);

    axios.get(`http://localhost:8080/item/${id}`, {
        headers: {
            Authorization: `Bearer ${tokenObject.accessToken}`
        }
    })
      .then(response => {
        setItem(response.data); // Set the item data in state
      })
      .catch(error => {
        console.error("Error fetching the item:", error);
      });
  }, [id]); // Dependency array updated to include 'id'

  const update = () => {
    const itemnam = document.getElementById("name").value;
    const itemdsc = document.getElementById("descr").value;
    const itemImg = document.getElementById("image").value;
    const storedToken = localStorage.getItem('token');
    const tokenObject = JSON.parse(storedToken);

    const decodedToken = jwtDecode(tokenObject.accessToken);
    const userId = decodedToken.user_Id;

    axios.patch(`http://localhost:8080/item/${id}`, {
        item_id: id,
        owner: {
            user_id: userId
        },
        itemName: itemnam,
        itemDescription: itemdsc,
        itemImage: itemImg,
        approved: false
      }, {
        headers: {
          Authorization: `Bearer ${tokenObject.accessToken}`
        }
      })
      .then(response => {
        setItem(response.data); // Update the item state with the new data
        navigate("/");
      });
  };

  return (
    <div className="w-full flex justify-center">
      <div className="w-full flex flex-col items-center m-10">
        <h2 className="text-3xl font-semibold mb-5">Update an Item</h2>
        <div className="w-full flex flex-col gap-4">
          <div className="flex w-full flex-col md:flex-nowrap mb-6 md:mb-0 gap-4">
            <Input 
              id="name" 
              type="text" 
              label="Item Name" 
              value={item?.itemName || ''} // Set the value of the input field to the item's name
              onChange={(e) => setItem({...item, itemName: e.target.value})} // Update state on change
            />
            <Textarea
              id="descr"
              label="Description"
              value={item?.itemDescription || ''} // Set the value of the textarea to the item's description
              onChange={(e) => setItem({...item, itemDescription: e.target.value})} // Update state on change
              style={{ height: "250px" }} // Inline style to set height
            />
            <Input 
              id="image" 
              type="text" 
              label="Image URL" 
              value={item?.itemImage || ''} // Set the value of the input field to the item's image URL
              onChange={(e) => setItem({...item, itemImage: e.target.value})} // Update state on change
            />
            <Button size="lg" color="success" className="w-full" onClick={update}>
              Update item
              <FireIcon className="w-5 h-5 ml-2" />
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UpdateProduct;
