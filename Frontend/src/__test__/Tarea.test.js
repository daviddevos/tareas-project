import { mount } from 'enzyme';
import React from 'react';
import Tarea from '../components/tareas/Tarea';
import { dataState, LOAD_DATA } from '../store/redux.actions';

it('El component renderiza', () => {
	const wrapper = mount(<Tarea />);
	expect(wrapper).toMatchSnapshot();
});

it('OnClick botón nuevo, modal de edición se muestra', () => {
	const wrapper = mount(<Tarea />);
	const nuevoBtn = wrapper.find('#nuevoBtn');
	nuevoBtn.simulate('click');
	expect(wrapper.find('#modalEdit').at(0).props().isOpen).toBeTruthy();
});

it('OnClick botón cancelar, modal de edición se oculta', () => {
	const wrapper = mount(<Tarea />);
	const nuevoBtn = wrapper.find('#nuevoBtn');

	nuevoBtn.simulate('click');
	expect(wrapper.find('#modalEdit').at(0).props().isOpen).toBeTruthy();

	const cancelarBtn = wrapper.find('#cancelarBtn');
	cancelarBtn.simulate('click');
	expect(wrapper.find('#modalEdit').at(0).props().isOpen).not.toBeTruthy();
});

it('Validar campos tareaForm-modalEdit', () => {
	const wrapper = mount(<Tarea />);
	const nuevoBtn = wrapper.find('#nuevoBtn');
	nuevoBtn.simulate('click');

	const tareaForm = wrapper.find('AvForm#tareaForm');
	expect(tareaForm.length).toBeGreaterThan(0);

	//Validación de campos
	expect(
		tareaForm.find('AvInput#identificador Input').props().required
	).toBeTruthy();
	expect(tareaForm.find('AvInput#identificador Input').props().minLength).toBe(
		1
	);
	expect(tareaForm.find('AvInput#identificador Input').props().maxLength).toBe(
		15
	);
	expect(tareaForm.find('AvInput#identificador Input').props().pattern).toMatch(
		'^[0-9]+$'
	);

	expect(
		tareaForm.find('AvInput#descripcion Input').props().required
	).toBeTruthy();
	expect(tareaForm.find('AvInput#descripcion Input').props().minLength).toBe(1);
	expect(tareaForm.find('AvInput#descripcion Input').props().maxLength).toBe(
		255
	);

	expect(tareaForm.find('input#fechaCreacion').props().readOnly).toBeTruthy();
	expect(tareaForm.find('input#fechaCreacion').props().value).toBeDefined();

	expect(tareaForm.find('input#horaCreacion').props().readOnly).toBeTruthy();
	expect(tareaForm.find('input#horaCreacion').props().value).toBeDefined();

	expect(tareaForm.find('AvRadioGroup#vigente').props().required).toBeTruthy();
});

it('Guardar estado de tarea', () => {
	const tareas = [
		{
			identificador: 1,
			descripcion: 'prueba1',
			fechaCreacion: '2021-02-01T07:00:00.000-0500',
			vigente: true
		}
	];
	const expectedAction = {
		type: LOAD_DATA,
		value: tareas
	};
	expect(dataState(tareas)).toEqual(expectedAction);
});
