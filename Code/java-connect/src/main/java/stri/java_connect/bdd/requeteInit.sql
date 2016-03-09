/* Suppression des tables existantes */

drop table if exists utilisateur;
drop table if exists competence;
drop table if exists posseder;
drop table if exists envoyerMessage;
drop table if exists aimer;

create table utlisateur (courriel string primary key not null,
                        motDePasse string not null,
                        nom string not null,
                        privilege string not null,
                        telephone string not null,
                        dateDiplome long not null,
                        permissionLecture string not null,
                        )


create table competence (idCompetence integer auto_increment not_null,
			nomCompetence string,
			idCompetence  primary key)

create table posseder (courriel string,
			idCompetence integer)

create table Message (courrielSource string not null,
				courrielDestinataire string not null,
				dateMessage timestamp not null,
				message text,
				foreing key (corrielSource) references utilisateur(courriel),
				foreing key (courrielDestinataire) references utilisateur(courriel),
				primary key (courrielSource,courrielDestinataire,dateMessage)
				)


create table aimer (courrielLikant string not null,
                    idCompetence string not null,
                    courrielPropCompetence string not null,
		foreing key (courrielLikant) references utilisateur(courriel),
		foreing key (courrielPropCompetence) references utilisateur(courriel),
		primary key (courrielLikant,courrielPropCompetence,idCompetence)
		)




/* requete d'insertion d'un nouvel utilisateur */
insert into Utilisateur (courriel, motDePasse, nom, privilege, telephone, dateDiplome, permissionLecture) values (....);

/* requete ajout compétence */
/* 1/ on regarde si compétence existe déjà */
SELECT COUNT(*) FROM Competence WHERE nomCompetence = ??variableAvecMotSaisi??;
/* 2/ si existe déjà */
INSERT INTO Posseder(courriel,idCompetence) VALUES (...);
/* 3/ si pas encore presente */
INSERT INTO Competence(nomCompetence) VALUES (...);
INSERT INTO Posseder(courriel,idCompetence) VALUES (...);

/* Envoi d'un nouveau mail */
INSERT INTO Message (courrielSource, courrielDestinataire, dateMessage, message) VALUES (...);

/* Lecture de ses mail */
SELECT * FROM Message WHERE courrielDestinataire = "...";

/* TODO : voir si pour mail on rajoute un champ vu pour ne pas supprimer les mess après la lecture */
/* TODO : voir si on ajoute une table pour contenir les infos (port, ip, ..) pour la discution instantannée */


/* Affichage de tous les utilisateur */
SELECT * from utilisateur;

/* Supprimer un utilisateur depuis mail */
DELETE FROM Utilisateur WHERE courriel = "...";

/* Détail profil à partir du courriel */
SELECT * FROM Utilisateur WHERE courriel LIKE "%...%";

/* Détail profil à partir du nom */
SELECT * FROM Utilisateur WHERE nom LIKE "%...%";

/* Récupérer toutes les compétences d'une personne */
SELECT nomCompetence FROM Competence WHERE idCompetence = (SELECT idCompetence FROM posseder WHERE courriel = "...");

/* recherche d'étudiants liste depuis mail */
==> voir quelles infos on a besoin de récupérer
SELECT courriel, nom, .. FROM Utilisateur where courriel LIKE "%...%";

/* Récupérer toutes les compétences existantes - voir si on récup l'id pour stocker dans un tableau et afficher un menu déroulant qui liste les compétences */
SELECT nomCompetence FROM competence;


/* Recherche etudiant par compétence ordre croissant de like */
SELECT ....Voir les infos utiles..., count(like...) FROM Utilisateur u, Posseder p, Competence c WHERE u.courriel = p.courriel AND c.idCompetence = p.idCompetence AND c.nomCompetence = "..." ORDER BY ;


/* Recherche etudiant par compétence ordre décroissant de like */
idem de juste au dessus en changeant le asc en desc




