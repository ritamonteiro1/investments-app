package com.example.investimentos;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class SimulationInformationAdapter extends RecyclerView.Adapter<SimulationInformationAdapter.SimulationInformationViewHolder>{
    private final List<SimulationInformation> informationList;

    public SimulationInformationAdapter(List<SimulationInformation> informationList) {
        this.informationList = informationList;
    }

    @NonNull
    @Override
    public SimulationInformationAdapter.SimulationInformationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simulation_information,
                parent, false);
        return new SimulationInformationViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(SimulationInformationViewHolder holder, int position) {
        holder.bind(informationList.get(position));
    }

    @Override
    public int getItemCount() {
        return informationList.size();
    }

    public static class SimulationInformationViewHolder extends RecyclerView.ViewHolder{
            private final TextView itemSimulationInformationTypeTextView;
            private final TextView itemSimulationInformationSimulationResultTextView;

        public SimulationInformationViewHolder(View itemView) {
            super(itemView);
            itemSimulationInformationSimulationResultTextView = itemView.findViewById(R.id.itemSimulationInformationSimulationResultTextView);
            itemSimulationInformationTypeTextView = itemView.findViewById(R.id.itemSimulationInformationTypeTextView);
        }

        public void bind(SimulationInformation simulationInformation) {
            itemSimulationInformationTypeTextView.setText(simulationInformation.getInformationType());
            itemSimulationInformationSimulationResultTextView.setText(simulationInformation.getSimulationResult());
        }
    }

}