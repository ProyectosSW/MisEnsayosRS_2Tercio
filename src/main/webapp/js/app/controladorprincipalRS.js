(function () {
    
    /**
     * 
     * @type @exp;angular@call;module
     */
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
        $scope.seleccion3=false;
        $scope.busqueda=false;
        $scope.seleccionHora=false;
        $scope.seleccionAdministrador=false;
        $scope.seleccionCliente=false;
        $scope.usuarioAutenticado=false;
        $scope.habilitarAlquiler=false;
        $scope.opcionEsta=false;
        $scope.opcionDeSala=false;
        $scope.respuestaprincipal=false;
        $scope.opcionBusqueda="vacio";
        $scope.establecimientoNombre="";
        $scope.nombreEstablecimientoBusqueda={};
        $scope.nombreInstrumento="";
        
        $scope.localidadEstablecimientoBusqueda="";
        $scope.establecimientoSeleccionado="";
        $scope.respuesta1="";
        $scope.respuesta2="";
        $scope.opcionesRegistro=["Registrar establecimiento", "Registrar sala"];
        $scope.localidades=["Usaquen", "Chapinero", "Santa Fe", "San Cristobal", "Usme", "Tunjuelito", "Bosa", "Kennedy", "Fontibon", "Engativa", "Suba", "Barrios Unidos", "Teusaquillo", "Los Martires", "Antonio NariÃ±o", "Puente Aranda", "La Candelaria", "Rafael Uribe Uribe", "Ciudad Bolivar", "Sumapaz"];
        $scope.horarios=[700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000];
        $scope.opcionesB=["Buscar por nombre", "Buscar por localidad"];

        $scope.establecimiento={};
        $scope.sala={};
        $scope.reservacion={};
        $scope.sala.establecimiento={};
        $scope.usuario={};
        $scope.cliente={};
        $scope.informacion={};
        $scope.ensayo={};
        $scope.usuariollaves={};        
        $scope.establecimiento.idEstablecimiento=0;
        $scope.sala.idSala=0;
        $scope.sala.nitValidacion="";
        $scope.cliente.idCliente=0;
        $scope.ensayo.descripcion="";
        $scope.nit="";
        
        /**
         * 
         * @param {type} logeo
         * @returns {undefined}
         */
        $scope.cargarInformacion=function (logeo){
            $scope.identificacion =parseInt(logeo);            
            logeo=logeo+"";
            MisEnsayosRSRestAPI.consultarCliente($scope.identificacion).then(
                //promise success
                function(response){
                    if(!isNaN(response.data ))
                        alert("El número de identificación ingresado no se encuentra en la base de datos, por favor ingrese nuevamente el número");
                    else{
                        console.log(response.data);
                        $scope.usuario=response.data;
                        $scope.usuariollaves=Object.keys(response.data);
                        $scope.usuarioAutenticado=true;

                        if(logeo.length<10){
                            $scope.seleccionAdministrador=true;
                            $scope.seleccionCliente=false;
                            cargarTodosEstablecimientosSinHabilitar();
                        }else if(logeo.length==10){
                            $scope.seleccionCliente=true;
                            $scope.seleccionAdministrador=false;
                            MisEnsayosRSRestAPI.reservasCliente($scope.identificacion).then(
                                //promise success
                                function(response){
                                    console.log(response.data);                    
                                    $scope.reservasClienteLista=response.data;
                                },
                                //promise error
                                function(response){
                                    console.log('Unable to get data from REST API:'+response);
                                }
                            );
                            MisEnsayosRSRestAPI.EstablecimientosEnsayados($scope.identificacion).then(
                            //promise success
                                function(response){
                                    console.log(response.data);                    
                                    $scope.listaensayos=response.data;

                                },
                                //promise error
                                function(response){
                                    console.log('Unable to get data from REST API:'+response);
                                }
                                   
                             );

                        }
                    }
                },
                //promise error
                function(response){
                    alert("no se encuentra el cliente");
                    console.log('Unable to get data from REST API:'+response);
                }
            );
            
        };
        
        /**
         * 
         * @param {type} logeo2
         * @returns {undefined}
         */
        $scope.cargarInfoEstablecimiento=function(logeo2){
            MisEnsayosRSRestAPI.EnsayosEstablecimientos(logeo2).then(
                    //promise success
                    function(response){
                        $scope.seleccionCliente=true;
                        $scope.seleccionEst=true;
                        console.log(response.data);
                        $scope.listaensayos=response.data;
                        
                    },
                    //promise error
                    function(response){
                    }
            );            
        };
        
        /**
         * 
         * @returns {undefined}
         */
        cargarTodosEstablecimientosSinHabilitar();
        function cargarTodosEstablecimientosSinHabilitar(){
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
        }
        
        /**
         * 
         * @param {type} esta
         * @returns {undefined}
         */
        $scope.habilitarEstablecimiento = function (esta){
            MisEnsayosRSRestAPI.habilitarEstablecimiento(esta.idEstablecimiento).then(
                //promise success
                function(response){
                    console.log(response.data);                    
                    cargarTodosEstablecimientosSinHabilitar();
                    $scope.seleccion=false;
                    alert("El establecimiento seleccionado se ha habilitado con exito");
                },
                //promise error
                function(response){
                    alert("El establecimiento seleccionado NO se ha habilitado");
                    console.log('Unable to get data from REST API:'+response);
                }
            );
        }        
        
        /**
         * 
         * @returns {undefined}
         */
        $scope.seleccionHoraInicio = function () {
            $scope.seleccionHora=true;
        }
        
        /**
         * 
         * @returns {undefined}
         */
        $scope.mayor = function () {
            if($scope.establecimiento.horaInicio>$scope.establecimiento.horaCierre){
                $scope.establecimiento.horaCierre=$scope.establecimiento.horaInicio;
            }
        }
        
        /**
         * 
         * @param {type} valor
         * @returns {undefined}
         */
        $scope.seleccionLocalidad = function (valor) {
            $scope.establecimiento.localidad=valor;
            $scope.seleccion3=!$scope.seleccion3;
        }
        
        /**
         * 
         * @returns {undefined}
         */
        $scope.cargarlocalidadesR  = function () {
            $scope.seleccion3=!$scope.seleccion3;
        }
        
        /**
         * 
         * @param {type} esta
         * @returns {undefined}
         */
        $scope.verificarlegalidad  = function (esta) {
            MisEnsayosRSRestAPI.verificarlegalidad(esta.nit).then(
                //promise success
                function(response){
                    console.log(response.data);                    
                    cargarTodosEstablecimientosSinHabilitar();
                    if(response.data)
                        alert("El establecimiento seleccionado esta registrado ante Camara de comercio");
                    else alert("El establecimiento seleccionado NO esta registrado ante Camara de comercio");
                },
                //promise error
                function(response){
                    console.log('Unable to get data from REST API:'+response);
                }
            );
            
        }
        
        /**
         * 
         * @returns {undefined}
         */
        $scope.consultarEstablecimientosNombre = function () {
            MisEnsayosRSRestAPI.consultarEstablecimientosNombre($scope.nombreEstablecimientoBusqueda.nombre).then(
                //promise success
                function(response){
                    console.log(response.data);  
                    if(!isNaN(response.data )){
                        $scope.respuestaprincipal=true;
                        $scope.respuesta1="El nombre dado no coincide con ninguno de los establecimientos registrados";
                        $scope.respuesta2="No se encuentra ningún establecimiento con un nombre parecido al dado";
                    }
                    else {
                        $scope.establecimientosHabilitados=response.data;
                        $scope.busqueda=true;
                    }
                },
                //promise error
                function(response){
                    $scope.respuestaprincipal=true;
                    $scope.respuesta1="La información enviada no es valida";
                    $scope.respuesta2="No se puede aceptar información enviada";
                    console.log('Unable to get data from REST API:'+response);
                }
            );        
        }
        
        /**
         * 
         * @returns {undefined}
         */
        $scope.consultarEstablecimientosLocalidad = function () {
            MisEnsayosRSRestAPI.consultarEstablecimientosLocalidad($scope.establecimiento.localidad).then(
                //promise success
                function(response){
                    console.log(response.data);                    
                    if(!isNaN(response.data )){
                        $scope.respuestaprincipal=true;
                        $scope.respuesta1="En la localidad seleccionada no se encontraron establecimientos registrados";
                        $scope.respuesta2="No se encuentra ningún establecimiento con una localidad igual a la dada";
                    }
                    else {
                        $scope.establecimientosHabilitados=response.data;
                        $scope.busqueda=true;
                    }
                },
                //promise error
                function(response){
                    $scope.respuestaprincipal=true;
                    $scope.respuesta1="La información enviada no es valida";
                    $scope.respuesta2="No se puede aceptar información enviada";
                    console.log('Unable to get data from REST API:'+response);
                }
            );            
        }
        
        /**
         * 
         * @param {type} esta
         * @returns {undefined}
         */
        $scope.seleccionaropcionBusqueda = function (esta) {
            $scope.opcionBusqueda=esta;
            $scope.busqueda=false;
            $scope.respuestaprincipal=false;
            $scope.respuesta1="";
            $scope.respuesta2="";
            $scope.establecimiento.localidad="";
            $scope.nombreEstablecimientoBusqueda.nombre="";
        }

        /**
         * 
         * @param {type} esta
         * @returns {undefined}
         */
        $scope.alarma = function (esta) {
            alert("alarma"+esta);
        }
        
        /**
         * 
         * @param {type} esta
         * @returns {undefined}
         */
        $scope.seleccionEstablecimientoASala = function (esta) {
            $scope.seleccion2=true;
            $scope.sala.establecimiento=esta;  
        }
        
        /**
         * 
         * @param {type} opcion
         * @returns {undefined}
         */
        $scope.eleccionRegistro = function (opcion) {
            $scope.respuestaprincipal=false;
            $scope.respuesta1="";
            $scope.respuesta2="";            
            $scope.seleccion3=false;
            $scope.establecimiento={};
            $scope.sala={};
            if(opcion.toString()==="Registrar establecimiento"){
                $scope.opcionEsta=true;
                $scope.opcionDeSala=false;                
            }else if(opcion.toString()==="Registrar sala"){
                $scope.opcionEsta=false;
                $scope.opcionDeSala=true;                                
            }
        };
        
        /**
         * 
         * @param {type} esta
         * @returns {undefined}
         */
        $scope.opcionSala=function (esta){
            $scope.salaRese=esta.idSala;
            delete esta.establecimiento;
            delete esta.reservacions;
            delete esta.$$hashKey;
            $scope.salaResecomnte=esta;
            $scope.salaReseLLaves=Object.keys(esta);
        };
        
        /**
         * 
         * @param {type} esta
         * @returns {undefined}
         */
        $scope.consultarSalaPorEstablecimiento=function (esta){
            $scope.seleccion=true;
            $scope.establecimientoNombre=esta.nombre;
            $scope.establecimientoIdentificaion=esta.idEstablecimiento;
            MisEnsayosRSRestAPI.consultarSalaPorEstablecimiento(esta.idEstablecimiento).then(
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
                    delete response.data.cuenta;
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
        
        /**
         * 
         * @returns {undefined}
         */
        $scope.registrarEstablecimiento = function (){            
            $scope.respuestaprincipal=true;
            $scope.opcionEsta=false;
            $scope.opcionDeSala=false;                                              
            MisEnsayosRSRestAPI.realizarPago($scope.establecimiento.tarjetaCredito).then(
                //promise success
                function(response){
                    if(response.data){
                        MisEnsayosRSRestAPI.consultarCantidadEstablcimientos().then(
                            //promise success
                            function(response){
                                console.log(response.data);                    
                                $scope.establecimiento.idEstablecimiento=response.data+1;
                                MisEnsayosRSRestAPI.registrarEstablecimiento($scope.establecimiento.idEstablecimiento, $scope.establecimiento.nombre, $scope.establecimiento.nit, $scope.establecimiento.descripcion, $scope.establecimiento.direccion, $scope.establecimiento.horaInicio.getHours()+":"+$scope.establecimiento.horaInicio.getMinutes()+":"+$scope.establecimiento.horaInicio.getSeconds(), $scope.establecimiento.horaCierre.getHours()+":"+$scope.establecimiento.horaCierre.getMinutes()+":"+$scope.establecimiento.horaCierre.getSeconds(), $scope.establecimiento.multa, $scope.establecimiento.localidad, $scope.establecimiento.telefono, $scope.establecimiento.cuenta).then(
                                    //promise success
                                    function(response){
                                        console.log(response.data);
                                        cargarTodosEstablecimientosSinHabilitar();
                                        alert("El registro del establecimiento fue exitoso");
                                        $scope.establecimiento={}
                                        $scope.respuesta1="El registro del establecimiento fue exitoso";
                                        $scope.respuesta2="No se presentó ningún problema en el registro y liquidación de pago de registro";
                                        
                                    },
                                    //promise error
                                    function(response){
                                        $scope.establecimiento={}
                                        $scope.respuesta1="Fallo el registro del establecimiento";
                                        $scope.respuesta2="El registro del establecmiento no se pudo efectuar debido a que el NIT enviado no es valido";
                                        console.log('Unable to get data from REST API:'+response);
                                    }
                                );                            
                            },
                            //promise error
                            function(response){
                                $scope.establecimiento={}
                                $scope.respuesta1="Fallo el registro del establecimiento";
                                $scope.respuesta2="Los datos enviados no son validos";
                                console.log('Unable to get data from REST API:'+response);
                            }
                        );
                    }else{
                        $scope.establecimiento={}
                        $scope.respuesta1="Fallo el registro del establecimiento";
                        $scope.respuesta2="Los datos enviados no son validos";
                    } 
                },
                //promise error
                function(response){
                    $scope.establecimiento={}
                    alert("El pago no se pudo efectuar debido a problemas de conexión con la pasarela de pagos");
                    console.log('Unable to get data from REST API:'+response);
                }            
            );
        }
        
        /**
         * 
         * @returns {undefined}
         */
        $scope.registrarSala = function () {
            $scope.respuestaprincipal=true;
            $scope.opcionEsta=false;
            $scope.opcionDeSala=false;                                  
            MisEnsayosRSRestAPI.consultarEstablecimientosporNit($scope.sala.nitValidacion).then(
                //promise success
                function(response){
                    console.log(response.data);
                    if(response.data!=null){
                        $scope.sala.establecimiento=response.data;
                        MisEnsayosRSRestAPI.consultarCantidadSalas().then(
                            //promise success
                            function(response){
                                console.log(response.data);                    
                                $scope.sala.idSala=response.data+100;
                                MisEnsayosRSRestAPI.registrarSala($scope.sala.idSala, $scope.sala.establecimiento.idEstablecimiento, $scope.sala.establecimiento.nombreEstablecimiento, $scope.sala.establecimiento.nit, $scope.sala.establecimiento.descripcionEstablecimiento, $scope.sala.establecimiento.direccion, $scope.sala.establecimiento.horaInicio, $scope.sala.establecimiento.horaCierre, $scope.sala.establecimiento.multa, $scope.sala.establecimiento.localidad, $scope.sala.establecimiento.telefono, $scope.sala.establecimiento.cuenta, $scope.sala.precio, $scope.sala.descripcion, $scope.sala.nombre).then(
                                    //promise success
                                    function(response){
                                        console.log(response.data);                    
                                        $scope.sala.establecimiento={};
                                        $scope.respuesta1="El registro de la sala fue exitoso";
                                        $scope.respuesta2="No se presentó ningún problema en el registro de la sala";                                        
                                        $scope.sala={};
                                    },
                                    //promise error
                                    function(response){
                                        $scope.sala={};
                                        $scope.sala.establecimiento={};                                        
                                        $scope.respuesta1="Fallo el registro de la sala";
                                        $scope.respuesta2="El registro de la sala no se pudo efectuar debido a errores en los datos enviados";
                                        console.log('Unable to get data from REST API:'+response);
                                    }
                                );
                            },
                            //promise error
                            function(response){
                                $scope.sala={};
                                $scope.respuesta1="Fallo el registro de la sala";
                                $scope.respuesta2="El registro de la sala no se pudo efectuar debido a errores en los datos enviados";
                                console.log('Unable to get data from REST API:'+response);
                            }
                        );        
                    }else {
                        $scope.sala={};
                        $scope.respuesta1="Fallo el registro de la sala";
                        $scope.respuesta2="El registro de la sala fallo debido a que el NIT dado no existe";
                    }
                },
                //promise error
                function(response){
                    $scope.sala={};
                    $scope.respuesta1="Fallo el registro de la sala";
                    $scope.respuesta2="Los datos enviados no son validos";
                    console.log('Unable to get data from REST API:'+response);
                }
            );              
        }        
        
        /**
         * 
         * @returns {undefined}
         */
        $scope.registroAlquiler=function(){
            //MisEnsayosRSRestAPI.alquilerCliente($scope.cliente.idCliente,$scope.identificaionReserva,$scope.ensayo.descripcion).then(
                MisEnsayosRSRestAPI.consultarCliente($scope.usuario.idCliente).then(
                //promise success
                function(response){
                    $scope.cliente=response.data;
                    
                    MisEnsayosRSRestAPI.consultarReserva($scope.identificaionReserva).then(
                            //promise success
                            function(response){
                                $scope.reservacion=response.data;
                                
                                MisEnsayosRSRestAPI.alquilerCliente($scope.cliente.idCliente, $scope.identificaionReserva, $scope.reservacion.fecha, $scope.reservacion.duracion, $scope.reservacion.hora, $scope.identificaionReserva,$scope.ensayo.descripcion).then(
                                        function(response){
                                            alert("Se ha creado satisfactoriamente el ensayo nuevo para el cliente " + $scope.cliente.nombre);
                                        },
                                        function(response){
                                            alert("no creo nada :(")
                                        });
                                    },
                            function(response){
                                
                            }
                    );
                },
                //promise error
                function(response){
                                
                });
        }
        
        /**
         * 
         * @returns {undefined}
         */
        $scope.reservarSala=function(){
            MisEnsayosRSRestAPI.consultaEstablecimientoId($scope.establecimientoIdentificaion) .then(
                //promise success
                function(response){
                    $scope.establecimientoReservacion=response.data;
                    MisEnsayosRSRestAPI.consultarSala($scope.salaRese) .then(
                        //promise success
                        function(response){
                            $scope.salaReservacion=response.data;
                            MisEnsayosRSRestAPI.registroReserva($scope.salaReservacion.idSala, $scope.establecimientoReservacion.idEstablecimiento, $scope.establecimientoReservacion.nombre, $scope.establecimientoReservacion.nit, $scope.establecimientoReservacion.descripcion, $scope.establecimientoReservacion.direccion, $scope.establecimientoReservacion.horaInicio, $scope.establecimientoReservacion.horaCierre, $scope.establecimientoReservacion.multa, $scope.establecimientoReservacion.localidad, $scope.establecimientoReservacion.telefono, $scope.establecimientoReservacion.cuenta, $scope.salaReservacion.precio, $scope.salaReservacion.descripcion, $scope.salaReservacion.nombre, $scope.reservacion.fecha.getFullYear()+"-"+$scope.reservacion.fecha.getMonth()+"-"+$scope.reservacion.fecha.getDate(), $scope.reservacion.hora.getHours()+":"+$scope.reservacion.hora.getMinutes()+":"+$scope.reservacion.hora.getSeconds(), $scope.reservacion.duracion) .then(
                                //promise success
                                function(response){
                                    alert("La reservacion se ha logrado exitosamente");
                                    $scope.identificaionReserva=response.data;
                                    $scope.habilitarAlquiler=true;
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
                    );                            },
                //promise error
                function(response){
                    console.log('Unable to get data from REST API:'+response);
                }
            );                   
        }
        
        /**
         * 
         * @param {type} calificacion
         * @param {type} ensayo2
         * @returns {undefined}
         */
        $scope.calificar=function (calificacion, ensayo2){
            alert("Solicitus de calificacion");
            if($scope.seleccionEst===true){
                MisEnsayosRSRestAPI.registrarCalificacionCliente(ensayo2.idEnsayo,$scope.usuario.idCliente, $scope.usuario.nombre, $scope.usuario.descripcion1, ensayo2.descripcion, null, calificacion, 0, ensayo2.descripcion2).then(
                    function(response){
                    console.log(response.data);                    
                    alert("La califiacion se ha registrado exitosamente");
                    },
                    //promise error
                    function(response){
                    console.log('Unable to get data from REST API:'+response);
                    alert("No se ha podido registrar la califiacion");
                    }
                )
            }else{
                MisEnsayosRSRestAPI.registrarCalificacionEstablecimiento(ensayo2.idEnsayo,$scope.usuario.idCliente, $scope.usuario.nombre, $scope.usuario.descripcion1, ensayo2.descripcion, null, 0, calificacion, ensayo2.descripcion2).then(
                    //promise success
                    function(response){
                        console.log(response.data);                    
                        alert("La califiacion se ha registrado exitosamente");
                    },
                    //promise error
                    function(response){
                        console.log('Unable to get data from REST API:'+response);
                        alert("No se ha podido registrar la califiacion");
                    }
                );
            }
    }
        
    }
    );

})();

