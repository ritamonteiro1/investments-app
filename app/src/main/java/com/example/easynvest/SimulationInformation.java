package com.example.easynvest;

public class SimulationInformation {
    private String informationType;
    private String simulationResult;

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
