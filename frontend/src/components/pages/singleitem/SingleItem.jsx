import { useParams, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { Image, Button, Textarea, Card, CardBody } from "@nextui-org/react";

function SingleItem() {
  const { id } = useParams(); // Get the item ID from the URL
  const navigate = useNavigate(); // Hook to navigate programmatically

  const items = [
    {
      itemId: 1,
      userOwner: "User",
      itemName: "Cool Cat",
      itemDescription:
        "Lorem ipsum odor amet, consectetuer adipiscing elit. Inceptos primis pretium mus adipiscing lacus nascetur volutpat. Litora sollicitudin facilisis hac mi efficitur taciti risus torquent. Duis nascetur class magna mauris phasellus. Nam etiam ante tempor hac blandit ultricies nulla congue quis. Malesuada a lacus fermentum erat eros sodales. Laoreet orci parturient vivamus libero suscipit natoque. Lacus iaculis ultricies inceptos sed ac tempor. Sodales eros sem ante cursus enim eget ex orci. Nunc luctus lobortis sem orci id cursus morbi.",
      itemImage: require("../../../img/items/test/01.jpg"),
      bidAmount: 15,
    },
    {
      itemId: 2,
      userOwner: "User",
      itemName: "Fancy Car",
      itemDescription:
        "Lorem ipsum odor amet, consectetuer adipiscing elit. Inceptos primis pretium mus adipiscing lacus nascetur volutpat. Litora sollicitudin facilisis hac mi efficitur taciti risus torquent. Duis nascetur class magna mauris phasellus. Nam etiam ante tempor hac blandit ultricies nulla congue quis. Malesuada a lacus fermentum erat eros sodales. Laoreet orci parturient vivamus libero suscipit natoque. Lacus iaculis ultricies inceptos sed ac tempor. Sodales eros sem ante cursus enim eget ex orci. Nunc luctus lobortis sem orci id cursus morbi.",
      itemImage: require("../../../img/items/test/02.jpg"),
      bidAmount: 95,
    },
    {
      itemId: 3,
      userOwner: "User",
      itemName: "Poor People's Plane",
      itemDescription:
        "Lorem ipsum odor amet, consectetuer adipiscing elit. Inceptos primis pretium mus adipiscing lacus nascetur volutpat. Litora sollicitudin facilisis hac mi efficitur taciti risus torquent. Duis nascetur class magna mauris phasellus. Nam etiam ante tempor hac blandit ultricies nulla congue quis. Malesuada a lacus fermentum erat eros sodales. Laoreet orci parturient vivamus libero suscipit natoque. Lacus iaculis ultricies inceptos sed ac tempor. Sodales eros sem ante cursus enim eget ex orci. Nunc luctus lobortis sem orci id cursus morbi.",
      itemImage: require("../../../img/items/test/03.jpg"),
      bidAmount: 1120,
    },
    {
      itemId: 4,
      userOwner: "User",
      itemName: "Totally Legal Gun",
      itemDescription:
        "Lorem ipsum odor amet, consectetuer adipiscing elit. Inceptos primis pretium mus adipiscing lacus nascetur volutpat. Litora sollicitudin facilisis hac mi efficitur taciti risus torquent. Duis nascetur class magna mauris phasellus. Nam etiam ante tempor hac blandit ultricies nulla congue quis. Malesuada a lacus fermentum erat eros sodales. Laoreet orci parturient vivamus libero suscipit natoque. Lacus iaculis ultricies inceptos sed ac tempor. Sodales eros sem ante cursus enim eget ex orci. Nunc luctus lobortis sem orci id cursus morbi.",
      itemImage: require("../../../img/items/test/04.jpg"),
      bidAmount: 666,
    },
    {
      itemId: 5,
      userOwner: "User",
      itemName: "Absolutely Illegal Chickens",
      itemDescription:
        "Lorem ipsum odor amet, consectetuer adipiscing elit. Inceptos primis pretium mus adipiscing lacus nascetur volutpat. Litora sollicitudin facilisis hac mi efficitur taciti risus torquent. Duis nascetur class magna mauris phasellus. Nam etiam ante tempor hac blandit ultricies nulla congue quis. Malesuada a lacus fermentum erat eros sodales. Laoreet orci parturient vivamus libero suscipit natoque. Lacus iaculis ultricies inceptos sed ac tempor. Sodales eros sem ante cursus enim eget ex orci. Nunc luctus lobortis sem orci id cursus morbi.",
      itemImage: require("../../../img/items/test/05.jpg"),
      bidAmount: 45263,
    },
  ];

  const comments = [
    ["cool cat!", "i want one of those cats!"],
    ["dope ride!", "i want one of those cars!"],
    ["eww plane!", "i want one of those planes!"],
    ["toy gun!", "i want one of those guns!"],
    ["don't trust the birds!", "constant surveillance!"],
  ];

  // Find the item that matches the ID from the URL
  const content = items.find((item) => item.itemId === parseInt(id));

  useEffect(() => {
    if (!content) {
      navigate("/404"); // Navigate to the NotFound page
    }
  }, [content, navigate]);

  if (!content) {
    return null; // Return null to avoid rendering if content is not found
  }

  return (
    <div key={content.itemId} className="flex flex-col items-center">
      <div className="text-center my-10">
        <h1 className="uppercase text-5xl">{content.itemName}</h1>
      </div>

      <div style={{ width: '1000px' }} className="flex flex-row justify-center items-start space-x-6">
        <div className="w-1/2">
          <Image
            isZoomed
            alt={content.itemName}
            className="object-cover"
            height={450}
            shadow="md"
            src={content.itemImage}
            width={450}
          />
        </div>
        <div className="w-1/2">
          <div className="flex w-full mb-2 space-x-5 items-center">
            <Button className="w-1/2" color="success">Place Bid</Button>
            <p className="w-1/2 text-left">
              Current Bid: {new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(content.bidAmount)}
            </p>
          </div>
          <p className="text-lg">{content.itemDescription}</p>
        </div>
      </div>

      <div style={{ width: '1000px' }} className="mt-10">
        <h2 className="uppercase text-3xl">Comments:</h2>
        <Textarea
          label="Comment"
          placeholder="Leave a comment"
          className="w-full my-5"
        />
        <Button className="w-1/4 mb-5" color="primary">Leave a Comment</Button>
        
        {comments[content.itemId - 1].map((comment, index) => (
          <Card key={index} className="mb-3 w-full">
            <CardBody>
              <p className="text-lg text-left">
                {comment}
              </p>
            </CardBody>
          </Card>
        ))}          
      </div>
    </div>
  );
}

export default SingleItem;
