-- ORDINEA DE CREEARE
-- TESTE_LABORATOR
-- PACIENTI
-- TESTE_PACIENTI
-- TIP_DOCTORI
-- DOCTORI
-- CABINETE_DOCTORI
-- MEDICAMENTE
-- RETETE
-- RETETE_CU_MEDICAMENTELE
-- 1

CREATE TABLE `teste_laborator` (
  `id_test` integer PRIMARY KEY AUTO_INCREMENT,
  `nume_test` varchar(50) NOT NULL, -- 1
  `valoare_minima` integer NULL,
  `valoare_maxima` integer NULL,
  `acuaratete` decimal(3,2) NOT NULL
);


-- 2
CREATE TABLE `pacienti` (
  `pacient_data_nastere` date NOT NULL,
  `pacient_cnp` varchar(25) PRIMARY KEY ,
  `pacient_nume` varchar(10) NOT NULL,
  `pacient_prenume` varchar(20) NOT NULL,
  `pacient_oras_nastere` varchar(15) NOT NULL,
  `pacient_polita_medicala` varchar(3)
  
);

-- 3
CREATE TABLE `teste_pacienti` (
  `pacienti_test_id` int PRIMARY KEY AUTO_INCREMENT,
  `pacient_cnp` varchar(25) NOT NULL,
  `nume_test` varchar(50) NOT NULL, -- 1
  `rezultate` ENUM ('bune', 'proaste', 'refacere' , 'pozitiv' , 'negativ') NOT NULL,
  `valoare_test` decimal(6,2) NULL,
  `data_emitere` date NULL
);

-- 4
CREATE TABLE `tip_doctori` (
  `tip_doctor_id` integer PRIMARY KEY AUTO_INCREMENT,
  `nume_specializare` varchar(40) NOT NULL
);
-- 5
CREATE TABLE `doctori` (
  `doctor_CUI` integer PRIMARY KEY ,
  `tip_doctor_id` integer NOT NULL,
  `doctor_nume` varchar(10) NOT NULL,
  `doctor_prenume` varchar(20) NOT NULL,
  `doctor_data_nastere` date NULL
);
-- 6
CREATE TABLE `cabinete_doctori` (
  `cabinet_id` integer PRIMARY KEY AUTO_INCREMENT,
  `doctor_CUI` integer NOT NULL,
  `cabinet_nume` varchar(50) NOT NULL,
  `cabinet_cod_postal` varchar(10) NOT NULL,
  `cabinet_oras` varchar(15) NOT NULL,
  `cabinet_strada` varchar(25) NULL,
  `cabinet_numar_strada` int(3) NOT NULL,
  `cabinet_nr_telefon` varchar(15) NULL
);
-- 7 
CREATE TABLE `medicamente` (
  `medicament_id` integer PRIMARY KEY AUTO_INCREMENT,
  `medicament_nume` varchar(50) NOT NULL,
  `medicament_gramaj` integer (5) NULL,
  `tip_medicament` varchar(20) NOT NULL
);
-- 8
CREATE TABLE `retete` (
  `reteta_id` integer PRIMARY KEY AUTO_INCREMENT,
  `pacient_cnp` varchar(25) NOT NULL,
  `doctor_CUI` integer NOT NULL,
  `reteta_data_emitere` date NOT NULL,
  `reteta_data_expirare` date NULL,
  `tip_reteta` varchar(10) NULL
);
-- 9
CREATE TABLE `retete_cu_medicamentele` (
  `reteta_cu_medicamentele_id` integer PRIMARY KEY AUTO_INCREMENT,
  `reteta_id` integer NOT NULL,
  `medicament_id` integer NOT NULL,
  `cantitate` integer NULL
);

