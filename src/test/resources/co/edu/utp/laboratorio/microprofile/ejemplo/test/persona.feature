Feature: Login
  The Person API provide to user the function for Manage Person application.
  Yo como usuario registrado
  quiero poder consultar los datos de una persona por su dni
  para poder hacer uso de dichos datos

  Scenario: Consultar datos de persona registrada
    Given Tengo el dni de una persona registrada
    When invoco el servicio de obtener persona
    Then obtengo un status code 200
    And los datos de la persona asociados al dni dado
    And consistente con la estrutura esperada de "persona"

  Scenario: Consultar datos de persona no registrada
    Given Tengo el dni de una persona no registrada
    When invoco el servicio de obtener persona
    Then obtengo un status code 404
    And un mensaje de error
    And consistente con la estrutura esperada de "error"