CREATE TABLE conectormc4 (
                        id VARCHAR(255) PRIMARY KEY,
                        name VARCHAR(255),
                        description TEXT,
                        price INTEGER
);
CREATE TABLE cable (
                       id VARCHAR(255) PRIMARY KEY,
                       name VARCHAR(255),
                       description TEXT,
                       price INTEGER,
                       brand VARCHAR(255),
                       size_in_meters DOUBLE PRECISION,
                       type VARCHAR(255)
);

CREATE TABLE inverter (
                          id VARCHAR(255) PRIMARY KEY,
                          name VARCHAR(255),
                          description TEXT,
                          price INTEGER,
                          max_potency_kw DOUBLE PRECISION,
                          exit_potency_v VARCHAR(255),
                          entry_potency_v VARCHAR(255),
                          type VARCHAR(255)
);

CREATE TABLE solarpainel (
                             id VARCHAR(255) PRIMARY KEY,
                             name VARCHAR(255),
                             description TEXT,
                             price INTEGER,
                             potency_kilo_watts DOUBLE PRECISION,
                             potency_voltage DOUBLE PRECISION,
                             height DOUBLE PRECISION,
                             width DOUBLE PRECISION
);


CREATE TABLE solarpainelsuport (
                                   id VARCHAR(255) PRIMARY KEY,
                                   name VARCHAR(255),
                                   description TEXT,
                                   price INTEGER,
                                   type VARCHAR(255),
                                   capacity INTEGER
);