--  FOREIGN KEYS CONSTRAINTS + OTHER ------------------------------ 
CREATE INDEX idx_nume_test ON teste_laborator(nume_test);
ALTER TABLE `teste_pacienti` ADD FOREIGN KEY (`pacient_cnp`) REFERENCES `pacienti` (`pacient_cnp`)ON DELETE CASCADE;
ALTER TABLE `teste_pacienti` ADD FOREIGN KEY (`nume_test`) REFERENCES `teste_laborator` (`nume_test`)ON DELETE CASCADE;
ALTER TABLE `retete` ADD FOREIGN KEY (`pacient_cnp`) REFERENCES `pacienti` (`pacient_cnp`)ON DELETE CASCADE;
ALTER TABLE `retete` ADD FOREIGN KEY (`doctor_CUI`) REFERENCES `doctori` (`doctor_CUI`)ON DELETE CASCADE;
ALTER TABLE `cabinete_doctori` ADD FOREIGN KEY (`doctor_CUI`) REFERENCES `doctori` (`doctor_CUI`) ON DELETE CASCADE;
ALTER TABLE `doctori` ADD FOREIGN KEY (`tip_doctor_id`) REFERENCES `tip_doctori` (`tip_doctor_id`) ;
ALTER TABLE `retete_cu_medicamentele` ADD FOREIGN KEY (`reteta_id`) REFERENCES `retete` (`reteta_id`) ON DELETE CASCADE;
ALTER TABLE `retete_cu_medicamentele` ADD FOREIGN KEY (`medicament_id`) REFERENCES `medicamente` (`medicament_id`) ON DELETE CASCADE;
-- ---------------------------------------------------------
-- CHECK/UNIQUE CONSTRAINTS ---------------------------------------
ALTER TABLE `pacienti` ADD CONSTRAINT  CHK_CNP CHECK (LENGTH(pacient_cnp) = 13 AND 
													 SUBSTRING(pacient_cnp,2,2) = EXTRACT(YEAR FROM pacient_data_nastere) % 100 AND
                                                     SUBSTRING(pacient_cnp,4,2) = EXTRACT(MONTH FROM pacient_data_nastere) AND
                                                     SUBSTRING(pacient_cnp,6,2) = EXTRACT(DAY FROM pacient_data_nastere) AND
													(pacient_cnp LIKE '1%' OR pacient_cnp LIKE '2%' OR 
													pacient_cnp LIKE '5%' OR pacient_cnp LIKE '6%'));
ALTER TABLE `doctori` ADD CONSTRAINT CHK_CUI CHECK (doctor_CUI >= 10000000 AND doctor_CUI <= 99999999);
ALTER TABLE `cabinete_doctori` ADD CONSTRAINT CHK_NUMAR_SI_COD_POSTAL CHECK ((length(cabinet_nr_telefon) = 12 OR cabinet_nr_telefon = null) AND length(cabinet_cod_postal)=6);
ALTER TABLE `teste_laborator` ADD CONSTRAINT CHK_VALMIN_VALMAX CHECK (valoare_minima >= 0 AND 
																	  valoare_maxima > 0 AND 
																	  valoare_minima < valoare_maxima );
ALTER TABLE cabinete_doctori ADD CONSTRAINT CHK_NR_TELEFON_UNIQUE UNIQUE (cabinet_nr_telefon);
ALTER TABLE `retete_cu_medicamentele` ADD CONSTRAINT CHK_CANTITATE CHECK (cantitate > 0);
ALTER TABLE `retete` ADD CONSTRAINT CHK_TIP_RETETA CHECK(tip_reteta = 'permanent');
ALTER TABLE `retete` ADD CONSTRAINT CHK_DATA CHECK (
												   (reteta_data_emitere IS NULL AND reteta_data_expirare IS NULL) OR
												   (reteta_data_emitere IS NOT NULL AND reteta_data_expirare IS NULL)OR
                                                   (reteta_data_emitere < reteta_data_expirare)
                                                   );
ALTER TABLE `medicamente` ADD CONSTRAINT CHK_TIP_MEDICAMENT CHECK(tip_medicament = 'comprimat' OR tip_medicament = 'capsula' OR 
																  tip_medicament = 'drajeu' OR tip_medicament = 'dizolvabil' OR 
                                                                  tip_medicament = 'sirop' OR tip_medicament = 'picaturi'); 
ALTER TABLE pacientii ADD CONSTRAINT CHK_POLITA CHECK(pacient_polita_medicala='Da' OR pacient_polita_medicala='Nu');   
-- CHECK/UNIQUE CONSTRAINTS --------------------------------------- 

                                                               
-- ordinea de inserare a datelor
-- 1. TESTE LABORATOR
-- 2. PACIENTI
-- 3. TESTE_PACIENTI
-- 4. TIP_DOCTORI
-- 5. DOCTORI
-- 6. CABINETE_DOCTORI
-- 7. RETETE
-- 8. MEDICAMENTE
-- 9. RETETE_CU_MEDICAMENTELE

