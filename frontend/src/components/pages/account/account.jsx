import { Image } from "@nextui-org/react";
import { Button } from "@nextui-org/react";
import { UserIcon, TrashIcon, CameraIcon,  } from "@heroicons/react/24/outline";
import { Input } from "@nextui-org/react";
import AdminPanel from "./admin-panel";


function Account() {
  const FALL_BACK_IMAGE = process.env.REACT_APP_FALL_BACK_IMAGE;
  const FALL_BACK_USER = process.env.REACT_APP_FALL_BACK_USER;
  const FALL_BACK_PASSWORD = process.env.REACT_APP_FALL_BACK_PASSWORD;

  const user = {
    username: FALL_BACK_USER,
    password: FALL_BACK_PASSWORD,
    image: FALL_BACK_IMAGE
  }

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
      <AdminPanel />
    </div>
  );
}

export default Account;
