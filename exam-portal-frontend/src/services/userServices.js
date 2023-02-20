import axios from "axios";

const updateUser = async (userId, user, token) => {
  try {
    // const formData = new FormData();
    // formData.append('file', file);
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };

    const { data } = await axios.put(`/api/user/${userId}`, user, config);

    console.log("userService:updateUser() Success: ", data);
    return { data: data };
  } catch (error) {
    console.error(
      "userService:updateUser() Error: ",
      error.response.statusText
    );
    return error.response.statusText;
  }
};

const changePassword = async (userId, oldPassword, newPassword, token) => {
  try {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };

    const { data } = await axios.put(
      `/api/user/${userId}?oldPassword=${oldPassword}&newPassword=${newPassword}`,
      config
    );

    console.log("userService:updateUser() Success: ", data);
    return { data: data };
  } catch (error) {
    console.error(
      "userService:updateUser() Error: ",
      error.response.statusText
    );
    return error.response.statusText;
  }
};

const userServices = { updateUser, changePassword };
export default userServices;