-- 1 INSERAREA IN TESTE_LABORATOR
INSERT INTO `teste_laborator`(nume_test , valoare_minima , valoare_maxima , acuaratete)
values('TGP' , 1 ,41 , 0.99);
INSERT INTO `teste_laborator`(nume_test , valoare_minima , valoare_maxima , acuaratete)
values('TGO' , 1 , 38 , 0.99);
INSERT INTO `teste_laborator`(nume_test , valoare_minima , valoare_maxima , acuaratete)
values('Glicemie' , 70 ,110, 0.99);
INSERT INTO `teste_laborator`(nume_test , valoare_minima , valoare_maxima , acuaratete)
values('COVID-19' , null,null,0.85);
INSERT INTO `teste_laborator`(nume_test , valoare_minima , valoare_maxima , acuaratete)
values('HCG' , 5,60000,0.99);
INSERT INTO `teste_laborator`(nume_test , valoare_minima , valoare_maxima , acuaratete)
values('Hemogoblina' , 27,35,0.99);
INSERT INTO `teste_laborator`(nume_test , valoare_minima , valoare_maxima , acuaratete)
values('Leucocite' , 4,10,0.99);
INSERT INTO `teste_laborator`(nume_test , valoare_minima , valoare_maxima , acuaratete)
values('Hermatii' , 35,49,0.99);
INSERT INTO `teste_laborator`(nume_test , valoare_minima , valoare_maxima , acuaratete)
values('Trombocite' , 150,400,0.99);
INSERT INTO `teste_laborator`(nume_test , valoare_minima , valoare_maxima , acuaratete)
values('Trigliceride' , 40,150,0.99);
-- testing
INSERT INTO `teste_laborator`(nume_test , valoare_minima , valoare_maxima , acuaratete)
values('TEST' , 20,30,0.99);
-- testing
-- 2. INSERAREA IN PACIENTI 
INSERT INTO `pacienti` (`pacient_data_nastere`, `pacient_cnp`, `pacient_nume`, `pacient_prenume`, `pacient_oras_nastere`, `pacient_polita_medicala`)
VALUES
('1990-01-01', '1900101345678', 'Marinescu', 'Andrei', 'Bucuresti', 'Da'),
('2002-05-15', '6020515234567', 'Albesteanu', 'Alina', 'Brasov', 'Nu'),
('1978-11-30', '1781130876543', 'Popa', 'Robert', 'Timisoara', 'Da'),
('1982-09-22', '2820922456789', 'Iftimie', 'Emilia', 'Bucuresti', 'Nu'),
('1995-03-10', '1950310654311', 'Lucretiu', 'Lucian', 'Timisoara', 'Da'),
('1988-07-18', '2880718345628', 'Andreea', 'Mihaela', 'Bucuresti', null),
('2001-12-05', '5011205654321', 'Becu', 'Daniel', 'Timisoara', 'Da'),
('1984-06-28', '2840628876543', 'Cosmina', 'Marasteanu', 'Bucuresti', 'Nu'),
('1998-02-14', '2980214341672', 'Mara', 'Iustina', 'Bucuresti', null),
('2004-10-03', '6041003762132', 'Pirvan', 'Dana', 'Bucuresti', 'Nu'),
('1991-08-25', '5910825345978', 'Montana', 'Mihai', 'Timisoara', 'Da'),
('1986-04-12', '1860412876793', 'Lamfri', 'Silviu', 'Silistea', 'Nu'),
('2002-09-29', '5020929765392', 'Gherman', 'Silviu', 'Rosu', 'Da'),
('1983-05-17', '1830517345178', 'Loncea', 'Codrut', 'Miercurea-Ciuc', 'Nu'),
('1996-11-08', '2961108876523', 'Eremia', 'Cosmina', 'Galati', 'Da'),
('1989-02-03', '2890203765492', 'Popescu', 'Larisa', 'Bacau', 'Nu'),
('1994-07-21', '2940721345238', 'Ella', 'Mihaela', 'Galati', 'Da'),
('2000-01-14', '5000114871543', 'Iosif', 'Stefan', 'Galati', 'Nu'),
('2003-12-30', '5031230340925', 'Misu', 'Stefan-Leonard', 'Rosiori-Vede', 'Da'),
('1987-12-23', '1871223765432', 'Mighi', 'Iosif', 'Miercurea-Ciuc', 'Nu'),
('1992-10-18', '2921018345678', 'Sara', 'Cristiana', 'Miercurea-Ciuc', 'Da'),
('1985-03-08', '1850308876543', 'Benjamin', 'Alin', 'Bucuresti', 'Nu'),
('1978-09-01', '2780901345678', 'Trahanache', 'Zoe', 'Balaci', 'Da'),
('2001-04-27', '5010427765432', 'Mija', 'Stefan', 'Bucuresti', 'Nu'),
('1995-12-15', '2951215325478', 'Burcescu', 'Liliana', 'Dobrotesti', 'Da'),
('1988-08-09', '1880809876583', 'Militaru', 'Liviu', 'Constanta', 'Nu'),
('1993-02-02', '1930202345628', 'Ivan', 'Liviu', 'Eforie-Nord', 'Da'),
('1984-10-24', '2841024765412', 'Agape', 'Ana', 'Satu-Mare', 'Nu'),
('1998-05-16', '1980516341698', 'Iustin', 'Mihai', 'Crangeni', 'Da'),
('1980-12-09', '1801209872553', 'Tudoran', 'Liviu', 'Vartoape', 'Nu'),
('1991-07-02', '2910702342311', 'Adam', 'Madalina', 'Peretu', 'Da'),
('1986-02-19', '2860219162332', 'Iordache', 'Cristina', 'Zambreasca', null),
('1979-08-12', '2790812545308', 'Ivan', 'Roxana', 'Baicoi', 'Da'),
('1983-03-05', '1830305872503', 'Gusa', 'Cosmin', 'Craiova', 'Nu'),
('1996-10-28', '2961028341600', 'Mihaela', 'Marin', 'Craiova', 'Da'),
('1989-05-21', '1890521125530', 'Onofrei', 'Radu', 'Dulceni', null),
('2002-01-13', '5020113325470', 'Grebla', 'Andrei', 'Craiova', 'Da'),
('1981-07-06', '1810706876543', 'Mosada', 'George', 'Timisoara', 'Nu'),
('2005-04-01', '6050401245978', 'Panait', 'Sofia', 'Bucuresti', null),
('1987-09-24', '1870924653320', 'Albu', 'Razvan', 'Copaceni', 'Nu'),
('2001-06-17', '6010617244098', 'Albu', 'Elena', 'Bucuresti', 'Da'),
('1985-01-10', '2850110846123', 'Leonard', 'Elena', 'Bucuresti', null),
('1978-07-03', '2780703392618', 'Cazutu', 'Andreea', 'Bucuresti', 'Da'),
('1965-10-26', '1651026765492', 'Misu', 'Dumitru', 'Bucuresti', 'Nu'),
('1999-08-09', '1990809315970', 'Misu', 'Adrian', 'Rosiori-Vede', null),
('1988-04-11', '1880411872503', 'Florea', 'Lucian', 'Bucuresti', 'Nu'),
('1993-11-04', '2931104025988', 'Manea', 'Lucia', 'Craiovita', 'Da'),
('1984-08-27', '1840827765432', 'Mihalache', 'Ion', 'Sinaia', null),
('1998-03-20', '1980320322688', 'Radu', 'Dumitru', 'Chisiniau', 'Da'),
('1980-11-13', '1801113874609', 'Ionita', 'Zaharia', 'Predeal', 'Nu');
-- testing
INSERT INTO `pacienti` (`pacient_data_nastere`, `pacient_cnp`, `pacient_nume`, `pacient_prenume`, `pacient_oras_nastere`, `pacient_polita_medicala`)
VALUES
('1985-04-15', '1850415515342', 'TEST', 'TEST', 'TEST', 'Da');
-- testing 
-- 3. INSERARE IN TESTE PACIENTI
INSERT INTO `teste_pacienti` (pacient_cnp , nume_test , rezultate , valoare_test ,data_emitere) 
VALUES
('1900101345678' , 'Glicemie' , 'bune' , 90 , '2023-11-30'),
('2860219162332' , 'HCG' , 'refacere' , -29 , '2023-10-28'),
('2931104025988' , 'TGP' , 'bune' , 27 , '2023-10-27'),
('1930202345628' , 'TGO' , 'proaste' , 2 , '2023-10-26'),
('5020113325470' , 'COVID-19' , 'pozitiv' , null  , '2023-09-08'),
('2961028341600' , 'Hemogoblina' , 'bune' , 28 , '2023-09-27'),
('1900101345678' , 'COVID-19' , 'negativ' , null , '2023-08-19'),
('6010617244098' , 'Leucocite' , 'refacere' , -0.29 , '2023-11-29'),
('1930202345628' , 'Hermatii' , 'bune' , 38 , '2023-10-26'),
('2860219162332' , 'Trombocite' , 'bune' ,  176 , '2023-10-28'),
('2951215325478' , 'Trigliceride' , 'proaste' , 35 , '2023-10-15');
-- testing
INSERT INTO `teste_pacienti` (pacient_cnp , nume_test , rezultate , valoare_test ,data_emitere) 
VALUES
('1900101345678' , 'Glicemie' , 'bune' , 90 , '2023-11-30');
INSERT INTO `teste_pacienti` (pacient_cnp , nume_test , rezultate , valoare_test ,data_emitere) 
VALUES
('1900101345678' , 'TGP' , 'bune' , 25 , '2023-11-30');
INSERT INTO `teste_pacienti` (pacient_cnp , nume_test , rezultate , valoare_test ,data_emitere) 
VALUES
('1900101345678' , 'LEUCOCITE' , 'proaste' , 5 , '2023-11-30');
-- testing 


