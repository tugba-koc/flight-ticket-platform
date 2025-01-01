export const userInitialState = {
  role: '',
  isLoggedIn: false,
};

export const userReducer = (state = userInitialState, action) => {
  switch (action.type) {
    case 'SET_ROLE':
      return {
        ...state,
        role: action.payload,
      };
    case 'SET_LOGIN':
      return {
        ...state,
        isLoggedIn: action.payload,
      };
    default:
      return state;
  }
};
