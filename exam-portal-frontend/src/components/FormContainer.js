import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import classNames from "classnames/bind";
import styles from "./FormContainer.module.scss";

const cx = classNames.bind(styles);

const FormContainer = ({ children }) => {
  return (
    // <div className={cx("wrapper")}>
    //   <div className={cx("container")}>
    //     <div className={cx("content")}>{children}</div>
    //   </div>
    // </div>
    <Container>
      <Row className='justify-content-md-center'>
        <Col xs={12} md={6}>
          {children}
        </Col>
      </Row>
    </Container>
  );
};

export default FormContainer;
