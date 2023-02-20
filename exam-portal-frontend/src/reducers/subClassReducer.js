import * as subClassConstants from "../constants/subClassConstants";

const subClassesInitialState = {
  loading: false,
  error: null,
  subClasses: [],
  isAdded: false,
  isDeleted: false,
  isUpdated: false,
};

export const subClassReducer = (state = subClassesInitialState, action) => {
    let temp = [];
    switch (action.type) {
      case subClassConstants.FETCH_SUB_CLASSES_REQUEST:
        return {
          ...state,
          loading: true,
        };
  
      case subClassConstants.FETCH_SUB_CLASSES_SUCCESS:
        return {
          ...state,
          loading: false,
          subClasses: action.payload,
        };
  
      case subClassConstants.FETCH_SUB_CLASSES_FAILURE:
        return {
          ...state,
          loading: false,
          error: action.payload,
        };
  
      case subClassConstants.DELETE_SUB_CLASS_REQUEST:
        return {
          ...state,
          loading: true,
        };
  
      case subClassConstants.DELETE_SUB_CLASS_SUCCESS:
        temp = state.subClasses;
        for (let i = 0; i < temp.length; i++) {
          if (temp[i].sunClassId == action.payload) {
            temp.splice(i, 1);
          }
        }
        return {
          ...state,
          loading: false,
          isDeleted: true,
          subClasses: temp,
        };
  
      case subClassConstants.DELETE_SUB_CLASS_FAILURE:
        return {
          ...state,
          loading: false,
          error: action.payload,
        };
  
      case subClassConstants.ADD_SUB_CLASS_REQUEST:
        return {
          ...state,
          loading: true,
        };
  
      case subClassConstants.ADD_SUB_CLASS_SUCCESS:
        return {
          ...state,
          loading: false,
          isAdded: true,
          subClasses: [...state.subClasses, action.payload],
        };
  
      case subClassConstants.ADD_SUB_CLASS_FAILURE:
        return {
          ...state,
          loading: false,
          error: action.payload,
        };
  
      case subClassConstants.UPDATE_SUB_CLASS_REQUEST:
        return {
          ...state,
          loading: true,
        };
  
      case subClassConstants.UPDATE_SUB_CLASS_SUCCESS:
        temp = state.quizzes;
        temp.forEach((quiz, index) => {
          if (quiz.quizId == action.payload.quizId) {
            temp.splice(index,1, action.payload);
          }
        });
        return {
          ...state,
          loading: false,
          isUpdated: true,
          subClasses: temp,
        };
  
      case subClassConstants.UPDATE_SUB_CLASS_FAILURE:
        return {
          ...state,
          loading: false,
          error: action.payload,
        };
  
      default:
        return state;
    }
  };
