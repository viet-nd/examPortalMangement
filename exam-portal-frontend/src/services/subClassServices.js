import axios from "axios";

const fetchSubClass = async (subClassId, token) => {
  try {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };

    let subClasses = null;
    if (subClassId === null) {
      const { data } = await axios.get("/api/subClass/", config);
      subClasses = data;
    } else {
      const { data } = await axios.get(`/api/subClass/${subClassId}`, config);
      subClasses = data;
    }
    console.log("subClassesServices:fetchSubClass() Success: ", subClasses);
    return subClasses;
  } catch (error) {
    console.error(
      "subClassesServices:fetchSubClass() Error: ",
      error.response.statusText
    );
    return error.response.statusText;
  }
};

const fetchSubClassByUserId = async (userId, token) => {
  try {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };

    let subClasses = null;

    const { data } = await axios.get(`/api/subClass/?userId=${userId}`, config);
    subClasses = data;

    console.log("subClassesServices:fetchSubClass() Success: ", subClasses);
    return subClasses;
  } catch (error) {
    console.error(
      "subClassesServices:fetchSubClass() Error: ",
      error.response.statusText
    );
    return error.response.statusText;
  }
};

// const addQuiz = async (quiz, token) => {
//   try {
//     const config = {
//       headers: { Authorization: `Bearer ${token}` },
//     };
//     const { data } = await axios.post("/api/quiz/", quiz, config);
//     console.log("quizzesServices:addQuiz()  Success: ", data);
//     return { data: data, isAdded: true, error: null };
//   } catch (error) {
//     console.error(
//       "quizzesServices:addQuiz()  Error: ",
//       error.response.statusText
//     );
//     return { data: null, isAdded: false, error: error.response.statusText };
//   }
// };

// const deleteQuiz = async (quizId, token) => {
//   try {
//     const config = {
//       headers: { Authorization: `Bearer ${token}` },
//     };
//     const { data } = await axios.delete(`/api/quiz/${quizId}/`, config);
//     console.log("quizzesServices:deleteQuiz()  Success: ", data);
//     return {
//       isDeleted: true,
//       error: null,
//     };
//   } catch (error) {
//     console.error(
//       "quizzesServices:deleteQuiz()  Error: ",
//       error.response.statusText
//     );
//     return {
//       isDeleted: false,
//       error: error.response.statusText,
//     };
//   }
// };

// const updateQuiz = async (quiz, token) => {
//   console.log(quiz);
//   try {
//     const config = {
//       headers: { Authorization: `Bearer ${token}` },
//     };
//     const { data } = await axios.put(`/api/quiz/${quiz.quizId}/`, quiz, config);
//     console.log("quizzesServices:updateQuiz()  Success: ", data);
//     return {
//       data: data,
//       isUpdated: true,
//       error: null,
//     };
//   } catch (error) {
//     console.error(
//       "quizzesServices:updateQuiz()  Error: ",
//       error.response.statusText
//     );
//     return {
//       data: null,
//       isUpdated: false,
//       error: error.response.statusText,
//     };
//   }
// };

const subClassessService = { fetchSubClass, fetchSubClassByUserId };
export default subClassessService;
