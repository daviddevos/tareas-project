import axios from 'axios';
import config from './config';
import { notificationError } from './notificacionService';

const todas = () => {
	return axios
		.get(config.baseUrl + 'todas')
		.then(response => {
			return response;
		})
		.catch(error => {
			notificationError(error.response.data);
		});
};

const actualizarLista = tareas => {
	return axios
		.post(config.baseUrl + 'actualizarLista', tareas)
		.then(response => {
			return response;
		})
		.catch(error => {
			notificationError(error.response.data);
		});
};

export const tareaService = {
	todas,
	actualizarLista
};
