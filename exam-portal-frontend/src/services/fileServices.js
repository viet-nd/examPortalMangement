import axios from "axios";

const uploadAvatar = async (file) => {
  try {
    const formData = new FormData();
    formData.append('file', file);
    const config = {
        headers: {
          'content-type': 'multipart/form-data',
        },
      };

    const { data } = await axios.post("/api/file/avatar", formData, config);
    if (data) {
      console.log(
        "fileService:uploadAvatar() Success: ",
        data
      );
      return { data };
    } else {
      console.error("fileService:uploadAvatar() Error: ");
      return { data };
    }
  } catch (error) {
    console.error("fileService:uploadAvatar() Error: ", error.response.statusText);
    return { isRegistered: false, error: error.response.statusText };
  }
};

const deleteImage = async (fileCode, token) => {
  try {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    const data  = await axios.delete(`/api/file/${fileCode}?type=avatar`, config);
    console.log("fileService:deleteImage() Success: ", data);
    return data;
  } catch (error) {
    console.error(
      "fileService:deleteImage() Error: ",
      error.response.statusText
    );
    return null;
  }
};

const fileServices = { uploadAvatar, deleteImage };
export default fileServices;