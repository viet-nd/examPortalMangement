import { useEffect, useState } from "react";
import { Button, Form } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { fetchSubject } from "~/actions/subjectsActions";
import { ClassItem } from "~/components/ClassItem";
import "./UserJoinClassesPage.css";

function UserJoinClassesPage() {
  const categoriesReducer = useSelector((state) => state.categoriesReducer);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const token = JSON.parse(localStorage.getItem("jwtToken"));
  const [subjects, setSubjects] = useState(null);
  const [subClasses, setSubClasses] = useState(null);
  const [selectorSubjectId, setSelectorSubjectId] = useState();
  const [selectorSubClassId, setSelectorSubClassId] = useState();


  const loginReducer = useSelector((state) => {
    console.log(state);
    return state.loginReducer;
  });

  const user = loginReducer.user;

  useEffect(() => {
    if (!localStorage.getItem("jwtToken")) navigate("/");
  }, []);

  useEffect(() => {
    console.log(user.userId);
    fetchSubject(dispatch, token).then((data) => {
      setSubjects(data.payload);
    });
  }, []);

  const onSelectSubjectHandler = (e) => {
    setSelectorSubjectId(e.target.value);
  }

  //Lấy subClass theo subId rồi lưu vào biến selectorSubClass
  // useEffect(() => {
  //   // console.log(user.userId);
  //   fetchSubject(dispatch, token).then((data) => {
  //     setSubjects(data.payload);
  //   });
  // }, [selectorSubjectId]);

  //   const [categories, setCategories] = useState(categoriesReducer.categories);fetchSubject
  const subjectss = [
    {
      title: "title",
      subId: 1,
    },
    {
      title: "title",
      subId: 1,
    },
    {
      title: "title",
      subId: 1,
    },
    {
      title: "title",
      subId: 1,
    },
  ];

  return (
    <div className="wrapper">
      <div className="header">
        <Form.Select
          className="choose-class"
          aria-label="Choose Subject"
          id="subject-select"
          onChange={onSelectSubjectHandler}
        >
          <option value="n/a">Choose Subject</option>
          {subjects ? (
            subjects.map((sub, index) => (
              <option key={index} value={sub.subId}>
                {sub.title}
              </option>
            ))
          ) : (
            <option value="">Choose one from below</option>
          )}
        </Form.Select>
        <Form.Select
          className="choose-class"
          aria-label="Choose Class"
          id="class-select"
          // onChange={onSelectCategoryHandler}
        >
          <option value="n/a">Choose Class</option>
          {subClasses ? (
            subClasses.map((subClass, index) => (
              <option key={index} value={subClass.subClassId}>
                {subClass.name}
              </option>
            ))
          ) : (
            <option value="">Choose one from below</option>
          )}
        </Form.Select>

        <Button>Join Class</Button>
      </div>
      <div className="body">
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
        <ClassItem title={"a"} description={"b"} />
      </div>
    </div>
  );
}

export default UserJoinClassesPage;
