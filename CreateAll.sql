CREATE TABLE Incident(
	RegistrationNumber INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	IncidentDate DATE,
	Description VARCHAR(10000),
	Decision VARCHAR(50)
);

CREATE TABLE CriminalCase(
	CriminalNumber INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	CriminalDate DATE,
	CriminalNote VARCHAR(10000),
        RegistrationNumber INTEGER UNIQUE
);

ALTER TABLE CriminalCase ADD FOREIGN KEY (RegistrationNumber)
REFERENCES Incident (RegistrationNumber) ON DELETE CASCADE;

CREATE TABLE Person(
	PersonNumber INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	FirstName VARCHAR(20),
	LastName VARCHAR(20),
	PassportNumber INTEGER,
	Address VARCHAR(100),
	BurnDate DATE,
	CourtRate INTEGER
);

CREATE TABLE Status(
	StatusNumber INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	StatusName VARCHAR(50)
);

CREATE TABLE Article(
	ArticleNumber INTEGER PRIMARY KEY,
	ArticleName VARCHAR(500),
	Discription VARCHAR(10000)
);

CREATE TABLE ArticleApply(
	ArticleNumber INTEGER,
	CriminalNumber INTEGER
);

ALTER TABLE ArticleApply ADD FOREIGN KEY (ArticleNumber)
REFERENCES Article (ArticleNumber) ON DELETE CASCADE;
ALTER TABLE ArticleApply ADD FOREIGN KEY (CriminalNumber)
REFERENCES CriminalCase (CriminalNumber) ON DELETE CASCADE;

CREATE TABLE IncidentInvolvment(
	PersonNumber INTEGER,
	RegistrationNumber INTEGER,
	StatusNumber INTEGER
);

ALTER TABLE IncidentInvolvment ADD FOREIGN KEY (PersonNumber)
REFERENCES Person (PersonNumber) ON DELETE CASCADE;
ALTER TABLE IncidentInvolvment ADD FOREIGN KEY (RegistrationNumber)
REFERENCES Incident(RegistrationNumber) ON DELETE CASCADE;
ALTER TABLE IncidentInvolvment ADD FOREIGN KEY (StatusNumber)
REFERENCES Status (StatusNumber) ON DELETE CASCADE;


