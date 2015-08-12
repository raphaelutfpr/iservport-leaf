(function() {
	app = angular.module('security', ['ui.bootstrap', 'app.layout', 'angular-loading-bar', 'app.services'])
	/**
	 * Resources service.
	 */
	.factory('resources', ['$resource', function($resource) {
		var service = {};
		service.login = $resource("/login/",{ username:"@username", password: "@password", rememberme:"@rememberme"},{
			login : { method:'POST',  headers : {'Content-Type': 'application/x-www-form-urlencoded'}}
		});
		service.signup = $resource("/signup/" , {principal:"@principal", tempEmail:"@tempEmail"});
		service.recovery = $resource("/recovery/", { email:"@email"}, {
			update: { method: 'PUT' }, create: { method: 'POST' }
		});
		return service;
	}]) 
	/**
	 * Angular security controller
	 */
	.controller('SecurityController', ['$scope', '$window', 'resources' , 'genericServices', 'securityServices'
	                                  , function($scope, $window, resources, genericServices, securityServices) {
	
		$scope.baseName = "home";
		$scope.menuName = "home";
		$scope.email = email!='undefined'?email:'' ;
		
		/**
		 * Autorização
		 */
		$scope.authList =[];
		defineAuthorities();
		function defineAuthorities(){
			securityServices.getAuthorizedRoles(null).success(function(data, status, headers, config) {
				$scope.authList = data.content;
			});
		}
		
		$scope.cannotChangePassword = true;
		
		$scope.$watch('[password,cpassword]', function () { 
			$scope.cannotChangePassword = !genericServices.verifyPassword($scope.password, $scope.cpassword);
		}, true);
		
		$scope.isAuthorized =function(role, ext){
			return securityServices.isAuthorized($scope.authList, role, ext);
		}
		
		$scope.login = function(usernameVal,passwordVal){
//			$scope.formLogin = {"username" :usernameVal, "password" :passwordVal };
			$scope.logged = resources.login.login($.param({username :usernameVal, password :passwordVal })); 
			$scope.logged.$promise.then(
					function(data, getReponseHeaders) {
						$window.location.href = "/";
					}
			);
		}
		
		$scope.saveEmail = function(emailVal){
			resources.signup.get({tempEmail:emailVal});
		}
		
		//form initializer
		$scope.create = function(){
			$scope.form =  resources.signup.get({create:true});
			$scope.form.$promise.then(function(data) {
				data.email = $scope.email;
				$scope.form = data;
			});
		} 
		$scope.create();
		$scope.signUp = function(){
			$scope.form.password = 'save';
			$scope.returnCode = resources.signup.save($scope.form);
			$scope.returnCode.$promise.then(function(data) {
				console.log(data);
			});
		};
	
		$scope.updateUser = function(){
			$scope.form.email = $scope.email;
			resources.signup.save($scope.form);
		};
		
		$scope.passwordEmail = function(val){
			resources.password.save({email:val});
		}
		
		$scope.updatePassword = function(){
			$scope.form.email = $scope.email;
			resources.recovery.update($scope.form);
		}
		
		$scope.passwordMatches = function(){
			return $scope.cpassword === $scope.form.password;
		}

		/**
		 * email validator:
		 * o 'emailTester' utiliza a validação do angular pra verificar o email,
		 * após isso vai até a base,
		 * e então retorna se pode criar um usuário novo com o email passado.
		 * 
		 */
		$scope.emailOk = false;	
		$scope.userNotExists = true;
		$scope.canRecover = false;
		$scope.showAlerts = false;
		$scope.emailTester=function(){
			//validação do angular
			if(!$scope.form.principal.$valid){
				$scope.emailOk = false;
				$scope.userNotExists = true;
			}else{
				//faz chamada ao servidor apenas se já passou no angular
				console.log($scope.principal);
				$scope.signUpResource.get({principal:$scope.principal}).$promise.then(function(data) {
					$scope.emailOk = data.canCreate;
					$scope.userNotExists = data.canCreate;
				});
			}
			$scope.showAlerts = true;
			
		}
		 
	}]); // SecurityController
	
} )();
