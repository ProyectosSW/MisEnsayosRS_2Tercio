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
        
        $scope.seleccion=false;
        $scope.opcionEsta=false;
        $scope.opcionSala=false;
        $scope.establecimientoNombre="";
        $scope.establecimientoSeleccionado="";
        $scope.opcionesRegistro=["Registrar establecimiento", "Registrar sala"];
        $scope.horarios=[700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000];
        //$scope.establecimiento={"idEstablecimiento": 1,"nombre": "nombre","nit": "nit","descripcion": "descripcion","direccion": "direccion","horaInicio": 700,"horaCierre": 2000,"multa": 0.0,"localidad": "localidad","telefono": "telefono"};
        $scope.establecimiento={};
        
        $scope.solicitudRegistro = function () {
            alert("$scope.establecimiento"+$scope.establecimiento);
            for (key in $scope.establecimiento) {
                alert(""+$scope.establecimiento.key);
             }
        }
        
        $scope.alarma = function () {
            alert("alarma");
        }
        
        $scope.eleccionRegistro = function (opcion) {
            if(opcion.toString()==="Registrar establecimiento"){
                $scope.opcionEsta=true;
                $scope.opcionSala=false;                
            }else if(opcion.toString()==="Registrar sala"){
                $scope.opcionEsta=false;
                $scope.opcionSala=true;                                
            }
        };
        
        $scope.consultarSalaPorEstablecimiento=function (esta){
            $scope.seleccion=true;
            $scope.establecimientoNombre=esta.nombre;
            MisEnsayosRSRestAPI.consultarSalaPorEstablecimiento(esta.nombre).then(
                //promise success
                function(response){
                    console.log(response.data);                    
                    $scope.salaPorEstablecimiento=response.data;
                },
                //promise error
                function(response){
                    console.log('Unable to get data from REST API:'+response);
                }
            );
            MisEnsayosRSRestAPI.consultaEstablecimientoId(esta.idEstablecimiento).then(
                //promise success
                function(response){
                    $scope.establecimientoSeleccionado=new Object();
                    delete response.data.idEstablecimiento;
                    delete response.data.nit;
                    delete response.data.instrumentos;
                    delete response.data.salas;
                    $scope.establecimientoSeleccionado=response.data;
                    $scope.establecimientoSeleccionadoLlaves=Object.keys(response.data);
                    
                },
                //promise error
                function(response){
                    console.log('Unable to get data from REST API:'+response);
                }
            );
    
        }

        $scope.registrarEstablecimiento = function (idEstablecimiento, nombre, nit, descripcion, direccion, horaInicio, horaCierre, multa, localidad, telefono){            
            MisEnsayosRSRestAPI.registrarEstablecimiento(idEstablecimiento, nombre, nit, descripcion, direccion, horaInicio, horaCierre, multa, localidad, telefono).then(
                //promise success
                function(response){
                    console.log(response.data);                    
                },
                //promise error
                function(response){
                    console.log('Unable to get data from REST API:'+response);
                }
            );        
        }
        
        this.registrarSala = function (idSala, idEstablecimiento, nombreEstablecimiento, nit, descripcionEstablecimiento, direccion, horaInicio, horaCierre, multa, localidad, telefono, precio, descripcion, nombre) {
            MisEnsayosRSRestAPI.registrarEstablecimiento(idSala, idEstablecimiento, nombreEstablecimiento, nit, descripcionEstablecimiento, direccion, horaInicio, horaCierre, multa, localidad, telefono, precio, descripcion, nombre).then(
                //promise success
                function(response){
                    console.log(response.data);                    
                },
                //promise error
                function(response){
                    console.log('Unable to get data from REST API:'+response);
                }
            );        
            
        }        
        
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

