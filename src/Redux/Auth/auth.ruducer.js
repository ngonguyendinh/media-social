import {
  GET_PROFILE_REQUEST,
  GET_PROFILE_SUCCESS,
  LOGIN_FAILURE,
  LOGIN_REQUEST,
  LOGIN_SUCCESS,
  LOGOUT_SUCCESS,
  REGISTER_FAILURE,
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
  UPDATE_PROFILE_REQUEST,
  UPDATE_PROFILE_SUCCESS,
  UPDATE_PROFILE_FAILURE,
  SEARCH_USER_SUCCESS,
  SEARCH_USER_REQUEST,
  SEARCH_USER_FAILURE,
  FORGOT_PASSWORD_SUCCESS,
  FORGOT_PASSWORD_REQUEST,
  FORGOT_PASSWORD_FAILURE,
  VERIFY_PASSWORD_CODE_SUCCESS, VERIFY_PASSWORD_CODE_REQUEST, VERIFY_PASSWORD_CODE_FAILURE,

} from "./auth.actionType";

const initialState = {
  jwt: localStorage.getItem("jwt") || null, // Load từ localStorage
  user: null,
  error: null,
  loading: false,
  searchUser: [],
  forgotPasswordSuccess: false,
};

export const authReducer = (state = initialState, action) => {
  switch (action.type) {
    case LOGIN_REQUEST: // xử lý trạng thái đăng nhập
    case REGISTER_REQUEST: // xử lý trạng thái đăng ký
    case GET_PROFILE_REQUEST: // xử lý trạng thái lấy thông tin profile
    case UPDATE_PROFILE_REQUEST: // xử lý trạng thái update thông tin profile
    case SEARCH_USER_REQUEST:
    case VERIFY_PASSWORD_CODE_REQUEST:
      return { ...state, loading: true, error: null };

    case FORGOT_PASSWORD_REQUEST:
      return { ...state, loading: true, error: null, forgotPasswordSuccess: false };

    case FORGOT_PASSWORD_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        forgotPasswordSuccess: true,
      };

    case FORGOT_PASSWORD_FAILURE:
      return { ...state, loading: false, error: action.payload, forgotPasswordSuccess: false };

    case GET_PROFILE_SUCCESS:
    case UPDATE_PROFILE_SUCCESS:
    case VERIFY_PASSWORD_CODE_SUCCESS:
      return { ...state, user: action.payload, loading: false, error: null };

    case LOGIN_SUCCESS:
    case REGISTER_SUCCESS:
      return { ...state, jwt: action.payload, loading: false, error: null }

    case SEARCH_USER_SUCCESS:
      return { ...state, searchUser: action.payload, loading: false, error: null }

    case LOGIN_FAILURE:
    case REGISTER_FAILURE: // xử lý lỗi khi đăng ký 
    case UPDATE_PROFILE_FAILURE: // Xử lý lỗi cập nhật profile
    case SEARCH_USER_FAILURE:
    case VERIFY_PASSWORD_CODE_FAILURE:
      return { ...state, loading: false, error: action.payload };

    case LOGOUT_SUCCESS:
      return { ...state, jwt: null, user: null };

    default:
      return state;
  }
};
