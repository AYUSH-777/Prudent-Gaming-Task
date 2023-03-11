CREATE TABLE betting_summary (
  id INT NOT NULL AUTO_INCREMENT,
  numbets INT NOT NULL,
  game VARCHAR(50) NOT NULL,
  stake DECIMAL(10,2) NOT NULL,
  returns DECIMAL(10,2) NOT NULL,
  clientid INT NOT NULL,
  date DATE NOT NULL,
  PRIMARY KEY (id),
  INDEX clientid_idx (clientid),
  INDEX date_idx (date)
);
