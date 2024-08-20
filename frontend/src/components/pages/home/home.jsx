import { Card, CardBody, Image, Button } from "@nextui-org/react";
import { EyeIcon, PlusIcon } from "@heroicons/react/24/outline";
import { Link } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react";

function Home() {
  const [items, setItems] = useState([]);
  const [bids, setBids] = useState([]); // State to store bids
  const [loading, setLoading] = useState(true); // State to manage loading state
  const [error, setError] = useState(null); // State to manage errors

  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    const tokenObject = JSON.parse(storedToken);

    // Fetch items
    axios.get('http://localhost:8080/item', {
      headers: {
        Authorization: `Bearer ${tokenObject.accessToken}`
      }
    })
      .then(response => {
        setItems(response.data); // Update the items state with the response data
        return axios.get('http://localhost:8080/bid', { // Fetch bids
          headers: {
            Authorization: `Bearer ${tokenObject.accessToken}`
          }
        });
      })
      .then(response => {
        setBids(response.data); // Update bids state with the fetched bids
        setLoading(false); // Set loading to false once data is fetched
      })
      .catch(err => {
        setError(err); // Set the error state if there's an error
        setLoading(false); // Set loading to false even if there's an error
      });
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error loading items: {error.message}</p>;

  return (
    <>
      <div className="text-center my-10">
        <h1 className="uppercase text-5xl">Auction House</h1>
        <Button as={Link} to={`/newitem`} className="mt-6" color="secondary">
          Add New Item <PlusIcon className="w-5 h-5 ml-2" />
        </Button>
      </div>

      <div className="flex flex-col items-center space-y-6 mb-10">
        {items.filter(item => item.approved).map((item) => {
          // Find the highest bid for the current item
          const itemBids = bids.filter(bid => bid.item.item_id === item.item_id);
          const highestBid = itemBids.length > 0 ? Math.max(...itemBids.map(bid => bid.amount)) : 0;

          return (
            <Card
              key={item.item_id}
              isBlurred
              className="border-none bg-background/60 dark:bg-default-100/50 max-w-[1000px]"
              shadow="sm"
            >
              <CardBody className="flex items-center">
                <div className="flex items-center">
                  <div className="flex-shrink-0">
                    <Image
                      isZoomed
                      alt={item.itemName}
                      className="object-cover"
                      height={300}
                      shadow="md"
                      src={item.itemImage}
                      width={300}
                    />
                  </div>
                  <div className="ml-6 flex flex-col">
                    <div className="flex w-full mb-2 space-x-5 items-center">
                      <Button as={Link} to={`/item/${item.item_id}`} className="w-1/3" color="success">Place Bid</Button>
                      <Button as={Link} to={`/item/${item.item_id}`} className="w-1/3" color="primary">View Item <EyeIcon className="w-5 h-5 ml-2" /></Button>
                      <p className="w-1/3 text-left">
                        Current Bid: {new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(highestBid)}
                      </p>
                    </div>
                    <h2 className="text-3xl my-3">{item.itemName}</h2>
                    <p className="text-l">{item.itemDescription}</p>
                  </div>
                </div>
              </CardBody>
            </Card>
          );
        })}
      </div>
    </>
  );
}

export default Home;
