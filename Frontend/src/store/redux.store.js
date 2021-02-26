import { applyMiddleware, createStore } from 'redux';
import rootReducer from './redux.reducer';
import { composeWithDevTools } from 'redux-devtools-extension';
import { loadState, saveState } from './localStorage';

const persistedState = loadState();

const store = createStore(
	rootReducer,
	persistedState,
	composeWithDevTools(applyMiddleware())
);

store.subscribe(() => {
	saveState(store.getState());
});

export default store;
