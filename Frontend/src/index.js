import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import Tarea from './components/tareas/Tarea';
import './index.css';
import reportWebVitals from './reportWebVitals';
import store from './store/redux.store';

ReactDOM.render(
	<Provider store={store}>
		<Tarea />
	</Provider>,

	document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
