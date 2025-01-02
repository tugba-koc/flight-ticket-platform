export const userInitialState = {
  role: '',
  isModalOpen: false,
  formError: {
    email: '',
    tcIdentityNumber: '',
    phoneNumber: '',
  },
};

export const userReducer = (state = userInitialState, action) => {
  switch (action.type) {
    case 'SET_ROLE':
      return {
        ...state,
        role: action.payload,
      };
    case 'SET_MODAL':
      return {
        ...state,
        isModalOpen: action.payload,
      };
    case 'SET_FORM_ERROR':
      return {
        ...state,
        formError: {
          ...state.formError,
          ...action.payload,
        },
      };
    default:
      return state;
  }
};
