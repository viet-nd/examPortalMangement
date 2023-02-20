//sidebar admin
import React, { useEffect, useState } from "react";
import { FaUserAlt } from "react-icons/fa";
import { TbLayoutGrid, TbLayoutGridAdd, TbReport } from "react-icons/tb";
import { MdQuiz, MdQueue } from "react-icons/md";
import { NavLink, useNavigate } from "react-router-dom";

//sidebar user
import { useDispatch, useSelector } from "react-redux";
import { fetchCategories } from "../../actions/categoriesActions";

//custome
import classNames from "classnames/bind";
import styles from "./Sidebar.module.scss";
import { MenuItem } from "./MenuItem";
// import { faUser } from "@fortawesome/free-regular-svg-icons";
import {
  faBook,
  faDoorOpen,
  faFileLines,
  faSquarePollVertical,
  faUser,
  faUserGear,
  faUserPlus,
} from "@fortawesome/free-solid-svg-icons";

const cx = classNames.bind(styles);

function Sidebar() {
  var sidebarRole = "";
  // useSelector((state) => {
  //   console.log(state);
  //   // console.log(state.loginReducer.user.authorities[0].authority);
  //   return state.loginReducer.user.authorities[0].authority;
  // });

  const navigate = useNavigate();
  const [showSidebar, setShowSidebar] = useState(false);

  useEffect(() => {
    setShowSidebar(false);
    if (localStorage.getItem("jwtToken")) {
      setShowSidebar(true);
      // sidebarRole = localStorage.getItem("user").role;
    }
  }, [navigate]);

  //sidebar admin
  const menuItemAdmin = [
    {
      path: "/adminProfile",
      name: "Profile",
      icon: faUser,
    },
    {
      path: "/adminAddUsers",
      name: "Add",
      icon: faUserPlus,
    },
    {
      path: "/adminManagerUser",
      name: "Users",
      icon: faUserGear,
    },
  ];

  //sidebar manager
  const menuItemManager = [
    {
      path: "/managerProfile",
      name: "Profile",
      icon: faUser,
    },
    {
      path: "/managerSubjects",
      name: "Subjects",
      icon: faBook,
    },
    {
      path: "/managerClasses",
      name: "Classes",
      icon: faDoorOpen,
    },
    {
      path: "/managerQuizzes",
      name: "Quizzes",
      icon: faFileLines,
    },
    {
      path: "/managerAddQuiz",
      name: "ResultQuiz",
      icon: faSquarePollVertical,
    },
  ];

  const menuItemUser = [
    {
      path: "/profile",
      name: "Profile",
      icon: faUser,
    },
    {
      path: "/classes",
      name: "Join Classes",
      icon: faDoorOpen,
    },
    {
      path: "/quizzes",
      name: "Quizzes",
      icon: faFileLines,
    },
    {
      path: "/quizResults/",
      name: "ResultQuiz",
      icon: faSquarePollVertical,
    },
  ];

  //sidebar user
  // const categoriesReducer = useSelector((state) => state.categoriesReducer);
  // const [categories, setCategories] = useState(categoriesReducer.categories);
  const dispatch = useDispatch();
  const token = JSON.parse(localStorage.getItem("jwtToken"));
  // const [menuItemUser, setMenuItems] = useState([
  //   {
  //     path: "/profile",
  //     name: "Profile",
  //     icon: <FaUserAlt />,
  //   },
  //   {
  //     path: "/quizResults",
  //     name: "Report Card",
  //     icon: <TbReport />,
  //   },
  //   {
  //     path: "/quizzes",
  //     name: "All Quizzes",
  //     icon: <MdQuiz />,
  //   },
  // ]);

  // useEffect(() => {
  //   console.log("Fetching Categories because of SidebarUser");
  //   fetchCategories(dispatch, token).then((data) => {
  //     const tempCategories = data.payload;
  //     setCategories(tempCategories);

  //     const newMenuItemsUser = tempCategories.map((c) => {
  //       return {
  //         path: `/quiz/cat${c.title}?catId=${c.catId}`,
  //         name: c.title,
  //         icon: <TbLayoutGrid />,
  //       };
  //     });
  //     setMenuItems([...menuItemUser, ...newMenuItemsUser]);
  //   });
  // }, []);

  return (
    <div>
      {showSidebar && (
        <div className={cx("container")}>
          <div className={cx("sidebar")}>
            {(() => {
              switch (sidebarRole) {
                case "ADMIN":
                  return menuItemAdmin.map((item, index) => (
                    <MenuItem
                      key={index}
                      to={item.path}
                      icon={item.icon}
                      content={item.name}
                    />
                    // <NavLink
                    //   to={item.path}
                    //   key={index}
                    //   className="sidemenulink"
                    //   activeclassname="sidemenulink-active"
                    // >
                    //   <div className={cx("icon")}>{item.icon}</div>
                    //     {item.name}
                    // </NavLink>
                  ));
                case "MANAGER":
                  return menuItemManager.map((item, index) => (
                    <MenuItem
                      key={index}
                      to={item.path}
                      icon={item.icon}
                      content={item.name}
                    />
                    // <NavLink
                    //   to={item.path}
                    //   key={index}
                    //   className="sidemenulink"
                    //   activeclassname="sidemenulink-active"
                    // >
                    //   <div className={cx("icon")}>{item.icon}</div>
                    //   <div
                    //     style={{ display: isOpen ? "block" : "none" }}
                    //     className={cx("link_text")}
                    //   >
                    //     {item.name}
                    //   </div>
                    // </NavLink>
                  ));

                default:
                  return menuItemUser.map((item, index) => (
                    <MenuItem
                      key={index}
                      to={item.path}
                      icon={item.icon}
                      content={item.name}
                    />
                  ));
              }
            })()}
            {/* {sidebarRole === "ADMIN"
            ? menuItemAdmin.map((item, index) => (
                <NavLink
                  to={item.path}
                  key={index}
                  className="sidemenulink"
                  activeclassname="sidemenulink-active"
                >
                  <div className={cx("icon")}>{item.icon}</div>
                  <div
                    style={{ display: isOpen ? "block" : "none" }}
                    className={cx("link_text")}
                  >
                    {item.name}
                  </div>
                </NavLink>
              ))
            : menuItemUser.map((item, index) => (
                <NavLink
                  to={item.path}
                  key={index}
                  className={cx("sidemenulink")}
                  activeclassname="sidemenulink-active"
                >
                  <div className={cx("icon")}>{item.icon}</div>
                  <div
                    style={{ display: "block"}}
                    className={cx("link_text")}
                  >
                    {item.name}
                  </div>
                </NavLink>
              ))} */}
          </div>
        </div>
      )}
    </div>

    // <div>
    //   {sidebarRole === "ADMIN" ? (
    //     <div
    //       className={cx("container")}
    //       style={{
    //         display: "flex",
    //         width: "auto",
    //         margin: "0px",
    //         padding: "0px",
    //       }}
    //     >
    //       <div style={{ width: isOpen ? "12em" : "3em" }} className={cx("sidebar")}>
    //         <div className={cx("top_section")}>
    //           <h1
    //             style={{ display: isOpen ? "block" : "none" }}
    //             className={cx("logo")}
    //           >
    //             Logo
    //           </h1>
    //           <div
    //             style={{ marginLeft: isOpen ? "50px" : "0px" }}
    //             className={cx("bars")}
    //           >
    //             <FaBars onClick={toggle} />
    //           </div>
    //         </div>
    //         {menuItem.map((item, index) => (
    //           <NavLink
    //             to={item.path}
    //             key={index}
    //             className="sidemenulink"
    //             activeclassname="sidemenulink-active"
    //           >
    //             <div className={cx("icon")}>{item.icon}</div>
    //             <div
    //               style={{ display: isOpen ? "block" : "none" }}
    //               className={cx("link_text")}
    //             >
    //               {item.name}
    //             </div>
    //           </NavLink>
    //         ))}
    //       </div>
    //     </div>
    //   ) : (
    //     <div
    //       className={cx("container")}
    //       style={{
    //         display: "flex",
    //         width: "auto",
    //         margin: "0px",
    //         padding: "0px",
    //       }}
    //     >
    //       <div style={{ width: isOpen ? "12em" : "3em" }} className={cx("sidebar")}>
    //         <div className={cx("top_section")}>
    //           <div
    //             style={{ marginLeft: isOpen ? "50px" : "0px" }}
    //             className={cx("bars")}
    //           >
    //             <FaBars onClick={toggle} />
    //           </div>
    //         </div>
    //         {menuItems.map((item, index) => (
    //           <NavLink
    //             to={item.path}
    //             key={index}
    //             className={cx("sidemenulink")}
    //             activeclassname="sidemenulink-active"
    //           >
    //             <div className={cx("icon")}>{item.icon}</div>
    //             <div
    //               style={{ display: isOpen ? "block" : "none" }}
    //               className={cx("link_text")}
    //             >
    //               {item.name}
    //             </div>
    //           </NavLink>
    //         ))}
    //       </div>
    //     </div>
    //   )}
    // </div>
  );
}

export default Sidebar;
