import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { Image, Button, Textarea, Card, CardBody, Input } from "@nextui-org/react";
import { Link } from "react-router-dom";
import { TrashIcon } from "@heroicons/react/24/outline";
import { jwtDecode } from 'jwt-decode';
import axios from "axios";

function SingleItem() {
  const { id } = useParams(); // Get the item ID from the URL
  const navigate = useNavigate(); // Hook to navigate programmatically
  const [item, setItem] = useState({});
  const [comments, setComments] = useState([]);
  const [bids, setBids] = useState([]);
  const [commentContent, setCommentContent] = useState("");
  const [currentUserId, setCurrentUserId] = useState(0);
  const [highestBid, setCurrentHighestBid] = useState(0);
  const [newBid, setNewBid] = useState(""); // Bid input state
  const [isValidBid, setIsValidBid] = useState(false);

  const handleCommentChange = (e) => {
    setCommentContent(e.target.value);
  }

  const handleNewBidChange = (e) => {
    const bidValue = parseFloat(e.target.value);
    setNewBid(bidValue);
    setIsValidBid(bidValue > highestBid);
  }

  const handlePlaceBid = () => {
    if (isValidBid) {
      const storedToken = localStorage.getItem('token');
      const tokenObject = JSON.parse(storedToken);
      const decodedToken = jwtDecode(tokenObject.accessToken);
      const userId = decodedToken.user_Id;

      const bidData = {
        bidtime: new Date().toISOString(),
        amount: newBid,
        user: { user_id: userId },
        item: { item_id: parseInt(id) }
      };

      axios.post(`http://localhost:8080/bid`, bidData, {
        headers: {
          Authorization: `Bearer ${tokenObject.accessToken}`
        }
      })
      .then(response => {
        setBids([...bids, response.data]);
        setCurrentHighestBid(newBid);
        setNewBid(""); // Reset the input field after successful bid
      })
      .catch(error => {
        console.error("Error placing bid", error);
      });
    }
  };

  // Preloads the item and retrieves it from the API
  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    const tokenObject = JSON.parse(storedToken);
    const decodedToken = jwtDecode(tokenObject.accessToken);
    const userId = decodedToken.user_Id;

    setCurrentUserId(userId);

    axios.get(`http://localhost:8080/item/${parseInt(id)}`, {
      headers: {
        Authorization: `Bearer ${tokenObject.accessToken}`
      }
    })
    .then(response => {
      setItem(response.data);
    });

    if (!item) {
      navigate("/404"); // Navigate to the NotFound page
    }
  }, [item, navigate]);

  // Preloads the comments attached to this item
  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    const tokenObject = JSON.parse(storedToken);
  
    axios.get(`http://localhost:8080/comment`, {
      headers: {
        Authorization: `Bearer ${tokenObject.accessToken}`
      }
    })
    .then(response => {
      const filteredComments = response.data.filter(comment => comment.item.item_id === parseInt(id));
      setComments(filteredComments);
    })
    .catch(error => {
      console.error("Error fetching comments:", error);
    });
  }, [id]);

  // Preloads the bids and sets the highestBid to the currently highest bid on this item
  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    const tokenObject = JSON.parse(storedToken);

    axios.get(`http://localhost:8080/bid`, {
      headers: {
        Authorization: `Bearer ${tokenObject.accessToken}`
      }
    })
    .then(response => {
      const filteredBids = response.data.filter(bid => bid.item.item_id === parseInt(id));
      setBids(filteredBids);

      // Find the highest bid amount
      if (filteredBids.length > 0) {
        const maxBid = Math.max(...filteredBids.map(bid => bid.amount));
        setCurrentHighestBid(maxBid);
      } else {
        setCurrentHighestBid(0); // If no bids, set highest bid to 0
      }
    })
    .catch(error => {
      console.error("Error fetching bids:", error);
    });
  }, [id]);

  // Method that creates a new comment and posts it to the database
  const postComment = () => {
    if (commentContent !== "") {
      const storedToken = localStorage.getItem('token');
      const tokenObject = JSON.parse(storedToken);
      const decodedToken = jwtDecode(tokenObject.accessToken);
      const userId = decodedToken.user_Id;

      const commentData = {
        comment_id: 20,
        commenttime: new Date().toISOString(),
        content: commentContent,
        user: { user_id: userId },
        item: { item_id: parseInt(id) }
      };

      axios.post('http://localhost:8080/comment', commentData, {
        headers: {
          Authorization: `Bearer ${tokenObject.accessToken}`
        }
      })
      .then(response => {
        console.log("Comment submitted successfully:", response.data);
        setComments([...comments, response.data]);
        setCommentContent("");
      })
      .catch(error => {
        console.error("Error submitting comment:", error);
      });
    }
  };

  // Method that deletes a comment by its id
  const deleteComment = (commentId) => {
    const storedToken = localStorage.getItem('token');
    const tokenObject = JSON.parse(storedToken);

    axios.delete(`http://localhost:8080/comment/delete/${commentId}`, {
      headers: {
        Authorization: `Bearer ${tokenObject.accessToken}`
      }
    })
    .then(() => {
      console.log("Comment deleted successfully");
      setComments(comments.filter(comment => comment.comment_id !== commentId));
    })
    .catch(error => {
      console.error("Error deleting comment:", error);
    });
  };

  if (!item) {
    return null; // Return null to avoid rendering if content is not found
  }

  return (
    <div key={item.item_id} className="flex flex-col items-center">
      <div className="text-center my-10">
        <h1 className="uppercase text-5xl">{item.itemName}</h1> 
      </div>
        <Button 
          as={Link} 
          to={`/item/update/${item.item_id}`}
          className="w-1/4 mb-5" 
          color="primary"
        >
          Update Item
        </Button>

      <div className="one-thousand-px flex flex-row justify-center items-start space-x-6">
        <div className="w-1/2">
          <Image
            isZoomed
            alt={item.itemName}
            className="object-cover"
            height={450}
            shadow="md"
            src={item.itemImage}
            width={450}
          />
        </div>
        <div className="w-1/2">
          <div className="flex w-full mb-2 space-x-5 items-center">
            <Input
              type="number"
              label="New Bid"
              value={newBid}
              onChange={handleNewBidChange}
              placeholder="Enter your bid"
              className="w-1/2"
            />
            <Button className="w-1/4" color="success" onClick={handlePlaceBid} disabled={!isValidBid}>
              Place Bid
            </Button>
            <p className="w-1/4 text-left">
              Current Bid: {new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(highestBid)}
            </p>
          </div>
          <p className="text-lg">{item.itemDescription}</p>
        </div>
      </div>

      <div style={{ width: '1000px' }} className="mt-10">
        <h2 className="uppercase text-3xl">Comments:</h2>
        <Textarea
          label="Comment"
          placeholder="Leave a comment"
          className="w-full my-5"
          value={commentContent}
          onChange={handleCommentChange}
        />
        <Button onClick={postComment} className="w-1/4 mb-5" color="primary">Leave a Comment</Button>
      </div>
      <div style={{ width: '1000px' }} >
        {comments.map((comment, index) => (
          <Card key={index} className="mb-3 w-full">
            <CardBody className="flex w-full flex-row items-center justify-between">
              <div className="flex flex-col">
                <span className="font-semibold">{comment.user.username}</span>
                <span className="text-sm text-gray-600">
                  {new Date(comment.commenttime).toLocaleString()}
                </span>
                <p>{comment.content}</p>
              </div>
              {((currentUserId === comment.user.user_id) || (currentUserId === item.owner.user_id)) && (
                <Button
                  onClick={() => deleteComment(comment.comment_id)}
                  className="ml-5"
                  color="danger"
                >
                  Delete 
                  <TrashIcon className="h-5 w-5" />
                </Button>
              )}
            </CardBody>
          </Card>
        ))}
      </div>
    </div>
  );
}

export default SingleItem;
