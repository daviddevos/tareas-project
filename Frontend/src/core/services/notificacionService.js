import Swal from 'sweetalert2';

const swalSuccess = params =>
	Swal.mixin({
		title:
			params && params.message
				? params.message
				: 'Acción realizada correctamente',
		icon: 'success',
		position: 'top',
		customClass: {
			title: 'notif-success',
			popup: 'swal2-popup swal2-toast swal2-icon-success swal2-show'
		},
		showConfirmButton: false,
		timer: 8000,
		timerProgressBar: true,
		onOpen: toast => {
			toast.addEventListener('mouseenter', Swal.stopTimer);
			toast.addEventListener('mouseleave', Swal.resumeTimer);
		}
	});

const swalError = params =>
	Swal.mixin({
		title: params ? 'Error: ' : 'No ha sido posible procesar su solicitud :(',
		text: params && params.message ? params.message : '',
		icon: 'error',
		position: 'top',
		customClass: {
			title: 'notif-error',
			popup: 'swal2-popup swal2-toast swal2-icon-success swal2-show'
		},
		showConfirmButton: false,
		timer: 4000,
		timerProgressBar: true,
		allowOutsideClick: false,
		onOpen: toast => {
			toast.addEventListener('mouseenter', Swal.stopTimer);
			toast.addEventListener('mouseleave', Swal.resumeTimer);
		}
	});

export const notificationError = props => swalError(props).fire();
export const notificationSuccess = params => swalSuccess(params).fire();
