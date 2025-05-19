package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Inverter extends Product {
    private String type;  // Ex: "Microinversor", "Bifásico", "Trifásico"

    @Column(name = "supported_panel_count")
    private Integer supportedPanelCount; // Número máximo de painéis suportados

    @Column(name = "supported_panel_max_power_w")
    private Integer supportedPanelMaxPowerW; // Potência máxima suportada por painel em watts

    @Column(name = "operating_voltage")
    private Integer operatingVoltage; // 110 ou 220 V

    @Column(name = "max_power_w")
    private Integer maxPowerW; // Potência nominal do inversor em watts

    public Inverter() {
        super();
    }

    public Inverter(String name, String description, Integer price, String brand,
                    String type, Integer supportedPanelCount, Integer supportedPanelMaxPowerW,
                    Integer operatingVoltage, Integer maxPowerW) {
        super(name, description, price, brand);
        this.type = type;
        this.supportedPanelCount = supportedPanelCount;
        this.supportedPanelMaxPowerW = supportedPanelMaxPowerW;
        this.operatingVoltage = operatingVoltage;
        this.maxPowerW = maxPowerW;
    }

    // Getters e Setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSupportedPanelCount() {
        return supportedPanelCount;
    }

    public void setSupportedPanelCount(Integer supportedPanelCount) {
        this.supportedPanelCount = supportedPanelCount;
    }

    public Integer getSupportedPanelMaxPowerW() {
        return supportedPanelMaxPowerW;
    }

    public void setSupportedPanelMaxPowerW(Integer supportedPanelMaxPowerW) {
        this.supportedPanelMaxPowerW = supportedPanelMaxPowerW;
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

    public Integer getMaxPowerW() {
        return maxPowerW;
    }

    public void setMaxPowerW(Integer maxPowerW) {
        this.maxPowerW = maxPowerW;
    }
}