-- 4. INSERARE IN TIP_DOCTORI
INSERT INTO `tip_doctori` (nume_specializare)
VALUES
('Medic familie'),
('Oftalmolog'),
('Ginecolog'),
('Cardiolog'),
('Urolog'),
('Pediatru'),
('Oncolog'),
('Reumatolog'),
('Dermatolog'),
('Psihiatru'), 
('Stomatolog');

-- 5. INSERARE IN DOCTORI 

INSERT INTO `doctori` (doctor_CUI , tip_doctor_id , doctor_nume , doctor_prenume , doctor_data_nastere)
VALUES
(20120905, 1 , 'Misu', 'Felicia', '1967-01-25'),
(41390433, 4 , 'Panait', 'Muresan', '1985-03-16'), --
(99100078, 8 , 'Dina', 'Ana-Maria', '1978-05-28'),
(12183299, 5 , 'Mihai', 'Cosmin-Silviu', '1965-04-15'), --
(10447120, 10, 'Diana', 'Maria', '1983-07-08'), --
(89100032, 11, 'Mihalache', 'Cosmina', '1982-10-25'), --
(39162783, 2,  'Dinulescu', 'Pirvan-Mihai', '1979-11-22'), --
(18260012, 3,  'Zopotila', 'Daria', '1978-12-28'), --
(91048290, 6,  'Baltoiu', 'Doina-Maria', '1974-06-16'), --
(52031288, 7 , 'Miron', 'Stefan', '1977-03-09'), --
(12043922, 9 , 'Ahmed', 'Darius', '1979-04-19'), -- 
(43806092, 2 , 'Costache', 'Andreea-Lucretia', '1969-07-14'), --
(89171032, 8 , 'Falca', 'Iulian', '1985-12-06'), --
(23491007, 4 , 'Dragan', 'Alexandra', null); --
-- testing
INSERT INTO `doctori` (doctor_CUI , tip_doctor_id , doctor_nume , doctor_prenume , doctor_data_nastere)
VALUES(10492355,1 , 'Mihaela' , 'Daniela' , '1976-07-12');
-- testing


