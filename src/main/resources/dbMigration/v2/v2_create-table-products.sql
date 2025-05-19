CREATE TABLE conector_mc4 (
                            id VARCHAR(255) PRIMARY KEY,
                            name VARCHAR(255),
                            description TEXT,
                            price INTEGER,
                            brand VARCHAR(255)
);

CREATE TABLE cable (
                       id VARCHAR(255) PRIMARY KEY,
                       name VARCHAR(255),
                       description TEXT,
                       price INTEGER,
                       brand VARCHAR(255),
                       type VARCHAR(255)
);

CREATE TABLE inverter (
                          id VARCHAR(255) PRIMARY KEY,
                          name VARCHAR(255),
                          description TEXT,
                          price INTEGER,
                          brand VARCHAR(255),
                          type VARCHAR(100),
                          supported_panel_count INTEGER,
                          supported_panel_max_power_w INTEGER,
                          operating_voltage INTEGER CHECK (operating_voltage IN (110, 220)),
                          max_power_w INTEGER
);

CREATE TABLE solar_panel (
                             id VARCHAR(255) PRIMARY KEY,
                             name VARCHAR(255),
                             description TEXT,
                             price INTEGER,
                             brand VARCHAR(255),
                             potency_watts INTEGER,
                             efficiency_per_mille INTEGER,
                             avg_daily_energy_wh INTEGER,
                             self_consumption_watts INTEGER,
                             max_operating_temp_c INTEGER,
                             height_meters INTEGER,
                             width_meters INTEGER,
                             operating_voltage INTEGER CHECK (operating_voltage IN (110, 220)),
                             weight_kilograms INTEGER
);


CREATE TABLE solar_panel_support (
                                    id VARCHAR(255) PRIMARY KEY,
                                    name VARCHAR(255),
                                    description TEXT,
                                    price INTEGER,
                                    brand VARCHAR(255),
                                    type VARCHAR(255),
                                    max_capacity INTEGER
);


