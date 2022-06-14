import React, { useContext } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePage from '../pages/HomePage';
import EmptyPage from '../pages/EmptyPage';
import ProjectsPage from '../pages/ProjectsPage';
import ProjectInfo from "../pages/ProjectInfoPage";
import CreateProjectPage from '../pages/CreateProjectPage';
import EditProjectPage from '../pages/EditProjectPage';
import ProfilesPage from '../pages/ProfilesPage';
import UsersPage from '../pages/UsersPage';
import CreateUser from '../pages/CreateUser';
import Header from "../pages/layout/Header";
import Footer from "../pages/layout/Footer";
import Navigation from "../components/Navigation";
import UserInfoPage from "../pages/UserInfoPage"
import UserStoryInfo from "../pages/UserStoryInfo"

import AppContext from '../context/AppContext';

import { Container } from 'reactstrap';
import ResourcePage from "../pages/ResourcePage";



//const displayName = App.name;
//console.log(displayName);

//import '../assets/styles.css';

function MainRoute() {
  const { loginUser } = useContext(AppContext);

  const routesAndLinks = [
    {
      path: "/", name: "Home", slug: "home", element: <HomePage />,
      navLink: false,
      profile: ["dev", "admin", "user", "director"],
      role: ["dev", "pm", "sm", "po", "team"]
    },
    

    {
      path: "/users", name: "Users", slug: "users", element: <UsersPage />,
      navLink: true,
      profile: ["dev", "admin", "director"],
      role: ["dev", "pm", "sm", "po", "team"]
    },

    {
      path: "/users/create", name: "UsersCreate", slug: "usersCreate", element: <CreateUser />,
      navLink: false,
      profile: ["dev", "admin", "director"],
      role: ["dev", "pm", "sm", "po", "team"]
    },

    {
      path: "/projects", name: "Projects", slug: "projects", element: <ProjectsPage />,
      navLink: true,
      profile: ["dev", "director", "user"],
      role: ["dev", "pm", "sm", "po", "team"]
    },
    

    {
      path: "/projects/:projectCode", name: "projectsView", slug: "projectsById", element: <ProjectInfo />,
      navLink: false,
      profile: ["dev", "admin", "director"],
      role: ["dev", "pm", "sm", "po", "team"]
    },

    {
      path: "/projects/create", name: "projectsCreate", slug: "projectsCreate", element: <CreateProjectPage />,
      navLink: false,
      profile: ["dev", "admin", "director"],
      role: ["dev", "pm", "sm", "po", "team"]
    },

    {
      path: "/projects/:projectCode/edit", name: "projectsEdit", slug: "projectsEdit", element: <EditProjectPage />,
      navLink: false,
      profile: ["dev", "admin", "director"],
      role: ["dev", "pm", "sm", "po", "team"]
    },
    {
      path: "/projects/:projectCode/resources", name: "projectsView", slug: "projectsById", element: <ProjectsPage />,
      navLink: false,
      profile: ["dev", "admin", "director"],
      role: ["dev", "pm", "sm", "po", "team"]
    },

    {
    path: "/projects/:projectCode/user-stories", name: "userStoryInfo", slug: "userStoryInfo", element: <UserStoryInfo />,
    navLink: false,
    profile: ["dev", "admin", "director"],
    role: ["dev", "pm", "sm", "po", "team"]
    },

    {
      path: "/profiles", name: "Profiles", slug: "profiles", element: <ProfilesPage />,
      navLink: true,
      profile: ["dev", "admin"],
      role: ["dev", "pm", "sm", "po", "team"]
    },
    
    {
      path: "/users/:email", name: "userByEmail", slug: "userByEmail", element: <UserInfoPage />,
      navLink: false,
      profile: ["dev", "admin", "director"],
      role: ["dev", "pm", "sm", "po", "team"]
    },

    {
      path: "*", name: "404", slug: "404", element: <EmptyPage />,
      navLink: false,
      profile: ["dev", "admin", "user", "director"],
      role: ["dev", "pm", "sm", "po", "team"]
    }

  ];

  const routeLinks = (
    <>
      {routesAndLinks.map((item) => (item.profile.includes(loginUser.profile) && item.role.includes(loginUser.role)) ?
        <Route path={item.path} element={item.element} key={item.slug} />
        : ""
      )}
    </>
  );

  /* const navLinks = (
    <>
      {routesAndLinks.map((item) => (item.profile.includes(headers.profile) && item.role.includes(headers.role) && item.navLink) ?
        <li key={item.slug} id={item.slug}><Link to={item.path}>{item.name}</Link ></li>
        : ""
      )}
    </>
  ); */

  return (

    <Router >
      <Container>

        <Header />

        <Navigation profile={loginUser.profile} routesAndLinks={routesAndLinks} />



        <hr />

        <Routes>
          {routeLinks}
        </Routes>

      </Container>

      <Footer />

    </Router>

  );
}

export default MainRoute;