-- 6. INSERAREA IN CABINETE DOCTORI
INSERT INTO `cabinete_doctori` (doctor_CUI , cabinet_nume , cabinet_cod_postal , cabinet_oras , cabinet_strada , cabinet_numar_strada , cabinet_nr_telefon)
VALUES
(20120905 , 'C.M.I. Misu Felicia' , '145302' , 'Balaci' , 'Strada Balaci' , 6 , '+40247031889'),
(20120905 , 'C.M.I. COMMED' , '145100' , 'Rosiori Vede' , 'Caporal Ghencea' , 3 , '+40247223322'),
(99100078 , 'C.M.I. Dina Ana-Maria' , '889106' , 'Bucuresti' , 'Voda Mihai' , 28 , '+40247991001' ),
(52031288 , 'C.M.I. HEALTHMED STEFAN' , '100220' , 'Alexandria' , 'Elena Doamna' , 7 , '+40723910002'),
(89171032 , 'C.M.I. IulianMED' , '900234' , 'Zambreasca' , 'Parc Nord' , 8 , '+40744921390'),
(10447120 , 'C.M.I. DianaMEDICAN' , '118002' , 'Timisoara' , 'Timisoara Vest' , 19 , '+40789110472'),
(10447120 , 'C.M.I. DianaBUCMEDICAN' , '889106' , 'Bucuresti' , 'Calea Giulesti' , 2 , '+40247111999'),
(39162783 , 'C.M.I. MED Dinulescu Pirvan' , '889106' , 'Bucuresti' , 'Bulevardul Eroilor' , 36 , '+40247898998'),
(41390433 , 'C.M.I. Dr. Panait Muresan' , '889106' , 'Bucuresti' , 'Bulevardul Iuliu Maniu' , 48 , '+40247199888'),
(52031288 , 'C.M.I. Miron Stefan' , '889106' , 'Bucuresti' , 'Bulevardul Timisoara' , 18 , '+40248123332'),
(23491007 , 'C.M.I. Dragan Alexandra', '889106' , 'Bucuresti' , 'Drumul Taberei' , 19 , '+40248474647');

