DROP TABLE IF EXISTS serverinfo;

CREATE TABLE serverinfo (
  id int(10) NOT NULL AUTO_INCREMENT,
  cpuUsage varchar(255) DEFAULT NULL,
  setCpuUsage varchar(255) DEFAULT NULL,
  jvmUsage varchar(255) DEFAULT NULL,
  setJvmUsage varchar(255) DEFAULT NULL,
  ramUsage varchar(255) DEFAULT NULL,
  setRamUsage varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  operTime timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
  mark varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)