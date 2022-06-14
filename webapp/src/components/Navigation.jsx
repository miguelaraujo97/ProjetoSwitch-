import React, { useContext } from "react";

import AppContext from '../context/AppContext';

import { NavLink } from 'react-router-dom'


import { Navbar, Nav , NavItem, Collapse, NavbarText } from 'reactstrap';

export default function Navigation(props) {
  const { loginUser } = useContext(AppContext);

  const PROFILE_NAME = (props.profile !== "dev") ? props.profile.toUpperCase() : "DEV - All Access";

  const navLinks = (
    <>
      {props.routesAndLinks.map((item) => (item.profile.includes(loginUser.profile) && item.role.includes(loginUser.role) && item.navLink) ?
        <NavItem key={item.slug} id={item.slug}> <NavLink className="navbar is-primary nav-link" to={item.path} >{item.name}</NavLink> </NavItem>
        : ""
      )}
    </>
  );


  return (
    <div>
      <Navbar className="naviagtionBar" color="primary" dark expand="md"  >
        <NavLink to="/" className="G4logo">
          G4
        </NavLink>
        <Collapse navbar>
          <Nav className="me-auto" navbar >
            {navLinks}
          </Nav>
          <NavbarText className="userInfo">
            {loginUser.name}<br />{PROFILE_NAME}
          </NavbarText>
        </Collapse>
      </Navbar>
    </div>
  )

}

