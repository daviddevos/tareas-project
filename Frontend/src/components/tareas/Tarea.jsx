import moment from 'moment';
import { useEffect, useState } from 'react';
import BootstrapTable from 'react-bootstrap-table-next';
import { withRouter } from 'react-router-dom';
import { tareaService } from '../../core/services/tarea.service';
import store from '../../store/redux.store';
import { dataState } from '../../store/redux.actions';
import { AvForm, AvField } from 'availity-reactstrap-validation';
import {
	Button,
	FormGroup,
	Modal,
	ModalBody,
	ModalFooter,
	ModalHeader
} from 'reactstrap';
import { CAMPO_INVALIDO, TAREA_LABELS } from './tareaUtils';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
	faArrowLeft,
	faEdit,
	faPlus,
	faSave,
	faSync
} from '@fortawesome/free-solid-svg-icons';
import AvRadioGroup from 'availity-reactstrap-validation/lib/AvRadioGroup';
import AvRadio from 'availity-reactstrap-validation/lib/AvRadio';
import withReactContent from 'sweetalert2-react-content';
import Swal from 'sweetalert2';
import { notificationError } from '../../core/services/notificacionService';

let customSwal = withReactContent(Swal);

const Tarea = props => {
	const [tareasList, setTareasList] = useState(
		store.getState().generalReducer.data
	);
	const [esEdicion, setEsEdicion] = useState(false);
	const [modalEdit, setModalEdit] = useState(false);
	const [tarea, setTarea] = useState({});

	const accionFormatter = (cell, row) => {
		return (
			<span onClick={e => e.stopPropagation()}>
				<Button
					outline
					color='danger'
					size='sm'
					className='col-12'
					onClick={() => {
						const tareaToDelete = { ...row };
						customSwal
							.fire({
								title: 'Confirmar',
								text: `¿Esta seguro de eliminar la tarea 
								${tareaToDelete.identificador}, ${tareaToDelete.descripcion}?`,
								showCancelButton: true,
								confirmButtonColor: '#004254',
								cancelButtonColor: '#03657c',
								confirmButtonText: `Si, Eliminar`,
								cancelButtonText: 'Cancelar'
							})
							.then(result => {
								if (result.value) {
									const newTareasList = tareasList.filter(
										elem =>
											Number(elem.identificador) !==
											Number(tareaToDelete.identificador)
									);
									setTareasList(newTareasList);
									store.dispatch(dataState(newTareasList));
								}
							});
					}}>
					Eliminar
				</Button>
			</span>
		);
	};

	const columns = [
		{
			dataField: 'identificador',
			text: 'Identificador'
		},
		{
			dataField: 'descripcion',
			text: 'Descripción'
		},
		{
			dataField: 'fechaCreacion',
			text: 'Fecha y hora de creación',
			formatter: cellContent => {
				if (cellContent != null && cellContent !== '')
					return moment(cellContent).format('DD/MM/YYYY HH:mm a');
				else return cellContent;
			}
		},
		{
			dataField: 'vigente',
			text: 'Vigente',
			formatter: cellContent => {
				return cellContent ? 'Si' : 'No';
			}
		},
		{
			dataField: '',
			text: 'Acciones',
			formatter: accionFormatter
		}
	];

	useEffect(() => {
		if (tareasList == null) {
			tareaService.todas().then(response => {
				setTareasList(response.data);
				store.dispatch(dataState(response.data));
			});
		}
	}, []);

	const rowEvents = {
		onClick: (e, row) => {
			setTarea({ ...row });
			setEsEdicion(true);
			toggle();
		}
	};

	const toggle = () => {
		setModalEdit(!modalEdit);
	};

	const handleChange = event => {
		let value = event.target.value;
		const name = event.target.name;
		if (name === 'vigente') {
			value = Boolean(value === 'Si');
		}
		setTarea({ ...tarea, [name]: value });
	};

	const handleValidSubmit = () => {
		const newTarea = { ...tarea };
		newTarea.fechaCreacion = new Date();
		const newTareasList = [...tareasList];
		const idxTarea = newTareasList.findIndex(
			elem => Number(elem.identificador) === Number(tarea.identificador)
		);
		if (esEdicion && idxTarea !== -1) {
			newTareasList[idxTarea] = newTarea;
		} else if (!esEdicion && idxTarea === -1) {
			newTareasList.push(newTarea);
		} else if (!esEdicion && idxTarea !== -1) {
			const params = {};
			params.message =
				'Ya existe una tarea con el identificador ' + tarea.identificador;
			notificationError(params);
		}
		setTareasList(newTareasList);
		store.dispatch(dataState(newTareasList));
		toggle();
	};

	const fragmentModalEdit = (
		<Modal isOpen={modalEdit} size='lg'>
			<ModalHeader>
				<FontAwesomeIcon icon={faEdit} />{' '}
				{esEdicion ? 'Editar tarea' : 'Nueva tarea'}
			</ModalHeader>
			<ModalBody>
				<FormGroup tag='fieldset'>
					<AvForm id='tareaForm' onValidSubmit={handleValidSubmit}>
						<div className='row pt-1'>
							<div className='col-12'>
								<div className='form-group row'>
									<label className='col-2 col-form-label'>
										{TAREA_LABELS.IDENTIFICADOR}
										<span
											style={{ color: 'red' }}
											title='Este campo es obligatorio.'>
											*
										</span>
									</label>
									<div className='col-10'>
										<AvField
											name='identificador'
											type='text'
											errorMessage={TAREA_LABELS.IDENTIFICADOR + CAMPO_INVALIDO}
											validate={{
												required: { value: true },
												pattern: {
													value: '^[0-9]+$'
												},
												minLength: { value: 1 },
												maxLength: { value: 15 }
											}}
											value={tarea.identificador || ''}
											onChange={handleChange}
											autoComplete='codigo'
											readOnly={esEdicion ? true : false}
										/>
									</div>
								</div>
								<div className='form-group row'>
									<label className='col-1 col-form-label'>
										{TAREA_LABELS.DESCRIPCION}
										<span
											style={{ color: 'red' }}
											title='Este campo es obligatorio.'>
											*
										</span>
									</label>
								</div>
								<div className='form-group row'>
									<div className='col-12'>
										<AvField
											name='descripcion'
											type='textarea'
											errorMessage={TAREA_LABELS.DESCRIPCION + CAMPO_INVALIDO}
											validate={{
												required: { value: true },
												minLength: { value: 1 },
												maxLength: { value: 255 }
											}}
											value={tarea.descripcion || ''}
											onChange={handleChange}
											autoComplete='descripcion'
										/>
									</div>
								</div>
								<div className='form-group row'>
									<label className='col-1 col-form-label'>
										{TAREA_LABELS.FECHA_CREACION}
									</label>
									<div className='col-3'>
										<input
											readOnly={true}
											className='form-control'
											type='text'
											value={
												tarea.fechaCreacion != null
													? moment(tarea.fechaCreacion).format('DD/MM/YYYY')
													: moment().format('DD/MM/YYYY')
											}
										/>
									</div>
									<label className='col-1 col-form-label'>
										{TAREA_LABELS.HORA_CREACION}
									</label>
									<div className='col-3'>
										<input
											readOnly={true}
											className='form-control'
											type='time'
											value={
												tarea.fechaCreacion != null
													? moment(tarea.fechaCreacion).format('HH:mm')
													: moment().format('HH:mm')
											}
										/>
									</div>
									<label className='col-1 col-form-label'>
										{TAREA_LABELS.VIGENTE}
									</label>
									<div className='col-3'>
										<AvRadioGroup
											inline
											name='vigente'
											required
											errorMessage={TAREA_LABELS.VIGENTE + CAMPO_INVALIDO}
											value={
												tarea.vigente != null
													? tarea.vigente
														? 'Si'
														: 'No'
													: null
											}
											onChange={handleChange}>
											<AvRadio label='Si' value={'Si'} />
											<AvRadio label='No' value={'No'} />
										</AvRadioGroup>
									</div>
								</div>
							</div>
						</div>
					</AvForm>
				</FormGroup>
			</ModalBody>
			<ModalFooter>
				<Button color='secondary' onClick={toggle}>
					<FontAwesomeIcon icon={faArrowLeft} /> Cancelar
				</Button>
				<Button color='primary' form='tareaForm'>
					<FontAwesomeIcon icon={faSave} /> Guardar
				</Button>
			</ModalFooter>
		</Modal>
	);

	return (
		<div className='flex-row'>
			<div className='col-12'>
				<div className='tool-bar rounded'>
					<div className='d-flex bd-highlight'>
						<div className='p-1 flex bd-highlight'>
							<div className='btn-group' role='group'>
								<button
									style={{ marginRight: '2px' }}
									className='btn-outline-primary rounded'
									onClick={() => {
										toggle();
										setEsEdicion(false);
										setTarea({});
									}}>
									<FontAwesomeIcon icon={faPlus} className='card-img-top' />
									{' Nuevo'}
								</button>
								<button
									style={{ marginRight: '2px' }}
									className='btn-outline-info rounded'
									onClick={() => {
										tareaService.todas().then(response => {
											setTareasList(response.data);
											store.dispatch(dataState(response.data));
										});
									}}>
									<FontAwesomeIcon icon={faSync} className='card-img-top' />
									{' Actualizar listado'}
								</button>
								<button
									style={{ marginRight: '2px' }}
									className='btn-outline-primary rounded'
									onClick={() => {
										tareaService.actualizarLista(tareasList).then(response => {
											setTareasList(response.data);
											store.dispatch(dataState(response.data));
										});
									}}>
									<FontAwesomeIcon icon={faSave} className='card-img-top' />
									{' Guardar cambios'}
								</button>
							</div>
						</div>
					</div>
				</div>
				<div className='bg-white'>
					<BootstrapTable
						keyField='identificador'
						data={tareasList}
						columns={columns}
						rowEvents={rowEvents}
						noDataIndication={'¡No existen tareas!'}
					/>
					{fragmentModalEdit}
				</div>
			</div>
		</div>
	);
};

export default withRouter(Tarea);
