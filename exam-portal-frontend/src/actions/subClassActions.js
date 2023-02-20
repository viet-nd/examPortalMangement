import * as subClassConstants from "../constants/subClassConstants";
import subClassServices from "../services/subClassServices";

export const fetchSubClass = async (dispatch, subClassId = null, token) => {
  dispatch({ type: subClassConstants.FETCH_SUB_CLASSES_REQUEST });
  const data = await subClassServices.fetchSubClass(subClassId, token);
  if (data) {
    return dispatch({
      type: subClassConstants.FETCH_SUB_CLASSES_SUCCESS,
      payload: data,
    });
  } else {
    return dispatch({
      type: subClassConstants.FETCH_SUB_CLASSES_FAILURE,
      payload: data,
    });
  }
};

export const fetchSubClassByUserId = async (dispatch, userId, token) => {
  dispatch({ type: subClassConstants.FETCH_SUB_CLASSES_REQUEST });
  const data = await subClassServices.fetchSubClassByUserId(userId, token);
  if (data) {
    return dispatch({
      type: subClassConstants.FETCH_SUB_CLASSES_SUCCESS,
      payload: data,
    });
  } else {
    return dispatch({
      type: subClassConstants.FETCH_SUB_CLASSES_FAILURE,
      payload: data,
    });
  }
};

// export const addQuiz = async (dispatch, quiz, token) => {
//   dispatch({ type: quizzesConstants.ADD_QUIZ_REQUEST });
//   const { data, isAdded, error } = await quizzesServices.addQuiz(quiz, token);

//   if (isAdded) {
//     return dispatch({
//       type: quizzesConstants.ADD_QUIZ_SUCCESS,
//       payload: data,
//     });
//   } else {
//     return dispatch({
//       type: quizzesConstants.ADD_QUIZ_FAILURE,
//       payload: error,
//     });
//   }
// };

// export const deleteQuiz = async (dispatch, quizId, token) => {
//   dispatch({ type: quizzesConstants.DELETE_QUIZ_REQUEST });
//   const { isDeleted, error } = await quizzesServices.deleteQuiz(quizId, token);
//   if (isDeleted) {
//     return dispatch({
//       type: quizzesConstants.DELETE_QUIZ_SUCCESS,
//       payload: quizId,
//     });
//   } else {
//     return dispatch({
//       type: quizzesConstants.DELETE_QUIZ_FAILURE,
//       payload: error,
//     });
//   }
// };

// export const updateQuiz = async (dispatch, quiz, token) => {
//   dispatch({ type: quizzesConstants.UPDATE_QUIZ_REQUEST });
//   const { data, isUpdated, error } = await quizzesServices.updateQuiz(
//     quiz,
//     token
//   );
//   if (isUpdated) {
//     return dispatch({
//       type: quizzesConstants.UPDATE_QUIZ_SUCCESS,
//       payload: data,
//     });
//   } else {
//     return dispatch({
//       type: quizzesConstants.UPDATE_QUIZ_FAILURE,
//       payload: error,
//     });
//   }
// };
