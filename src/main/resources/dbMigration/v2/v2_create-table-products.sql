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
                          max_potency_kw INTEGER,
                          exit_potency_v INTEGER,
                          entry_potency_v INTEGER,
                          type VARCHAR(255)
);

CREATE TABLE solar_panel (
                             id VARCHAR(255) PRIMARY KEY,
                             name VARCHAR(255),
                             description TEXT,
                             price INTEGER,
                             brand VARCHAR(255),
                             potency_kilo_watts INTEGER,
                             potency_voltage INTEGER,
                             height INTEGER,
                             width INTEGER
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