-- testing
INSERT INTO `cabinete_doctori` (doctor_CUI , cabinet_nume , cabinet_cod_postal , cabinet_oras , cabinet_strada , cabinet_numar_strada , cabinet_nr_telefon)
VALUES (10492355, 'C.M.I MIHAELA DANIELA' , '145100' , 'Rosiori Vede' , 'Bulevardul Comercial' , 2 , '+40247121312');

-- testing

-- 7.INSERAREA IN RETETE
INSERT INTO `retete` (pacient_cnp , doctor_CUI , reteta_data_emitere , reteta_data_expirare, tip_reteta)
VALUES
('1900101345678' , 41390433 , '2023-10-27' , '2023-11-15' , null),
('6020515234567' , 41390433 , '2023-10-27' , '2023-11-05', null),
('1781130876543' , 20120905 , '2023-08-09' , null , 'permanent'),
('2820922456789' , 20120905 , '2023-08-10' , null , 'permanent'),
('5020113325470' , 18260012 , '2023-07-15' , '2023-07-27', null),
('2951215325478' , 12183299 , '2023-06-13' , '2023-06-25' , null),
('2951215325478' , 12183299 , '2023-07-10' , '2023-07-25' , null),
('2921018345678' , 91048290 , '2023-03-02' , '2023-03-22' , null),
('1980320322688' , 91048290 , '2023-05-05' , '2023-05-19' , null),
('5020929765392' , 91048290 , '2023-09-04' , null , 'permanent'),
('5011205654321' , 18260012 , '2023-08-10' , '2023-08-25' , null),
('1781130876543' , 52031288 , '2023-02-10' , null , 'permanent'),
('1781130876543' , 89100032 , '2023-03-02' , '2023-03-15' , null),
('6041003762132' , 12043922 , '2023-04-10' , null , 'permanent'),
('6010617244098' , 23491007 , '2023-01-15' , '2023-01-25' , null),
('1890521125530' , 18260012 , '2023-12-01' , null , 'permanent'),
('1810706876543' , 23491007 , '2023-12-02' , '2023-12-12' , null),
('1930202345628' , 89100032 , '2023-11-05' , '2023-11-15' , null),
('2980214341672' , 12043922 , '2023-01-03' , null , 'permanent'),
('2980214341672' , 52031288 , '2023-06-06' , '2023-06-20' , null),
('1651026765492' , 52031288 , '2023-08-10' , null , 'permanent'),
('1651026765492' , 52031288 , '2023-08-27' , '2023-09-15' , null);
-- testing
INSERT INTO `retete` (pacient_cnp , doctor_CUI , reteta_data_emitere , reteta_data_expirare, tip_reteta)
VALUES
('1871223765432' , 10492355, '2023-08-27' , '2023-09-15' , null);
INSERT INTO `retete` (pacient_cnp , doctor_CUI , reteta_data_emitere , reteta_data_expirare, tip_reteta)
VALUES
('1810706876543' , 10492355, '2023-08-27' , '2023-09-15' , null);

