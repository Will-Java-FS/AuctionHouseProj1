import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/home/home';
import Account from './pages/account/account';
import NotFound from './pages/notfound/not-found';
import Login from './pages/login/login';
import SingleItem from "./pages/singleitem/single-item"
import NewProduct from './pages/new-product/new-product';

function AppRouter() {
    return (
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/account" element={<Account />} />
          <Route path="/login" element={<Login />} />
          <Route path="/item/:id" element={<SingleItem />} />
          <Route path="*" element={<NotFound />} />
          <Route path = "/newitem" element = {<NewProduct />} />
        </Routes>
      </Router>
    );
  }
  
  export default AppRouter;