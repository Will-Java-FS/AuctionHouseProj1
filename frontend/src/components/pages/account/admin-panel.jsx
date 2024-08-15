import { Button } from "@nextui-org/react";
import { EyeIcon, UsersIcon, TrashIcon, ShoppingBagIcon, BoltSlashIcon, BoltIcon } from "@heroicons/react/24/outline";
import { Tabs, Tab, Card, CardBody } from "@nextui-org/react";
import { Link } from "react-router-dom";

function AdminPanel() {

    // Dummy content
    const users = [
        {
            userId: 1,
            username: 'turtle',
            isadmin: false
        },
        {
            userId: 2,
            username: 'woodpecker',
            isadmin: false
        },
        {
            userId: 3,
            username: 'capybara',
            isadmin: false
        },
        {
            userId: 4,
            username: 'elephant',
            isadmin: true
        },
        {
            userId: 5,
            username: 'seagull',
            isadmin: false
        },
        {
            userId: 6,
            username: 'cobra',
            isadmin: false
        },
        {
            userId: 7,
            username: 'squirrel',
            isadmin: false
        },
        {
            userId: 8,
            username: 'llama',
            isadmin: true
        },
        {
            userId: 9,
            username: 'lion',
            isadmin: false
        },
        {
            userId: 10,
            username: 'coala',
            isadmin: false
        },
        {
            userId: 1,
            username: 'panda',
            isadmin: false
        }
    ];

    const items = [
        {
            itemId: 1,
            itemName: 'Cool Cat',
            itemIsApproved: true,
        },
        {
            itemId: 2,
            itemName: 'Fancy Car',
            itemIsApproved: true,
        },
        {
            itemId: 3,
            itemName: "Poor People's Plane",
            itemIsApproved: true,
        },
        {
            itemId: 4,
            itemName: 'Totally Legal Gun',
            itemIsApproved: true,
        },
        {
            itemId: 5,
            itemName: 'Absolutely Illegal Chickens',
            itemIsApproved: false,
        }
    ];

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
                                        <li key={user.userId} className="mb-2">
                                            <div className="flex justify-between items-center">
                                                <span>{user.username}</span>
                                                <div className="flex space-x-2">
                                                    <Button
                                                        color={user.isadmin ? "warning" : "success"}
                                                        className="w-36 flex justify-between items-center"
                                                    >
                                                        <span className="flex-grow text-left">
                                                            {user.isadmin ? "Make User" : "Make Admin"}
                                                        </span>
                                                        {user.isadmin ? (
                                                            <BoltSlashIcon className="w-5 h-5 ml-2" />
                                                        ) : (
                                                            <BoltIcon className="w-5 h-5 ml-2" />
                                                        )}
                                                    </Button>
                                                    <Button color="danger">
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
                                        <li key={item.itemId} className="mb-2">
                                            <div className="flex justify-between items-center">
                                                <span>{item.itemName}</span>
                                                <div className="flex space-x-2">
                                                    <Button
                                                        color={item.itemIsApproved ? "warning" : "success"}
                                                        className="w-36 flex justify-between items-center"
                                                    >
                                                        <span className="flex-grow text-left">
                                                            {item.itemIsApproved ? "Disapprove" : "Approve"}
                                                        </span>
                                                        {item.itemIsApproved ? (
                                                            <BoltSlashIcon className="w-5 h-5 ml-2" />
                                                        ) : (
                                                            <BoltIcon className="w-5 h-5 ml-2" />
                                                        )}
                                                    </Button>
                                                    <Button as={Link} to={`/item/${item.itemId}`} color="primary">
                                                        View Item
                                                        <EyeIcon className="w-5 h-5 ml-2" />
                                                    </Button>
                                                    <Button color="danger">
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