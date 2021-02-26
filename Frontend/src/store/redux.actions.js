//Constants
export const LOAD_DATA = 'LOAD_DATA';

//Actions
export const dataState = state => ({
	type: LOAD_DATA,
	value: state
});