-- 8. INSERAREA IN MEDICAMENTE
INSERT INTO `medicamente` (medicament_nume , medicament_gramaj, tip_medicament)
VALUES
('PARACETAMOL_325' , 325 , 'comprimat'),
('PARACETAMOL_500' , 500, 'comprimat'),
('PARACETAMOL_750' , 750, 'comprimat'),
('PARACETAMOL_1000' , 1000, 'comprimat'),
('AUGUMENTIN_875' , 875, 'comprimat'),
('ROFEDEX_1000' , 1000 , 'sirop'),
('ACID ACETILSALISIC_500' , 500, 'comprimat'),
('OMEPRAZOL_20' , 20, 'comprimat'),
('DICLOFENAC HELCOR_50' , 50 , 'capsula'),
('FASCONAL' , null , 'capsula'),
('CLORFENIRAMIN_4' , 4 , 'capsula'),
('KETORPOXIN_100' , 100 , 'dizolvabil'),
('ALGOCALMIN_500' , 500 , 'comprimat'),
('DECASEPT' ,null, 'drajeu'),
('DOXAZOSINUM_4' , 4 , 'comprimat'),
('NORFLOXACINUM_400' , 400 , 'capsula'),
('ESOMEPRAZOLIUM_500' , 500 , 'comprimat'),
('TOBRAMYCINUM_500' , 500 , 'picaturi'),
('METROPROLOLUM_100' , 100 , 'comprimat'),
('METROPROLOLUM_50' , 50 , 'comprimat'),
('METROPROLOLUM_25' , 25 , 'comprimat'),
('BETAXOLOLUM_20' , 20,  'comprimat'),
('KLAMBAMOXIN_325' , 325 , 'comprimat');
-- INSERARE IN RETETE_CU_MEDICAMENTELE
INSERT INTO `retete_cu_medicamentele` (reteta_id , medicament_id , cantitate)
VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 2),
(3, 4, 1),
(3, 5, 1),
(7, 3, 2),
(3, 3, 2),
(4, 9, 1),
(4, 10, 2),
(7, 11, 1),
(8, 13, 2),
(8, 12, 2),
(2, 13, 2),
(2, 7, 1),
(10, 15, 1),
(12, 3, 2),
(13, 7, 2),
(5, 1, 1),
(5, 4, 2),
(6, 9, 1),
(6, 16, 4);
-- testing
INSERT INTO `retete_cu_medicamentele` (reteta_id , medicament_id , cantitate)
VALUES
(8, 1, 2);
INSERT INTO `retete_cu_medicamentele` (reteta_id , medicament_id , cantitate)
VALUES
(5, 2, 2);
INSERT INTO `retete_cu_medicamentele` (reteta_id , medicament_id , cantitate)
VALUES
(4, 2, 2),
(7, 3, 1);

SHOW CREATE TABLE cabinete_doctori;
SHOW CREATE TABLE doctori;
SHOW CREATE TABLE medicamente;
SHOW CREATE TABLE pacienti;
SHOW CREATE TABLE retete;
SHOW CREATE TABLE retete_cu_medicamentele;
SHOW CREATE TABLE teste_laborator;
SHOW CREATE TABLE teste_pacienti;
SHOW CREATE TABLE tip_doctori;

ALTER TABLE doctori DROP CONSTRAINT doctori_ibfk_1;
ALTER TABLE cabinete_doctori DROP CONSTRAINT cabinete_doctori_ibfk_1;
ALTER TABLE retete DROP CONSTRAINT retete_ibfk_1;
ALTER TABLE retete DROP CONSTRAINT retete_ibfk_2;
ALTER TABLE retete_cu_medicamentele DROP CONSTRAINT retete_cu_medicamentele_ibfk_1;
ALTER TABLE retete_cu_medicamentele DROP CONSTRAINT retete_cu_medicamentele_ibfk_2;
ALTER TABLE teste_pacienti DROP CONSTRAINT teste_pacienti_ibfk_1;
ALTER TABLE teste_pacienti DROP CONSTRAINT teste_pacienti_ibfk_2;
ALTER TABLE doctori DROP CONSTRAINT tip_doctori_ibfk_1;

DROP TABLE cabinete_doctori;
DROP TABLE doctori;
DROP TABLE medicamente;
DROP TABLE pacienti;
DROP TABLE retete;
DROP TABLE retete_cu_medicamentele;
DROP TABLE teste_laborator;
DROP TABLE teste_pacienti;
DROP TABLE tip_doctori;








