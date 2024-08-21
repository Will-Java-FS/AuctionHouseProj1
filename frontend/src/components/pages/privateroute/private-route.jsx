import { Navigate } from 'react-router-dom';

function PrivateRoute({ element: Component }) {
    const isLoggedIn = !!localStorage.getItem('token');

    return isLoggedIn ? Component : <Navigate to="/login" />;
}

export default PrivateRoute;
