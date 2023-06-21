import {ng, template} from 'entcore';
import {IScope} from "angular";

declare let window: any;

interface IViewModel extends ng.IController {
	userId: string;
}

interface IMainScope extends IScope {
	vm: IViewModel;
}

class Controller implements IViewModel {

	userId: string;

	constructor(private $scope: IMainScope,
				private route: any,
				/*  inject service etc..just as we do in controller */) {
		this.$scope.vm = this;
	}

	$onInit() {
		this.route({
			defaultView: () => {
				template.open('main', `main`);
			}
		});
	}

	$onDestroy() {

	}
}

export const mainController = ng.controller('MainController', ['$scope', 'route', Controller]);
