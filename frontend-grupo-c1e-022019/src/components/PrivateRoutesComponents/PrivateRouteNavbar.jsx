/* eslint-disable no-shadow */
/* eslint-disable react/jsx-props-no-spreading */
import React, { Component } from 'react';
import { Redirect, Route } from 'react-router';

export default class PrivateRouteNavbar extends Component {
  componentDidMount() {}

  render() {
    const { navbar: NavbarComponent, component: Component, ...props } = this.props;
    return (
      <Route
        {...props}
        render={(props) => (
          props.location.state !== undefined
          && props.location.state.googleId
          && props.location.state.accessToken
            ? (
              <div>
                <NavbarComponent {...props} />
                <Component {...props} />
              </div>
            )
            : <Redirect to="/" />
        )}
      />
    );
  }
}
