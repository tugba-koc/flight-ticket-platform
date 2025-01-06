export const userInitialState = {
  role: '',
  Modal: {
    isModalOpen: false,
    message: null,
  },
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
  filterData: [],
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
        Modal: {
          isModalOpen: action.payload.view,
          message: action.payload.message,
        },
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
    case 'SET_FILTER_DATA':
      return {
        ...state,
        filterData: action.payload,
      };
    default:
      return state;
  }
};
