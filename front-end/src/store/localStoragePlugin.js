export default (store) => {
  store.subscribe((mutation, state) => {
    const stateToPersist = {
      textStyle: state.textStyle,
      readingState: state.readingState,
    };

    localStorage.setItem("appState", JSON.stringify(stateToPersist));
  });

  const persistedState = localStorage.getItem("appState");
  if (persistedState) {
    const parsedState = JSON.parse(persistedState);
    store.replaceState(Object.assign({}, store.state, parsedState));
  }
};
