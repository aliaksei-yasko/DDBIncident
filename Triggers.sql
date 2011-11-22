CREATE TRIGGER ChangeIncidentDecision
  AFTER INSERT ON alex.criminalcase
  REFERENCING NEW AS NEW
  FOR EACH ROW MODE DB2SQL
  UPDATE alex.incident SET alex.incident.decision = 'Возбудить уголовное дело'
    WHERE alex.incident.registrationnumber = NEW.registrationnumber;
	
CREATE TRIGGER DeleteIncidentDecision
  AFTER DELETE ON alex.criminalcase
  REFERENCING OLD AS OLD
  FOR EACH ROW MODE DB2SQL
  UPDATE alex.incident SET alex.incident.decision = 'Не возбуждать уголовное дело'
    WHERE alex.incident.registrationnumber = OLD.registrationnumber;