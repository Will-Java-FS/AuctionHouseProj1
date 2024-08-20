import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/home/home';
import Account from './pages/account/account';
import NotFound from './pages/notfound/not-found';
import Login from './pages/login/login';
import SingleItem from "./pages/singleitem/single-item"
import UpdateProduct from './update-product/update-product';

function AppRouter() {
    return (
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/account" element={<Account />} />
          <Route path="/login" element={<Login />} />
          <Route path="/item/:id" element={<SingleItem />} />
          <Route path="/item/update/:id" element={<UpdateProduct />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>
    );
  }
  
  export default AppRouter;