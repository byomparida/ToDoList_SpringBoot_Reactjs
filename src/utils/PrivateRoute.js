import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { isLogin } from './Common';

// const PrivateRoute = ({component: Component, ...rest}) => {
//     return (
//         // Show the component only when the user is logged in
//         // Otherwise, redirect the user to /signin page
//         <Route {...rest} render={props => (
//             isLogin() ?
//                 <Component {...props} />
//             : <Redirect to="/login" />
//         )} />
//     );
// };

const PrivateRoute = ({component: Component, path, ...rest}) => {
    return (
        // Show the component only when the user is logged in
        // Otherwise, redirect the user to /signin page
        <Route {...rest} render={props => (
            isLogin() ?
                <Component {...props} />
            : <Redirect
                    to={{
                        pathname: "/login",
                        // state: {
                            prevLocation: path
                        // },
                    }}
                />
        )} />
    );
};

export default PrivateRoute;