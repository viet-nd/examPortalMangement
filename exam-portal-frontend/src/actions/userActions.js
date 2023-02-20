import userServices from "~/services/userServices";
import * as authConstants from "../constants/authConstants";

export const updateUser = async (dispatch, userId, user, token) => {
  // dispatch({ type: authConstants.USER_REGISTER_REQUEST });
  const data = await userServices.updateUser(userId, user, token);
  if (data) {
    return dispatch({
      type: authConstants.USER_LOGIN_SUCCESS,
      payload: data.data,
    });
  } else {
    return dispatch({
      type: authConstants.USER_LOGIN_FAILURE,
      payload: data.data,
    });
  }
};

export const changePassword = async (
  dispatch,
  userId,
  oldPassword,
  newPassword,
  token
) => {
  //   dispatch({ type: authConstants.USER_REGISTER_REQUEST });
  const { data } = await userServices.changePassword(
    userId,
    oldPassword,
    newPassword,
    token
  );
  if (data) {
    return dispatch({
      type: authConstants.USER_LOGIN_SUCCESS,
      payload: data,
    });
  } else {
    return dispatch({
      type: authConstants.USER_LOGIN_FAILURE,
      payload: data,
    });
  }
};
