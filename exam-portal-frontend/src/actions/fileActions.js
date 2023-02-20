import fileServices from "../services/fileServices";

export const uploadAvatar = async (file) => {
//   dispatch({ type: authConstants.USER_REGISTER_REQUEST });
  const { data } = await fileServices.uploadAvatar(file);
  return data;
};

export const deleteImage = async (fileCode, token) => {
  //   dispatch({ type: authConstants.USER_REGISTER_REQUEST });
    const { data } = await fileServices.deleteImage(fileCode, token);
    return data;
  };