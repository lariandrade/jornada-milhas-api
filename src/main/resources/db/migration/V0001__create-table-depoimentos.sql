CREATE TABLE depoimentos (
    id INT NOT NULL auto_increment,
    foto VARCHAR(100) NOT NULL,
    depoimento VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,

    PRIMARY KEY(id)
);