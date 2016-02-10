/**
 * Created by xavier on 05/02/16.
 */
var app = angular.module('personesapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl'
    }).when('/create', {
        templateUrl: 'views/create.html',
        controller: 'CreateCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http) {
    $http.get('/persona').success(function (data) {
        $scope.persones = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
});

app.controller('CreateCtrl', function ($scope, $http, $location) {
    $scope.persona = {
        nom: '',
        cognom: ''
    };

    $scope.createPerson = function () {
        console.log($scope.persona);
        $http.post('/persona/'+$scope.persona.nom+"/"+$scope.persona.cognom, $scope.persona).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});
