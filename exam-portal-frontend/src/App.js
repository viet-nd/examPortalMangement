import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import "./App.css";
// import Header from "./components/Header";
import { publicRoutes } from "./routes";

// import { Header } from "~/components/Header1";
import { DefaultLayout } from "./layouts/DefaultLayout";

const App = () => {
  return (
    <Router>
      {/* <Header /> */}
      <DefaultLayout>
        <Routes>
          {publicRoutes.map((route, index) => {
            const Page = route.component;

            return <Route key={index} path={route.path} element={<Page />} />;
          })}
        </Routes>
      </DefaultLayout>
    </Router>

    // <Router>
    //   <Header />
    //   <Routes>
    //     <Route path="/" element={<LoginPage />} />
    //     <Route path="/login" element={<LoginPage />} />
    //     <Route path="/register" element={<RegisterPage />} />
    //     <Route path="/adminProfile" element={<AdminProfilePage />} />
    //     <Route path="/adminCategories" element={<AdminCategoriesPage />} />
    //     <Route path="/adminAddCategory" element={<AdminAddCategoryPage />} />
    //     <Route
    //       path="/adminUpdateCategory/:catId"
    //       element={<AdminUpdateCategoryPage />}
    //     />
    //     <Route path="/adminQuizzes" element={<AdminQuizzesPage />} />
    //     <Route path="/adminAddQuiz" element={<AdminAddQuiz />} />
    //     <Route path="/adminUpdateQuiz/:quizId" element={<AdminUpdateQuiz />} />
    //     <Route path="/adminQuestions" element={<AdminQuestionsPage />} />
    //     <Route path="/adminAddQuestion" element={<AdminAddQuestionsPage />} />
    //     <Route
    //       path="/adminUpdateQuestion/:quesId"
    //       element={<AdminUpdateQuestionPage />}
    //     />
    //     <Route path="/profile" element={<UserProfilePage />} />
    //     <Route path="/quizzes" element={<UserQuizzesPage />} />
    //     <Route path="/quiz/*" element={<UserQuizzesPage />} />
    //     <Route path="/quizManual/" element={<UserQuizManualPage />} />
    //     <Route path="/questions/" element={<UserQuestionsPage />} />
    //     <Route path="/quizResults/" element={<UserQuizResultPage />} />
    //   </Routes>
    // </Router>
  );
};

export default App;
