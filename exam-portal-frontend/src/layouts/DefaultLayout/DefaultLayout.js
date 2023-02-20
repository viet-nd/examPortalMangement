import classNames from "classnames/bind";
import styles from "./DefaultLayout.module.scss";
import Header from "~/components/Header1/Header";
import { Sidebar } from "~/components/Sidebar";
import { useSelector } from "react-redux";

const cx = classNames.bind(styles);

function DefaultLayout({ children }) {
  // const userRole = useSelector((state) => {
  //   return state.loginReducer.user.authorities[0].authority;
  // });
  return (
    <div className={cx("wrapper")}>
      <Header />
      <div className={cx("container")}>
        <Sidebar />
        <div className={cx("content")}>{children}</div>
      </div>
    </div>
    // <div>{children}</div>
  );
}

export default DefaultLayout;
