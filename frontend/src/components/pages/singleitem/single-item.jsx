import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { Image, Button, Textarea, Card, CardBody } from "@nextui-org/react";
import { TrashIcon } from "@heroicons/react/24/outline";
import { jwtDecode } from 'jwt-decode'
import axios from "axios";

function SingleItem() {
  const { id } = useParams(); // Get the item ID from the URL
  const navigate = useNavigate(); // Hook to navigate programmatically
  const [item, setItem] = useState({});
  const [comments, setComments] = useState([]);
  const [commentContent, setCommentContent] = useState("");

  const handleCommentChange = (e) => {
    setCommentContent(e.target.value);
  }

  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    const tokenObject = JSON.parse(storedToken);

    axios.get(`http://localhost:8080/item/${parseInt(id)}`, {
      headers: {
        Authorization: `Bearer ${tokenObject.accessToken}`
      }
    })
    .then(response => {
      setItem(response.data);
    })

    if (!item) {
      navigate("/404"); // Navigate to the NotFound page
    }
  }, [item, navigate]);

  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    const tokenObject = JSON.parse(storedToken);
  
    axios.get(`http://localhost:8080/comment`, {
      headers: {
        Authorization: `Bearer ${tokenObject.accessToken}`
      }
    })
    .then(response => {
      // Filter comments based on the current item id NOTE: This is not optimal and the backend should have an endpoint for this
    const filteredComments = response.data.filter(comment => comment.item.item_id === parseInt(id));
    setComments(filteredComments);

    //TODO: Below is testing, delete afterwards
    const decodedToken = jwtDecode(storedToken);
    const userId = decodedToken.user_Id;
    console.log("User ID: " + userId);
    })
    .catch(error => {
      console.error("Error fetching comments:", error);
    });
  }, [id]);

  const postComment = () => {
    if(commentContent !== "")
    {
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
    })
    .catch(error => {
      console.error("Error submitting comment:", error);
    });
    }
  };

  const deleteComment = (commentId) =>
  {
    const storedToken = localStorage.getItem('token');
    const tokenObject = JSON.parse(storedToken);

    console.log("Comment ID to Delete: " + commentId);

    axios.delete(`http://localhost:8080/comment/delete/${commentId}`, {
      headers: {
        Authorization: `Bearer ${tokenObject.accessToken}`
      }
    })
    .then(() => {
      console.log("Comment deleted successfully");
      // Update the comments state by filtering out the deleted comment
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
            <Button className="w-1/2" color="success">Place Bid</Button>
            <p className="w-1/2 text-left">
              {/* Current Bid: {new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(content.bidAmount)} TOOD: FIX THIS */}
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
            <span className="mt-2">{comment.content}</span>
          </div>
          <div className="justify-right">
            <Button color="danger" className="flex items-center" onClick={() => deleteComment(comment.comment_id)}>
              Delete <TrashIcon className="w-5 h-5 ml-2" />
            </Button>
          </div>
        </CardBody>
      </Card>
    ))}

      </div>
    </div>
  );
}

export default SingleItem;
