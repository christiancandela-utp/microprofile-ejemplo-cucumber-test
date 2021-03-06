Feature: Login
  The Person API provide to user the function for Manage Person application.
  Yo como usuario registrado
  quiero poder registrar los datos de una persona
  para que puedan ser usados posteriormente

  Background:
    Given Estoy autenticado

  Scenario: Registrar datos de persona no registrada
    Given Tengo los datos de una persona no registrada
    When invoco el servicio de registrar persona
    Then obtengo un status code 201
    And los datos de la persona enviados
    And consistente con la estrutura esperada de "persona"

  Scenario: Registrar datos incompletos de persona no registrada
    Given Tengo los datos incompletos de una persona no registrada
    When invoco el servicio de registrar persona
    Then obtengo un status code 400
    And un mensaje de error
    And consistente con la estrutura esperada de "error"

  Scenario: Registrar datos de persona registrada
    Given Tengo los datos de una persona registrada
    When invoco el servicio de registrar persona
    Then obtengo un status code 409
    And un mensaje de error
    And consistente con la estrutura esperada de "error"