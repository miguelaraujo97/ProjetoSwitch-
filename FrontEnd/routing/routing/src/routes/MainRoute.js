import React from 'react';
import {BrowserRouter as Router, Route, Link, Routes } from "react-router-dom";
import Home from '../pages/Home';
import Contact from '../pages/Contact';
import About from '../pages/About';
import NoMatch from '../pages/NoMatch';
import Projects from '../pages/Projects';
import Users from '../pages/Users';
import Typologies from "../pages/Typologies";
import Profiles from '../pages/Profiles';
import Resources from '../pages/Resources';
import classes from './MainRoute.module.css';
import CreateProject from '../pages/CreateProject';
import CreateUser from '../pages/CreateUser';
import Project from "../pages/Project";
import CreateUserStory from "../pages/CreateUserStory";
import Administrator from "../pages/Administrator";
import UpdateProfile from "../pages/UpdateProfile"
import EditProject from '../pages/EditProject';
import GetProjectByCode from '../pages/GetProjetByCode';
import UserStories from "../pages/UserStories";
import ProductBacklog from "../pages/ProductBacklog";
import AccountMenu from '../components/AccountMenu';
import ChangePassword from '../pages/ChangePassword';



function MainRoute() {
  return (
    <Router>
      <header className={classes.header}>
      <div className={classes.logo}>
        <nav>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/projects">Projects</Link></li>
            <li><Link to="/profiles">Profiles</Link></li>
            <li><Link to="/users">Users</Link></li>
            <li><Link to="/administrator">Administrator</Link></li>
            <li><Link to="/contact">Contact</Link></li>
            <li><Link to="/about">About</Link></li>
            <li><AccountMenu/></li>

          </ul>
        </nav>
        <hr />
        </div>
      </header>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="home" element={<Home />} />
          <Route path="contact" element={<Contact />} />
          <Route path="about" element={<About />} />
          <Route path="projects" element={<Projects />} />
          <Route path='/projects/:projectCode' element={<Project />} />
          <Route path='/projects/:projectCode/edit' element={<EditProject />} />
          <Route path="projects/:projectCode/user-stories" element={<UserStories />}/>
          <Route path="projects/:projectCode/user-stories/create" element={<CreateUserStory />}/>
          <Route path="projects/:projectCode/user-stories/product-backlog" element={<ProductBacklog />}/>
          <Route path="projects/:projectCode" element={<GetProjectByCode />}/>
          <Route path="projects/resources" element={<Resources />} />
          <Route path="projects/typologies" element={<Typologies />} />
          <Route path="projects/create" element={<CreateProject />} />
          <Route path="profiles" element={<Profiles />} />
          <Route path="users" element={<Users/>} />
          <Route path="users/create" element={<CreateUser />} />
          <Route path="administrator" element={<Administrator />} />
          <Route path="/users/updateprofile" element={<UpdateProfile />} />
          <Route path="changepassword" element={<ChangePassword />} />
          <Route path="*" element={<NoMatch />} />
        </Routes>
    </Router>

  );
}

export default MainRoute;

