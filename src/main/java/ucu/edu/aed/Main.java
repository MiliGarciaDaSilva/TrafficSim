package ucu.edu.aed;

import ucu.edu.aed.sistema.SistemaTrafico;


public class Main {
  public static void main(String[] args) {
    SistemaTrafico sistema = SistemaTrafico.getInstancia();
    sistema.incorporarInterseccion("Cruce1");
    sistema.incorporarCalle("Rivera", "Cruce1", 10);
    sistema.incorporarCalle("Buxareo", "Cruce1", 15);
    sistema.registrarVehiculo("Ambulancia", 5, "Ford");
    sistema.registrarVehiculo("Ambulancia", 5, "Ford");
    sistema.registrarVehiculo("Patrulla", 4, "Toyota");
    sistema.registrarVehiculo("Autobomba", 6, "Iveco");
    sistema.registrarVehiculo("Grua", 2, "Mercedes-Benz");
    sistema.registrarVehiculo("Omnibus", 45, "Mercedes-Benz");
    sistema.registrarVehiculo("Taxi", 4, "Chevrolet");
    sistema.registrarVehiculo("Escolar", 20, "Volkswagen");
    sistema.registrarVehiculo("Furgon", 3, "Renault");
    sistema.registrarVehiculo("Camion", 2, "Scania");
    sistema.registrarVehiculo("Pickup", 5, "Ford");
    sistema.incorporarVehiculo(1, "Cruce1", "Rivera");
    sistema.incorporarVehiculo(2, "Cruce1", "Rivera");
    sistema.incorporarVehiculo(3, "Cruce1", "Rivera");
    sistema.incorporarVehiculo(4, "Cruce1", "Rivera");
    sistema.incorporarVehiculo(5, "Cruce1", "Buxareo");
    sistema.incorporarVehiculo(6, "Cruce1", "Buxareo");
    sistema.incorporarVehiculo(7, "Cruce1", "Buxareo");
    sistema.incorporarVehiculo(8, "Cruce1", "Buxareo");
    sistema.simularTrafico("Cruce1", 3);
  }
}
