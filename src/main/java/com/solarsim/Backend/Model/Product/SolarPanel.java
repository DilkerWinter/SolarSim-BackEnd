package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class SolarPanel extends Product {

    @Column(name = "potency_watts")
    private Integer potencyWatts; // Potência nominal em watts (ex: 2440)

    @Column(name = "efficiency_per_mille")
    private Integer efficiencyPerMille; // Eficiência em partes por mil (ex: 850 = 85.0%)

    @Column(name = "avg_daily_energy_wh")
    private Integer avgDailyEnergyWh; // Energia média diária gerada em Wh (ex: 292000 Wh = 292 kWh)

    @Column(name = "self_consumption_watts")
    private Integer selfConsumptionWatts; // Consumo próprio da placa em watts

    @Column(name = "max_operating_temp_c")
    private Integer maxOperatingTempC; // Temperatura máxima de operação em °C

    @Column(name = "height_meters")
    private Integer heightMeters; // Altura da placa em metros

    @Column(name = "width_meters")
    private Integer widthMeters; // Largura da placa em metros

    @Column(name = "operating_voltage")
    private Integer operatingVoltage; // 110V ou 220V

    @Column(name = "weight_kilograms")
    private Integer weightKg; // Peso da placa em kg

    public SolarPanel() {
        super();
    }

    public SolarPanel(String name, String description, Integer price, String brand,
                      Integer potencyWatts, Integer efficiencyPerMille, Integer avgDailyEnergyWh,
                      Integer selfConsumptionWatts, Integer maxOperatingTempC,
                      Integer heightMeters, Integer widthMeters, Integer weightKg, Integer operatingVoltage) {
        super(name, description, price, brand);
        this.potencyWatts = potencyWatts;
        this.efficiencyPerMille = efficiencyPerMille;
        this.avgDailyEnergyWh = avgDailyEnergyWh;
        this.selfConsumptionWatts = selfConsumptionWatts;
        this.maxOperatingTempC = maxOperatingTempC;
        this.heightMeters = heightMeters;
        this.widthMeters = widthMeters;
        this.weightKg = weightKg;
        setOperatingVoltage(operatingVoltage);
    }

    // Getters e Setters

    public Integer getPotencyWatts() {
        return potencyWatts;
    }

    public void setPotencyWatts(Integer potencyWatts) {
        this.potencyWatts = potencyWatts;
    }

    public Integer getEfficiencyPerMille() {
        return efficiencyPerMille;
    }

    public void setEfficiencyPerMille(Integer efficiencyPerMille) {
        this.efficiencyPerMille = efficiencyPerMille;
    }

    public Integer getAvgDailyEnergyWh() {
        return avgDailyEnergyWh;
    }

    public void setAvgDailyEnergyWh(Integer avgDailyEnergyWh) {
        this.avgDailyEnergyWh = avgDailyEnergyWh;
    }

    public Integer getSelfConsumptionWatts() {
        return selfConsumptionWatts;
    }

    public void setSelfConsumptionWatts(Integer selfConsumptionWatts) {
        this.selfConsumptionWatts = selfConsumptionWatts;
    }

    public Integer getMaxOperatingTempC() {
        return maxOperatingTempC;
    }

    public void setMaxOperatingTempC(Integer maxOperatingTempC) {
        this.maxOperatingTempC = maxOperatingTempC;
    }

    public Integer getHeightMeters() {
        return heightMeters;
    }

    public void setHeightMeters(Integer heightMeters) {
        this.heightMeters = heightMeters;
    }

    public Integer getWidthMeters() {
        return widthMeters;
    }

    public void setWidthMeters(Integer widthMeters) {
        this.widthMeters = widthMeters;
    }

    public Integer getOperatingVoltage() {
        return operatingVoltage;
    }

    public void setOperatingVoltage(Integer operatingVoltage) {
        if (operatingVoltage != null && operatingVoltage != 110 && operatingVoltage != 220) {
            throw new IllegalArgumentException("Operating Voltage must be 110V or 220V.");
        }
        this.operatingVoltage = operatingVoltage;
    }

    public Integer getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Integer weightKg) {
        this.weightKg = weightKg;
    }
}
