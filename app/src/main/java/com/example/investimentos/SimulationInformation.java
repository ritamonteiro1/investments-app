package com.example.investimentos;

public class SimulationInformation {
    private final String informationType;
    private final String simulationResult;

    public SimulationInformation(String informationType, String simulationResult) {
        this.informationType = informationType;
        this.simulationResult = simulationResult;
    }

    public String getInformationType() {
        return informationType;
    }

    public String getSimulationResult() {
        return simulationResult;
    }
}
