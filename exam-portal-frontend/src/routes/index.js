import routesConfig from "~/config/routes";
import AdminAddCategoryPage from "~/pages/admin/categories/AdminAddCategoryPage";
import AdminCategoriesPage from "~/pages/admin/categories/AdminCategoriesPage";
import AdminUpdateCategoryPage from "~/pages/admin/categories/AdminUpdateCategoryPage";
import AdminProfilePage from "~/pages/admin/AdminProfilePage";
import LoginPage from "~/pages/LoginRegister/LoginPage";
import RegisterPage from "~/pages/LoginRegister/RegisterPage";
import AdminQuizzesPage from "~/pages/admin/quizzes/AdminQuizzesPage";
import AdminAddQuiz from "~/pages/admin/quizzes/AdminAddQuiz";
import AdminUpdateQuiz from "~/pages/admin/quizzes/AdminUpdateQuiz";
import AdminQuestionsPage from "~/pages/admin/questions/AdminQuestionsPage";
import AdminAddQuestionsPage from "~/pages/admin/questions/AdminAddQuestionsPage";
import AdminUpdateQuestionPage from "~/pages/admin/questions/AdminUpdateQuestionPage";
import UserProfilePage from "~/pages/users/UserProfilePage";
import UserQuizzesPage from "~/pages/users/UserQuizzesPage";
import UserQuizManualPage from "~/pages/users/UserQuizManualPage";
import UserQuestionsPage from "~/pages/users/UserQuestionsPage";
import UserQuizResultPage from "~/pages/users/UserQuizResultPage";
import UserJoinClassesPage from "~/pages/users/UserJoinClassesPage";

const publicRoutes = [
  { path: routesConfig.home, component: LoginPage },
  { path: routesConfig.login, component: LoginPage },
  { path: routesConfig.register, component: RegisterPage },
  { path: routesConfig.adminProfile, component: AdminProfilePage },
  { path: routesConfig.adminCategories, component: AdminCategoriesPage },
  { path: routesConfig.adminAddCategory, component: AdminAddCategoryPage },
  {
    path: routesConfig.adminUpdateCategoryId,
    component: AdminUpdateCategoryPage,
  },
  { path: routesConfig.adminQuizzes, component: AdminQuizzesPage },
  { path: routesConfig.adminAddQuiz, component: AdminAddQuiz },
  { path: routesConfig.adminUpdateQuizId, component: AdminUpdateQuiz },
  { path: routesConfig.adminQuestions, component: AdminQuestionsPage },
  { path: routesConfig.adminAddQuestion, component: AdminAddQuestionsPage },
  {
    path: routesConfig.adminUpdateQuestion,
    component: AdminUpdateQuestionPage,
  },
  { path: routesConfig.profile, component: UserProfilePage },
  { path: routesConfig.quizzes, component: UserQuizzesPage },
  { path: routesConfig.quiz, component: UserQuizzesPage },
  { path: routesConfig.quizManual, component: UserQuizManualPage },
  { path: routesConfig.questions, component: UserQuestionsPage },
  { path: routesConfig.quizResults, component: UserQuizResultPage },
  { path: routesConfig.joinClasses, component: UserJoinClassesPage },
];

const privateRoutes = [];

export { publicRoutes, privateRoutes };
