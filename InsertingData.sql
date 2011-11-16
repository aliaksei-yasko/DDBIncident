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






























		