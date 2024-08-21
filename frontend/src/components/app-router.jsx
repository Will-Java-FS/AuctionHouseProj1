import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/home/home';
import Account from './pages/account/account';
import NotFound from './pages/notfound/not-found';
import Login from './pages/login/login';
import SingleItem from "./pages/singleitem/single-item"
import UpdateProduct from './update-product/update-product';
import NewProduct from './pages/new-product/new-product';
import PrivateRoute from './pages/privateroute/private-route'; // Import the PrivateRoute component

function AppRouter() {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<Login />} />
                {/* Protect these routes with PrivateRoute */}
                <Route path="/" element={<PrivateRoute element={<Home />} />} />
                <Route path="/account" element={<PrivateRoute element={<Account />} />} />
                <Route path="/item/:id" element={<PrivateRoute element={<SingleItem />} />} />
                <Route path="/item/update/:id" element={<PrivateRoute element={<UpdateProduct />} />} />
                <Route path="/newitem" element={<PrivateRoute element={<NewProduct />} />} />
                <Route path="*" element={<NotFound />} />
            </Routes>
        </Router>
    );
}

export default AppRouter;
