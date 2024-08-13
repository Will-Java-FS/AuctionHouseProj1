import { useParams, useNavigate } from "react-router-dom";
import { Card, CardBody, Image, Button } from "@nextui-org/react";
import { EyeIcon } from "@heroicons/react/24/outline";
import { useEffect } from "react";

function SingleItem() {
  const { id } = useParams(); // Get the item ID from the URL
  const navigate = useNavigate(); // Hook to navigate programmatically

    const items = [
        {
          itemId: 1,
          userOwner: "User",
          itemName: "Cool Cat",
          itemDescription: "Lorem ipsum odor amet, consectetuer adipiscing elit. Inceptos primis pretium mus adipiscing lacus nascetur volutpat. Litora sollicitudin facilisis hac mi efficitur taciti risus torquent. Duis nascetur class magna mauris phasellus. Nam etiam ante tempor hac blandit ultricies nulla congue quis. Malesuada a lacus fermentum erat eros sodales. Laoreet orci parturient vivamus libero suscipit natoque. Lacus iaculis ultricies inceptos sed ac tempor. Sodales eros sem ante cursus enim eget ex orci. Nunc luctus lobortis sem orci id cursus morbi.",
          itemImage: require("../../../img/items/test/01.jpg"),
          bidAmount: 15
        },
        {
          itemId: 2,
          userOwner: "User",
          itemName: "Fancy Car",
          itemDescription: "Lorem ipsum odor amet, consectetuer adipiscing elit. Inceptos primis pretium mus adipiscing lacus nascetur volutpat. Litora sollicitudin facilisis hac mi efficitur taciti risus torquent. Duis nascetur class magna mauris phasellus. Nam etiam ante tempor hac blandit ultricies nulla congue quis. Malesuada a lacus fermentum erat eros sodales. Laoreet orci parturient vivamus libero suscipit natoque. Lacus iaculis ultricies inceptos sed ac tempor. Sodales eros sem ante cursus enim eget ex orci. Nunc luctus lobortis sem orci id cursus morbi.",
          itemImage: require("../../../img/items/test/02.jpg"),
          bidAmount: 95
        },
        {
          itemId: 3,
          userOwner: "User",
          itemName: "Poor People's Plane",
          itemDescription: "Lorem ipsum odor amet, consectetuer adipiscing elit. Inceptos primis pretium mus adipiscing lacus nascetur volutpat. Litora sollicitudin facilisis hac mi efficitur taciti risus torquent. Duis nascetur class magna mauris phasellus. Nam etiam ante tempor hac blandit ultricies nulla congue quis. Malesuada a lacus fermentum erat eros sodales. Laoreet orci parturient vivamus libero suscipit natoque. Lacus iaculis ultricies inceptos sed ac tempor. Sodales eros sem ante cursus enim eget ex orci. Nunc luctus lobortis sem orci id cursus morbi.",
          itemImage: require("../../../img/items/test/03.jpg"),
          bidAmount: 1120
        },
        {
          itemId: 4,
          userOwner: "User",
          itemName: "Totally Legal Gun",
          itemDescription: "Lorem ipsum odor amet, consectetuer adipiscing elit. Inceptos primis pretium mus adipiscing lacus nascetur volutpat. Litora sollicitudin facilisis hac mi efficitur taciti risus torquent. Duis nascetur class magna mauris phasellus. Nam etiam ante tempor hac blandit ultricies nulla congue quis. Malesuada a lacus fermentum erat eros sodales. Laoreet orci parturient vivamus libero suscipit natoque. Lacus iaculis ultricies inceptos sed ac tempor. Sodales eros sem ante cursus enim eget ex orci. Nunc luctus lobortis sem orci id cursus morbi.",
          itemImage: require("../../../img/items/test/04.jpg"),
          bidAmount: 666
        },
        {
          itemId: 5,
          userOwner: "User",
          itemName: "Absolutely Illegal Chickens",
          itemDescription: "Lorem ipsum odor amet, consectetuer adipiscing elit. Inceptos primis pretium mus adipiscing lacus nascetur volutpat. Litora sollicitudin facilisis hac mi efficitur taciti risus torquent. Duis nascetur class magna mauris phasellus. Nam etiam ante tempor hac blandit ultricies nulla congue quis. Malesuada a lacus fermentum erat eros sodales. Laoreet orci parturient vivamus libero suscipit natoque. Lacus iaculis ultricies inceptos sed ac tempor. Sodales eros sem ante cursus enim eget ex orci. Nunc luctus lobortis sem orci id cursus morbi.",
          itemImage: require("../../../img/items/test/05.jpg"),
          bidAmount: 45263
        }
      ];

  // Find the item that matches the ID from the URL
  const content = items.find((item) => item.itemId === parseInt(id));

  useEffect(() => {
    if (!content) {
      navigate("*"); // Navigate to the NotFound page
    }
  }, [content, navigate]);

  return (
    <>
      <div className="text-center my-10">
        <h1 className="uppercase text-5xl">{content.itemName}</h1>
      </div>

      <div className="flex flex-col items-center space-y-6">
        <Card
          key={content.itemId}
          isBlurred
          className="border-none bg-background/60 dark:bg-default-100/50 max-w-[1000px]"
          shadow="sm"
        >
          <CardBody className="flex items-center">
            <div className="flex items-center">
              <div className="flex-shrink-0">
                <Image
                  isZoomed
                  alt={content.itemName}
                  className="object-cover"
                  height={300}
                  shadow="md"
                  src={content.itemImage}
                  width={300}
                />
              </div>
              <div className="ml-6 flex flex-col">
                <div className="flex w-full mb-2 space-x-5 items-center">
                  <Button className="w-1/2" color="success">
                    Place Bid
                  </Button>
                  <Button className="w-1/4" color="primary">
                    View Item <EyeIcon className="w-5 h-5 ml-2" />
                  </Button>
                  <p className="w-1/4 text-left">
                    Current Bid:{" "}
                    {new Intl.NumberFormat("en-US", {
                      style: "currency",
                      currency: "USD",
                    }).format(content.bidAmount)}
                  </p>
                </div>
                <h2 className="text-3xl my-3">{content.itemName}</h2>
                <p className="text-l">{content.itemDescription}</p>
              </div>
            </div>
          </CardBody>
        </Card>
      </div>
    </>
  );
}

export default SingleItem;