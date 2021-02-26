import { combineReducers } from 'redux';

/** Import de todas las Acciones */
import { LOAD_DATA } from './redux.actions';

import { tareaService } from '../core/services/tarea.service';

/**Estado general de la App */
let generalState = {
	data: []
};

const generalReducer = (state = generalState, action) => {
	switch (action.type) {
		case LOAD_DATA:
			return { ...state, data: action.value };
		default:
			return state;
	}
};

export default combineReducers({ generalReducer });
