export const userInitialState = {
  role: '',
  isModalOpen: false,
  formError: {
    email: '',
    turkishId: '',
    phoneNumber: '',
  },
  formData: {
    name: '',
    surname: '',
    email: '',
    turkishId: '',
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
    case 'SET_FORM_DATA':
      return {
        ...state,
        formData: {
          ...state.formData,
          ...action.payload,
        },
      };
    default:
      return state;
  }
};
