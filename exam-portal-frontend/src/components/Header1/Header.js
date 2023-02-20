import classNames from "classnames/bind";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import routesConfig from "~/config/routes";
import styles from "./Header.module.scss";
import HeadlessTippy from "@tippyjs/react/headless";
import "tippy.js/dist/tippy.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import { Image } from "../Image";
import { faAddressCard } from "@fortawesome/free-regular-svg-icons";
import { faRightFromBracket } from "@fortawesome/free-solid-svg-icons";

const cx = classNames.bind(styles);

function Header() {
  const loginReducer = useSelector((state) => state.loginReducer);
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(loginReducer.loggedIn);
  const [showHeader, setShowHeader] = useState(false);

  const logoutHandler = () => {
    setIsLoggedIn(false);
    localStorage.clear();
    navigate("/login");
  };

  // console.log(loginReducer.user.avatar);

  const MENU_ACTION = [
    {
      icon: faAddressCard,
      title: "Profile",
      to: routesConfig.home,
    },
    {
      icon: faRightFromBracket,
      title: "Logout",
      to: routesConfig.home,
    },
  ];

  useEffect(() => {
    setShowHeader(false);
    if (localStorage.getItem("jwtToken")) {
      setShowHeader(true);
      setIsLoggedIn(true);
    }
  }, [navigate]);

  return (
    <div>
      {showHeader && (
        <div className={cx("wrapper")}>
          <Link to={routesConfig.home} className={cx("logo")}>
            Exam Portal
          </Link>
          <div className={cx("action")}>
            <HeadlessTippy
              interactive
              placement="bottom-end"
              render={(attrs) => (
                <div className={cx("info-user")}>
                  <Link to={routesConfig.home} className={cx("action-profile")}>
                    <FontAwesomeIcon
                      className={cx("left-icon")}
                      icon={faAddressCard}
                    />
                    Profile
                  </Link>
                  <div className={cx("btn-logout")} onClick={logoutHandler}>
                    <FontAwesomeIcon
                      className={cx("left-icon")}
                      icon={faRightFromBracket}
                    />
                    Logout
                  </div>
                </div>
              )}
            >
              <div>
                <Image
                  className={cx("avatar")}
                  src={
                    "http://127.0.0.1:8081/api/file/avatar/" +
                    loginReducer.user.avatar
                  }
                />
              </div>
            </HeadlessTippy>
          </div>
        </div>
      )}
    </div>
  );
}

export default Header;

// import React, { useEffect, useState } from "react";
// import { Navbar, Nav, Container } from "react-bootstrap";
// import { useSelector } from "react-redux";
// import { LinkContainer } from "react-router-bootstrap";
// import { useNavigate } from "react-router-dom";

// const Header = () => {
//   const navigate = useNavigate();
//   const loginReducer = useSelector((state) => state.loginReducer);
//   const [isLoggedIn, setIsLoggedIn] = useState(loginReducer.loggedIn);
//   let profilePageUrl = "";
//   const [showHeader, setShowHeader] = useState(false);

//   const logoutHandler = () => {
//     setIsLoggedIn(false);
//     localStorage.clear();
//     navigate("/login");
//   };

//   useEffect(() => {
//     setShowHeader(false);
//     if (localStorage.getItem("jwtToken")) {
//       setShowHeader(true);
//       setIsLoggedIn(true);
//       loginReducer.user.roles.map((r) => {
//         if (r["roleName"] === "ADMIN") {
//           profilePageUrl = "/adminProfile";
//         } else {
//           profilePageUrl = "/";
//         }
//       });
//     }
//   }, [navigate]);

//   return (
//     <div>
//       {showHeader && (
//         <header>
//           <Navbar bg="dark" variant="dark" expand="lg" collapseOnSelect>
//             <Container>
//               <Navbar.Brand>Exam-Portal</Navbar.Brand>

//               <Navbar.Toggle aria-controls="responsive-navbar-nav" />
//               <Navbar.Collapse id="responsive-navbar-nav">
//                 <Nav className="justify-content-end flex-grow-1 pe-3">
//                   {isLoggedIn ? (
//                     <Nav.Link>{loginReducer.user.firstName}</Nav.Link>
//                   ) : (
//                     <LinkContainer to="/">
//                       <Nav.Link>Login</Nav.Link>
//                     </LinkContainer>
//                   )}

//                   {isLoggedIn ? (
//                     <LinkContainer to="/">
//                       <Nav.Link onClick={logoutHandler}>Logout</Nav.Link>
//                     </LinkContainer>
//                   ) : (
//                     <LinkContainer to="/register">
//                       <Nav.Link>Register</Nav.Link>
//                     </LinkContainer>
//                   )}
//                 </Nav>
//               </Navbar.Collapse>
//             </Container>
//           </Navbar>
//         </header>
//       )}
//     </div>
//   );
// };

// export default Header;