insert into Article values(139, 'Убийство', 'Умышленное противоправное лишение жизни другого человека (убийство) - 
наказывается лишением свободы на срок от шести до пятнадцати лет.');

insert into Article values(140, 'Убийство матерью новорожденного ребенка', 'Убийство матерью своего ребенка во время родов
или непосредственно после них, совершенное в условиях психотравмирующей ситуации, вызванной родами, -
наказывается ограничением свободы на срок до пяти лет или лишением свободы на тот же срок.');

insert into Article values(141, 'Убийство, совершенное в состоянии аффекта', 'Убийство, совершенное в состоянии внезапно 
возникшего сильного душевного волнения, вызванного насилием, издевательством, тяжким оскорблением
или иными противозаконными или грубыми аморальными действиями потерпевшего либо длительной психотравмирующей
ситуацией, возникшей в связи с систематическим противоправным или аморальным поведением потерпевшего, - 
наказывается ограничением свободы на срок до пяти лет или лишением свободы на срок до четырех лет.');

insert into Article values(142, 'Убийство при превышении мер, необходимых для задержания лица, совершившего преступление',
'Убийство, совершенное при превышении мер, необходимых для задержания лица, совершившего преступление, -
наказывается ограничением свободы на срок до трех лет или лишением свободы на тот же срок.');

insert into Article values(143, 'Убийство при превышении пределов необходимой обороны',
'Убийство, совершенное при превышении пределов необходимой обороны, -
наказывается исправительными работами на срок до двух лет, или ограничением свободы на тот же срок,
или лишением свободы на срок до двух лет.');

insert into Article values(144, 'Причинение смерти по неосторожности',
'Причинение смерти по неосторожности - наказывается исправительными работами
на срок до двух лет, или ограничением свободы на срок до трех лет, или лишением свободы на тот же срок.');

insert into Article values(166, 'Изнасилование',
'Половое сношение вопреки воле потерпевшей с применением насилия или с угрозой его применения к женщине или
ее близким либо с использованием беспомощного состояния потерпевшей (изнасилование) -
наказывается ограничением свободы на срок до четырех лет или лишением свободы на срок от трех до семи лет.');

insert into Article values(181, 'Торговля людьми',
'Купля-продажа человека или совершение иных сделок в отношении его, а равно совершенные в целях эксплуатации
вербовка, перевозка, передача, укрывательство или получение человека (торговля людьми) -
наказываются лишением свободы на срок от пяти до семи лет с конфискацией имущества.');

insert into Article values(182, 'Похищение человека',
'Тайное, открытое, путем обмана или злоупотребления доверием, или соединенное с насилием или с 
угрозой его применения, или иными формами принуждения противоправное завладение лицом при отсутствии 
признаков преступления, предусмотренного статьей 291 настоящего Кодекса (похищение человека), -
наказывается лишением свободы на срок от пяти до семи лет с конфискацией имущества или без конфискации.');

insert into Article values(183, 'Незаконное лишение свободы',
'Ограничение личной свободы человека путем водворения его в какое-либо помещение,
связывания или иного насильственного удержания при отсутствии признаков должностного или другого
более тяжкого преступления (незаконное лишение свободы) -
наказывается лишением свободы на срок от двух до пяти лет.');

insert into Article values(205, 'Кража',
'Тайное похищение имущества (кража) -
наказывается общественными работами, или штрафом, или исправительными работами на срок до двух лет,
или арестом на срок до шести месяцев, или ограничением свободы на срок до трех лет, или лишением свободы
на тот же срок.');							

insert into Article values(206, 'Грабеж',
'Открытое похищение имущества (грабеж) -
наказывается общественными работами, или штрафом, или исправительными работами на срок до двух лет,
или арестом на срок до шести месяцев, или ограничением свободы на срок до четырех лет, или лишением
свободы на тот же срок.');									


insert into Status(StatusName) values('Подозреваемый');
insert into Status(StatusName) values('Обвиняемый');
insert into Status(StatusName) values('Виновный');
insert into Status(StatusName) values('Свидетель');
insert into Status(StatusName) values('Потерпевший');



insert into Person(FirstName, LastName, PassportNumber, Address, BurnDate, CourtRate) values(
	'Петров', 'Василий', 1529299, 'г. Минск ул. Якуба Коласа 28', '11.03.1992', 1);

insert into Person(FirstName, LastName, PassportNumber, Address, BurnDate, CourtRate) values(
	'Васечкин', 'Петр', 1322291, 'г. Минск ул. Якуба Коласа 28', '16.02.1982', 0);

insert into Person(FirstName, LastName, PassportNumber, Address, BurnDate, CourtRate) values(
	'Булгак', 'Алексей', 3559289, 'г. Минск ул. Якуба Коласа 28', '12.06.1991', 3);

insert into Person(FirstName, LastName, PassportNumber, Address, BurnDate, CourtRate) values(
	'Джексон', 'Майкл', 8569223, 'г. Минск ул. Якуба Коласа 28', '29.08.1958', 1);

insert into Person(FirstName, LastName, PassportNumber, Address, BurnDate, CourtRate) values(
	'Матвеенко', 'Денис', 1569627, 'г. Минск ул. Якуба Коласа 28', '17.03.1992', 5);

insert into Person(FirstName, LastName, PassportNumber, Address, BurnDate, CourtRate) values(
	'Тайсон', 'Майк', 5569823, 'г. Минск ул. Якуба Коласа 28', '30.06.1966', 2);




insert into Incident(IncidentDate, Description, Decision) values('03.12.2009', 'Убили человека', 'Возбудить уголовное дело');
insert into Incident(IncidentDate, Description, Decision) values('06.02.2010', 'Закидали яблоками автобус с детьми', 'Не возбуждать уголовное дело');
insert into Incident(IncidentDate, Description, Decision) values('15.02.2010', 'Студенты похитили дочку главы минсктранса', 'Возбудить уголовное дело');
insert into Incident(IncidentDate, Description, Decision) values('27.06.2010', 'У гражданина украли с грядки 20 кг клубники', 'Возбудить уголовное дело');
insert into Incident(IncidentDate, Description, Decision) values('05.12.2010', 'С зоопарка сбежал тюлень', 'Не возбуждать уголовное дело');
insert into Incident(IncidentDate, Description, Decision) values('01.01.2011', 'Гражданин напился на новый год и приставал к другому гражданину', 'Возбудить уголовное дело');
insert into Incident(IncidentDate, Description, Decision) values('19.04.2011', 'У пенсионера украли курицу, он в свою очередь зарезал нападавших', 'Возбудить уголовное дело');




insert into CriminalCase(CriminalDate, CriminalNote, RegistrationNumber) values(
		'05.12.2009', 'Уголовное дело возбуждено следователем Пупкиным.', 1);

insert into CriminalCase(CriminalDate, CriminalNote, RegistrationNumber) values(
		'18.02.2010', 'Уголовное дело возбуждено следователем Мухиным.', 3);

insert into CriminalCase(CriminalDate, CriminalNote, RegistrationNumber) values(
		'01.07.2010', 'Уголовное дело возбуждено следователем Пупкиным.', 4);

insert into CriminalCase(CriminalDate, CriminalNote, RegistrationNumber) values(
		'15.01.2011', 'Уголовное дело возбуждено следователем Пупкиным.', 6);

insert into CriminalCase(CriminalDate, CriminalNote, RegistrationNumber) values(
		'20.04.2011', 'Уголовное дело возбуждено следователем Мухиным.', 7);





insert into ArticleApply(ArticleNumber, CriminalNumber) values(139, 1);
insert into ArticleApply(ArticleNumber, CriminalNumber) values(182, 2);
insert into ArticleApply(ArticleNumber, CriminalNumber) values(205, 3);
insert into ArticleApply(ArticleNumber, CriminalNumber) values(166, 4);
insert into ArticleApply(ArticleNumber, CriminalNumber) values(143, 5);
insert into ArticleApply(ArticleNumber, CriminalNumber) values(144, 5);
insert into ArticleApply(ArticleNumber, CriminalNumber) values(206, 3);




insert into IncidentInvolvment(PersonNumber, RegistrationNumber, StatusNumber) values(1, 1, 1);
insert into IncidentInvolvment(PersonNumber, RegistrationNumber, StatusNumber) values(1, 1, 4);
insert into IncidentInvolvment(PersonNumber, RegistrationNumber, StatusNumber) values(4, 2, 5);
insert into IncidentInvolvment(PersonNumber, RegistrationNumber, StatusNumber) values(3, 3, 5);
insert into IncidentInvolvment(PersonNumber, RegistrationNumber, StatusNumber) values(5, 3, 3);
insert into IncidentInvolvment(PersonNumber, RegistrationNumber, StatusNumber) values(4, 4, 1);
insert into IncidentInvolvment(PersonNumber, RegistrationNumber, StatusNumber) values(6, 4, 5);
insert into IncidentInvolvment(PersonNumber, RegistrationNumber, StatusNumber) values(2, 5, 3);
insert into IncidentInvolvment(PersonNumber, RegistrationNumber, StatusNumber) values(1, 6, 5);
insert into IncidentInvolvment(PersonNumber, RegistrationNumber, StatusNumber) values(3, 7, 1);



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


























		