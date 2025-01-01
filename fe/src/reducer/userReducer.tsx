export const userInitialState = {
  role: '',
  isModalOpen: false,
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
    default:
      return state;
  }
};
