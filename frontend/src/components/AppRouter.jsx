import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/home/Home';
import Account from './pages/account/Account';
import NotFound from './pages/notfound/NotFound';
import Login from './pages/login/Login';
import SingleItem from "./pages/singleitem/SingleItem"

function AppRouter() {
    return (
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/account" element={<Account />} />
          <Route path="/login" element={<Login />} />
          <Route path="/item/:id" element={<SingleItem />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>
    );
  }
  
  export default AppRouter;