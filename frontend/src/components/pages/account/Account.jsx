import { Image } from "@nextui-org/react";
import { Button } from "@nextui-org/react";
import { UserIcon, UsersIcon, TrashIcon, CameraIcon, ShoppingBagIcon, BoltSlashIcon, BoltIcon } from "@heroicons/react/24/outline";
import { Input, Tabs, Tab, Card, CardBody } from "@nextui-org/react";

function Account() {
  const FALL_BACK_IMAGE = process.env.REACT_APP_FALL_BACK_IMAGE;
  const FALL_BACK_USER = process.env.REACT_APP_FALL_BACK_USER;
  const FALL_BACK_PASSWORD = process.env.REACT_APP_FALL_BACK_PASSWORD;

  const user = {
    username: FALL_BACK_USER,
    password: FALL_BACK_PASSWORD,
    image: FALL_BACK_IMAGE
  }

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
      itemName: 'cat',
      itemIsApproved: true,
      comments: {
        commentId: 1,
        commentContent: "blah",

      }
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

  return (
    <div className="App flex flex-col items-center">
      <div className="one-thousand-px flex flex-row items-start justify-center m-10 space-x-10">
        <div className="flex flex-col items-center">
          <Image
            isBlurred
            src={FALL_BACK_IMAGE}
            alt="NextUI Album Cover"
            className="mb-4 account-image"
            width={300}
            height={300}
          />
          <Button color="success" className="mb-2 w-full max-w-xs">
            Change Image
            <CameraIcon className="w-5 h-5 ml-2" />
          </Button>
          <Button color="danger" variant="bordered" className="w-full max-w-xs">
            Delete Image
            <TrashIcon className="w-5 h-5 ml-2" />
          </Button>
        </div>

        <div className="flex flex-col items-center w-full max-w-md">
          <h2 className="text-4xl font-semibold mb-10">
            Hello, {user.username}
          </h2>
          <Input size="lg" type="text" label="Username" placeholder="Enter your username" className="mb-5 w-full" />
          <Input size="lg" type="password" label="Password" placeholder="Enter your password" className="mb-5 w-full" />
          <Button color="success" className="w-full">
            Update Profile
            <UserIcon className="w-5 h-5 ml-2" />
          </Button>
        </div>
      </div>

      {/* Render dynamically if the user is admin */}
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
          </Tabs>
        </div>
      </div>
    </div>
  );
}

export default Account;
