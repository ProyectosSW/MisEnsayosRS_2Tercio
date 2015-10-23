(function () {
    var app = angular.module('MisEnsayosRSApp', ['ngRoute','MisEnsayosRS']);

    app.config(function ($routeProvider) {
        $routeProvider
                .when('/audio', {
                    templateUrl: 'audio.html'
                })                
                .when('/about', {
                    templateUrl: 'about.html'
                })
                .when('/video', {
                    templateUrl: 'video.html'
                })
                .when('/tour-dates', {
                    templateUrl: 'tour-dates.html'
                })
                .when('/gallery', {
                    templateUrl: 'gallery.html'
                })
                .when('/contacts', {
                    templateUrl: 'contacts.html'                 
                });
    });
    
    app.controller('misensayosrscontroller', function ($scope,MisEnsayosRSRestAPI) {
        
        MisEnsayosRSRestAPI.consultarTodosEstablecimientos().then(
            //promise success
            function(response){
                console.log(response.data);                    
                $scope.todosEstablecimientos=response.data;
            },
            //promise error
            function(response){
                console.log('Unable to get data from REST API:'+response);
            }
        );
        
        MisEnsayosRSRestAPI.consultarEstablecimientosHabilitados().then(
            //promise success
            function(response){
                console.log(response.data);                    
                $scope.establecimientosHabilitados=response.data;
            },
            //promise error
            function(response){
                console.log('Unable to get data from REST API:'+response);
            }
        );

        MisEnsayosRSRestAPI.consultarTodosEstablecimientosSinHabilitar().then(
            //promise success
            function(response){
                console.log(response.data);                    
                $scope.consultarTodosEstablecimientosSinHabilitar=response.data;
            },
            //promise error
            function(response){
                console.log('Unable to get data from REST API:'+response);
            }
        );

        MisEnsayosRSRestAPI.consultarSalaPorEstablecimiento().then(
            //promise success
            function(response){
                console.log(response.data);                    
                $scope.salasPorEstablecimiento=response.data;
            },
            //promise error
            function(response){
                console.log('Unable to get data from REST API:'+response);
            }
        );        
        
    }
    );

})();

