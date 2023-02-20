import classNames from "classnames/bind";
import { useEffect, useState } from "react";

import styles from "./Image.module.scss";
import images from "~/assets/images";

const cx = classNames.bind(styles);

function Image({ src, alt, ...props }) {
  const [fallback, setFallback] = useState("");

  const handleError = () => {
    setFallback(images.noImage);
  };

  return (
    <img
      className={cx("image")}
      src={src || fallback}
      alt={"Image" || alt}
      {...props}
      onError={handleError}
    />
  );
}

export default Image;
