Feature: Login
  The Login API provide to user the function for Login and Logout of application
  Yo como usuario registrado
  quiero poder autenticarme en el sistema
  para poder hacer uso de las funcionalidades

  Scenario: Autenticación valida
    Given Soy un usuario registrado del sistema usando credenciales validas
    When invoco el servicio de autenticación
    Then obtengo un status code 200
    And un token de autenticación

  Scenario: Validar login respuesta de login incorrecto
    Given Soy un usuario registrado del sistema usando credenciales no validas
    When invoco el servicio de autenticación
    Then obtengo un status code 401
    And el cual retorna un mensaje de error
