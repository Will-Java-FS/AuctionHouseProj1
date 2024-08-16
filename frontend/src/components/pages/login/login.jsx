import { useState, useEffect } from 'react';
import { Tabs, Tab, Input, Link, Button, Card, CardBody, CardHeader } from "@nextui-org/react";
import axios from 'axios';
import { Router, useNavigate } from 'react-router-dom';

function Login() {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [selected, setSelected] = useState("login");

  const [isVisible, setIsVisible] = useState(false);

  const toggleVisibility = () => setIsVisible(!isVisible);

  const navigate = useNavigate();

  const login = () => {
    // Define the URL of your API endpoint
    const url = 'http://localhost:8080/user/login';
    


    // Make a POST request
    axios.post(url, {username: username, password: password}, {
      headers: {
        'Content-Type': 'application/json', // Set content type to JSON
        // Add any other headers if needed, e.g., Authorization
      }
    })
    .then((res) => {
      // Store the response body in local storage
      localStorage.setItem('token', JSON.stringify(res.data));

      navigate('/');
    })
  };

  return (
    <>
    <div className="aurora-background"></div>
      <div className="flex items-center justify-center mt-150px relative z-10">
        <div className="flex">
          <Card className="max-w-full center w-[340px]">
            <CardBody className="overflow-hidden">
              <Tabs
                fullWidth
                size="md"
                aria-label="Tabs form"
                selectedKey={selected}
                onSelectionChange={setSelected}
              >
                <Tab key="login" title="Login">
                  <form className="flex flex-col gap-4">
                    <Input
                      isRequired
                      label="Username"
                      placeholder="Enter your username"
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                      type="text"
                    />
                    <Input
                      isRequired
                      label="Password"
                      placeholder="Enter your password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      type="password"
                    />
                    <p className="text-center text-small theme-link">
                      Need to create an account?{" "}
                      <Link size="sm" onPress={() => setSelected("sign-up")} style={{ cursor: "pointer" }}>
                        Sign up
                      </Link>
                    </p>
                    <div className="flex gap-2 justify-end">
                      <Button onClick={login} className="button-text" fullWidth color="primary">
                        Login
                      </Button>
                    </div>
                    {errorMessage && (
                      <p className="text-center text-sm text-red-600 mt-1">{errorMessage}</p>
                    )}
                  </form>
                </Tab>
                <Tab key="sign-up" title="Sign up">
                  <form className="flex flex-col gap-4">
                    <Input
                      isRequired
                      label="Username"
                      placeholder="Enter your username"
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                      type="text"
                    />
                    <Input
                      isRequired
                      label="Password"
                      placeholder="Enter your password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      type="password"
                    />
                    <p className="text-center text-small theme-link">
                      Already have an account?{" "}
                      <Link size="sm" onPress={() => setSelected("login")} style={{ cursor: "pointer" }}>
                        Login
                      </Link>
                    </p>
                    <div className="flex gap-2 justify-end">
                      <Button type="submit" fullWidth className="button-text" color="primary">
                        Sign up
                      </Button>
                    </div>
                    {errorMessage && (
                      <p className="text-center text-sm text-red-600 mt-1">{errorMessage}</p>
                    )}
                    {message && (
                      <p className="text-center text-sm text-green-600 mt-1">{message}</p>
                    )}
                  </form>
                </Tab>
              </Tabs>
            </CardBody>
          </Card>
        </div>
      </div>
      </>
  );
}
  
  export default Login;
  