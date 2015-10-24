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
        $scope.seleccion2=false;
        $scope.opcionEsta=false;
        $scope.opcionSala=false;
        $scope.establecimientoNombre="";
        $scope.establecimientoSeleccionado="";
        $scope.opcionesRegistro=["Registrar establecimiento", "Registrar sala"];
        $scope.horarios=[700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000];
        //$scope.establecimiento={"idEstablecimiento": 1,"nombre": "nombre","nit": "nit","descripcion": "descripcion","direccion": "direccion","horaInicio": 700,"horaCierre": 2000,"multa": 0.0,"localidad": "localidad","telefono": "telefono"};
        $scope.establecimiento={};
        $scope.sala={};
        $scope.sala.establecimiento={};
        $scope.establecimiento.idEstablecimiento=0;
        $scope.sala.idSala=0;
        
        $scope.solicitudRegistro = function () {
            alert("$scope.establecimiento"+$scope.establecimiento);
        }
        
        $scope.alarma = function () {
            alert("alarma");
        }
               
        $scope.seleccionEstablecimientoASala = function (esta) {
            $scope.seleccion2=true;
            $scope.sala.establecimiento=esta;   
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
            $scope.establecimientoNombre=esta;
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

        $scope.registrarEstablecimiento = function (){            
            MisEnsayosRSRestAPI.consultarCantidadEstablcimientos().then(
                //promise success
                function(response){
                    console.log(response.data);                    
                    $scope.establecimiento.idEstablecimiento=response.data+1;
                    alert($scope.establecimiento.idEstablecimiento);
                    alert($scope.establecimiento.nombre);
                    MisEnsayosRSRestAPI.registrarEstablecimiento($scope.establecimiento.idEstablecimiento, $scope.establecimiento.nombre, $scope.establecimiento.nit, $scope.establecimiento.descripcion, $scope.establecimiento.direccion, $scope.establecimiento.horaInicio, $scope.establecimiento.horaCierre, $scope.establecimiento.multa, $scope.establecimiento.localidad, $scope.establecimiento.telefono).then(
                        //promise success
                        function(response){
                            console.log(response.data);                    
                        },
                        //promise error
                        function(response){
                            console.log('Unable to get data from REST API:'+response);
                        }
                    );                            
                },
                //promise error
                function(response){
                    console.log('Unable to get data from REST API:'+response);
                }
            );        
        }
        
        this.registrarSala = function () {
            MisEnsayosRSRestAPI.consultarCantidadSalas().then(
                //promise success
                function(response){
                    console.log(response.data);                    
                    $scope.sala.idSala=response.data+1;
                    MisEnsayosRSRestAPI.registrarEstablecimiento($scope.sala.idSala, $scope.sala.establecimiento.idEstablecimiento, $scope.sala.establecimiento.nombreEstablecimiento, $scope.sala.establecimiento.nit, $scope.sala.establecimiento.descripcionEstablecimiento, $scope.sala.establecimiento.direccion, $scope.sala.establecimiento.horaInicio, $scope.sala.establecimiento.horaCierre, $scope.sala.establecimiento.multa, $scope.sala.establecimiento.localidad, $scope.sala.establecimiento.telefono, $scope.sala.precio, $scope.sala.descripcion, $scope.sala.nombre).then(
                        //promise success
                        function(response){
                            console.log(response.data);                    
                        },
                        //promise error
                        function(response){
                            console.log('Unable to get data from REST API:'+response);
                        }
                    );
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

