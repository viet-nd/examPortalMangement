import { createStore, combineReducers, applyMiddleware } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import thunk from "redux-thunk";
import { loginReducer, registerReducer } from "./reducers/authReducer";
import { categoriesReducer } from "./reducers/categoriesReducer";
import { questionsReducer } from "./reducers/questionsReducer";
import { quizResultReducer } from "./reducers/quizResultReducer";
import { quizzesReducer } from "./reducers/quizzesReducer";
import { subjectsReducer } from "./reducers/subjectsReducer";
import { subClassReducer } from "./reducers/subClassReducer";

const reducer = combineReducers({
  loginReducer: loginReducer,
  registerReducer: registerReducer,
  categoriesReducer: categoriesReducer,
  subjectsReducer: subjectsReducer,
  subClassReducer: subClassReducer,
  quizzesReducer: quizzesReducer,
  questionsReducer: questionsReducer,
  quizResultReducer: quizResultReducer,
});

const middleware = [thunk];
const store = createStore(
  reducer,
  composeWithDevTools(applyMiddleware(...middleware))
);

export default store;
