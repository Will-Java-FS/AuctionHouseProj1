import { Button } from "@nextui-org/react";
import { EyeIcon, UsersIcon, TrashIcon, ShoppingBagIcon, BoltSlashIcon, BoltIcon } from "@heroicons/react/24/outline";
import { Tabs, Tab, Card, CardBody } from "@nextui-org/react";
import { Link } from "react-router-dom";
import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import { jwtDecode } from 'jwt-decode';
import { UserPlusIcon } from "@heroicons/react/16/solid";

function AdminPanel() {

    const navigate = useNavigate(); // Hook to navigate programmatically
    const [users, setUsers] = useState([]);
    const [items,setItems] = useState([]);

    useEffect(() => {

        const storedToken = localStorage.getItem('token');
        const tokenObject = JSON.parse(storedToken);
    
        axios.get(`http://localhost:8080/user`, {
          headers: {
            Authorization: `Bearer ${tokenObject.accessToken}`
          }
        })
        .then(response => {
          setUsers(response.data);
        })
        .catch(error => {
            console.error("Error fetching users:", error);//navigate("/404"); // Navigate to the NotFound page
        });
      }, [users,navigate]);



      useEffect(() => {
        const storedToken = localStorage.getItem('token');
        const tokenObject = JSON.parse(storedToken);
    
        axios.get(`http://localhost:8080/item`, {
          headers: {
            Authorization: `Bearer ${tokenObject.accessToken}`
          }
        })
        .then(response => {
          setItems(response.data);
        })
    
        .catch(error => {
            console.error("Error fetching users:", error); // Navigate to the NotFound page
        });
      }, [items]);



      const toggleAdminStatus = async (userId) => {

        const storedToken = localStorage.getItem('token');
        const tokenObject = JSON.parse(storedToken);

        try {
            // Fetch the current user data to get the current isadmin status
            const response = await axios.get(`http://localhost:8080/user/${userId}`,{
                headers: {
                Authorization: `Bearer ${tokenObject.accessToken}`
                  
                }});
            const user = response.data;
    
            if (user) {
                // Toggle the isadmin property
                const updatedUser = {
                    ...user,
                    admin: !user.admin
                };
    
                // Send a PATCH request to update the user
                const patchResponse = await axios.patch(`http://localhost:8080/user/${userId}`, updatedUser,{
                    headers: {
                    Authorization: `Bearer ${tokenObject.accessToken}`
                      
                    }});
    
                if (patchResponse.status === 200) {
                    const updatedUserData = patchResponse.data;
                    console.log('User updated successfully:', updatedUserData);
                    // Optionally, update the local state with the new user data
                    setUsers(prevUsers => 
                        prevUsers.map(u => (u.userId === userId ? updatedUserData : u))
                    );
                } else {
                    console.error('Failed to update user:', patchResponse.statusText);
                }
            }
        } catch (error) {
            console.error('Error toggling admin status:', error);
        }
        
    };

    const toggleItemApproval = async (itemId) => {

        const storedToken = localStorage.getItem('token');
        const tokenObject = JSON.parse(storedToken);

        try {
            // Fetch the current user data to get the current isadmin status
            const response = await axios.get(`http://localhost:8080/item/${itemId}`,{
                headers: {
                Authorization: `Bearer ${tokenObject.accessToken}`
                  
                }});
            const item = response.data;
    
            if (item) {
                // Toggle the isadmin property
                const updatedItem = {
                    ...item,
                    approved: !item.approved
                };
    
                // Send a PATCH request to update the user
                const patchResponse = await axios.patch(`http://localhost:8080/item/${itemId}`, updatedItem,{
                    headers: {
                    Authorization: `Bearer ${tokenObject.accessToken}`
                      
                    }});
    
                if (patchResponse.status === 200) {
                    const updatedItemData = patchResponse.data;
                    console.log('User updated successfully:', updatedItemData);
                    // Optionally, update the local state with the new user data
                    setUsers(prevItem => 
                        prevItem.map(i => (i.itemId === itemId ? updatedItemData : i))
                    );
                } else {
                    console.error('Failed to update item:', patchResponse.statusText);
                }
            }
        } catch (error) {
            console.error('Error toggling item status:', error);
        }
        
    };

    const deleteUser = async (userId) => {

        const storedToken = localStorage.getItem('token');
        const tokenObject = JSON.parse(storedToken);

        try {
            // Fetch the current user data to get the current isadmin status
            const response = await axios.get(`http://localhost:8080/user/${userId}`,{
                headers: {
                Authorization: `Bearer ${tokenObject.accessToken}`
                  
                }});
            const user = response.data;
    
            if (user) {
               
                // Send a DELETE request to update the user
                const patchResponse = await axios.delete(`http://localhost:8080/user/${userId}`,{
                    headers: {
                    Authorization: `Bearer ${tokenObject.accessToken}`
                      
                    }});
    
                if (patchResponse.status === 200) {
                   
                    console.log('User deleted successfully:');
                    // Optionally, update the local state with the new user data
                    
                } else {
                    console.error('Failed to update user:', patchResponse.statusText);
                }
            }
        } catch (error) {
            console.error('Error deleting user', error);
        }
        
    };

    const deleteItem = async (itemId) => {

        const storedToken = localStorage.getItem('token');
        const tokenObject = JSON.parse(storedToken);

        try {
          
            const response = await axios.get(`http://localhost:8080/item/${itemId}`,{
                headers: {
                Authorization: `Bearer ${tokenObject.accessToken}`
                  
                }});
            const item = response.data;
    
            if (item) {
               
                // Send a DELETE request to update the user
                const patchResponse = await axios.delete(`http://localhost:8080/item/delete/${itemId}`,{
                    headers: {
                    Authorization: `Bearer ${tokenObject.accessToken}`
                      
                    }});
    
                if (patchResponse.status === 200) {
                   
                    console.log('Item deleted successfully:');
                    // Optionally, update the local state with the new user data
                    
                } else {
                    console.error('Failed to update item:', patchResponse.statusText);
                }
            }
        } catch (error) {
            console.error('Error deleting item', error);
        }
        
    };

    return (
        <div className="one-thousand-px flex flex-col items-center m-10">
            <h2 className="text-2xl font-semibold mb-10">Admin Panel</h2>
            <div className="flex w-full flex-col">
                <Tabs aria-label="Options" items={Tabs}>
                    <Tab
                        key="users"
                        title={
                            <div className="flex items-center space-x-2">
                                <UsersIcon className="w-5 h-5 ml-2" />
                                <span>Users</span>
                            </div>
                        }
                    >
                        <Card>
                            <CardBody>
                                <ul className="mt-2">
                                    {users.map((user, index) => (
                                        <li key={user.user_id} className="mb-2">
                                            <div className="flex justify-between items-center">
                                                <span>{user.username}</span>
                                                <div className="flex space-x-2">
                                                    <Button
                                                        color={user.admin ? "success" : "warning"}
                                                        className="w-36 flex justify-between items-center"
                                                        onClick={() => toggleAdminStatus(user.user_id)} // Attach the click handler
                                                    >
                                                        <span className="flex-grow text-left">
                                                            {user.admin ? "Make User" : "Make Admin"}
                                                        </span>
                                                        {user.isadmin ? (
                                                            <BoltSlashIcon className="w-5 h-5 ml-2" />
                                                        ) : (
                                                            <BoltIcon className="w-5 h-5 ml-2" />
                                                        )}
                                                    </Button>

                                                    <Button color="danger" onClick={() => deleteUser(user.user_id)}>
                                                        Delete
                                                        <TrashIcon className="w-5 h-5 ml-2" />
                                                    </Button>

                                                </div>
                                            </div>
                                            {index < users.length - 1 && <hr className="my-2 border-t border-gray-200" />}
                                        </li>
                                    ))}
                                </ul>
                            </CardBody>
                        </Card>


                    </Tab>
                    <Tab
                        key="items"
                        title={
                            <div className="flex items-center space-x-2">
                                <ShoppingBagIcon className="w-5 h-5 ml-2" />
                                <span>Items</span>
                            </div>
                        }
                    >
                        <Card>
                            <CardBody>
                                <ul className="mt-2">
                                    {items.map((item, index) => (
                                        <li key={item.item_id} className="mb-2">
                                            <div className="flex justify-between items-center">
                                                <span>{item.itemName}</span>
                                                <div className="flex space-x-2">
                                                    <Button
                                                        color={item.approved ? "warning" : "success"}
                                                        className="w-36 flex justify-between items-center"
                                                        onClick={() => toggleItemApproval(item.item_id)}
                                                    >
                                                        <span className="flex-grow text-left">
                                                            {item.approved ? "Disapprove" : "Approve"}
                                                        </span>
                                                        {item.approved ? (
                                                            <BoltSlashIcon className="w-5 h-5 ml-2" />
                                                        ) : (
                                                            <BoltIcon className="w-5 h-5 ml-2" />
                                                        )}
                                                    </Button>
                                                    <Button as={Link} to={`/item/${item.item_id}`} color="primary">
                                                        View Item
                                                        <EyeIcon className="w-5 h-5 ml-2" />
                                                    </Button>
                                                    <Button color="danger" onClick={() => deleteItem(item.item_id)}>
                                                        Delete
                                                        <TrashIcon className="w-5 h-5 ml-2" />
                                                    </Button>
                                                </div>
                                            </div>
                                            {index < items.length - 1 && <hr className="my-2 border-t border-gray-200" />}
                                        </li>
                                    ))}
                                </ul>
                            </CardBody>
                        </Card>

                    </Tab>
                </Tabs>
            </div>
        </div>
    );
}

export default AdminPanel;