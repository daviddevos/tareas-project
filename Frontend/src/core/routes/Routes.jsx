import React, { Component } from 'react';
import {
	BrowserRouter as Router,
	Redirect,
	Route,
	Switch
} from 'react-router-dom';
import Tarea from '../../components/tareas/Tarea';

class Routes extends Component {
	render() {
		return (
			<Router>
				<Switch>
					<Redirect exact from={'/'} to={'/tareas'} />
					<Route exact path={'/tareas'} component={Tarea} />
					<Redirect exact to={'/tareas'} />
				</Switch>
			</Router>
		);
	}
}
export default Routes;
