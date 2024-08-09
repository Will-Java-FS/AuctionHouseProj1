import { Image } from "@nextui-org/react";
import { Button } from "@nextui-org/react";
import { CameraIcon } from "@heroicons/react/24/outline";
import { TrashIcon } from "@heroicons/react/24/outline";
import { UserIcon } from "@heroicons/react/24/outline";
import { Input } from "@nextui-org/react";

function Account() {
  const FALL_BACK_IMAGE = process.env.REACT_APP_FALL_BACK_IMAGE;
  const FALL_BACK_USER = process.env.REACT_APP_FALL_BACK_USER;

  return (
    <div className="App flex justify-center">
      <div className="flex flex-col items-center m-10">
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
      <div className="flex flex-col items-center m-10 w-full max-w-md">
        <h2 className="text-4xl font-semibold mb-10">
          Hello, {FALL_BACK_USER}
        </h2>
        <Input size="lg" type="text" label="Username" placeholder="Enter your username" className="mb-5 w-full" />
        <Input size="lg" type="password" label="Password" placeholder="Enter your password" className="mb-5 w-full" />
        <Button color="success" className="w-full">
          Update Profile
          <UserIcon className="w-5 h-5 ml-2" />
        </Button>
      </div>
    </div>
  );
}

export default Account;
